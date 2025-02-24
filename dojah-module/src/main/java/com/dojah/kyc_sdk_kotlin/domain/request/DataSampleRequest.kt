package com.dojah.kyc_sdk_kotlin.domain.request

import com.google.gson.annotations.SerializedName


data class DataSampleRequest(
    @SerializedName("accept_challenge") var acceptChallenge: Boolean? = null,

)