package com.dojah.sdk_kyc.domain.responses

import com.google.gson.annotations.SerializedName

data class GetIpResponse(
    @SerializedName("ip") var ip: String? = null,
)