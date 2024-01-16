package com.dojah.sdk_kyc.data.network.interceptor

import com.dojah.sdk_kyc.data.io.SharedPreferenceManager
import okhttp3.Interceptor
import okhttp3.Response
import timber.log.Timber
import java.util.logging.Level
import java.util.logging.Logger

class HeaderInterceptor(
        prefManager: SharedPreferenceManager) : Interceptor {

    private var bearer = prefManager.getBearerToken()

    private var sessionId = prefManager.getSessionId()

    private var pkey = prefManager.getPKey()

    private var ref = prefManager.getReference()

    private var appId = prefManager.getAppId()

    init {
        prefManager.addCallback(object: SharedPreferenceManager.Callback{
            override fun onChange(key: String) {
                Timber.d("onSharedChange")
                if (key == SharedPreferenceManager.KEY_BEARER_TOKEN) bearer = prefManager.getBearerToken()

                if(key == SharedPreferenceManager.KEY_SESSION_ID) sessionId = prefManager.getSessionId()

                if(key == SharedPreferenceManager.KEY_PKEY) pkey = prefManager.getPKey()

                if(key == SharedPreferenceManager.KEY_REF) ref = prefManager.getReference()

                if(key == SharedPreferenceManager.KEY_APP_ID) appId = prefManager.getAppId()
            }
        })
    }

    override fun intercept(chain: Interceptor.Chain): Response {

        return chain.request().run {

            val newRequest = newBuilder().run {
                addHeader("Accept", "application/json")

                addHeader("Content-Type", "application/json")

                if (bearer != null) addHeader("Authorization", bearer!!)
                if (sessionId?.isNotEmpty() == true) addHeader("Session", sessionId!!)
                if (pkey?.isNotEmpty() == true) addHeader("p-key", pkey!!)
                if (appId?.isNotEmpty() == true) addHeader("app-id", appId!!)
                if (ref?.isNotEmpty() == true) addHeader("reference", ref!!)

                build()
            }


            chain.proceed(newRequest)
        }
    }
}