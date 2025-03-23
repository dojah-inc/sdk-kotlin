package com.dojah.kyc_sdk_kotlin

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import com.dojah.kyc_sdk_kotlin.core.di.DojahContainer
import com.dojah.kyc_sdk_kotlin.ui.splash.SplashActivity
import okhttp3.logging.HttpLoggingInterceptor

@SuppressLint("StaticFieldLeak")
object DojahSdk {
    var context: Context? = null
    lateinit var dojahContainer: DojahContainer

    fun with(context: Context): DojahSdk {
        DojahSdk.context = context
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