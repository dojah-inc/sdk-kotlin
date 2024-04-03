package com.dojah.sdk_kyc.domain.request

import com.google.gson.annotations.SerializedName

data class OtpRequest(
    val destination: String?=null,
    val email: String?=null,
    @SerializedName("sender_id") val senderId: String,
    val channel: String,
    val length: Int,
)