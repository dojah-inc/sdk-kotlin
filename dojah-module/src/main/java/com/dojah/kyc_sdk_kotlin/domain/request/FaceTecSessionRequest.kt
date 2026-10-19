package com.dojah.kyc_sdk_kotlin.domain.request

import com.google.gson.annotations.SerializedName

data class FaceTecSessionRequest(
    @SerializedName("requestBlob") val requestBlob: String,
    @SerializedName("selfie") val selfie: String,
    @SerializedName("verification_id") val verificationId: Int? = null,
    @SerializedName("step_number") val stepNumber: Int? = null,
)
