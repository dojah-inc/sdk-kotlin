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

    init {
        prefManager.addCallback(object: SharedPreferenceManager.Callback{
            override fun onChange(key: String) {
                Timber.d("onSharedChange")
                if (key == SharedPreferenceManager.KEY_BEARER_TOKEN) bearer = prefManager.getBearerToken()

                if(key == SharedPreferenceManager.KEY_SESSION_ID) sessionId = prefManager.getSessionId()
            }
        })
    }

    override fun intercept(chain: Interceptor.Chain): Response {

        return chain.request().run {

            val newRequest = newBuilder().run {
                addHeader("Accept", "application/json")

                addHeader("Content-Type", "application/json")

                Timber.i("bearer tok is $bearer")
                if (bearer != null) addHeader("Authorization", bearer!!)
                if (sessionId?.isNotEmpty() == true) addHeader("Session", sessionId!!)

                build()
            }


            chain.proceed(newRequest)
        }
    }
}