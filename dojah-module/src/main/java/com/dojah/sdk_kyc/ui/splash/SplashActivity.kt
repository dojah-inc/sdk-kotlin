package com.dojah.sdk_kyc.ui.splash

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.dojah.sdk_kyc.R
import com.dojah.sdk_kyc.data.LocationManager
import com.dojah.sdk_kyc.data.io.CountryManager
import com.dojah.sdk_kyc.data.io.FileManager
import com.dojah.sdk_kyc.ui.main.MainActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import timber.log.Timber
import java.util.concurrent.atomic.AtomicReference
import javax.inject.Inject

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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.splash_loading)
        permissionLauncher = registerForActivityResult(ActivityResultContracts.RequestPermission()) {
            locationManager.hasPermission = it
            if (it) {
                locationManager.startLocationUpdates()
//                onBackPressed()
//                startActivity(Intent(this, MainActivity::class.java).apply {
//                    putExtra("sandbox", booleanExtra)
//                    Timber.d("sandbox extra is : $booleanExtra")
//                })
            }
            else finish()
        }

        //First is splashscreen and second is the asset rewrite
        val atomic = AtomicReference(Pair(first = false, second = false))

        assetRewrite.copyAssets {

            countryManager.start(lifecycleScope)

            assetRewrite.close()

            if (atomic.get().first) nextScreen()
            else atomic.set(Pair(first = false, second = true))
        }

        lifecycleScope.launch {
            delay(3000)

            if (atomic.get().second) nextScreen()
            else atomic.set(Pair(first = true, second = false))
        }

    }

    private fun nextScreen() {
        val booleanExtra = intent.getBooleanExtra("sandbox", false)
        onBackPressed()
        startActivity(Intent(this, MainActivity::class.java).apply {
            putExtra("sandbox", booleanExtra)
            Timber.d("sandbox extra is : $booleanExtra")
        })
//        permissionLauncher.launch(android.Manifest.permission.ACCESS_FINE_LOCATION)
    }
}