package com.dojah.kyc_sdk_kotlin.ui.splash

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.dojah.kyc_sdk_kotlin.DojahSdk
import com.dojah.kyc_sdk_kotlin.R
import com.dojah.kyc_sdk_kotlin.core.Result
import com.dojah.kyc_sdk_kotlin.data.io.CountryManager
import com.dojah.kyc_sdk_kotlin.data.io.FileManager
import com.dojah.kyc_sdk_kotlin.ui.main.DojahMainActivity
import com.dojah.kyc_sdk_kotlin.ui.main.viewmodel.VerificationViewModel
import com.dojah.kyc_sdk_kotlin.ui.utils.FailedReasons
import okhttp3.logging.HttpLoggingInterceptor
import java.util.concurrent.atomic.AtomicReference

const val COUNTRY_ERROR = "country_error"

const val VERIFICATION_COMPLETE_ERROR = "verification_complete_error"

private const val DEFAULT_ERROR = "default_error"

@SuppressLint("CustomSplashScreen")
class SplashActivity : AppCompatActivity() {

    val assetRewrite: FileManager by lazy {
        DojahSdk.dojahContainer.fileManager
    }

    val countryManager: CountryManager by lazy {
        DojahSdk.dojahContainer.countryManager
    }

//    val locationManager: LocationManager by lazy {
//        DojahSdk.dojahContainer.locationManager
//    }

//    private lateinit var permissionLauncher: ActivityResultLauncher<String>

    private val viewModel by viewModels<VerificationViewModel>{
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
        viewModel.prefManager.setWidgetId(widgetId)
        viewModel.authenticate(widgetId, referenceId = referenceId, email = email)
//        viewModel.authenticate("6568cae99806dc0040cb372b")
        //safe
//        viewModel.authenticate("64e46e763a47c3003ff04266")

    }

    private fun nextScreen(errorType: String? = null, message: String? = null) {
        onBackPressedDispatcher.onBackPressed()
        startActivity(Intent(this, DojahMainActivity::class.java).apply {
            val preAuthResponseResult = viewModel.preAuthDataLiveData.value
            if (preAuthResponseResult is Result.Success) {
                val serverEnvironment =
                    preAuthResponseResult.data.widget?.env
                HttpLoggingInterceptor.Logger.DEFAULT.log("sandbox extra is : ${serverEnvironment?.lowercase() == "sandbox"}")
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
    }
}