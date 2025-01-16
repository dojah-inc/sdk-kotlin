package com.dojah_inc.dojah_android_sdk

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import com.dojah_inc.dojah_android_sdk.core.di.DojahContainer
import com.dojah_inc.dojah_android_sdk.data.io.SharedPreferenceManager
import com.dojah_inc.dojah_android_sdk.ui.splash.SplashActivity
import com.google.android.gms.common.GooglePlayServicesNotAvailableException
import com.google.android.gms.common.GooglePlayServicesRepairableException
import com.google.android.gms.security.ProviderInstaller
import okhttp3.logging.HttpLoggingInterceptor
import java.security.KeyManagementException
import java.security.NoSuchAlgorithmException
import java.security.SecureRandom
import javax.net.ssl.SSLContext

@SuppressLint("StaticFieldLeak")
object DojahSdk {
    var context: Context? = null
    lateinit var dojahContainer: DojahContainer

    fun with(context: Context): DojahSdk {
        DojahSdk.context = context
//        try {
//            // Google Play will install latest OpenSSL
//            ProviderInstaller.installIfNeeded(context)
//            val sslContext: SSLContext = SSLContext.getInstance("TLSv1.2")
//            sslContext.init(null, null, SecureRandom())
//            val sslSocketFactory = sslContext.socketFactory
//            sslContext.createSSLEngine()
//        } catch (e: GooglePlayServicesRepairableException) {
//            e.printStackTrace()
//        } catch (e: GooglePlayServicesNotAvailableException) {
//            e.printStackTrace()
//        } catch (e: (NoSuchAlgorithmException  )) {
//            e.printStackTrace()
//        } catch (e: (KeyManagementException)) {
//            e.printStackTrace()
//        }
        dojahContainer = DojahContainer(context)
        return this
    }

    fun getIdHistory(): List<Pair<String, String>> {
        if (context == null) {
            throw Exception("You have to call the with(...) function first")
        }
        val idHistory = dojahContainer.sharedPreferenceManager.getIdHistory
        HttpLoggingInterceptor.Logger.DEFAULT.log("history length is ${idHistory.size}")
        return idHistory
    }

    fun launch(widgetId: String, referenceId: String? = null, email: String? = null) {
        if (context == null) {
            throw Exception("You have to call the with(...) function first")
        }
        context!!.startActivity(Intent(context, SplashActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK
            putExtra("widget_id", widgetId)
            putExtra("reference_id", referenceId)
            putExtra("email", email)
        })
    }
}