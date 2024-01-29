package com.dojah.sdk_kyc.ui.main.viewmodel

import android.annotation.SuppressLint
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dojah.sdk_kyc.core.Result
import com.dojah.sdk_kyc.data.io.CountryManager
import com.dojah.sdk_kyc.data.io.SharedPreferenceManager
import com.dojah.sdk_kyc.data.repository.DojahRepository
import com.dojah.sdk_kyc.domain.request.EventRequest
import com.dojah.sdk_kyc.domain.request.LivenessCheckRequest
import com.dojah.sdk_kyc.domain.request.LivenessVerifyRequest
import com.dojah.sdk_kyc.domain.request.OtpRequest
import com.dojah.sdk_kyc.domain.responses.*
import dagger.Lazy
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import okhttp3.logging.HttpLoggingInterceptor
import javax.inject.Inject
import com.dojah.sdk_kyc.ui.utils.*
import kotlinx.coroutines.flow.collect

const val analysisRetryMax = 3
const val checkRetryMax = 2

@SuppressLint("StaticFieldLeak")
@HiltViewModel
class GovDataViewModel @Inject constructor(
    private val prefManager: SharedPreferenceManager,
    private val repo: DojahRepository,
    private val countryManager: Lazy<CountryManager>
) : ViewModel() {
    private val logger: HttpLoggingInterceptor.Logger = HttpLoggingInterceptor.Logger.DEFAULT
    private val _verificationTypeLiveData = MutableLiveData<VerificationType?>()

    val verificationTypeLiveData: LiveData<VerificationType?>
        get() = _verificationTypeLiveData

    private val _selectedGovIdDataLiveData = MutableLiveData<EnumAttr?>()
    val selectedGovDataLiveData: LiveData<EnumAttr?>
        get() = _selectedGovIdDataLiveData


    private val _submitGovLiveData = MutableLiveData<Result<String>>()
    val submitGovLiveData: LiveData<Result<String>>
        get() = _submitGovLiveData

    private val _lookUpLiveData = MutableLiveData<GovIdEntityInterface>()
    val lookUpLiveData: LiveData<GovIdEntityInterface>
        get() = _lookUpLiveData


    private val _sendOtpLiveData = MutableLiveData<Result<SendOtpResponse?>>()
    val sendOtpLiveData: LiveData<Result<SendOtpResponse?>>
        get() = _sendOtpLiveData


    private val _validateOtpLiveData = MutableLiveData<Result<ValidateOtpResponse?>>()
    val validateOtpLiveData: LiveData<Result<ValidateOtpResponse?>>
        get() = _validateOtpLiveData


    private val _isResentOtpLiveData = MutableLiveData<Boolean>()
    val isResentOtpLiveData: LiveData<Boolean>
        get() = _isResentOtpLiveData


    private val _imageAnalysisLiveData = MutableLiveData<Result<ImageAnalysisResponse?>>()
    val imageAnalysisLiveData: LiveData<Result<ImageAnalysisResponse?>>
        get() = _imageAnalysisLiveData

    private val _livenessCheckLiveData = MutableLiveData<Result<LivenessCheckResponse?>>()
    val livenessCheckLiveData: LiveData<Result<LivenessCheckResponse?>>
        get() = _livenessCheckLiveData

    private val _verifyLiveData = MutableLiveData<Result<LivenessVerifyResponse?>>()
    val verifyLiveData: LiveData<Result<LivenessVerifyResponse?>>
        get() = _verifyLiveData


    //a single event to listen to all liveness chained events
    private val _submitLivenessLiveData = MutableLiveData<Result<SimpleResponse?>>()
    val submitLivenessLiveData: LiveData<Result<SimpleResponse?>>
        get() = _submitLivenessLiveData

    private val _analysisRetryCountLiveData = MutableLiveData<Int>(0)
    val analysisRetryCountLiveData: LiveData<Int>
        get() = _analysisRetryCountLiveData

    private val _verifyCheckRetryCountLiveData = MutableLiveData<Int>(0)

    val dojahEnum
        get(): DojahEnum {
            return repo.getDojahEnum.data
        }

    fun selectVerificationType(type: String) {
        _verificationTypeLiveData.postValue(VerificationType.enumOfValue(type))
    }

    fun getGovIdTypes(
        verificationVm: VerificationViewModel
    ): List<EnumAttr?>? {
        return verificationVm.getCurrentPage(KycPages.GOVERNMENT_DATA.serverKey)?.config?.govIds?.map { govIdKey ->
            verificationVm.dojahEnum.toMap()[govIdKey]
        }
    }

    //get verification methods from current page config
    fun getVerifyMethods(
        verificationVm: VerificationViewModel
    ): List<String>? {
        return verificationVm.getCurrentPage(KycPages.GOVERNMENT_DATA.serverKey)?.config?.verificationMethods?.map { method ->
            VerificationType.findEnumWithKey(method)?.value ?: method
        }
    }

    fun selectGovIdentity(id: EnumAttr?) {
        _selectedGovIdDataLiveData.postValue(id)
    }


    /** do chain request to conclude gov data submission*/
    fun submitGovDataForm(
        verifyVm: VerificationViewModel,
        userId: String,
        services: List<String> = listOf(),
    ) {
        val dojahConstants = dojahEnum
        val stepNumber = verifyVm.getCurrentPage(KycPages.GOVERNMENT_DATA.serverKey)?.id
            ?: throw Exception("No stepNumber")
        viewModelScope.launch {
            val selectedIdEnum =
                selectedGovDataLiveData.value?.enum ?: throw Exception("Selected id is null")
            /** log [EventTypes.VERIFICATION_TYPE_SELECTED] event*/
            logGovTypeSelected(
                verifyVm,
                services,
                selectedIdEnum,
                userId,
                stepNumber = stepNumber,
            ).onStart {
                /** startLoading */
                _submitGovLiveData.postValue(Result.Loading)
            }.collect {
                if (it is Result.Success) {
                    /** then look up gov ID*/
                    doGovIdLookUp(
                        selectedIdEnum, dojahConstants, userId,
                    ).collect { lookUpResult ->
                        if (lookUpResult is Result.Success) {
                            _lookUpLiveData.postValue(lookUpResult.data.entity)
                            val lookUpEntity = lookUpResult.data.entity
                            /** then log [EventTypes.CUSTOMER_GOVERNMENT_DATA_COLLECTED] event*/
                            logGovDataCollected(
                                verifyVm,
                                services,
                                userId,
                                lookUpEntity,
                                stepNumber = stepNumber,
                            ).collect { govDataResult ->
                                if (govDataResult is Result.Success) {
                                    /** then log [EventTypes.GOVERNMENT_IMAGE_COLLECTED] event*/
                                    logGovImageCollected(
                                        verifyVm,
                                        services,
                                        lookUpEntity,
                                        stepNumber = stepNumber,
                                    ).collect { imageEventResult ->
                                        if (imageEventResult is Result.Success) {
                                            if (getVerifyMethods(verifyVm)?.isEmpty() == true) {
                                                /** if there are no gov verification options*/
                                                /** log [EventTypes.STEP_COMPLETED] directly*/
                                                logGovDataCompleted(
                                                    verifyVm, services, stepNumber
                                                )
                                            } else {
                                                /**
                                                 * else if there are options
                                                 * log [EventTypes.VERIFICATION_MODE_SELECTED]
                                                 * first
                                                 **/
                                                logVerifyMethodSelected(
                                                    verifyVm,
                                                    services,
                                                    stepNumber,
                                                ).collect { modeSelectResult ->
                                                    if (modeSelectResult is Result.Success) {
                                                        /** then log [EventTypes.STEP_COMPLETED]*/
                                                        logGovDataCompleted(
                                                            verifyVm, services, stepNumber
                                                        )
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }


    private suspend fun logGovDataCompleted(
        verificationVm: VerificationViewModel,
        services: List<String> = listOf(),
        stepNumber: Int,
    ): Flow<Result<SimpleResponse>> {
        val request = verificationVm.buildEventRequest(
            services,
            EventTypes.STEP_COMPLETED.serverKey,
            KycPages.GOVERNMENT_DATA.serverKey,
            stepNumber
        )
        ///log step completed event
        return repo.logEvent(request).apply {
            collect {
                if (it is Result.Success) {
                    sendOtp(verificationVm)
                } else if (it is Result.Error) {
                    _submitGovLiveData.postValue(it)
                }
            }
        }
    }

    private suspend fun logGovTypeSelected(
        verificationVm: VerificationViewModel,
        services: List<String>,
        selectedIdEnum: String,
        userId: String,
        stepNumber: Int,
    ): Flow<Result<SimpleResponse>> {
        return repo.logEvent(
            verificationVm.buildEventRequest(
                services,
                EventTypes.VERIFICATION_TYPE_SELECTED.serverKey,
                "${selectedIdEnum.lowercase()},${userId}", //e.g BVN,2222222
                stepNumber = stepNumber
            )
        ).apply {
            collect {
                if (it is Result.Error) {
                    _submitGovLiveData.postValue(it)
                }
            }
        }
    }

    private suspend fun doGovIdLookUp(
        selectedIdEnum: String,
        dojahConstants: DojahEnum,
        userId: String,
    ) = when (selectedIdEnum) {
        dojahConstants.bvn.enum -> repo.lookUpBvn(userId)

        dojahConstants.nin.enum -> repo.lookUpNin(userId)

        dojahConstants.vnin.enum -> repo.lookUpVnin(userId)

        dojahConstants.dl.enum -> repo.lookUpDriverLicense(userId)

        else -> throw Exception("This id enum is not valid")

    }.apply {
        collect {
            if (it is Result.Error) {
                _submitGovLiveData.postValue(it)
            }
        }
    }

    private suspend fun logVerifyMethodSelected(
        verificationVm: VerificationViewModel,
        services: List<String>,
        stepNumber: Int,
    ): Flow<Result<SimpleResponse>> {

        val verificationType = verificationTypeLiveData.value
        var verifyTypeKey = verificationType?.serverKey
        if (verificationType == VerificationType.Selfie || verificationType == VerificationType.SelfieVideo) {
            verifyTypeKey = "LIVENESS"
            _analysisRetryCountLiveData.value = 0
            _verifyCheckRetryCountLiveData.value = 0
        }
        return repo.logEvent(
            verificationVm.buildEventRequest(
                services,
                EventTypes.VERIFICATION_MODE_SELECTED.serverKey,
                "${verifyTypeKey?.uppercase()}", //e.g selfie
                stepNumber = stepNumber
            )
        ).apply {
            collect { verifyModeResult ->
                if (verifyModeResult is Result.Error) {
                    _submitGovLiveData.postValue(
                        verifyModeResult
                    )
                }
            }
        }


    }

    private suspend fun logGovImageCollected(
        verificationVm: VerificationViewModel,
        services: List<String>,
        lookUpEntity: GovIdEntityInterface?,
        stepNumber: Int,
    ) = repo.logEvent(
        verificationVm.buildEventRequest(
            services,
            EventTypes.GOVERNMENT_IMAGE_COLLECTED.serverKey,
            "${lookUpEntity?.image}", // base64 image
            stepNumber = stepNumber
        )
    ).apply {
        collect {
            if (it is Result.Error) {
                _submitGovLiveData.postValue(it)
            }
        }
    }

    private suspend fun logGovDataCollected(
        verificationVm: VerificationViewModel,
        services: List<String>,
        userId: String,
        lookUpEntity: GovIdEntityInterface?,
        stepNumber: Int,
    ): Flow<Result<SimpleResponse>> {
        val selectedIdEnum =
            selectedGovDataLiveData.value?.enum ?: throw Exception("Selected id is null")
        val countryId = verificationVm.selectedCountryLiveData.value?.id?.uppercase()
        val selectedGovIdType = selectedIdEnum.lowercase()
        return repo.logEvent(
            verificationVm.buildEventRequest(
                services,
                EventTypes.CUSTOMER_GOVERNMENT_DATA_COLLECTED.serverKey,
                "$userId|$selectedGovIdType|$countryId|${lookUpEntity?.fName}|${lookUpEntity?.mName}|${lookUpEntity?.lName}|${lookUpEntity?.dob}", //e.g 2222222|bvn|NG|firstname|middlename|lastname|dob
                stepNumber = stepNumber
            )
        ).apply {
            collect {
                if (it is Result.Error) {
                    _submitGovLiveData.postValue(it)
                }
            }
        }
    }

    private suspend fun sendOtp(
        verifyVm: VerificationViewModel,
        resent: Boolean = false
    ): Flow<Result<SendOtpResponse>> {
        var phoneNumber = _lookUpLiveData.value?.phoneNumber ?: ""
        if (phoneNumber.startsWith("0")) {
            val countryCode = verifyVm.selectedCountryLiveData.value?.code ?: ""
            phoneNumber = phoneNumber.replaceFirst("0", countryCode)
        }
        val payload = OtpRequest(
            phoneNumber, "kedesa", channel = "sms", 4
        )
        logger.log("Send Otp request: $payload")
        _isResentOtpLiveData.postValue(resent)
        return repo.sendOtp(
            payload
        ).onStart {
            _sendOtpLiveData.postValue(Result.Loading)
        }.apply {
            collect { otpResponse ->
                if (otpResponse is Result.Success) {
                    _sendOtpLiveData.postValue(otpResponse)
                    _submitGovLiveData.postValue(
                        Result.Success(otpResponse.data.entity.first().status)
                    )
                    verifyVm.startTimer()
                } else if (otpResponse is Result.Error) {
                    _sendOtpLiveData.postValue(otpResponse)
                    _submitGovLiveData.postValue(otpResponse)
                }
            }
        }
    }

    fun sendOtpSync(verificationVm: VerificationViewModel, resent: Boolean = false) {
        viewModelScope.launch {
            sendOtp(verificationVm, resent)
        }
    }

    fun validateOtp(code: String) {
        if (_sendOtpLiveData.value !is Result.Success) {
            throw Exception("can't fetch otp reference")
        }
        val sendOtpData = (_sendOtpLiveData.value as Result.Success).data
        val referenceId = sendOtpData?.entity?.first()?.referenceId ?: ""
        viewModelScope.launch {
            repo.validateOtp(code, referenceId)
                .onStart {
                    _validateOtpLiveData.postValue(Result.Loading)
                }
                .collect {
                    _validateOtpLiveData.postValue(it)
                }
        }
    }

    fun performImageAnalysis(image: String) {
        viewModelScope.launch {
            val verificationType = verificationTypeLiveData.value
                ?: throw Exception("verification type is null")
            repo.performImageAnalysis(image, verificationType.actualServerKey)
                .onStart {
                    _imageAnalysisLiveData.postValue(Result.Loading)
                }
                .collect {
                    if (it is Result.Success) {
                        val faceResult = it.data.entity?.face
                        val config =
                            getCurrentPage(KycPages.GOVERNMENT_DATA.serverKey)?.config
                        if (faceResult?.faceSuccess(config) == false) {
                            if (_analysisRetryCountLiveData.value!! < analysisRetryMax) {
                                _analysisRetryCountLiveData.value =
                                    _analysisRetryCountLiveData.value?.plus(1)
                            }
                        }
                    }
                    _imageAnalysisLiveData.postValue(it)
                }
        }
    }

    fun checkLiveness(
        image: String,
        param: String = "face",
        selfieType: String = "selfie_type",
        docType: String = "image",
    ) {
        viewModelScope.launch {
            val verificationId =
                getAuthDataFromPref()?.initData?.authData?.verificationId
                    ?: throw Exception("Verification id is null")
            val stepNumber = getCurrentPage(KycPages.GOVERNMENT_DATA_VERIFICATION.serverKey)?.id
            val continueVerification = _analysisRetryCountLiveData.value!! >= analysisRetryMax
            repo.checkLiveness(
                LivenessCheckRequest(
                    image,
                    verificationId,
                    stepNumber,
                    param,
                    selfieType,
                    docType,
                    continueVerification
                )
            ).onStart {
                /// start loading @check, and stop @ step event
                /// or on error
                _submitLivenessLiveData.postValue(Result.Loading)
            }.collect {
                _livenessCheckLiveData.postValue(it)
                if (it is Result.Error) {
                    _submitLivenessLiveData.postValue(it)
                    return@collect
                }
                if (it is Result.Success) {
                    if (it.data.entity?.match == true) {
                        //then verify liveness
                        verifyLiveness().collect { verifyResult ->
                            if (verifyResult is Result.Success) {
                                //then log step completed event
                                logStepEvent(
                                    KycPages.GOVERNMENT_DATA_VERIFICATION,
                                    EventTypes.STEP_COMPLETED
                                ).collect { eventResult ->
                                    /// fire event for all liveness process
                                    _submitLivenessLiveData.postValue(eventResult)
                                }
                            }
                        }
                    } else {
                        if (_verifyCheckRetryCountLiveData.value!! < checkRetryMax) {
                            _verifyCheckRetryCountLiveData.value =
                                _verifyCheckRetryCountLiveData.value?.plus(1)
                            ///Retry verification
                            checkLiveness(image)
                        } else {
                            verifyLiveness().collect { verifyResult ->
                                if (verifyResult is Result.Success) {
                                    //then log step failed event
                                    logStepEvent(
                                        KycPages.GOVERNMENT_DATA_VERIFICATION,
                                        EventTypes.STEP_FAILED
                                    ).collect { eventResult ->
                                        /// fire event for all liveness process
                                        _submitLivenessLiveData.postValue(eventResult)
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    private suspend fun verifyLiveness(): Flow<Result<LivenessVerifyResponse>> {
        val verificationId =
            getAuthDataFromPref()?.initData?.authData?.verificationId
                ?: throw Exception("Verification id is null")
        return repo.verifyLiveness(
            LivenessVerifyRequest(
                appId = prefManager.getAppId(),
                prefManager.getSessionId(),
                verificationId = verificationId
            )
        ).apply {
            collect {
                if (it is Result.Error) {
                    /// fire error event for all liveness process
                    _submitLivenessLiveData.postValue(it)
                }
                _verifyLiveData.postValue(it)
            }
        }
    }


    private suspend fun logStepEvent(
        page: KycPages,
        event: EventTypes
    ): Flow<Result<SimpleResponse>> {
        val verificationId =
            getAuthDataFromPref()?.initData?.authData?.verificationId
                ?: throw Exception("Verification id is null")
        val stepNumber =
            getCurrentPage(page.serverKey)?.id
                ?: throw Exception("No stepNumber")
        return repo.logEvent(
            EventRequest(
                verificationId,
                stepNumber,
                event.serverKey,
                page.serverKey,
            )
        ).apply {
            collect {
                if (it is Result.Error) {
                    _submitGovLiveData.postValue(it)
                }
            }
        }
    }

    private fun getCurrentPage(currentPage: String): Step? {
        val steps = getAuthDataFromPref()?.initData?.authData?.steps
        return steps?.find { it.name == currentPage }
    }

    private fun getAuthDataFromPref(): AuthResponse? {
        return repo.getLocalResponse(
            SharedPreferenceManager.KEY_AUTH_RESPONSE, AuthResponse::class.java
        )?.data
    }


}
