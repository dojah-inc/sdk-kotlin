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
                val auth = container.userRepository.getLocalResponse(
                    SharedPreferenceManager.KEY_AUTH_RESPONSE,
                    AuthResponse::class.java,
                )?.data
                val authData = auth?.initData?.authData
                val verificationId = authData?.verificationId
                val pages = authData?.pages
                val currentIndex = container.sharedPreferenceManager.getCurrentPageIndex()
                val currentStep = pages?.getOrNull(currentIndex)
                    ?: pages?.firstOrNull { it.name == KycPages.SELFIE.serverKey }
                    ?: pages?.firstOrNull {
                        it.name == KycPages.GOVERNMENT_DATA_VERIFICATION.serverKey
                    }

                val result = container.userRepository.processFaceTecSession(
                    FaceTecSessionRequest(
                        requestBlob = sessionRequestBlob,
                        selfie = sessionRequestBlob,
                        verificationId = verificationId,
                        stepNumber = currentStep?.id,
                    )
                )

                when (result) {
                    is Result.Success -> {
                        val blob = result.data.blob()
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
