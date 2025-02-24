package com.dojah.kyc_sdk_kotlin.core.di

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.dojah.kyc_sdk_kotlin.BuildConfig
import com.dojah.kyc_sdk_kotlin.core.Constants
import com.dojah.kyc_sdk_kotlin.data.LocationManager
import com.dojah.kyc_sdk_kotlin.data.io.CountryManager
import com.dojah.kyc_sdk_kotlin.data.io.FileManager
import com.dojah.kyc_sdk_kotlin.data.io.SharedPreferenceManager
import com.dojah.kyc_sdk_kotlin.data.network.NetworkManager
import com.dojah.kyc_sdk_kotlin.data.network.interceptor.HeaderInterceptor
import com.dojah.kyc_sdk_kotlin.data.network.service.DojahService
import com.dojah.kyc_sdk_kotlin.data.repository.DojahRepository
import com.dojah.kyc_sdk_kotlin.ui.base.NavigationViewModel
import com.dojah.kyc_sdk_kotlin.ui.main.viewmodel.GovDataViewModel
import com.dojah.kyc_sdk_kotlin.ui.main.viewmodel.VerificationViewModel
import com.google.gson.Gson
import com.google.i18n.phonenumbers.PhoneNumberUtil
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

// Container of objects shared across the whole app
class DojahContainer(
    val context: Context,
) {
    private val networkManager = NetworkManager(context)

    private val gson = Gson()

    val sharedPreferenceManager = SharedPreferenceManager(context)

    private val phoneUtil = PhoneNumberUtil.getInstance()

    val countryManager = CountryManager(context, phoneUtil)

    val locationManager = LocationManager(context, sharedPreferenceManager)

    val fileManager = FileManager(context)

    val client: OkHttpClient =
        OkHttpClient.Builder().run {
            connectTimeout(Constants.CONNECT_TIMEOUT, TimeUnit.SECONDS)
            writeTimeout(Constants.WRITE_TIMEOUT, TimeUnit.SECONDS)
            readTimeout(Constants.READ_TIMEOUT, TimeUnit.SECONDS)
//            addInterceptor(VersionCodeInterceptor(BuildConfig.VERSION_CODE, gson))
            addInterceptor(HeaderInterceptor(sharedPreferenceManager))

            cache(null)

            if (BuildConfig.DEBUG) addInterceptor(HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            })

            build()
        }
    private val retrofit = Retrofit.Builder()
        .baseUrl(BuildConfig.BASE_URL)
        .client(client)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val dojahService = retrofit.create(DojahService::class.java)

    // userRepository is not private; it'll be exposed
    val userRepository =
        DojahRepository(networkManager, gson, sharedPreferenceManager, dojahService)

    val navViewModelFactory = object : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return NavigationViewModel(sharedPreferenceManager, userRepository) as T
        }
    }

    val verificationViewModelFactory = object : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return VerificationViewModel(
                sharedPreferenceManager,
                userRepository,
                countryManager
            ) as T
        }
    }

    val govViewModelFactory = object : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return GovDataViewModel(sharedPreferenceManager, userRepository) as T
        }
    }
}