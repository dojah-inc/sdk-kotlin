package com.dojah.kyc_sdk_kotlin.domain.request

import com.google.gson.annotations.SerializedName

data class MetaDataRequest(
    @SerializedName("app_id") val appId: String,
    @SerializedName("verification_id") val verificationId: Int,
    @SerializedName("meta") val meta: Map<String, Any>,
)
