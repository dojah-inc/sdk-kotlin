package com.dojah.kyc_sdk_kotlin.ui.splash

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.result.ActivityResult
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import com.dojah.kyc_sdk_kotlin.DOJAH_APPROVED_RESULT
import com.dojah.kyc_sdk_kotlin.DOJAH_FAILED_RESULT
import com.dojah.kyc_sdk_kotlin.DOJAH_PENDING_RESULT
import com.dojah.kyc_sdk_kotlin.DOJAH_RESULT_KEY
import com.dojah.kyc_sdk_kotlin.DojahSdk
import com.dojah.kyc_sdk_kotlin.R
import com.dojah.kyc_sdk_kotlin.core.Result
import com.dojah.kyc_sdk_kotlin.data.io.CountryManager
import com.dojah.kyc_sdk_kotlin.data.io.FileManager
import com.dojah.kyc_sdk_kotlin.databinding.SplashLoadingBinding
import com.dojah.kyc_sdk_kotlin.databinding.SuccessViewBinding
import com.dojah.kyc_sdk_kotlin.domain.ExtraUserData
import com.dojah.kyc_sdk_kotlin.ui.main.DojahMainActivity
import com.dojah.kyc_sdk_kotlin.ui.main.viewmodel.VerificationViewModel
import com.dojah.kyc_sdk_kotlin.ui.utils.FailedReasons
import com.dojah.kyc_sdk_kotlin.ui.utils.delegates.viewBinding
import okhttp3.logging.HttpLoggingInterceptor
import java.util.concurrent.atomic.AtomicReference

const val COUNTRY_ERROR = "country_error"

const val VERIFICATION_COMPLETE_ERROR = "verification_complete_error"

private const val DEFAULT_ERROR = "default_error"

@SuppressLint("CustomSplashScreen")
class SplashActivity : AppCompatActivity() {


    private val dojahResultLauncher: ActivityResultLauncher<Intent> =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { activityResult: ActivityResult ->
            if (activityResult.resultCode == RESULT_OK) {
                HttpLoggingInterceptor.Logger.DEFAULT.log("Got Result: ${activityResult.data}")
                setResult(RESULT_OK, activityResult.data)
                finish()
            }
        }


    private val assetRewrite: FileManager by lazy {
        DojahSdk.dojahContainer.fileManager
    }

    private val countryManager: CountryManager by lazy {
        DojahSdk.dojahContainer.countryManager
    }

    private val viewModel by viewModels<VerificationViewModel> {
        DojahSdk.dojahContainer.verificationViewModelFactory
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.splash_loading)

        //First is splashscreen and second is the asset rewrite
        val atomic = AtomicReference(
            Triple<Boolean, Boolean, Pair<String, String>?>(
                first = false, second = false, third = null
            )
        )

        //observe authentication progress

        viewModel.authVerificationCompletedLD.observe(this) {
            if (it == false) {
                return@observe
            } else {
                //if verification has already been completed, show success message
                val error =
                    VERIFICATION_COMPLETE_ERROR to getString(R.string.error_verification_success)
                if (atomic.get().second) nextScreen(
                    errorType = error.first,
                    message = error.second
                ) else {
                    atomic.set(
                        Triple(
                            first = true, second = false, third = error
                        )
                    )
                }
                viewModel.resetAuthVerificationCompleted()
            }
        }

        viewModel.authErrLiveData.observe(this) {
            val error = DEFAULT_ERROR to it
            if (atomic.get().second) nextScreen(errorType = error.first, message = error.second)
            else {
                atomic.set(Triple(first = true, second = false, third = error))
            }
        }

        viewModel.checkIpDataLiveData.observe(this) {
            val preAuthResult = viewModel.preAuthDataLiveData.value
            if (it is Result.Success && preAuthResult is Result.Success) {

                val fullSupportedCountryNames =
                    viewModel.getFullCountryNames(this)
                val userCountry = it.data.entity?.country ?: ""
                // if user country is among the supported country
                if (fullSupportedCountryNames?.contains(userCountry) == true) {
//                    Toast.makeText(this, "$userFullCountry is supported", Toast.LENGTH_LONG).show()
                    if (atomic.get().second) nextScreen()
                    else atomic.set(Triple(first = true, second = false, third = null))
                } else {
//                    Toast.makeText(this, "$userFullCountry is not supported", Toast.LENGTH_LONG)
//                        .show()
                    val error =
                        COUNTRY_ERROR to FailedReasons.GOV_DATA_NOT_AVAILABLE.message
                    if (atomic.get().second) nextScreen(
                        errorType = error.first,
                        message = error.second
                    ) else {
                        atomic.set(
                            Triple(
                                first = true, second = false, third = error
                            )
                        )
                    }
                }
            }
        }

        assetRewrite.copyAssets {

            countryManager.start(lifecycleScope)

            assetRewrite.close()

            if (atomic.get().first) nextScreen(
                errorType = atomic.get().third?.first,
                message = atomic.get().third?.second
            )
            else atomic.set(Triple(first = false, second = true, third = null))
        }

        val widgetId = intent.getStringExtra("widget_id") ?: throw Exception("Empty Widget ID")
        val referenceId = intent.getStringExtra("reference_id")
        val email = intent.getStringExtra("email")
        //check Build. VERSION_CODES. TIRAMISU
        val extraData =
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.TIRAMISU) {
                intent.getSerializableExtra("extra_data", ExtraUserData::class.java)
            } else {
                intent.getSerializableExtra("extra_data") as? ExtraUserData?
            }
        HttpLoggingInterceptor.Logger.DEFAULT.log(
            "Splash extraData passed is: $extraData"
        )

        viewModel.prefManager.setWidgetId(widgetId)
        viewModel.authenticate(
            widgetId,
            referenceId = referenceId,
            email = email,
            extraUserData = extraData
        )

    }

    private fun nextScreen(errorType: String? = null, message: String? = null) {
        dojahResultLauncher.launch(Intent(this, DojahMainActivity::class.java).apply {
            val preAuthResponseResult = viewModel.preAuthDataLiveData.value
            if (preAuthResponseResult is Result.Success) {
                val serverEnvironment =
                    preAuthResponseResult.data.widget?.env
//                HttpLoggingInterceptor.Logger.DEFAULT.log("sandbox extra is : ${serverEnvironment?.lowercase() == "sandbox"}")
                putExtra("sandbox", serverEnvironment?.lowercase() == "sandbox")
                if (errorType != null) {
                    putExtra("error", errorType)
                }
                if (message != null) {
                    putExtra("message", message)
                }
            } else {
                putExtra("error", errorType)
                if (message != null) {
                    putExtra("message", message)
                }
            }
        })
        findViewById<View>(R.id.progress_indicator).isVisible = false
    }
}