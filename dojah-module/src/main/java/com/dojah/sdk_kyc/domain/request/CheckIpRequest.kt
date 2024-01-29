package com.dojah.sdk_kyc.domain.request

import com.google.gson.annotations.SerializedName


data class CheckIpRequest(

    @SerializedName("ip") var ip: String? = null,
    @SerializedName("device_info") var deviceInfo: String? = null,
    @SerializedName("verification_id") var verificationId: Int? = null,

    )