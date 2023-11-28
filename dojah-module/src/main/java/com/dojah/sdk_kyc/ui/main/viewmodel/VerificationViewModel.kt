package com.dojah.sdk_kyc.ui.main.viewmodel

import android.annotation.SuppressLint
import android.content.Context
import android.net.Uri
import android.os.CountDownTimer
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dojah.sdk_kyc.R
import com.dojah.sdk_kyc.data.io.CountryManager
import com.dojah.sdk_kyc.data.io.SharedPreferenceManager
import com.dojah.sdk_kyc.data.repository.SampleRepository
import com.dojah.sdk_kyc.domain.Country
import com.dojah.sdk_kyc.domain.responses.*
import dagger.Lazy
import dagger.hilt.android.lifecycle.HiltViewModel
import java.text.SimpleDateFormat
import java.time.Duration
import java.util.Date
import java.util.Locale
import javax.inject.Inject

@SuppressLint("StaticFieldLeak")
@HiltViewModel
class VerificationViewModel @Inject constructor(
    private val prefManager: SharedPreferenceManager,
    private val repo: SampleRepository,
    private val countryManager: Lazy<CountryManager>
) : ViewModel() {

    private val _countryLiveData = MutableLiveData<List<Country>>()
    private val _frontDocUriLiveData = MutableLiveData<Uri>()
    private val _backDocUriLiveData = MutableLiveData<Uri>()
    private val _isBackDocLiveData = MutableLiveData<Boolean>(false)
    private val _docTypeLiveData = MutableLiveData<GovDocType?>()
    private val _verificationTypeLiveData = MutableLiveData<VerificationType?>()
    private val _selfiePhotoUriLiveData = MutableLiveData<Uri>()
    private val _timerOtpLiveData = MutableLiveData<String>()
    private val _timerOtpDoneLiveData = MutableLiveData<Boolean>(false)

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
    RC_NUMBER("RC Number"),
    TIN("TIN Number");

    companion object {
        fun enumOfValue(value: String): VerificationType? {
            return VerificationType.values().find { it.value == value }
        }
    }
}

enum class VerificationType(val value: String, val title: String = "", val preview: String = "") {
    Selfie("Selfie", "Place your face in the circle and click Capture", "Preview your Selfie"),
    Video("Video KYC", "Place your face in the circle and click Record", "Preview your Video"),
    PHONE_OTP("Phone Number OTP", "phone number"),
    EMAIL_OTP("Email OTP", "email"),
    HOME_ADDRESS("Home Address");

    companion object {
        fun enumOfValue(value: String): VerificationType? {
            return VerificationType.values().find { it.value == value }
        }
    }
}

enum class GovDocType(val value: String, val title: String = "", val info: String = "") {
    BUSINESS(
        "CAC",
        "Capture the CAC Document",
        "Make sure your CAC Document is properly placed, and hold it still for a few seconds"
    ),
    NIN(
        "NIN",
        "Capture the NIN Document",
        "Make sure your NIN Document is properly placed, and hold it still for a few seconds"
    ),
    DRIVER_LICENCE("Driver’s License"),
    OTHER(
        "Other Doc",
        "Upload Document",
        "Make sure your Document is properly placed, and hold it still for a few seconds"
    );

    companion object {
        fun enumOfValue(value: String): GovDocType? {
            return values().find { it.value == value }
        }
    }

}