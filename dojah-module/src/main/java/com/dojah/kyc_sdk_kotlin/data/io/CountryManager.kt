package com.dojah.kyc_sdk_kotlin.data.io

import android.content.Context
import androidx.core.os.ConfigurationCompat
import com.google.i18n.phonenumbers.PhoneNumberUtil
import com.dojah.kyc_sdk_kotlin.data.io.FileManager.Companion.getAssetDirectory
import com.dojah.kyc_sdk_kotlin.domain.Country
import kotlinx.coroutines.*
import okhttp3.logging.HttpLoggingInterceptor
import java.io.File
import java.util.*

typealias CountryCallback = (List<Country>) -> Unit


class CountryManager (
        private val context: Context,
        private val phoneNumberUtil: PhoneNumberUtil) {

    private val listeners = mutableListOf<CountryCallback>()

    private var countries: List<Country>? = null
        set(value) {
            field = value
            listeners.forEach {
                it.invoke(value ?: emptyList())
            }
            listeners.clear()
        }

    fun start(scope: CoroutineScope) {
        scope.launch(Dispatchers.IO) {
            withContext(Dispatchers.Default) {
                getCountries()
            }.apply { countries = this }
        }
    }

    private suspend fun getCountries(): List<Country> {
        return withContext(Dispatchers.Default) {

            if (countries != null) countries!!
            else {
                val locale = ConfigurationCompat.getLocales(context.resources.configuration)[0]!!
                val baseDir = File(context.getAssetDirectory(), FileManager.COUNTRIES_DIR)

                val countryFiles = baseDir.list()?.toMutableList()
                        ?: mutableListOf()

                phoneNumberUtil.run {
                    val fetchedCountries = supportedRegions.map {
                        async {
                            val phoneCode = "+" + getCountryCodeForRegion(it)
                            val name = Locale(locale.displayLanguage, it).displayCountry
                            val path = getCountryImagePath(it, countryFiles)

                            Country(it, name, phoneCode, "$baseDir/$path")
                        }
                    }.awaitAll()

                    fetchedCountries.sortedBy { it.name }
                }
            }
        }
    }

    /**
     * Add a callback which will be called when countries has been fetched
     * it calls the function immediately if the countries are already available
     * it also auto clears the callback after it has been called
     */
    fun addCallback(callback: CountryCallback) {
        countries?.let { callback(it) } ?: listeners.add(callback)
    }

    private fun getCountryImagePath(id: String, list: List<String>): String {
        return list.run {
            find {
                val name = File(it).nameWithoutExtension

                name.contains(id, ignoreCase = true)
            } ?: ""
        }
    }
}