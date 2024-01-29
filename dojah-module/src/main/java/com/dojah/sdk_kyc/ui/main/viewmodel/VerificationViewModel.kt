package com.dojah.sdk_kyc.ui.main.viewmodel

import android.annotation.SuppressLint
import android.content.Context
import android.net.Uri
import android.os.Build
import android.os.CountDownTimer
import androidx.core.os.ConfigurationCompat
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dojah.sdk_kyc.BuildConfig
import com.dojah.sdk_kyc.R
import com.dojah.sdk_kyc.core.Result
import com.dojah.sdk_kyc.data.io.CountryManager
import com.dojah.sdk_kyc.data.io.SharedPreferenceManager
import com.dojah.sdk_kyc.data.repository.DojahRepository
import com.dojah.sdk_kyc.domain.Country
import com.dojah.sdk_kyc.domain.request.CheckIpRequest
import com.dojah.sdk_kyc.domain.request.EventRequest
import com.dojah.sdk_kyc.domain.responses.*
import com.dojah.sdk_kyc.ui.utils.*
import dagger.Lazy
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import okhttp3.logging.HttpLoggingInterceptor
import java.text.SimpleDateFormat
import java.time.Duration
import java.util.Date
import java.util.Locale
import javax.inject.Inject

@SuppressLint("StaticFieldLeak")
@HiltViewModel
class VerificationViewModel @Inject constructor(
    private val prefManager: SharedPreferenceManager,
    private val repo: DojahRepository,
    private val countryManager: Lazy<CountryManager>
) : ViewModel() {
    private val logger: HttpLoggingInterceptor.Logger = HttpLoggingInterceptor.Logger.DEFAULT
    private val _countryLiveData = MutableLiveData<List<Country>>()
    private val _frontDocUriLiveData = MutableLiveData<Uri>()
    private val _backDocUriLiveData = MutableLiveData<Uri>()
    private val _isBackDocLiveData = MutableLiveData<Boolean>(false)
    private val _docTypeLiveData = MutableLiveData<GovDocType?>()
    private val _verificationTypeLiveData = MutableLiveData<VerificationType?>()
    private val _selfiePhotoUriLiveData = MutableLiveData<Uri>()
    private val _timerOtpLiveData = MutableLiveData<String>()
    private val _timerOtpDoneLiveData = MutableLiveData<Boolean>(false)
    private val _preAuthDataLiveData = MutableLiveData<Result<PreAuthResponse>>()
    private val _authDataLiveData = MutableLiveData<Result<AuthResponse>>()
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

    val checkIpDataLiveData: LiveData<Result<CheckIpResponse>>
        get() = _checkIpDataLiveData

    val getIpDataLiveData: LiveData<Result<GetIpResponse>>
        get() = _getIpDataLiveData

    val authErrLiveData: LiveData<String>
        get() = _authErrLiveData

    val countryLiveData: LiveData<List<Country>>
        get() = _countryLiveData

    private val _eventLiveData = MutableLiveData<Pair<EventRequest, Result<SimpleResponse>>>()
    val eventLiveData: LiveData<Pair<EventRequest, Result<SimpleResponse>>>
        get() = _eventLiveData
    val frontDocUriLiveData: LiveData<Uri>
        get() = _frontDocUriLiveData
    val backDocUriLiveData: LiveData<Uri>
        get() = _backDocUriLiveData

    val isBackDocLiveData: LiveData<Boolean>
        get() = _isBackDocLiveData

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


    val dojahEnum
        get(): DojahEnum {
            return repo.getDojahEnum.data
        }

    private fun saveBrandColor(color: String?) {
        logger.log("Brand color: $color")
        prefManager.setMaterialButtonBgColor(color)
    }

    fun setFrontDocUri(uri: Uri) {
        _isBackDocLiveData.postValue(false)
        _frontDocUriLiveData.postValue(uri)
    }

    fun setSelfieUri(uri: Uri) {
        _selfiePhotoUriLiveData.postValue(uri)
    }

    fun setBackDocUri(uri: Uri) {
        _isBackDocLiveData.postValue(true)
        _backDocUriLiveData.postValue(uri)
    }

    fun selectDocType(type: String) {
        _docTypeLiveData.postValue(GovDocType.enumOfValue(type))
    }

    fun getCountries() {
        countryManager.get().addCallback {
            _countryLiveData.postValue(it)
        }
    }

    fun getGIDs(context: Context): List<String> {
        return context.resources.getStringArray(R.array.gov_ids).toList()
    }

    fun getDocType(): List<String> {
        return GovDocType.values().map { it.sName }
    }


    fun getBussinessTypes(): List<String> {
        return BusinessDataType.values().map { it.value }
    }

    fun selectGovIdentity(id: EnumAttr?) {
        _selectedGovIdDataLiveData.postValue(id)
    }

    fun authenticate(widgetId: String) {
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
                        repo.doAuth(preAuthResult.data.toAuthRequest()).collect { authResult ->
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
                                _authErrLiveData.postValue(getErrorMessage(authResult))
                            }
                        }
                    } else if (preAuthResult is Result.Error) {
                        _authErrLiveData.postValue(getErrorMessage(preAuthResult))
                    }
                }
        }
    }

    fun buildEventRequest(
        services: List<String>,
        eventType: String,
        eventValue: String,
        stepNumber: Int,
    ): EventRequest {
        val authData = repo.getLocalResponse(
            SharedPreferenceManager.KEY_AUTH_RESPONSE, AuthResponse::class.java
        )?.data
        val verificationId =
            authData?.initData?.authData?.verificationId
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

    fun getErrorMessage(
        error: Result.Error,
    ): String {
        return when (error) {
            is Result.Error.NetworkError -> "Check your internet connection and try again."
            is Result.Error.TimeoutError -> "Timeout, Check your network and try again later."
            is Result.Error.NoDataError -> "Data not received"
            else -> {
                (error as Result.Error.ApiError).error?.let {
                    if(it.isEmpty()){
                        return "Data not received"
                    }
                    val tmpErr = it["error"]
                    if (tmpErr is Map<*, *>) {
                        tmpErr["message"].toString()
                    }
                    tmpErr.toString()
                } ?: if (BuildConfig.DEBUG) "Something went wrong" else "Unknown error"
            }
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
        val steps = repo.getLocalResponse(
            SharedPreferenceManager.KEY_AUTH_RESPONSE, AuthResponse::class.java
        )?.data?.initData?.authData?.steps
        _pages.postValue(steps)
        return steps
    }

    fun getCurrentPage(currentPage: String): Step? {
        return getPagesFromPrefs()?.find { it.name == currentPage }
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

}
