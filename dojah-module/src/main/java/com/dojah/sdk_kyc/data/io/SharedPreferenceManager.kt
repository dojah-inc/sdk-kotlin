package com.dojah.sdk_kyc.data.io

import android.content.Context
import android.content.SharedPreferences
import androidx.core.content.edit
import com.google.gson.Gson
import dagger.hilt.android.qualifiers.ApplicationContext
import okhttp3.ResponseBody
import okhttp3.ResponseBody.Companion.toResponseBody
import retrofit2.Response
import timber.log.Timber
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SharedPreferenceManager @Inject constructor(
        private val gson: Gson,
        @ApplicationContext private val context: Context) : SharedPreferences.OnSharedPreferenceChangeListener {
    companion object {
        ///Dojah keys
        const val KEY_BEARER_TOKEN = " bearer token"
        const val KEY_NOTIFICATION_TOKEN = "notification_token"
        const val KEY_SERIAL_NUMBER = "serial number"
        const val KEY_LOCATION = "location"

        const val KEY_ANALYSIS_RESPONSE = "key_analysis_response"
    }

    private val userPref: SharedPreferences = context.getSharedPreferences("user", Context.MODE_PRIVATE)

    private val appPref = context.getSharedPreferences("app", Context.MODE_PRIVATE)

    private val callbacks = mutableListOf<Callback>()

    init {
        userPref.registerOnSharedPreferenceChangeListener(this)
    }

    fun addCallback(callback: Callback) {
        callbacks.add(callback)
    }


    fun getBearerToken() = userPref.getString(KEY_BEARER_TOKEN, "")!!

    fun getNotificationToken() = appPref.getString(KEY_NOTIFICATION_TOKEN, "")!!


    fun getDeviceSerialNumber() = appPref.getString(KEY_SERIAL_NUMBER, null)

    override fun onSharedPreferenceChanged(sharedPreferences: SharedPreferences?, key: String?) {
        key?.let { k ->
            callbacks.forEach { it.onChange(k) }
        }
    }

    fun setLocation(latitude: Double, longitude: Double) {
        appPref.edit {
            putString(KEY_LOCATION, "$latitude,$longitude")
        }
    }

    fun getLocation() = appPref.getString(KEY_LOCATION, "")!!


    fun saveJsonResponse(responseString: String?, key: String) {
        if (responseString == null) {
//            Timber.d("no response to save")
            return
        }
        appPref.edit {
            putString(key, responseString.trimIndent())
        }
    }

    fun getSavedJsonResponse(key: String): Response<ResponseBody>? {
        val responseBody = appPref.getString(key, null)?.trimIndent()?.toResponseBody()
        if (responseBody == null) {
            Timber.d("no response to get")
            return null
        }
        return Response.success(responseBody)
    }

    fun clearTemporaryPref() {
//        TODO("Not yet implemented")
    }

    interface Callback {
        fun onChange(key: String)
    }
}