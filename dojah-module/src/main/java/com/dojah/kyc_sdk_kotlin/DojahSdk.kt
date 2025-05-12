package com.dojah.kyc_sdk_kotlin

import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.activity.result.ActivityResultLauncher
import com.dojah.kyc_sdk_kotlin.core.di.DojahContainer
import com.dojah.kyc_sdk_kotlin.domain.ExtraUserData
import com.dojah.kyc_sdk_kotlin.ui.splash.SplashActivity
import java.lang.ref.WeakReference

const val DOJAH_RESULT_KEY = "result"
const val DOJAH_CLOSED_RESULT = "closed"
const val DOJAH_APPROVED_RESULT = "approved"
const val DOJAH_PENDING_RESULT = "pending"
const val DOJAH_FAILED_RESULT = "failed"

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

    fun launch(
        widgetId: String,
        referenceId: String? = null,
        email: String? = null,
        dojahLauncher: ActivityResultLauncher<Intent>,
        extraData: ExtraUserData,
    ) {
        if (context == null) {
            throw Exception("You have to call the with(...) function first")
        }
        dojahLauncher.launch(Intent(context, SplashActivity::class.java).apply {
//            flags = Intent.FLAG_ACTIVITY_NEW_TASK
            putExtra("widget_id", widgetId)
            putExtra("reference_id", referenceId)
            putExtra("email", email)
            putExtra("extra_data", extraData)
        })
    }

    fun launchWithBackwardCompatibility(
        activity: Activity,
        widgetId: String,
        referenceId: String? = null,
        email: String? = null,
        extraData: ExtraUserData,
    ) {
        if (context == null) {
            throw Exception("You have to call the with(...) function first")
        }
        activity.startActivityForResult(Intent(context, SplashActivity::class.java).apply {
//            flags = Intent.FLAG_ACTIVITY_NEW_TASK
            putExtra("widget_id", widgetId)
            putExtra("reference_id", referenceId)
            putExtra("email", email)
            putExtra("extra_data", extraData)
        }, 1001)
    }
}