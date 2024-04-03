package com.dojah.sdk_kyc.core

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import com.dojah.sdk_kyc.data.io.SharedPreferenceManager
import com.dojah.sdk_kyc.ui.splash.SplashActivity
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
        this.context = context
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