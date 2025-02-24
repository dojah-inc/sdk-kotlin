package com.dojah.kyc_sdk_kotlin.domain.responses

import com.google.gson.annotations.SerializedName

data class GetIpResponse(
    @SerializedName("ip") var ip: String? = null,
)
