package com.dojah_inc.dojah_android_sdk.domain.responses

import com.google.gson.annotations.SerializedName

data class GetIpResponse(
    @SerializedName("ip") var ip: String? = null,
)
