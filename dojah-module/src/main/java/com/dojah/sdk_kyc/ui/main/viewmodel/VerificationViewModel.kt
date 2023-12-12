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
import com.dojah.sdk_kyc.domain.responses.*
import com.dojah.sdk_kyc.ui.main.fragment.datacollection.BioDataFragment
import com.dojah.sdk_kyc.ui.main.fragment.datacollection.BizDocTypeFragment
import com.dojah.sdk_kyc.ui.main.fragment.datacollection.BusinessDataFragment
import com.dojah.sdk_kyc.ui.main.fragment.datacollection.CaptureDocumentFragment
import com.dojah.sdk_kyc.ui.main.fragment.datacollection.CountryFragment
import com.dojah.sdk_kyc.ui.main.fragment.datacollection.DisclaimerFragment
import com.dojah.sdk_kyc.ui.main.fragment.datacollection.DocTypeFragment
import com.dojah.sdk_kyc.ui.main.fragment.datacollection.EmailOtpFragment
import com.dojah.sdk_kyc.ui.main.fragment.datacollection.EnterOtpFragment
import com.dojah.sdk_kyc.ui.main.fragment.datacollection.GovDataFragment
import com.dojah.sdk_kyc.ui.main.fragment.datacollection.HomeAddressFragment
import com.dojah.sdk_kyc.ui.main.fragment.datacollection.PhoneOtpFragment
import com.dojah.sdk_kyc.ui.main.fragment.datacollection.SelfieDisclaimerFragment
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

    fun selectVerificationType(type: String) {
        _verificationTypeLiveData.postValue(VerificationType.enumOfValue(type))
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
        return GovDocType.values().map { it.value }
    }

    fun getVerifyMethods(): List<String> {
        return VerificationType.values().map { it.value }
    }

    fun getBussinessTypes(): List<String> {
        return BusinessDataType.values().map { it.value }
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
                        //do Auth
//                        logger.log("print result")
//                        logger.log(preAuthResult.data.toAuthRequest().toString())
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

    fun getErrorMessage(
        error: Result.Error,
    ): String {
        return when (error) {
            is Result.Error.NetworkError -> "Check your internet connection and try again."
            is Result.Error.TimeoutError -> "Timeout, Check your network and try again later."
            is Result.Error.NoDataError -> "Data not received"
            else -> {
                (error as Result.Error.ApiError).error?.let {
                    //extract the message
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

    val targetDuration: Duration = Duration.ofMinutes(2).plusSeconds(43)

    fun startTimer() {
        val timer = object : CountDownTimer(targetDuration.toMillis(), 1000) {
            override fun onTick(millisUntilFinished: Long) {
                val format = SimpleDateFormat("mm:ss", Locale.ENGLISH)
                _timerOtpLiveData.postValue(format.format(Date(millisUntilFinished)))
//                _timerOtpLiveData.postValue("$min:$secs")
            }

            override fun onFinish() {
                _timerOtpDoneLiveData.postValue(true)
            }
        }
        timer.cancel()
        timer.start()
    }

}


enum class BusinessDataType(val value: String) {
    RC_NUMBER("RC Number"), TIN("TIN Number");

    companion object {
        fun enumOfValue(value: String): VerificationType? {
            return VerificationType.values().find { it.value == value }
        }
    }
}

enum class VerificationType(
    val value: String,
    val serverKey: String,
    val details: String? = "",
    val title: String = "",
    val preview: String = ""
) {
    Selfie(
        "Selfie", "Place your face in the circle and click Capture", "Preview your Selfie"
    ),
    Video(
        "Video KYC",
        "selfie",
        "Place your face in the circle and click Record",
        "Preview your Video"
    ),
    OTP("OTP", "otp"), PHONE_OTP("Phone Number OTP", "phone number"), EMAIL_OTP(
        "Email OTP", "email"
    ),
    HOME_ADDRESS("Home Address", "home address");

    companion object {
        fun enumOfValue(value: String): VerificationType? {
            return VerificationType.values().find { it.value == value }
        }

        fun findEnumWithKey(serverKey: String?): VerificationType? {
            return VerificationType.values().find { it.serverKey == serverKey }
        }
    }
}

enum class GovDocType(
    val value: String, val serverKey: String, val title: String = "", val info: String = ""
) {

    DRIVER_LICENCE("Driver’s License", "dl",
    "Capture the Driver’s License Document",
    "Make sure your Driver’s License Document is properly placed, and hold it still for a few seconds"
    ),
    BVN(
        "BVN",
        "bvn",
        "Capture the CAC Document",
        "Make sure your CAC Document is properly placed, and hold it still for a few seconds"
    ),
    VOTER(
        "Voter’s Card",
        "voter",
        "Capture the Voter’s Card Document",
        "Make sure your Voter’s Card Document is properly placed, and hold it still for a few seconds"
    ),
    PASSPORT(
        "National Passport",
        "passport",
        "Capture your National Passport",
        "Make sure your National Passpor Document is properly placed, and hold it still for a few seconds"
    ),
    NIN(
        "NIN",
        "nin",
        "Capture the NIN Document",
        "Make sure your NIN Document is properly placed, and hold it still for a few seconds"
    ),
    NATIONAL(
        "National ID",
        "national",
        "Capture the National ID Document",
        "Make sure your National ID Document is properly placed, and hold it still for a few seconds"
    ),
    VNIN(
        "VNIN",
        "vnin",
        "Capture the VNIN Document",
        "Make sure your VNIN Document is properly placed, and hold it still for a few seconds"
    );
//    BUSINESS(
//        "CAC",
//        "cac",
//        "Capture the CAC Document",
//        "Make sure your CAC Document is properly placed, and hold it still for a few seconds"
//    ),
//    OTHER(
//        "Other Doc",
//        "Upload Document",
//        "Make sure your Document is properly placed, and hold it still for a few seconds"
//    );

    companion object {
        fun enumOfValue(value: String): GovDocType? {
            return values().find { it.value == value }
        }
        fun findEnumWithKey(serverKey: String?): GovDocType? {
            return values().find { it.serverKey == serverKey }
        }
    }

}

enum class KycPages(
    val serverKey: String,
    val fragmentClassName: String,
    val optionpages: List<Pair<String, String>>? = null
) {
    INDEX(
        "index", DisclaimerFragment::class.java.name
    ),
    COUNTRY(
        "countries",
        CountryFragment::class.java.name,
    ),
    USER_DATA(
        "user-data",
        BioDataFragment::class.java.name,
    ),
    GOVERNMENT_DATA(
        "government-data",
        GovDataFragment::class.java.name,
    ),
    GOVERNMENT_DATA_VERIFICATION(
        "government-data-verification", "", listOf(
            VerificationType.Video.serverKey to SelfieDisclaimerFragment::class.java.name,
            VerificationType.OTP.serverKey to EnterOtpFragment::class.java.name,
        )
    ),
    ID_OPTION(
        "id-options",
        DocTypeFragment::class.java.name,
    ),
    ID(
        "id",
        DisclaimerFragment::class.java.name,
    ),
    BUSINESS_DATA(
        "business-data",
        BusinessDataFragment::class.java.name,
    ),
    PHONE_NUMBER(
        "phone-number",
        PhoneOtpFragment::class.java.name,
    ),
    EMAIL(
        "email",
        EmailOtpFragment::class.java.name,
    ),
    BUSINESS_ID(
        "business-id",
        BizDocTypeFragment::class.java.name,
    ),
    ADDRESS(
        "address",
        HomeAddressFragment::class.java.name,
    ),
    SELFIE(
        "selfie",
        SelfieDisclaimerFragment::class.java.name,
    ),
    OTHER_DOCUMENT(
        "additional-document",
        CaptureDocumentFragment::class.java.name,
    );

    companion object {
        fun findPageEnum(serverKey: String): KycPages? {
            return values().find { it.serverKey == serverKey }
        }
    }
}