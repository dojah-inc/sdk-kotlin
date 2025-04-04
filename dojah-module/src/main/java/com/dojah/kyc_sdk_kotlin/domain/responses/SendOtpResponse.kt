package com.dojah.kyc_sdk_kotlin.domain.responses

import com.google.gson.annotations.SerializedName

data class SendOtpResponse(
    val entity: List<SendOtpEntity>
)

data class SendOtpEntity(
    @SerializedName("reference_id") val referenceId: String,
    @SerializedName("status_id") val statusId: String,
    val destination: String,
    val status: String,
)