package com.dojah.kyc_sdk_kotlin

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.activity.result.ActivityResultLauncher
import androidx.annotation.RequiresApi
import com.dojah.kyc_sdk_kotlin.core.di.DojahContainer
import com.dojah.kyc_sdk_kotlin.data.io.DeviceIdManager
import com.dojah.kyc_sdk_kotlin.domain.ExtraUserData
import com.dojah.kyc_sdk_kotlin.ui.splash.SplashActivity
import io.sentry.Sentry
import io.sentry.SentryLevel
import io.sentry.android.core.SentryAndroid
import io.sentry.android.replay.maskAllImages
import io.sentry.android.replay.maskAllText
import io.sentry.android.timber.SentryTimberIntegration
import java.lang.ref.WeakReference

const val DOJAH_RESULT_KEY = "result"
const val DOJAH_CLOSED_RESULT = "closed"
const val DOJAH_APPROVED_RESULT = "approved"
const val DOJAH_PENDING_RESULT = "pending"
const val DOJAH_FAILED_RESULT = "failed"

const val BACKWARD_CALL_REQUEST_CODE = 1001

object DojahSdk {
    private var contextRef: WeakReference<Context>? = null

    val context get() = contextRef?.get()

    lateinit var dojahContainer: DojahContainer

    @RequiresApi(Build.VERSION_CODES.N)
    fun with(context: Context, source: String? = null): DojahSdk {
        this.contextRef = WeakReference(context)
        dojahContainer = DojahContainer(contextRef!!)

        contextRef?.get()?.let {
            dojahContainer.sharedPreferenceManager.setDeviceSignature(
                DeviceIdManager.androidId(it)
            )
        }

        // Initialize Sentry error monitoring & tracing
        initSentry(context.applicationContext)

        with(dojahContainer) {
            source?.takeIf { it.isNotBlank() }
                ?.let { sharedPreferenceManager.setAndroidSource(source) }
        }
        return this
    }

    private var sentryInitialized = false

    private fun initSentry(context: Context) {
        if (sentryInitialized) return

        try {
            SentryAndroid.init(context) { options ->
                options.dsn = BuildConfig.SENTRY_DSN

                // Environment and release
                options.environment = if (BuildConfig.DEBUG) "debug" else "release"
                options.release = "com.dojah.kyc_sdk_kotlin@${BuildConfig.VERSION_NAME}"

                // Tracing — 100% in dev, 20% in production
                options.tracesSampleRate = if (BuildConfig.DEBUG) 1.0 else 0.2

                // Session Replay (API 26+; silent no-op on API 21–25)
                options.sessionReplay.sessionSampleRate = 0.1   // 10% of all sessions
                options.sessionReplay.onErrorSampleRate = 1.0   // 100% on error
                options.sessionReplay.maskAllText = true         // mask text for privacy
                options.sessionReplay.maskAllImages = true       // mask images for privacy

                // Structured logging
                options.logs.isEnabled = true

                // Error enrichment
                options.isAttachScreenshot = true
                options.isAttachViewHierarchy = true

                // ANR detection
                options.isAnrEnabled = true

                // Send PII (disabled for SDK — host app controls PII policy)
                options.isSendDefaultPii = false

                // Trace propagation to Dojah API for distributed tracing
                options.setTracePropagationTargets(
                    listOf(
                        "api.dojah.io",
                        ".*\\.dojah\\.io",
                        ".*\\.dojah\\.services"
                    )
                )

                // Debug logging — only in debug builds
                options.isDebug = BuildConfig.DEBUG

                // Bridge Timber logs → Sentry breadcrumbs & events
                options.addIntegration(
                    SentryTimberIntegration(
                        minEventLevel = SentryLevel.ERROR,
                        minBreadcrumbLevel = SentryLevel.INFO
                    )
                )
            }

            sentryInitialized = true
        } catch (e: Exception) {
            // Sentry init failure must never crash the host app
            e.printStackTrace()
        }
    }

    fun getIdHistory(): List<Pair<String, String>> {
        if (context == null) {
            throw Exception("You have to call the with(...) function first")
        }
        val idHistory = dojahContainer.sharedPreferenceManager.getIdHistory
        return idHistory
    }

    fun launch(
        dojahLauncher: ActivityResultLauncher<Intent>,
        widgetId: String,
        referenceId: String? = null,
        email: String? = null,
        extraData: ExtraUserData? = null,
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
        extraData: ExtraUserData? = null,
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
        }, BACKWARD_CALL_REQUEST_CODE)
    }
}