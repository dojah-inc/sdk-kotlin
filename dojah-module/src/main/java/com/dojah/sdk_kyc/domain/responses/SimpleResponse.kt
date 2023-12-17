package com.dojah.sdk_kyc.domain.responses

import com.google.gson.annotations.SerializedName


data class SimpleResponse (
    @SerializedName("entity" ) var entity : SimpleResponseEntity? = SimpleResponseEntity()
)

data class SimpleResponseEntity (
    @SerializedName("success" ) var success : Boolean? = null,
    @SerializedName("msg"     ) var msg     : String?  = null
)