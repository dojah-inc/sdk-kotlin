package com.dojah_inc.dojah_android_sdk.domain.request

import com.google.gson.annotations.SerializedName


data class DataSampleRequest(
    @SerializedName("accept_challenge") var acceptChallenge: Boolean? = null,

)