package com.dojah.sdk_kyc.data.network.interceptor

import android.os.Build
import com.dojah.sdk_kyc.BuildConfig
import com.dojah.sdk_kyc.data.io.SharedPreferenceManager
import okhttp3.Interceptor
import okhttp3.Response

class HeaderInterceptor(
        private val versionCode: String,
        prefManager: SharedPreferenceManager) : Interceptor {

    private var bearer = prefManager.getBearerToken()

    private var notificationToken = prefManager.getNotificationToken()

    private var serialNumber = prefManager.getDeviceSerialNumber()

    private var location = prefManager.getLocation()

    init {
        prefManager.addCallback(object: SharedPreferenceManager.Callback{
            override fun onChange(key: String) {
                if (key == SharedPreferenceManager.KEY_BEARER_TOKEN) bearer = prefManager.getBearerToken()

                if(key == SharedPreferenceManager.KEY_NOTIFICATION_TOKEN) notificationToken = prefManager.getNotificationToken()

                if(key == SharedPreferenceManager.KEY_SERIAL_NUMBER) serialNumber = prefManager.getDeviceSerialNumber()

                if(key == SharedPreferenceManager.KEY_LOCATION) location = prefManager.getLocation()
            }
        })
    }

    override fun intercept(chain: Interceptor.Chain): Response {
        return chain.request().run {

            val newRequest = newBuilder().run {
                addHeader("Accept", "application/json")

                addHeader("Content-Type", "application/json")

                addHeader("VERSIONCODE", versionCode)

//                addHeader("APPVERSION", BuildConfig.VERSION_NAME)

                addHeader("DEVICENAME", Build.DEVICE)

                addHeader("DEVICEMODEL", Build.MODEL)

                addHeader("DEVICETYPE", if(BuildConfig.IS_POS) "POS" else "Mobile")

                if(location.isNotEmpty()) addHeader("LOCATION", location)

                if (bearer.isNotEmpty()) addHeader("Authorization", "Bearer $bearer")

                if(notificationToken.isNotEmpty()) addHeader("NOTIFICATIONTOKEN", notificationToken)

                if(!serialNumber.isNullOrEmpty()) {
                    addHeader("SERIALNUMBER", serialNumber!!)
                    addHeader("DEVICEID", serialNumber!!)
                    addHeader("DEVICESERIAL", serialNumber!!)
                }

                build()
            }


            chain.proceed(newRequest)
        }
    }
}