package com.dojah.sdk_kyc.ui.main.viewmodel

import android.annotation.SuppressLint
import android.content.Context
import android.net.Uri
import android.os.Build
import android.os.CountDownTimer
import android.provider.OpenableColumns
import androidx.core.os.ConfigurationCompat
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dojah.sdk_kyc.R
import com.dojah.sdk_kyc.core.Result
import com.dojah.sdk_kyc.data.io.CountryManager
import com.dojah.sdk_kyc.data.io.SharedPreferenceManager
import com.dojah.sdk_kyc.data.repository.DojahRepository
import com.dojah.sdk_kyc.domain.Country
import com.dojah.sdk_kyc.domain.DocumentInfo
import com.dojah.sdk_kyc.domain.request.CheckIpRequest
import com.dojah.sdk_kyc.domain.request.EventRequest
import com.dojah.sdk_kyc.domain.request.UserDataRequest
import com.dojah.sdk_kyc.domain.responses.*
import com.dojah.sdk_kyc.ui.utils.*
import dagger.Lazy
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import okhttp3.logging.HttpLoggingInterceptor
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader
import java.text.SimpleDateFormat
import java.time.Duration
import java.util.Date
import java.util.Locale
import javax.inject.Inject
import kotlin.math.log

@SuppressLint("StaticFieldLeak")
@HiltViewModel
class VerificationViewModel @Inject constructor(
    val prefManager: SharedPreferenceManager,
    private val repo: DojahRepository,
    private val countryManager: Lazy<CountryManager>
) : ViewModel() {
    private val logger: HttpLoggingInterceptor.Logger = HttpLoggingInterceptor.Logger.DEFAULT
    private val _countryLiveData = MutableLiveData<List<Country>>()
    private val _frontDocUriLiveData = MutableLiveData<Uri>()

    //this stores the front and back doc uri info
    private val _docInfoLiveData = MutableLiveData<Pair<DocumentInfo?, DocumentInfo?>>()
    private val _backDocUriLiveData = MutableLiveData<Uri>()
    private val _isBackDocLiveData = MutableLiveData(false)
    private val _isUploadDocLiveData = MutableLiveData(false)
    private val _docTypeLiveData = MutableLiveData<GovDocType?>()
    private val _verificationTypeLiveData = MutableLiveData<VerificationType?>()
    private val _selfiePhotoUriLiveData = MutableLiveData<Uri>()
    private val _timerOtpLiveData = MutableLiveData<String>()
    private val _timerOtpDoneLiveData = MutableLiveData<Boolean>(false)
    private val _preAuthDataLiveData = MutableLiveData<Result<PreAuthResponse>>()
    private val _authDataLiveData = MutableLiveData<Result<AuthResponse>>()
    private val _authVerificationCompletedLD = MutableLiveData<Boolean>(false)
    private val _checkIpDataLiveData = MutableLiveData<Result<CheckIpResponse>>()
    private val _getIpDataLiveData = MutableLiveData<Result<GetIpResponse>>()
    private val _authErrLiveData = MutableLiveData<String>()
    private val _pages = MutableLiveData<List<Step>?>()

    val pages: LiveData<List<Step>?>
        get() = _pages

    val preAuthDataLiveData: LiveData<Result<PreAuthResponse>>
        get() = _preAuthDataLiveData
    val authDataLiveData: LiveData<Result<AuthResponse>>
        get() = _authDataLiveData
    val authVerificationCompletedLD: LiveData<Boolean>
        get() = _authVerificationCompletedLD

    val checkIpDataLiveData: LiveData<Result<CheckIpResponse>>
        get() = _checkIpDataLiveData

    val getIpDataLiveData: LiveData<Result<GetIpResponse>>
        get() = _getIpDataLiveData

    val authErrLiveData: LiveData<String>
        get() = _authErrLiveData

    val countryLiveData: LiveData<List<Country>>
        get() = _countryLiveData

    private val _eventLiveData = MutableLiveData<Pair<EventRequest, Result<SimpleResponse>>?>()
    val eventLiveData: LiveData<Pair<EventRequest, Result<SimpleResponse>>?>
        get() = _eventLiveData

    private val _mailLiveData = MutableLiveData<Pair<List<String>?, List<String>?>>()
    val mailLiveData: LiveData<Pair<List<String>?, List<String>?>>
        get() = _mailLiveData

    fun resetEventData() {
        _eventLiveData.postValue(null)
    }

    fun resetAuthVerificationCompleted() {
        //clear necessary event
        _authVerificationCompletedLD.postValue(false)
    }


    val frontDocUriLiveData: LiveData<Uri>
        get() = _frontDocUriLiveData
    val backDocUriLiveData: LiveData<Uri>
        get() = _backDocUriLiveData
    val docInfoLiveData: LiveData<Pair<DocumentInfo?, DocumentInfo?>>
        get() = _docInfoLiveData
    val isBackDocLiveData: LiveData<Boolean>
        get() = _isBackDocLiveData
    val isUploadDocLiveData: LiveData<Boolean>
        get() = _isUploadDocLiveData

    val docTypeLiveData: LiveData<GovDocType?>
        get() = _docTypeLiveData

    val verificationTypeLiveData: LiveData<VerificationType?>
        get() = _verificationTypeLiveData

    val selfieUriLiveData: LiveData<Uri>
        get() = _selfiePhotoUriLiveData

    val timerOtpLiveData: LiveData<String>
        get() = _timerOtpLiveData
    val timerOtpDoneLiveData: LiveData<Boolean>
        get() = _timerOtpDoneLiveData

    private val _selectedCountryLiveData = MutableLiveData<Country?>()
    val selectedCountryLiveData: LiveData<Country?>
        get() = _selectedCountryLiveData

    private val _selectedGovIdDataLiveData = MutableLiveData<EnumAttr?>()
    val selectedGovDataLiveData: LiveData<EnumAttr?>
        get() = _selectedGovIdDataLiveData


    private val _submitGovLiveData = MutableLiveData<Result<String>>()
    val submitGovLiveData: LiveData<Result<String>>
        get() = _submitGovLiveData

    private val _submitAddressLiveData = MutableLiveData<Result<SimpleResponse>>()
    val submitAddressLiveData: LiveData<Result<SimpleResponse>>
        get() = _submitAddressLiveData

    private val _submitUserLiveData = MutableLiveData<Result<SimpleResponse>>()
    val submitUserLiveData: LiveData<Result<SimpleResponse>>
        get() = _submitUserLiveData

    val dojahEnum
        get(): DojahEnum {
            return repo.getDojahEnum.data
        }

    private fun saveBrandColor(color: String?) {
        var newColor = color
        if (color.isNullOrBlank()) {
            logger.log("Brand color: null")
            newColor = null
        }
        logger.log("Brand color: $newColor")
        prefManager.setMaterialButtonBgColor(newColor)
    }

    fun setFrontDocUri(context: Context, uri: Uri, isUpload: Boolean): DocumentInfo? {
        val frontDocInfo = getDocInfo(context, uri, isUpload)
        _isBackDocLiveData.postValue(false)
        _isUploadDocLiveData.postValue(isUpload)
        _frontDocUriLiveData.postValue(uri)
        _docInfoLiveData.postValue(
            frontDocInfo to _docInfoLiveData.value?.second
        )
        return frontDocInfo
    }

    fun setSelfieUri(uri: Uri) {
        _selfiePhotoUriLiveData.postValue(uri)
    }

    fun setBackDocUri(context: Context, uri: Uri, isUpload: Boolean): DocumentInfo? {
        val backDocInfo = getDocInfo(context, uri, isUpload)

        _isBackDocLiveData.postValue(true)
        _isUploadDocLiveData.postValue(isUpload)
        _backDocUriLiveData.postValue(uri)
        _docInfoLiveData.postValue(
            _docInfoLiveData.value?.first to backDocInfo
        )
        return backDocInfo
    }

    fun selectDocType(type: String) {
        _docTypeLiveData.postValue(GovDocType.enumOfValue(type))
    }

    fun loadCountries() {
        getCountries()?.addCallback {
            _countryLiveData.postValue(it)
        }
    }

    fun getCountries(): CountryManager? {
        return countryManager.get()
    }

    fun authenticate(
        widgetId: String,
        referenceId: String? = null,
        email: String? = null,
    ) {
        val showLoader = true
        viewModelScope.launch {
            //first delete all auth-local data
            repo.deleteAllAuthData()
            //do PreAuth
            repo.doPreAuth(widgetId)
                .onStart { if (showLoader) _preAuthDataLiveData.postValue(Result.Loading) }
                .collect { preAuthResult ->
                    _preAuthDataLiveData.postValue(preAuthResult)
                    if (preAuthResult is Result.Success) {
                        saveBrandColor(preAuthResult.data.app?.colorCode)
                        //do Auth
                        repo.doAuth(
                            preAuthResult.data.toAuthRequest(
                                referenceId = referenceId,
                                email = email,
                            )
                        ).collect { authResult ->
                            _authDataLiveData.postValue(authResult)
                            if (authResult is Result.Success) {
                                //getUserIp
                                repo.getUserIp().collect { ipResult ->
                                    _getIpDataLiveData.postValue(ipResult)
                                    if (ipResult is Result.Success) {
                                        //verify UserIp
                                        repo.checkUserIp(
                                            CheckIpRequest(
                                                ipResult.data.ip!!,
                                                deviceInfo = "${Build.VERSION.SDK_INT},${Build.DEVICE},${Build.MODEL},${Build.PRODUCT},${Build.MANUFACTURER},${Build.BRAND},${Build.BOARD},${Build.HARDWARE}",
                                                verificationId = authResult.data.initData?.authData?.verificationId,
                                            )
                                        ).collect {
                                            _checkIpDataLiveData.postValue(it)
                                            if (it is Result.Error) {
                                                _authErrLiveData.postValue(
                                                    getErrorMessage(
                                                        it
                                                    )
                                                )
                                            }
                                        }
                                    } else if (ipResult is Result.Error) {
                                        _authErrLiveData.postValue(getErrorMessage(ipResult))
                                    }
                                }

                            } else if (authResult is Result.Error) {
                                if (getBodyErrorCode(authResult)?.toInt() == 402) {
                                    logger.log("Verification resume success vm")
                                    //show verification success screen
                                    _authVerificationCompletedLD.postValue(true)
                                } else {
                                    logger.log("Verification resume fail vm")
                                    _authErrLiveData.postValue(getErrorMessage(authResult))
                                }
                            }
                        }
                    } else if (preAuthResult is Result.Error) {
                        _authErrLiveData.postValue(getErrorMessage(preAuthResult))
                    }
                }
        }
    }

    //send user data with UserDataRequest as param
    fun sendUserData(
        dob: String, // 1996-04-30
        firstName: String, // Osarumen
        lastName: String, // Alohan
        middleName: String, // Eleojo
    ) {
        viewModelScope.launch {
            val verificationId =
                authDataFromPref?.initData?.authData?.verificationId
                    ?: throw Exception("Verification id is null")
            val stepNumber = getStepWithPageName(KycPages.USER_DATA.serverKey)?.id
                ?: throw Exception("No stepNumber")
            repo.sendUserData(
                UserDataRequest(
                    appId = authDataFromPref?.app?.id,
                    sessionId = prefManager.getSessionId(),
                    country = selectedCountryLiveData.value?.code,
                    stepNumber = stepNumber,
                    verificationId = verificationId,
                    dob = dob,
                    firstName = firstName,
                    lastName = lastName,
                    middleName = middleName,
                    mobile = null,
                    residenceCountry = null
                )
            )
                .onStart { _submitUserLiveData.postValue(Result.Loading) }
                .collect { userResult ->
                    if (userResult is Result.Success) {
                        logStepEvent(
                            page = KycPages.USER_DATA,
                            event = EventTypes.STEP_COMPLETED
                        ).collect { eventResult ->
                            _submitUserLiveData.postValue(eventResult)
                        }
                    } else if (userResult is Result.Error) {
                        logStepEvent(
                            page = KycPages.USER_DATA,
                            event = EventTypes.STEP_FAILED,
                            error = userResult
                        ).collect { _ ->
                            _submitUserLiveData.postValue(userResult)
                        }
                    }
                }
        }
    }

    fun sendAddress(
        selectedAddressLatitude: Double,
        selectedAddressLongitude: Double,
        addressName: String,
        match: Boolean
    ) {
        val doVerification =
            getStepWithPageName(KycPages.ADDRESS.serverKey)?.config?.verification ?: false
        viewModelScope.launch {
            repo.sendBaseAddress(
                selectedAddressLatitude,
                selectedAddressLongitude,
                addressName
            ).onStart {
                _submitAddressLiveData.postValue(Result.Loading)
            }.collect {
                if (it is Result.Success) {
                    if (doVerification) {
                        repo.sendAddress(
                            match,
                        ).collect { sendAddressResult ->
                            if (sendAddressResult is Result.Success) {
                                logStepEvent(
                                    page = KycPages.ADDRESS,
                                    event = EventTypes.STEP_COMPLETED
                                ).collect { eventResult ->
                                    _submitAddressLiveData.postValue(eventResult)
                                }
                            } else if (sendAddressResult is Result.Error) {
                                _submitAddressLiveData.postValue(it)
                                logStepEvent(
                                    page = KycPages.ADDRESS,
                                    event = EventTypes.STEP_FAILED,
                                    error = sendAddressResult
                                )
                            }
                        }
                    } else {
                        logStepEvent(
                            page = KycPages.ADDRESS,
                            event = EventTypes.STEP_COMPLETED
                        ).collect { eventResult ->
                            _submitAddressLiveData.postValue(eventResult)
                        }
                    }
                } else if (it is Result.Error) {
                    _submitAddressLiveData.postValue(it)
                    logStepEvent(
                        page = KycPages.ADDRESS,
                        event = EventTypes.STEP_FAILED,
                        error = it
                    )
                }
            }
        }
    }


    private suspend fun logStepEvent(
        page: KycPages,
        event: EventTypes,
        failedReasons: FailedReasons? = null,
        error: Result.Error? = null
    ): Flow<Result<SimpleResponse>> {
        var failureCode: String? = null
        val services: MutableList<String> = mutableListOf()

        val doVerification =
            getStepWithPageName(page.serverKey)?.config?.verification
                ?: false
        /**log price if event is
         * [EventTypes.STEP_FAILED]
         * or [EventTypes.STEP_COMPLETED]
         * and page == [KycPages.ADDRESS]
         * **/
        if (page == KycPages.ADDRESS
            && (event == EventTypes.STEP_FAILED
                    || event == EventTypes.STEP_COMPLETED)
            && doVerification
        ) {
            repo.dojahPricing.data.verificationMap()[page.serverKey]?.verification
                ?.also { price ->
                    services.add(
                        price,
                    )
                }
        }

        /** send Failure code if event == [EventTypes.STEP_FAILED]**/
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

        val request =
            buildStepEventRequest(
                page, event,
                failureCode = failureCode,
                services = services,
            )
        return repo.logEvent(request).apply {
            collect {
                _eventLiveData.postValue(request to it)
            }
        }
    }

    fun logEvent(
        eventType: String,
        eventValue: String,
        services: List<String> = listOf(),
        stepNumber: Int,
    ) {
        viewModelScope.launch {
            val request = buildEventRequest(services, eventType, eventValue, stepNumber)
            repo.logEvent(request)
                .onStart { _eventLiveData.postValue(request to Result.Loading) }
                .collect {
                    _eventLiveData.postValue(request to it)
                }
        }
    }

    fun logCountryEvents(
    ) {
        viewModelScope.launch {
            val stepNumber = getStepWithPageName(KycPages.COUNTRY.serverKey)?.id
                ?: throw Exception("No stepNumber")
            val selectedCountry = selectedCountryLiveData.value
            val request = buildEventRequest(
                listOf(),
                EventTypes.COUNTRY_SELECTED.serverKey,
                selectedCountry?.name ?: "",
                stepNumber
            )
            repo.logEvent(request)
                .onStart { _eventLiveData.postValue(request to Result.Loading) }
                .collect {
                    logger.log("Country else")
                    if (it is Result.Success) {
                        logger.log("Country success")
                        if (selectedCountry?.id?.lowercase() == "ng") {
                            logger.log("Country success ng")
                            logStepEvent(KycPages.COUNTRY, EventTypes.STEP_COMPLETED)
                        } else {
                            logger.log("Country success ng else")
                            logStepEvent(
                                KycPages.COUNTRY,
                                EventTypes.STEP_FAILED,
                                failedReasons = FailedReasons.GOV_DATA_NOT_AVAILABLE
                            )
                        }
                    } else if (it is Result.Error) {
                        logStepEvent(KycPages.COUNTRY, EventTypes.STEP_FAILED, error = it)
                    }
                }
        }
    }


    private fun buildStepEventRequest(
        page: KycPages,
        event: EventTypes,
        failureCode: String? = null,
        services: List<String> = listOf(),
    ): EventRequest {
        val verificationId =
            authDataFromPref?.initData?.authData?.verificationId
                ?: throw Exception("Verification id is null")
        val stepNumber =
            getStepWithPageName(page.serverKey)?.id
                ?: throw Exception("No stepNumber")

        return EventRequest(
            stepNumber = stepNumber,
            eventType = event.serverKey,
            eventValue = failureCode ?: page.serverKey,
            verificationId = verificationId,
            pageKey = page.serverKey,
            services = services
        ).apply {
            logger.log("EventRequest: $this")
        }
    }

    fun buildEventRequest(
        services: List<String> = listOf(),
        eventType: String,
        eventValue: String,
        stepNumber: Int,
    ): EventRequest {
        val verificationId =
            authDataFromPref?.initData?.authData?.verificationId
                ?: throw Exception("Verification id is null")
        return EventRequest(
            stepNumber = stepNumber,
            services = services,
            eventType = eventType,
            eventValue = eventValue,
            verificationId = verificationId
        ).apply {
            logger.log("EventRequest: $this")
        }
    }

    private fun getBodyErrorCode(
        error: Result.Error,
    ): Number? {
        if (error !is Result.Error.ApiError) {
            return null
        }
        logger.log("Verification resume code vm actual: ${error.code}")
        return error.error?.let { actualError ->
            if (actualError.isEmpty()) {
                return null
            }
            val tmpErr = actualError["error"]
            logger.log("Verification resume code vm tmpErr: ${tmpErr}")

            if (tmpErr is Map<*, *>) {
                logger.log("Verification resume code vm tmpErr code: ${tmpErr["code"]}")
                return tmpErr["code"] as Number?
            } else {
                return null
            }
        }
    }

    fun getErrorMessage(
        error: Result.Error,
        page: KycPages? = null,
        verifyType: VerificationType? = null,
        govDataViewModel: GovDataViewModel? = null,
    ): String {
        return when (error) {
            is Result.Error.NetworkError -> "Check your internet connection and try again."
            is Result.Error.TimeoutError -> "Timeout, Check your network and try again later."
            is Result.Error.NoDataError -> "Data not received"
            is Result.Error.ApiError -> {

                return FailedReasons.getStatusCodeReason(error).let { reason ->
                    if (reason == FailedReasons.THIRD_PARTY) {
                        if (page == KycPages.GOVERNMENT_DATA) {
                            return FailedReasons.THIRD_PARTY.getGovBizMsg(idType = govDataViewModel?.selectedGovDataLiveData?.value)
                        } else if (page == KycPages.BUSINESS_DATA) {
                            return FailedReasons.THIRD_PARTY.getGovBizMsg(idType = govDataViewModel?.selectedBizDataLiveData?.value)
                        }
                    } else if (reason == FailedReasons.ID_INVALID_NOT_FOUND) {
                        if (page == KycPages.GOVERNMENT_DATA) {
                            return FailedReasons.ID_INVALID_NOT_FOUND.getGovBizMsg(idType = govDataViewModel?.selectedGovDataLiveData?.value)
                        } else if (page == KycPages.BUSINESS_DATA) {
                            return FailedReasons.ID_INVALID_NOT_FOUND.getGovBizMsg(idType = govDataViewModel?.selectedBizDataLiveData?.value)
                        }
                    }
                    reason?.message
                        ?: (error.error?.let { actualError ->
                            if (actualError.isEmpty()) {
                                return FailedReasons.UNKNOWN.message
                            }
                            val tmpErr = actualError["error"]
                            if (tmpErr is Map<*, *>) {
                                return tmpErr["message"].toString()
                            } else {
                                return tmpErr.toString()
                            }
                        } ?: FailedReasons.UNKNOWN.message)
                }
            }

            else -> FailedReasons.UNKNOWN.message
        }

    }

    /// get full country names
    fun getFullCountryNames(
        context: Context,
        originalCountryList: ArrayList<String>? = (preAuthDataLiveData.value as Result.Success).data.widget?.country
    ): List<String>? {
        val locale = ConfigurationCompat.getLocales(context.resources.configuration)[0]!!

        val fullSupportedCountryList = originalCountryList?.map {
            Locale(
                locale.displayLanguage, it
            ).displayCountry
        }?.toList()
        return fullSupportedCountryList
    }

    fun getDojahAppAttribute(context: Context): App? {
        return repo.getLocalResponse(
            SharedPreferenceManager.KEY_PRE_AUTH_RESPONSE, PreAuthResponse::class.java
        )?.data?.app
    }

    fun getCountriesFullFromPrefs(context: Context): List<String>? {
        return getFullCountryNames(
            context, repo.getLocalResponse(
                SharedPreferenceManager.KEY_PRE_AUTH_RESPONSE, PreAuthResponse::class.java
            )?.data?.widget?.country
        )
    }

    fun getUserCountryFromPrefs(): String? {
        return repo.getLocalResponse(
            SharedPreferenceManager.KEY_CHECK_IP_RESPONSE, CheckIpResponse::class.java
        )?.data?.entity?.country
    }

    fun getPagesFromPrefs(): List<Step>? {
        val steps = authDataFromPref?.initData?.authData?.pages
        _pages.postValue(steps)
        return steps
    }

    val lastStep: Step?
        get() =
            getPagesFromPrefs()?.sortedBy {
                it.id
            }?.lastOrNull()


    fun isLastPage(page: KycPages): Boolean {
        return lastStep?.name == page.serverKey
    }

    val authDataFromPref: AuthResponse?
        get() {
            return repo.getLocalResponse(
                SharedPreferenceManager.KEY_AUTH_RESPONSE, AuthResponse::class.java
            )?.data
        }


    fun getStepWithPageName(currentPage: String): Step? {
        return getPagesFromPrefs()?.find { it.name == currentPage }
    }


    private fun getDocInfo(context: Context, uri: Uri, isUpload: Boolean): DocumentInfo? {
        if (!isUpload) {
            val filePath = uri.path ?: throw Exception("can't get file path from uri")
            val fileName = filePath.substringAfterLast("/").substringBeforeLast(".")
            logger.log("getDocInfo capture: $filePath")
            logger.log("capture name: $fileName")
            return DocumentInfo(
                fileName,
                filePath.substringAfterLast(".")
            )
        }
        context.contentResolver.apply {
            query(uri, null, null, null, null)?.use { cursor ->
                val nameIndex = cursor.getColumnIndex(OpenableColumns.DISPLAY_NAME);
                cursor.moveToFirst()
                cursor.getString(nameIndex)
            }?.let { fileName ->
                logger.log("file name: $fileName")
                return fileName.let { fullName ->
                    DocumentInfo(
                        fullName.substringBeforeLast("."),
                        fullName.substringAfterLast(".")
                    )
                }
            }
        }
        return null
    }

    //    val targetDuration: Duration = Duration.ofMinutes(0).plusSeconds(13)
    val targetDuration: Duration = Duration.ofMinutes(2).plusSeconds(43)

    private val otpTimer = object : CountDownTimer(targetDuration.toMillis(), 1000) {
        override fun onTick(millisUntilFinished: Long) {
            val format = SimpleDateFormat("mm:ss", Locale.ENGLISH)
            _timerOtpLiveData.postValue(format.format(Date(millisUntilFinished)))
        }

        override fun onFinish() {
            _timerOtpDoneLiveData.postValue(true)
            cancel()
        }
    }

    fun startTimer() {
        otpTimer.cancel()
        otpTimer.start()
    }

    fun setSelectedCountry(country: Country) {
        _selectedCountryLiveData.postValue(country)
    }


    fun readMailList(context: Context) {
        _mailLiveData.postValue(
            readRawFile(context, R.raw.disposable) to readRawFile(context, R.raw.free)
        )
    }

    fun isDisposableMail(searchItem: String?): Boolean {
        val mailConfig = getStepWithPageName(KycPages.EMAIL.serverKey)?.config
        if (mailConfig?.disposable != true) {
            return false
        }
        val search = searchItem?.replaceBefore("@", "")?.replace("@", "")

        return if (search?.isNotEmpty() == true) {
            logger.log("searchItem is $search")
            _mailLiveData.value?.first?.any { it.equals(search, ignoreCase = true) } ?: false
        } else {
            logger.log("searchItem is null")
            false
        }
    }

    fun isFreeMail(searchItem: String): Boolean {
        val mailConfig = getStepWithPageName(KycPages.EMAIL.serverKey)?.config
        if (mailConfig?.freeProvider != true) {
            return false
        }
        val search = searchItem.replaceBefore("@", "").replace("@", "")
        return if (search.isNotEmpty()) {
            _mailLiveData.value?.second?.any { it.equals(search, ignoreCase = true) } ?: false
        } else {
            false
        }
    }

}


