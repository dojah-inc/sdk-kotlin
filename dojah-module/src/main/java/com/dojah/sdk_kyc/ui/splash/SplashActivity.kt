package com.dojah.sdk_kyc.ui.splash

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.dojah.sdk_kyc.R
import com.dojah.sdk_kyc.core.Result
import com.dojah.sdk_kyc.data.LocationManager
import com.dojah.sdk_kyc.data.io.CountryManager
import com.dojah.sdk_kyc.data.io.FileManager
import com.dojah.sdk_kyc.ui.main.MainActivity
import com.dojah.sdk_kyc.ui.main.viewmodel.VerificationViewModel
import dagger.hilt.android.AndroidEntryPoint
import okhttp3.logging.HttpLoggingInterceptor
import timber.log.Timber
import java.util.concurrent.atomic.AtomicReference
import javax.inject.Inject

const val COUNTRY_ERROR = "country_error"

private const val DEFAULT_ERROR = "default_error"

@SuppressLint("CustomSplashScreen")
@AndroidEntryPoint
class SplashActivity : AppCompatActivity() {

    @Inject
    lateinit var assetRewrite: FileManager

    @Inject
    lateinit var countryManager: CountryManager //This is only here to init the class and fetch the countries

    @Inject
    lateinit var locationManager: LocationManager

    private lateinit var permissionLauncher: ActivityResultLauncher<String>

    private val viewModel by viewModels<VerificationViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.splash_loading)
//        permissionLauncher = registerForActivityResult(ActivityResultContracts.RequestPermission()) {
//            locationManager.hasPermission = it
//            if (it) {
//                locationManager.startLocationUpdates()
////                onBackPressed()
////                startActivity(Intent(this, MainActivity::class.java).apply {
////                    putExtra("sandbox", booleanExtra)
////                    Timber.d("sandbox extra is : $booleanExtra")
////                })
//            }
//            else finish()
//        }

        //First is splashscreen and second is the asset rewrite
        val atomic = AtomicReference(
            Triple<Boolean, Boolean, Pair<String, String>?>(
                first = false, second = false, third = null
            )
        )

        //observe authentication progress

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
                        COUNTRY_ERROR to getString(R.string.verification_is_not_allowed_in_your_country)
                    if (atomic.get().second) nextScreen(
                        errorType = error.first,
                        message = error.second
                    )
                    else {
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

        //ours sandbox
//        viewModel.authenticate("6568cae99806dc0040cb372b")
        viewModel.authenticate("64e46e763a47c3003ff04266")

    }

    private fun nextScreen(errorType: String? = null, message: String? = null) {
        val booleanExtra = intent.getBooleanExtra("sandbox", false)
        onBackPressedDispatcher.onBackPressed()
        startActivity(Intent(this, MainActivity::class.java).apply {
            val serverEnvironment =
                (viewModel.preAuthDataLiveData.value as Result.Success).data.widget?.env
            HttpLoggingInterceptor.Logger.DEFAULT.log("sandbox extra is : ${serverEnvironment?.lowercase() == "sandbox"}")
            putExtra("sandbox", serverEnvironment?.lowercase() == "sandbox")
            if (errorType != null) {
                putExtra("error", errorType)
            }
            if (message != null) {
                putExtra("message", message)
            }
        })
    }
}