package com.dojah.kyc_sdk_kotlin.ui.utils.facetec

import android.app.Activity
import android.content.Context
import android.content.Intent
import com.dojah.kyc_sdk_kotlin.BuildConfig
import com.facetec.sdk.FaceTecInitializationError
import com.facetec.sdk.FaceTecSDK
import com.facetec.sdk.FaceTecSDKInstance
import com.facetec.sdk.FaceTecSessionResult
import com.facetec.sdk.FaceTecSessionStatus
import timber.log.Timber

/**
 * Parallel 3D-liveness provider. CameraX selfie remains the default when this is off.
 * Does not launch ID scan or 3D:3D match.
 */
object FaceTecManager {
    private var sdkInstance: FaceTecSDKInstance? = null
    private var initializing = false
    private var sessionListener: SessionListener? = null

    fun interface SessionListener {
        fun onFinished(success: Boolean, cancelled: Boolean)
    }

    fun preload(context: Context) {
        FaceTecSDK.preload(context.applicationContext)
    }

    fun resolveDeviceKey(): String? {
        return BuildConfig.FACETEC_DEVICE_KEY.trim().takeIf { it.isNotEmpty() }
    }

    fun canLaunchLiveness(): Boolean {
        return !resolveDeviceKey().isNullOrBlank()
    }

    fun deinitialize() {
        sessionListener = null
        sdkInstance = null
        initializing = false
        FaceTecSDK.deinitialize()
    }

    fun startLiveness(
        activity: Activity,
        deviceKey: String,
        brandColorHex: String?,
        listener: SessionListener,
    ) {
        if (initializing) {
            listener.onFinished(success = false, cancelled = false)
            return
        }
        sessionListener = listener
        val existing = sdkInstance
        if (existing != null) {
            existing.start3DLiveness(activity, FaceTecSessionProcessor())
            return
        }
        initializing = true

        FaceTecSDK.setCustomization(FaceTecCustomizationFactory.fromBrandColor(brandColorHex))
        FaceTecSDK.initializeWithSessionRequest(
            activity,
            deviceKey,
            FaceTecSessionProcessor(),
            object : FaceTecSDK.InitializeCallback {
                override fun onSuccess(instance: FaceTecSDKInstance) {
                    initializing = false
                    sdkInstance = instance
                    instance.start3DLiveness(activity, FaceTecSessionProcessor())
                }

                override fun onError(error: FaceTecInitializationError) {
                    initializing = false
                    Timber.e("FaceTec init failed: $error")
                    notifyFinished(success = false, cancelled = false)
                }
            }
        )
    }

    fun onHostActivityResult(
        requestCode: Int,
        resultCode: Int,
        data: Intent?,
    ): Boolean {
        val result: FaceTecSessionResult =
            FaceTecSDK.getActivitySessionResult(requestCode, resultCode, data) ?: return false
        val status = result.status
        val success = status == FaceTecSessionStatus.SESSION_COMPLETED
        val cancelled = status == FaceTecSessionStatus.USER_CANCELLED_FACE_SCAN
        Timber.d("FaceTec liveness finished status=%s", status)
        notifyFinished(success, cancelled)
        return true
    }

    private fun notifyFinished(success: Boolean, cancelled: Boolean) {
        val listener = sessionListener
        sessionListener = null
        listener?.onFinished(success, cancelled)
    }
}
