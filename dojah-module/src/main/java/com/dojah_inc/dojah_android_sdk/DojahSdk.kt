package com.dojah_inc.dojah_android_sdk

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import com.dojah_inc.dojah_android_sdk.data.io.SharedPreferenceManager
import com.dojah_inc.dojah_android_sdk.ui.splash.SplashActivity
import okhttp3.logging.HttpLoggingInterceptor

@SuppressLint("StaticFieldLeak")
object DojahSdk {
    var context: Context? = null

    fun getIdHistory(context: Context): List<Pair<String, String>> {
        val idHistory = SharedPreferenceManager(context).getIdHistory
        HttpLoggingInterceptor.Logger.DEFAULT.log("history length is ${idHistory.size}")
        return idHistory
    }

    fun with(context: Context): DojahSdk {
        DojahSdk.context = context
        return this
    }

    fun launch(widgetId: String, referenceId: String?=null, email: String?=null) {
        if (context == null) {
            throw Exception("You have to call the with(...) function first")
        }
        context!!.startActivity(Intent(context, SplashActivity::class.java).apply {
            putExtra("widget_id", widgetId)
            putExtra("reference_id", referenceId)
            putExtra("email", email)
        })
    }
}