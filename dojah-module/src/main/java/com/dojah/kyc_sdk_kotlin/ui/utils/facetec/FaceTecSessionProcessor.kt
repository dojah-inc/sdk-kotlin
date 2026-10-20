package com.dojah.kyc_sdk_kotlin.ui.utils.facetec

import com.dojah.kyc_sdk_kotlin.DojahSdk
import com.dojah.kyc_sdk_kotlin.core.Result
import com.dojah.kyc_sdk_kotlin.data.io.SharedPreferenceManager
import com.dojah.kyc_sdk_kotlin.domain.request.FaceTecSessionRequest
import com.dojah.kyc_sdk_kotlin.domain.responses.AuthResponse
import com.dojah.kyc_sdk_kotlin.ui.utils.KycPages
import com.facetec.sdk.FaceTecSessionRequestProcessor
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import timber.log.Timber

/**
 * Forwards FaceTec session blobs to Dojah. Dojah must proxy them to FaceTec Server.
 * Extra UI or business logic is not allowed in this callback.
 */
class FaceTecSessionProcessor : FaceTecSessionRequestProcessor {
    private val scope = CoroutineScope(SupervisorJob() + Dispatchers.IO)

    override fun onSessionRequest(
        sessionRequestBlob: String,
        sessionRequestCallback: FaceTecSessionRequestProcessor.Callback,
    ) {
        scope.launch {
            try {
                val container = DojahSdk.dojahContainer
                val result = container.userRepository.processFaceTecSession(
                    FaceTecSessionRequest(selfie = sessionRequestBlob)
                )

                when (result) {
                    is Result.Success -> {
                        val blob = result.data.livenessResponse?.responseBlob
                        if (blob.isNullOrBlank()) {
                            Timber.e("FaceTec session response was missing responseBlob")
                            sessionRequestCallback.abortOnCatastrophicError()
                        } else {
                            sessionRequestCallback.processResponse(blob)
                        }
                    }

                    else -> {
                        Timber.e("FaceTec session request failed")
                        sessionRequestCallback.abortOnCatastrophicError()
                    }
                }
            } catch (error: Exception) {
                Timber.e(error, "FaceTec session request failed")
                sessionRequestCallback.abortOnCatastrophicError()
            }
        }
    }
}
