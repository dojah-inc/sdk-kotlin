package com.dojah.kyc_sdk_kotlin.ui.main.viewmodel

import android.annotation.SuppressLint
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dojah.kyc_sdk_kotlin.core.Result
import com.dojah.kyc_sdk_kotlin.core.util.DojahPricingUtil
import com.dojah.kyc_sdk_kotlin.data.io.SharedPreferenceManager
import com.dojah.kyc_sdk_kotlin.data.repository.DojahRepository
import com.dojah.kyc_sdk_kotlin.domain.request.AdditionalDocRequest
import com.dojah.kyc_sdk_kotlin.domain.request.EventRequest
import com.dojah.kyc_sdk_kotlin.domain.request.LivenessCheckRequest
import com.dojah.kyc_sdk_kotlin.domain.request.LivenessVerifyRequest
import com.dojah.kyc_sdk_kotlin.domain.request.OtpRequest
import com.dojah.kyc_sdk_kotlin.domain.responses.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import okhttp3.logging.HttpLoggingInterceptor
import com.dojah.kyc_sdk_kotlin.ui.utils.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.zip

const val analysisRetryMax = 3
const val checkRetryMax = 2

@SuppressLint("StaticFieldLeak")
class GovDataViewModel(
    private val prefManager: SharedPreferenceManager,
    private val repo: DojahRepository,
) : ViewModel() {
    private val logger: HttpLoggingInterceptor.Logger = HttpLoggingInterceptor.Logger.DEFAULT
    private val _verificationTypeLiveData = MutableLiveData<VerificationType?>()

    val verificationTypeLiveData: LiveData<VerificationType?>
        get() = _verificationTypeLiveData

    private val _selectedGovIdDataLiveData = MutableLiveData<EnumAttr?>()
    val selectedGovDataLiveData: LiveData<EnumAttr?>
        get() = _selectedGovIdDataLiveData


    private val _selectedBizIdDataLiveData = MutableLiveData<EnumAttr?>()
    val selectedBizDataLiveData: LiveData<EnumAttr?>
        get() = _selectedBizIdDataLiveData


    private val _submitGovLiveData = MutableLiveData<Result<String>?>()
    val submitGovLiveData: LiveData<Result<String>?>
        get() = _submitGovLiveData

    fun resetSubmitGovLiveData() {
        _submitGovLiveData.postValue(null)
    }

    private val _lookUpLiveData = MutableLiveData<GovIdEntityInterface>()

    private val _sendOtpLiveData = MutableLiveData<Result<SendOtpResponse?>>()
    val sendOtpLiveData: LiveData<Result<SendOtpResponse?>>
        get() = _sendOtpLiveData


    private val _validateOtpLiveData = MutableLiveData<Result<ValidateOtpResponse?>?>()
    val validateOtpLiveData: LiveData<Result<ValidateOtpResponse?>?>
        get() = _validateOtpLiveData

    private val _collectEmailLiveData = MutableLiveData<Result<SimpleResponse?>?>()
    val collectEmailLiveData: LiveData<Result<SimpleResponse?>?>
        get() = _collectEmailLiveData

    fun resetValidateOtpLiveData() {
        _validateOtpLiveData.postValue(null)
    }

    fun resetCollectedEmailLiveData() {
        _collectEmailLiveData.postValue(null)
    }

    private val _isResentOtpLiveData = MutableLiveData<Boolean>()
    val isResentOtpLiveData: LiveData<Boolean>
        get() = _isResentOtpLiveData


    private val _imageAnalysisLiveData = MutableLiveData<Result<ImageAnalysisResponse?>?>()
    val imageAnalysisLiveData: LiveData<Result<ImageAnalysisResponse?>?>
        get() = _imageAnalysisLiveData

    fun resetImageAnalysisLiveData() {
        _imageAnalysisLiveData.postValue(null)
    }

    private val _docImageAnalysisLiveData = MutableLiveData<Result<DocImageAnalysisResponse?>?>()
    val docImageAnalysisLiveData: LiveData<Result<DocImageAnalysisResponse?>?>
        get() = _docImageAnalysisLiveData

    fun resetDocImageAnalysisLiveData() {
        _docImageAnalysisLiveData.postValue(null)
    }

    private val _livenessCheckLiveData = MutableLiveData<Result<LivenessCheckResponse?>>()
    val livenessCheckLiveData: LiveData<Result<LivenessCheckResponse?>>
        get() = _livenessCheckLiveData

    private val _verifyLiveData = MutableLiveData<Result<LivenessVerifyResponse?>>()
    val verifyLiveData: LiveData<Result<LivenessVerifyResponse?>>
        get() = _verifyLiveData


    //a single event to listen to all liveness chained events
    private val _submitLivenessLiveData = MutableLiveData<Result<SimpleResponse?>?>()
    val submitLivenessLiveData: LiveData<Result<SimpleResponse?>?>
        get() = _submitLivenessLiveData

    private val _analysisRetryCountLiveData = MutableLiveData<Int>(0)
    val analysisRetryCountLiveData: LiveData<Int>
        get() = _analysisRetryCountLiveData

    private val _docAnalysisRetryCountLiveData = MutableLiveData(Pair(0, 0))
    val docAnalysisRetryCountLiveData: LiveData<Pair<Int, Int>>
        get() = _docAnalysisRetryCountLiveData

    private val _submitBizLiveData = MutableLiveData<Result<SimpleResponse?>?>()
    val submitBizLiveData: LiveData<Result<SimpleResponse?>?>
        get() = _submitBizLiveData


    private val _submitSignatureLiveData = MutableLiveData<Result<SimpleResponse>?>()
    val submitSignatureLiveData: LiveData<Result<SimpleResponse>?>
        get() = _submitSignatureLiveData

    fun resetDocTypeLiveData() {
        _analysisRetryCountLiveData.postValue(0)
        _docAnalysisRetryCountLiveData.postValue(null)
        _submitLivenessLiveData.postValue(null)
    }

    private val _verifyCheckRetryCountLiveData = MutableLiveData<Int>(0)

    private val dojahEnum
        get(): DojahEnum {
            return repo.getDojahEnum.data
        }

    private val dojahPricing
        get(): DojahPricing {
            return repo.dojahPricing.data
        }

    fun selectVerificationType(type: String?) {
        type?.also {
            _verificationTypeLiveData.postValue(VerificationType.enumOfValue(type))
        }
    }

    //get gov id types from GOVERNMENT_DATA page config
    fun getGovIdTypes(
        verificationVm: VerificationViewModel
    ): List<EnumAttr?>? {
        return verificationVm.getStepWithPageName(KycPages.GOVERNMENT_DATA.serverKey)?.config?.govIds?.map { govIdKey ->
            verificationVm.dojahEnum.toMap()[govIdKey]
        }
    }

    //get verification methods from GOVERNMENT_DATA page config
    fun getVerifyMethods(
        verificationVm: VerificationViewModel
    ): List<String>? {
        return verificationVm.getStepWithPageName(KycPages.GOVERNMENT_DATA.serverKey)?.config?.verificationMethods?.map { method ->
            VerificationType.findEnumWithKey(method)?.value ?: method
        }
    }


    ///get doc id types from ID_OPTION page config
    fun getDocIDTypes(
        verificationVm: VerificationViewModel
    ): List<EnumAttr?>? {
        return verificationVm.getStepWithPageName(KycPages.ID_OPTION.serverKey)?.config?.govIds?.map { govIdKey ->
            verificationVm.dojahEnum.toMap()[govIdKey]
        }
    }

    ///get biz data types from Business_data page config
    fun getBusinessTypes(
        verificationVm: VerificationViewModel
    ): List<EnumAttr?>? {
        return getCurrentPage(KycPages.BUSINESS_DATA.serverKey)?.config?.businessTypes?.map { idKey ->
            verificationVm.dojahEnum.toMap()[idKey]
        }
    }

    fun getCompanyTypes(
    ): List<CompanyType> {
        return CompanyType.values().toList()
    }

    fun prefillGovIdentity(id: EnumAttr?) {
        _selectedGovIdDataLiveData.postValue(_selectedGovIdDataLiveData.value ?: id)
    }

    fun prefillBizId(id: EnumAttr?) {
        selectBizIdentity(_selectedBizIdDataLiveData.value ?: id)
    }

    fun selectGovIdentity(id: EnumAttr?) {
        _selectedGovIdDataLiveData.postValue(id)
    }

    fun selectBizIdentity(id: EnumAttr?) {
        _selectedBizIdDataLiveData.postValue(id)
    }


    /** do chain request to conclude gov data submission*/
    fun submitGovDataForm(
        verifyVm: VerificationViewModel,
        userInputId: String,
        services: List<String> = listOf(),
    ) {
        val dojahConstants = dojahEnum
        val stepNumber = verifyVm.getStepWithPageName(KycPages.GOVERNMENT_DATA.serverKey)?.id
            ?: throw Exception("No stepNumber")

        viewModelScope.launch {
            _submitGovLiveData.postValue(Result.Loading)

            val selectedIdEnum =
                selectedGovDataLiveData.value?.enum ?: throw Exception("Selected id is null")

            /** log [EventTypes.VERIFICATION_TYPE_SELECTED] event
             * zipped with GovId lookUp call
             * */
            logVerifyTypeSelected(
                verifyVm,
                services,
                selectedIdEnum,
                userInputId,
                stepNumber = stepNumber,
            ).onStart {
                /** startLoading */
            }.zip(
                doGovIdLookUp(
                    selectedIdEnum, dojahConstants, userInputId,
                )
            ) { typeSelectResult, lookUpResult ->
                return@zip typeSelectResult to lookUpResult
            }.collect {
                val lookUpResult = it.second
                if (lookUpResult is Result.Error) {
                    logStepEvent(
                        page = KycPages.GOVERNMENT_DATA,
                        event = EventTypes.STEP_FAILED,
                        error = lookUpResult
                    ).collect { _ ->
                        _submitGovLiveData.postValue(lookUpResult)
                    }
                    return@collect
                }
                val typeSelectResult = it.first
                if (typeSelectResult is Result.Error) {
                    _submitGovLiveData.postValue(typeSelectResult as Result.Error)
                    return@collect
                }
                if (lookUpResult is Result.Success && typeSelectResult is Result.Success) {
                    val lookUpEntity = lookUpResult.data.entity
                    lookUpEntity?.also { entity ->
                        _lookUpLiveData.postValue(entity)
                    }

                    val destination =
                        getlookUpPhoneNumberForGovData(verifyVm, lookUpEntity?.phoneNumber)
                    /** then log [EventTypes.CUSTOMER_GOVERNMENT_DATA_COLLECTED] event
                     *
                     */
                    logGovDataCollected(
                        verifyVm,
                        services,
                        userInputId,
                        lookUpEntity,
                        stepNumber = stepNumber,
                    ).zip(
                        repo.logEvent(
                            verifyVm.buildEventRequest(
                                services,
                                EventTypes.VERIFICATION_PAGE_CONFIG_COLLECTED.serverKey,
                                "${_verificationTypeLiveData.value},$destination",
                                stepNumber
                            )
                        )
                    ) { dataCollected, verifyConfigCollected ->
                        return@zip dataCollected to verifyConfigCollected
                    }.collect { (dataCollected, verifyConfigCollected) ->
                        HttpLoggingInterceptor.Logger.DEFAULT.log("Gov data: Selected aft dataCollected")
                        if (dataCollected is Result.Error) {
                            _submitGovLiveData.postValue(dataCollected)
                        } else if (verifyConfigCollected is Result.Error) {
                            _submitGovLiveData.postValue(verifyConfigCollected)
                        } else if (dataCollected is Result.Success) {
                            /** After gov data is collected,
                             *  then zip [EventTypes.GOVERNMENT_IMAGE_COLLECTED], with
                             *  [EventTypes.STEP_COMPLETED] or [EventTypes.VERIFICATION_MODE_SELECTED] event
                             * */
                            logGovImageCollected(
                                verifyVm,
                                services,
                                lookUpEntity,
                                stepNumber = stepNumber,
                            ).zip(
                                if (getVerifyMethods(verifyVm)?.isEmpty() == true) {
                                    /** if there are no gov verification options*/
                                    /** log [EventTypes.STEP_COMPLETED] directly*/
                                    logStepEvent(
                                        page = KycPages.GOVERNMENT_DATA,
                                        event = EventTypes.STEP_COMPLETED
                                    )
                                } else {
                                    //Log Data completed and mode selected
                                    logDataCompletedOrModeSelected(verifyVm, services, stepNumber)
                                }
                            ) { imageCollectedResult, govCompleteOrModeResult ->
                                return@zip imageCollectedResult to govCompleteOrModeResult
                            }.collect { mergedResult ->
                                val govImageCollectResult = mergedResult.first
                                val govCompleteResult = mergedResult.second

                                if (govImageCollectResult is Result.Error) {
                                    _submitGovLiveData.postValue(govImageCollectResult)
                                }


                                if (govCompleteResult is Result.Success) {
                                    if (_verificationTypeLiveData.value == VerificationType.OTP) {
                                        if (destination != null) {
                                            sendOtp(
                                                verifyVm,
                                                destination = destination,
                                                currentRoute = KycPages.GOVERNMENT_DATA_VERIFICATION.serverKey
                                            )
                                        }
                                    } else {
                                        _submitGovLiveData.postValue(
                                            Result.Success(
                                                govCompleteResult.data.entity?.msg ?: ""
                                            )
                                        )
                                    }
                                } else if (govCompleteResult is Result.Error) {
                                    _submitGovLiveData.postValue(govCompleteResult)
                                }

                            }
                        }
                    }
                }
            }
        }
    }

    private suspend fun logDataCompletedOrModeSelected(
        verifyVm: VerificationViewModel,
        services: List<String>,
        stepNumber: Int
    ): Flow<Result<SimpleResponse>> {
        /**
         * else if there are options
         * log [EventTypes.VERIFICATION_MODE_SELECTED]
         * first
         **/
        return logVerifyMethodSelected(
            verifyVm,
            services,
            stepNumber,
        ).zip(
            logStepEvent(
                page = KycPages.GOVERNMENT_DATA,
                event = EventTypes.STEP_COMPLETED
            )
        ) { _, govDataResult ->

            return@zip govDataResult
        }
    }


    private suspend fun logVerifyTypeSelected(
        verificationVm: VerificationViewModel,
        services: List<String> = listOf(),
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
        )
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
        )


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
    )

    private suspend fun logGovDataCollected(
        verificationVm: VerificationViewModel,
        services: List<String>,
        userInputId: String,
        lookUpEntity: GovIdEntityInterface?,
        stepNumber: Int,
    ): Flow<Result<SimpleResponse>> {
        val countryId = verificationVm.selectedCountryLiveData.value?.id?.uppercase()
        return repo.logEvent(
            verificationVm.buildEventRequest(
                services,
                EventTypes.CUSTOMER_GOVERNMENT_DATA_COLLECTED.serverKey,
                "${lookUpEntity?.customerID}|$userInputId|$countryId|${lookUpEntity?.fName}|${lookUpEntity?.mName}|${lookUpEntity?.lName}|${lookUpEntity?.dob}", //e.g 2222222|bvn|NG|firstname|middlename|lastname|dob
                stepNumber = stepNumber
            )
        )
    }

    private fun getlookUpPhoneNumberForGovData(
        verifyVm: VerificationViewModel,
        phone: String? = null,
    ): String? {
        var phoneNumber = phone ?: _lookUpLiveData.value?.phoneNumber
        if (phoneNumber == null) {
            _submitGovLiveData.postValue(
                Result.Error.ApiError(
                    mapOf(
                        "error" to FailedReasons.OTP_NOT_SENT.message,
                    )
                )
            )
            return null
        }
        logger.log("sendOtp phoneNumber: $phoneNumber")
        val countryCode = verifyVm.selectedCountryLiveData.value?.code ?: ""
        logger.log("sendOtp countryCode: $countryCode")
        phoneNumber = if (phoneNumber.startsWith("0")) {
            val alteredPhoneNumber = phoneNumber.replaceFirst("0", countryCode)
            logger.log("sendOtp alteredPhoneNumber @ 0: $alteredPhoneNumber")
            alteredPhoneNumber
        } else if (phoneNumber.length <= 10) {
            val alteredPhone = "$countryCode$phoneNumber"
            logger.log("sendOtp alteredPhone @ <=10: $alteredPhone")
            alteredPhone
        } else {
            phoneNumber
        }
        return phoneNumber
    }

    suspend fun sendOtp(
        verifyVm: VerificationViewModel,
        destination: String,
        currentRoute: String,
        resent: Boolean = false,
        isEmail: Boolean = false,
        onSuccess: () -> Unit? = {},
        onError: () -> Unit? = {},
    ) {

        //Options        ['sms', 'whatsapp', 'voice'], or 'email'
        val payload = if (isEmail) OtpRequest(
            email = destination,
            senderId = "kedesa",
            channel = "email",
            length = 4
        ) else OtpRequest(
            destination = destination,
            senderId = "kedesa",
            channel = "sms",
            length = 4
        )
        logger.log("Send Otp request: $payload")
        _isResentOtpLiveData.postValue(resent)

        repo.sendOtp(
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
                    onSuccess.invoke()
                } else if (otpResponse is Result.Error) {
                    val page = (KycPages.findPageEnum(currentRoute)
                        ?: KycPages.GOVERNMENT_DATA_VERIFICATION)
                    logStepEvent(
                        page = page,
                        event = EventTypes.STEP_FAILED,
                        failedReasons = FailedReasons.OTP_NOT_SENT
                    ).collect {
                        val otpError = Result.Error.ApiError(
                            code = -111, error = mapOf(
                                "error" to FailedReasons.OTP_NOT_SENT.message
                            )
                        )
                        _sendOtpLiveData.postValue(otpError)
                        _submitGovLiveData.postValue(otpError)
                        onError.invoke()
                    }
                }
            }
        }
    }

    fun sendOtpSync(
        verificationVm: VerificationViewModel,
        destination: String,
        currentRoute: String,
        resent: Boolean = false,
        isEmail: Boolean = false,
    ) {
        viewModelScope.launch {
            sendOtp(
                verificationVm,
                destination,
                currentRoute = currentRoute,
                resent,
                isEmail = isEmail
            )
        }
    }

    fun validateGovDataPhoneOtp(
        code: String,
        currentRoute: String,
        referenceId: String,
    ) {
        viewModelScope.launch {
            repo.validateOtp(code, referenceId)
                .onStart {
                    _validateOtpLiveData.postValue(Result.Loading)
                }.collect {
                    if (it is Result.Success && it.data.entity.valid) {
                        logStepEvent(
                            KycPages.GOVERNMENT_DATA_VERIFICATION,
                            EventTypes.STEP_COMPLETED
                        ).collect { _ ->
                            _validateOtpLiveData.postValue(it)
                        }
                    } else {
                        logInvalidOtpFailedEvent(currentRoute, it)
                    }
                }
        }
    }


    fun validateEmailOtp(
        viewModel: VerificationViewModel, code: String, currentRoute: String,
        sentOtpEntity: SendOtpEntity?
    ) {
        viewModelScope.launch {
            if (sentOtpEntity == null) {
                throw Exception("can't fetch otp reference")
            }
            val referenceId = sentOtpEntity.referenceId

            repo.validateOtp(code, referenceId)
                .onStart {
                    _validateOtpLiveData.postValue(Result.Loading)
                }.collect {
                    if (it is Result.Success && it.data.entity.valid) {
                        collectEmail(viewModel, email = sentOtpEntity.destination, it)
                    } else {
                        logInvalidOtpFailedEvent(currentRoute, it)
                    }
                }

        }
    }

    fun collectEmailWithoutOtp(
        viewModel: VerificationViewModel,
        email: String,
    ) {
        viewModelScope.launch {
            _validateOtpLiveData.postValue(Result.Loading)
            _collectEmailLiveData.postValue(Result.Loading)
            collectEmail(
                viewModel,
                email = email,
                null
            )
        }
    }


    fun validatePhoneOtp(
        viewModel: VerificationViewModel,
        code: String,
        currentRoute: String,
        sentOtpEntity: SendOtpEntity?
    ) {
        viewModelScope.launch {
            if (sentOtpEntity == null) {
                throw Exception("can't fetch otp reference")
            }
            val referenceId = sentOtpEntity.referenceId

            repo.validateOtp(code, referenceId)
                .onStart {
                    _validateOtpLiveData.postValue(Result.Loading)
                }.collect {
                    if (it is Result.Success && it.data.entity.valid) {
                        collectPhoneNumber(viewModel, sentOtpEntity.destination, it)
                    } else {
                        logInvalidOtpFailedEvent(currentRoute, it)
                    }
                }
        }
    }

    fun collectPhoneNumber(
        viewModel: VerificationViewModel,
        phone: String,
    ) {
        viewModelScope.launch {
            _validateOtpLiveData.postValue(Result.Loading)
            collectPhoneNumber(
                viewModel,
                phone,
                Result.Success(ValidateOtpResponse(entity = ValidateOtpEntity(valid = true)))
            )
        }
    }

    private suspend fun collectEmail(
        viewModel: VerificationViewModel,
        email: String,
        it: Result<ValidateOtpResponse>?
    ) {
        val stepNumber = getCurrentPage(KycPages.EMAIL.serverKey)?.id
            ?: throw Exception("No stepNumber")
        val duplicate = getPreAuthDataFromPref()?.widget?.duplicateCheck
        repo.logEvent(
            viewModel.buildEventRequest(
                eventType = EventTypes.EMAIL_COLLECTED.serverKey,
                eventValue = "$email,Successful,$duplicate",
                stepNumber = stepNumber
            )
        ).collect { emailResponse ->
            if (emailResponse is Result.Success) {
                logStepEvent(
                    KycPages.EMAIL,
                    EventTypes.STEP_COMPLETED
                ).collect { _ ->
                    ///update auth data
                    val emailEntity = emailResponse.data.entity
                    if (emailEntity?.continueVerification == true) {
                        val authDataFromPref =
                            getAuthDataFromPref() ?: throw Exception("No auth data")

                        repo.saveAuthDetailsToPrefs(
                            Result.Success(
                                authDataFromPref.copyUpdateFromEmail(emailResponse.data)
                            )
                        )
                    }

                    _validateOtpLiveData.postValue(it)
                    _collectEmailLiveData.postValue(emailResponse)
                }
            } else if (emailResponse is Result.Error) {
                logStepEvent(
                    KycPages.EMAIL,
                    EventTypes.STEP_FAILED
                ).collect { _ ->
                    _validateOtpLiveData.postValue(emailResponse)
                    _collectEmailLiveData.postValue(emailResponse)
                }

            }
        }
    }

    private suspend fun collectPhoneNumber(
        viewModel: VerificationViewModel,
        phone: String,
        it: Result<ValidateOtpResponse>
    ) {
        val stepNumber = getCurrentPage(KycPages.PHONE_NUMBER.serverKey)?.id
            ?: throw Exception("No stepNumber")
        repo.logEvent(
            viewModel.buildEventRequest(
                eventType = EventTypes.PHONE_NUMBER_VALIDATION.serverKey,
                eventValue = "${phone.replace("+", "")},Successful",
                stepNumber = stepNumber
            )
        ).collect { phoneResponse ->
            if (phoneResponse is Result.Success) {
                logStepEvent(
                    KycPages.PHONE_NUMBER,
                    EventTypes.STEP_COMPLETED
                ).collect { _ ->
                    _validateOtpLiveData.postValue(it)
                }
            } else if (phoneResponse is Result.Error) {
                logStepEvent(
                    KycPages.PHONE_NUMBER,
                    EventTypes.STEP_FAILED
                ).collect { _ ->
                    _validateOtpLiveData.postValue(phoneResponse)
                }

            }
        }
    }


    private suspend fun logInvalidOtpFailedEvent(
        currentRoute: String,
        it: Result<ValidateOtpResponse>
    ) {
        val page = (KycPages.findPageEnum(currentRoute)
            ?: KycPages.GOVERNMENT_DATA_VERIFICATION)
        if (it is Result.Success) {
            if (!it.data.entity.valid) {
                logStepEvent(
                    page = page,
                    event = EventTypes.STEP_FAILED,
                    failedReasons = FailedReasons.INVALID_OTP
                ).collect { _ ->
                    _validateOtpLiveData.postValue(it)
                }
            }
        } else if (it is Result.Error) {
            logStepEvent(
                page = page,
                event = EventTypes.STEP_FAILED,
                error = it
            ).collect { _ ->
                _validateOtpLiveData.postValue(it)
            }
        }
    }

    fun startLoadingImageAnalysis() {
        _imageAnalysisLiveData.postValue(Result.Loading)
    }

    fun performImageAnalysis(
        image: String,
        imageType: String? = verificationTypeLiveData.value?.actualServerKey,
        currentRoute: String? = null
    ) {
        viewModelScope.launch {
            if (imageType == null) throw Exception("verification type is null")
            repo.performImageAnalysis(image, imageType)
                .collect {
                    _imageAnalysisLiveData.postValue(it)

                    if (it is Result.Success) {
                        val faceResult = it.data.entity?.face
                        val config =
                            getCurrentPage(
                                currentRoute ?: KycPages.GOVERNMENT_DATA.serverKey
                            )?.config
                        if (faceResult?.faceSuccess(config) == false) {
                            if (_analysisRetryCountLiveData.value!! < analysisRetryMax) {
                                _analysisRetryCountLiveData.postValue(
                                    _analysisRetryCountLiveData.value?.plus(1)
                                )
                            }
                        }
                    }
                }
        }
    }

    fun performDocImageAnalysis(
        mainVm: VerificationViewModel,
        image: String,
    ) {
        viewModelScope.launch {
            repo.performDocImageAnalysis(image)
                .onStart {
                    _docImageAnalysisLiveData.postValue(Result.Loading)
                }
                .collect {

                    if (it is Result.Success) {
                        val docDetails = it.data.entity?.id?.details
                        val docType = mainVm.docTypeLiveData.value
                        if (docDetails?.docSucceed(docType) == true) {
                            _docImageAnalysisLiveData.postValue(it)
                        } else {
                            if (mainVm.isBackDocLiveData.value == true) {
                                // if back doc analysis fails the 0...max times
                                val backCount =
                                    _docAnalysisRetryCountLiveData.value?.second ?: 0
                                //TODO: this is a quick fix, we need to refactor this
                                if (backCount < analysisRetryMax - 1) {
                                    // increment back doc analysis count
                                    _docAnalysisRetryCountLiveData.postValue(
                                        Pair(
                                            _docAnalysisRetryCountLiveData.value?.first ?: 0,
                                            backCount.plus(1)
                                        )
                                    )
                                } else {
                                    _docImageAnalysisLiveData.postValue(it)
                                    return@collect
                                }
                            } else {
                                // if front doc analysis fails the 0...max times
                                val firstCount =
                                    _docAnalysisRetryCountLiveData.value?.first ?: 0
                                //TODO: this is a quick fix, we need to refactor this
                                if (firstCount < analysisRetryMax - 1) {
                                    // increment front doc analysis count
                                    _docAnalysisRetryCountLiveData.postValue(
                                        Pair(
                                            firstCount.plus(1),
                                            _docAnalysisRetryCountLiveData.value?.second ?: 0
                                        )
                                    )
                                } else {
                                    _docImageAnalysisLiveData.postValue(it)
                                    return@collect
                                }
                            }

                            _docImageAnalysisLiveData.postValue(
                                Result.Error.ApiError(
                                    mapOf(
                                        "error" to (it.data.entity?.id?.details?.analysisErrorMsg(
                                            docType
                                        ) ?: it.data.entity?.id?.message
                                        ?: "No ID card detected, please try again")
                                    )
                                )
                            )

                        }
                    } else {
                        _docImageAnalysisLiveData.postValue(it)
                    }
                }
        }
    }

    fun doCheckForDocId(
        mainVm: VerificationViewModel,
        image: String,
        image2: String? = null,
        page: KycPages = KycPages.ID,
        selfieType: String?,
    ) {
        val param =
            if (page == KycPages.BUSINESS_ID) "BUSINESS"
            else
                getServerEnumValueOfDocType(
                    docType = mainVm.docTypeLiveData.value
                        ?: throw Exception("Doc type is null"),
                    selectedCountryCode = mainVm.selectedCountryLiveData.value?.id
                        ?: throw Exception("Country code is null")
                )
        val fileInfo =
            mainVm.docInfoLiveData.value?.first ?: mainVm.docInfoLiveData.value?.second
            ?: throw Exception("can't fetch doc info")
        val retryCount = _docAnalysisRetryCountLiveData.value ?: Pair(0, 0)
        val continueVerify =
            retryCount.first >= analysisRetryMax &&
                    retryCount.second >= analysisRetryMax

        val docType = if (fileInfo.docType != "pdf") "image" else fileInfo.docType
        checkLiveness(
            image,
            image2,
            page = page,
            param = param,
            selfieType = selfieType,
            continueVerification = continueVerify,
            docType = docType,
        )
    }

    fun checkLiveness(
        image: String,
        image2: String? = null,
        page: KycPages,
        param: String = "face",
        selfieType: String? = "selfie_type",
        continueVerification: Boolean? = null,
        docType: String? = "image",
    ) {
        viewModelScope.launch {
            val verificationId =
                getAuthDataFromPref()?.initData?.authData?.verificationId
                    ?: throw Exception("Verification id is null")
            val stepNumber = getCurrentPage(page.serverKey)?.id
            val continueVerify =
                continueVerification ?: ((_analysisRetryCountLiveData.value
                    ?: 0) >= analysisRetryMax)
            repo.checkLiveness(
                LivenessCheckRequest(
                    image,
                    image2,
                    verificationId,
                    stepNumber,
                    param,
                    selfieType,
                    docType,
                    continueVerify
                )
            ).onStart {
                /// start loading @check, and stop @ step event
                /// or on error
                _submitLivenessLiveData.postValue(Result.Loading)
            }.collect {
                _livenessCheckLiveData.postValue(it)
                if (it is Result.Error) {
                    _submitLivenessLiveData.postValue(it)
                    logStepEvent(
                        page,
                        EventTypes.STEP_FAILED,
                        error = it,
                    ).collect { _ ->
                        /// fire event for all liveness process
                        _submitLivenessLiveData.postValue(it)
                    }
                    return@collect
                }
                if (it is Result.Success) {
                    if (it.data.entity?.match == true) {
                        //then verify liveness
                        verifyLiveness().collect { verifyResult ->
                            if (verifyResult is Result.Success) {
                                //then log step completed event
                                logStepEvent(
                                    page,
                                    EventTypes.STEP_COMPLETED
                                ).collect { eventResult ->
                                    /// fire event for all liveness process
                                    _submitLivenessLiveData.postValue(eventResult)
                                }
                                _verifyLiveData.postValue(verifyResult)
                            }else if(verifyResult is Result.Error){
                                _submitLivenessLiveData.postValue(verifyResult)
                            }
                        }
                    } else {
                        if (_verifyCheckRetryCountLiveData.value!! < checkRetryMax) {
                            _verifyCheckRetryCountLiveData.value =
                                _verifyCheckRetryCountLiveData.value?.plus(1)
                            ///Retry verification
                            checkLiveness(
                                image,
                                image2,
                                page,
                                param,
                                selfieType,
                                continueVerification,
                                docType
                            )
                        } else {
                            //log step failed event
                            logStepEvent(
                                page,
                                EventTypes.STEP_FAILED,
                                failedReasons = FailedReasons.ID_FAILED_MAX_TIME
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
        )
    }


    fun sendAdditionalDoc(
        mainVm: VerificationViewModel,
        fileBase64: String,
    ) {
        viewModelScope.launch {
            val verificationId = getAuthDataFromPref()?.initData?.authData?.verificationId
                ?: throw Exception("Verification id is null")
            val uri = mainVm.frontDocUriLiveData.value ?: throw Exception("Uri is null")
            logger.log("uri is: ${uri.path}")
            val fileInfo =
                mainVm.docInfoLiveData.value?.first ?: throw Exception("can't fetch doc info")
            val docType = if (fileInfo.docType != "pdf") "image" else fileInfo.docType
            logger.log("fileName is: ${fileInfo.docName}")
            logger.log("fileExt is: $docType")
            repo.uploadAdditionalFile(
                AdditionalDocRequest(
                    fileBase64,
                    fileInfo.docName,
                    docType,
                    verificationId,
                    title = mainVm.getStepWithPageName(KycPages.OTHER_DOCUMENT.serverKey)?.config?.title
                        ?: throw Exception("No title")
                )
            )
                .onStart { _submitLivenessLiveData.postValue(Result.Loading) }
                .collect { govResult ->
                    if (govResult is Result.Success) {
                        logStepEvent(
                            page = KycPages.OTHER_DOCUMENT,
                            event = EventTypes.STEP_COMPLETED
                        ).collect { eventResult ->
                            _submitLivenessLiveData.postValue(eventResult)
                        }
                    } else if (govResult is Result.Error) {
                        logStepEvent(
                            page = KycPages.OTHER_DOCUMENT,
                            event = EventTypes.STEP_FAILED,
                            error = govResult
                        ).collect { _ ->
                            _submitLivenessLiveData.postValue(govResult)
                        }
                    }
                }
        }
    }

    fun submitBusinessData(
        verificationVm: VerificationViewModel,
        number: String,
        companyName: String? = null,
        companyType: CompanyType,
    ) {
        viewModelScope.launch {
            val selectedTypeEnum =
                _selectedBizIdDataLiveData.value?.enum
                    ?: throw Exception("No option selected")
            val stepNumber =
                getCurrentPage(KycPages.BUSINESS_DATA.serverKey)?.id
                    ?: throw Exception("No stepNumber")
            logVerifyTypeSelected(
                verificationVm,
                selectedIdEnum = selectedTypeEnum,
                userId = number,
                stepNumber = stepNumber

            ).onStart {
                _submitBizLiveData.postValue(Result.Loading)
            }.collect {
                if (it is Result.Error) {
                    _submitBizLiveData.postValue(it)
                } else if (it is Result.Success) {
                    val selectedTypeIdName =
                        _selectedBizIdDataLiveData.value?.idName?.lowercase()
                            ?: throw Exception("No option selected")
                    val appId = prefManager.getAppId() ?: throw Exception("App id is null")
                    when (selectedTypeIdName) {
                        BusinessType.CAC.serverKey -> {
                            repo.lookupCac(number, companyName, companyType.serverKey, appId)
                        }

                        BusinessType.TIN.serverKey -> {
                            repo.lookupTin(number, companyName, appId)
                        }

                        else -> throw Exception("Type not currently supported")
                    }.collect { lookUpResult ->
                        if (lookUpResult is Result.Error) {
                            _submitBizLiveData.postValue(lookUpResult)
                        } else if (lookUpResult is Result.Success) {

                            val verificationId =
                                getAuthDataFromPref()?.initData?.authData?.verificationId
                                    ?: throw Exception("Verification id is null")

                            val bizEntity = lookUpResult.data.entity
                            HttpLoggingInterceptor.Logger.DEFAULT.log("Lookup Result:${bizEntity}")
                            repo.logEvent(
                                EventRequest(
                                    stepNumber = stepNumber,
                                    services = listOf(),
                                    eventType = EventTypes.CUSTOMER_BUSINESS_DATA_COLLECTED.serverKey,
                                    eventValue = "${bizEntity?.business},${selectedTypeEnum},${verificationVm.selectedCountryLiveData.value?.id?.uppercase()},${bizEntity?.companyName}",
                                    verificationId = verificationId
                                )
                            ).collect { bizCollectedResult ->
                                if (bizCollectedResult is Result.Success) {
                                    logStepEvent(
                                        KycPages.BUSINESS_DATA,
                                        EventTypes.STEP_COMPLETED
                                    ).collect { _ ->
                                        _submitBizLiveData.postValue(bizCollectedResult)
                                    }
                                } else if (bizCollectedResult is Result.Error) {
                                    logStepEvent(
                                        page = KycPages.BUSINESS_DATA,
                                        event = EventTypes.STEP_FAILED,
                                        error = bizCollectedResult
                                    ).collect { _ ->
                                        _submitBizLiveData.postValue(bizCollectedResult)
                                    }
                                }
                            }
                        }
                    }
                }
            }


        }
    }

    fun submitSignature(mainVm: VerificationViewModel, name: String) {
        viewModelScope.launch {
            val stepNumber =
                getCurrentPage(KycPages.SIGNATURE.serverKey)?.id
                    ?: throw Exception("No stepNumber")
            repo.logEvent(
                mainVm.buildEventRequest(
                    listOf(),
                    KycPages.SIGNATURE.serverKey,
                    "$name|${signatureInfo}", //e.g BVN,2222222
                    stepNumber = stepNumber
                )
            ).onStart {
                _submitSignatureLiveData.postValue(Result.Loading)
            }.collect {
                if (it is Result.Success) {
                    logStepEvent(KycPages.SIGNATURE, EventTypes.STEP_COMPLETED).collect { _ ->
                        _submitSignatureLiveData.postValue(it)
                    }
                } else if (it is Result.Error) {
                    logStepEvent(
                        page = KycPages.SIGNATURE,
                        event = EventTypes.STEP_FAILED,
                        error = it,
                    ).collect { _ ->
                        _submitSignatureLiveData.postValue(it)
                    }

                }
            }
        }
    }

    val signatureInfo: String
        get() =
            getCurrentPage(KycPages.SIGNATURE.serverKey)?.config?.information?.replace("|", "")
                ?: throw Exception("No information")


    fun logIdOptionEvents(navGraphVm: VerificationViewModel, activityVm: VerificationViewModel) {
        viewModelScope.launch {
            val stepNumber = getCurrentPage(KycPages.ID_OPTION.serverKey)?.id
                ?: throw Exception("No stepNumber")

            val selectedTypeServerEnum = getServerEnumOfDocType(
                navGraphVm.docTypeLiveData.value ?: activityVm.docTypeLiveData.value
                ?: throw Exception("Doc type is null"),
                selectedCountryCode = navGraphVm.selectedCountryLiveData.value?.id
                    ?: activityVm.selectedCountryLiveData.value?.id
                    ?: throw Exception("Country code is null")
            )
            val typeSelectEventRequest = navGraphVm.buildEventRequest(
                eventType = EventTypes.VERIFICATION_TYPE_SELECTED.serverKey,
                eventValue = selectedTypeServerEnum,
                stepNumber = stepNumber,
            )
            val modeSelectEventRequest = navGraphVm.buildEventRequest(
                eventType = EventTypes.VERIFICATION_MODE_SELECTED.serverKey,
                eventValue = "LIVENESS",
                stepNumber = stepNumber,
            )
            repo.logEvent(typeSelectEventRequest)
                .onStart { _submitGovLiveData.postValue(Result.Loading) }
                .zip(repo.logEvent(modeSelectEventRequest)) { typeSelect, modeSelect ->
                    return@zip typeSelect to modeSelect
                }.collect {
                    val (typeSelect, modeSelect) = it
                    if (typeSelect is Result.Success && modeSelect is Result.Success) {
                        logStepEvent(
                            KycPages.ID_OPTION,
                            EventTypes.STEP_COMPLETED
                        ).collect { eventResult ->
                            if (eventResult is Result.Success) {
                                _submitGovLiveData.postValue(
                                    Result.Success(
                                        eventResult.data.entity?.msg ?: ""
                                    )
                                )
                            } else {
                                _submitGovLiveData.postValue(
                                    Result.Error.ApiError(
                                        mapOf(
                                            "error" to "Error logging events"
                                        )
                                    )
                                )
                            }
                        }
                    } else {
                        _submitGovLiveData.postValue(
                            Result.Error.ApiError(
                                mapOf(
                                    "error" to "Error logging events"
                                )
                            )
                        )
                    }
                }
        }
    }

    private fun getServerEnumOfDocType(
        docType: GovDocType,
        selectedCountryCode: String
    ): String {
        return dojahEnum.toMap().entries.find { entry ->
            logger.log("key ${entry.key} value ${entry.value.id} docType ${docType.serverKey}, selectedCountryCode $selectedCountryCode")
            entry.key.lowercase()
                .startsWith(selectedCountryCode.lowercase())
                    && entry.value.id == docType.serverKey
        }?.value?.enum ?: throw Exception("No enum found for this doc type")
    }

    private fun getServerEnumValueOfDocType(
        docType: GovDocType,
        selectedCountryCode: String
    ): String {
        val defaultValue = dojahEnum.toMap()[docType.serverKey]?.value
        val firstValueSplit = defaultValue?.split("-")?.firstOrNull()
            ?: throw Exception("No enum found for this doc type")
        logger.log("firstValueSplit $firstValueSplit, selectedCountryCode $selectedCountryCode")
        logger.log(
            "new enumValue is: ${
                defaultValue.replace(
                    firstValueSplit,
                    selectedCountryCode
                )
            }"
        )
        return defaultValue.replace(firstValueSplit, selectedCountryCode)
    }


    private suspend fun logStepEvent(
        page: KycPages,
        event: EventTypes,
        failedReasons: FailedReasons? = null,
        error: Result.Error? = null
    ): Flow<Result<SimpleResponse>> {
        var failureCode: String? = null

        /**send price if event is
         * [EventTypes.STEP_FAILED]
         * or [EventTypes.STEP_COMPLETED]
         * **/
        val services: List<String> = DojahPricingUtil.getPricingServices(
            page = page,
            pricing = dojahPricing,
            event = event,
            govViewModel = this
        )

        if (event == EventTypes.STEP_FAILED) {
            getFailureCode(error, page, failedReasons).also { generatedFailureCode ->
                if (generatedFailureCode != null) {
                    failureCode = generatedFailureCode
                } else {
                    flow {
                        emit(error)
                    }.flowOn(Dispatchers.IO)
                }
            }
        }
        val verificationId =
            getAuthDataFromPref()?.initData?.authData?.verificationId
                ?: throw Exception("Verification id is null")
        HttpLoggingInterceptor.Logger.DEFAULT.log("Failure code: $failureCode")
        HttpLoggingInterceptor.Logger.DEFAULT.log("page: ${page.serverKey}")
        HttpLoggingInterceptor.Logger.DEFAULT.log("stepNumb: ${getCurrentPage(page.serverKey)?.id}")

        val stepNumber =
            getCurrentPage(page.serverKey)?.id
                ?: 2

        return repo.logEvent(
            EventRequest(
                verificationId,
                stepNumber,
                event.serverKey,
                eventValue = failureCode ?: page.serverKey,
                services = services,
            )
        )
    }

    fun getCurrentPage(currentPage: String): Step? {
        val steps = getAuthDataFromPref()?.initData?.authData?.pages
        return steps?.find { it.name == currentPage }
    }

    private fun getAuthDataFromPref(): AuthResponse? {
        return repo.getLocalResponse(
            SharedPreferenceManager.KEY_AUTH_RESPONSE, AuthResponse::class.java
        )?.data
    }

    private fun getPreAuthDataFromPref(): PreAuthResponse? {
        return repo.getLocalResponse(
            SharedPreferenceManager.KEY_PRE_AUTH_RESPONSE, PreAuthResponse::class.java
        )?.data
    }

}

