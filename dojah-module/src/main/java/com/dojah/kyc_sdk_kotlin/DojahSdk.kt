package com.dojah.kyc_sdk_kotlin

import android.content.Context
import android.content.Intent
import com.dojah.kyc_sdk_kotlin.core.di.DojahContainer
import com.dojah.kyc_sdk_kotlin.ui.splash.SplashActivity
import java.lang.ref.WeakReference


object DojahSdk {
    private var contextRef: WeakReference<Context>? = null

    val context get() = contextRef?.get()

    lateinit var dojahContainer: DojahContainer

    fun with(context: Context): DojahSdk {
        this.contextRef = WeakReference(context)
        dojahContainer = DojahContainer(contextRef!!)
        return this
    }

    fun getIdHistory(): List<Pair<String, String>> {
        if (context == null) {
            throw Exception("You have to call the with(...) function first")
        }
        val idHistory = dojahContainer.sharedPreferenceManager.getIdHistory
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