package com.dojah.sdk_kyc.data.io

import android.content.Context
import android.content.SharedPreferences
import androidx.core.content.edit
import com.dojah.sdk_kyc.domain.responses.AuthResponse
import com.dojah.sdk_kyc.domain.responses.PreAuthResponse
import dagger.hilt.android.qualifiers.ApplicationContext
import okhttp3.ResponseBody
import okhttp3.ResponseBody.Companion.toResponseBody
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Response
import timber.log.Timber
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SharedPreferenceManager @Inject constructor(
    @ApplicationContext private val context: Context
) : SharedPreferences.OnSharedPreferenceChangeListener {
    companion object {
        ///Dojah keys
        const val KEY_BEARER_TOKEN = " bearer token"
        const val KEY_NOTIFICATION_TOKEN = "notification_token"
        const val KEY_SESSION_ID = "session id"
        const val KEY_PKEY = "key pkey"
        const val KEY_REF = "key ref"
        const val KEY_APP_ID = "key app id"
        const val KEY_LOCATION = "location"

        const val KEY_AUTH_RESPONSE = "key_analysis_response"
        const val KEY_PRE_AUTH_RESPONSE = "key_pre_auth_response"
        const val KEY_CHECK_IP_RESPONSE = "key_check_ip_response"
        const val KEY_GET_IP_RESPONSE = "key_get_ip_response"
        const val KEY_BTN_COLOR = "key_btn_color"
        const val KEY_ID_HISTORY = "id_history"
        const val KEY_Widget_ID = "key_widget_id"
    }

    private val userPref: SharedPreferences =
        context.getSharedPreferences("user", Context.MODE_PRIVATE)

    private val appPref = context.getSharedPreferences("app", Context.MODE_PRIVATE)

    private val callbacks = mutableListOf<Callback>()

    init {
        userPref.registerOnSharedPreferenceChangeListener(this)
    }

    fun addCallback(callback: Callback) {
        callbacks.add(callback)
    }


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

    val location
        get(): Pair<Double, Double>? {
            return appPref.getString(KEY_LOCATION, null)?.split(",")?.let {
                HttpLoggingInterceptor.Logger.DEFAULT.log("location lat_lng: $it")
                if (it.size == 2) {
                    Pair(it[0].toDouble(), it[1].toDouble())
                } else {
                    null
                }
            }
        }


    fun getBearerToken() = userPref.getString(KEY_BEARER_TOKEN, null)

    fun getSessionId() = userPref.getString(KEY_SESSION_ID, null)

    fun getPKey() = userPref.getString(KEY_PKEY, null)

    fun getReference() = userPref.getString(KEY_REF, null)

    fun getAppId() = userPref.getString(KEY_APP_ID, null)

    fun setPKey(pkey: String) {
        userPref.edit {
            putString(KEY_PKEY, pkey)
        }
    }

    fun setReference(ref: String) {
        userPref.edit {
            putString(KEY_REF, ref)
        }
    }

    fun setAppId(appId: String) {
        userPref.edit {
            putString(KEY_APP_ID, appId)
        }
    }

    fun setBearerToken(token: String?) {
        userPref.edit {
            putString(KEY_BEARER_TOKEN, token)
        }
    }

    fun setSessionId(id: String) {
        userPref.edit {
            putString(KEY_SESSION_ID, id)
        }
    }


    fun setWidgetId(appId: String) {
        userPref.edit {
            putString(KEY_Widget_ID, appId)
        }
    }

    private fun getWidgetId() = userPref.getString(KEY_Widget_ID, null)

    fun addIdToHistory(data: AuthResponse) {
        appPref.edit {
            val newID = "${data.companyName}||${getWidgetId()}"
            val idSet = appPref.getStringSet(KEY_ID_HISTORY, null)
            val newSet = mutableSetOf(newID).apply {
                if (idSet != null) {
                    addAll(idSet)
                }
            }
            putStringSet(
                KEY_ID_HISTORY, newSet
            )
            HttpLoggingInterceptor.Logger.DEFAULT.log("history length added is ${newSet.size}")

        }
    }

    val getIdHistory: List<Pair<String, String>>
        get() {
            val idSet = appPref.getStringSet(KEY_ID_HISTORY, null)
            HttpLoggingInterceptor.Logger.DEFAULT.log("history length get source is ${idSet?.size}")
            return idSet?.map {
                val list = it.split("||")
                list.first() to list.last()
            } ?: listOf()
        }

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


    fun setMaterialButtonBgColor(color: String?) {
        userPref.edit {
            putString(KEY_BTN_COLOR, color)
        }
    }

    val getMaterialButtonBgColor: String?
        get() = userPref.getString(KEY_BTN_COLOR, null)

    fun clearTemporaryPref() {
//        TODO("Not yet implemented")
    }

    interface Callback {
        fun onChange(key: String)
    }
}