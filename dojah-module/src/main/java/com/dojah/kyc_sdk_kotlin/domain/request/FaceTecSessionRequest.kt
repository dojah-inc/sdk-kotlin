package com.dojah.kyc_sdk_kotlin.domain.request

import com.google.gson.annotations.SerializedName

data class FaceTecSessionRequest(
    @SerializedName("selfie") val selfie: String
)
