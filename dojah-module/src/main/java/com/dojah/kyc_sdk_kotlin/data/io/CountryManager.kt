package com.dojah.kyc_sdk_kotlin.data.io

import android.content.Context
import androidx.core.os.ConfigurationCompat
import com.dojah.kyc_sdk_kotlin.data.io.FileManager.Companion.getAssetDirectory
import com.dojah.kyc_sdk_kotlin.domain.Country
import com.dojah.kyc_sdk_kotlin.domain.CountryState
import com.google.i18n.phonenumbers.PhoneNumberUtil
import kotlinx.coroutines.*
import org.json.JSONArray
import org.json.JSONObject
import java.io.File
import java.io.IOException
import java.io.InputStream
import java.nio.charset.StandardCharsets
import java.util.*

typealias CountryCallback = (List<Country>) -> Unit

class CountryManager(
    private val context: Context,
    private val phoneNumberUtil: PhoneNumberUtil
) {

    private val listeners = mutableListOf<CountryCallback>()
    private var countriesList = mutableListOf<Country>()

    suspend fun getCountryStatesList(countryName: String): List<CountryState> {
        if (countriesList.isEmpty()) {
            getCountries()
        }
        val country = countriesList.firstOrNull {
            it.name.equals(countryName, ignoreCase = true)
        }

        return country?.states ?: emptyList()
    }

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

    private fun loadJSONFromAsset(path: String): String? {
        val json: String?
        try {
            val stream: InputStream = context.assets.open(path)
            val size = stream.available()
            val buffer = ByteArray(size)
            stream.read(buffer)
            stream.close()
            json = String(buffer, StandardCharsets.UTF_8)
        } catch (ex: IOException) {
            ex.printStackTrace()
            return null
        }
        return json
    }

    private suspend fun loadCountries() {
        return withContext(Dispatchers.IO) {
            countriesList.ifEmpty {
                val jsonString = loadJSONFromAsset("countrystates/countries.json")
                jsonString?.let {
                    val array = JSONObject(it).optJSONArray("countries") ?: JSONArray()

                    for (i in 0 until array.length()) {
                        val countryJson = array.optJSONObject(i)
                        val statesJson = countryJson.optJSONArray("states") ?: JSONArray()
                        countriesList.add(
                            Country(
                                countryJson.optString("code3"),
                                countryJson.optString("name"),
                                countryJson.optString("code2"),
                                "",
                                states = List(statesJson.length()) { index ->
                                    val stateJson = statesJson.optJSONObject(index)
                                    CountryState(
                                        stateJson.optString("name"),
                                        stateJson.optJSONArray("subdivision")
                                            ?.let { subdivisionArray ->
                                                List(subdivisionArray.length()) { subIndex ->
                                                    subdivisionArray.optString(subIndex)
                                                }
                                            } ?: emptyList()
                                    )
                                }
                            )
                        )
                    }
                }
                countriesList
            }
        }
    }

    private suspend fun getCountries(): List<Country> {
        return withContext(Dispatchers.Default) {

            loadCountries()
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