private fun readRawFile(context: Context, fileInt: Int): List<String>? {
    val searchList = mutableListOf<String>()
    try {
        val inputStream = context.resources.openRawResource(fileInt)
        val reader = BufferedReader(InputStreamReader(inputStream))

        var line: String?
        while (reader.readLine().also { line = it } != null) {
            if (line.isNullOrBlank()) continue
            searchList.add(line!!) // Add each line to the searchList
        }

        reader.close()
        inputStream.close()
        return searchList
    } catch (e: IOException) {
        e.printStackTrace()
        return null
    }
}

fun getFailureCode(
    error: Result.Error?,
    page: KycPages,
    failedReasons: FailedReasons? = null
): String? {
    if (error is Result.Error.NetworkError || error is Result.Error.TimeoutError) {
        return null
    } else if (error != null && error is Result.Error.ApiError) {
        val statusCodeReason = FailedReasons.getStatusCodeReason(error)
        if (statusCodeReason != null) {
            return if (statusCodeReason == FailedReasons.THIRD_PARTY
                && (page == KycPages.GOVERNMENT_DATA || page == KycPages.BUSINESS_DATA)
            ) {
                FailedReasons.THIRD_PARTY.code
            } else {
                statusCodeReason.code
            }
        }
    }
    return failedReasons?.code ?: FailedReasons.UNKNOWN.code
}


enum class DecisionStatus {
    approved, failed, pending,
}
