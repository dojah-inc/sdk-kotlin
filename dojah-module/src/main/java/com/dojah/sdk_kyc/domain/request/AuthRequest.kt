package com.dojah.sdk_kyc.domain.request

import com.dojah.sdk_kyc.domain.responses.Config
import com.google.gson.annotations.SerializedName


data class AuthRequest (

    @SerializedName("public_key"     ) var publicKey     : String?          = null,
    @SerializedName("app_id"         ) var appId         : String?          = null,
    @SerializedName("type"           ) var type          : String?          = null,
    @SerializedName("review_process" ) var reviewProcess : String?          = null,
    @SerializedName("steps"          ) var steps         : List<AuthReqSteps> = listOf()

)


data class AuthReqSteps (

    @SerializedName("config" ) var authReqConfigConfig : Config? = Config(),
    @SerializedName("name"   ) var name   : String? = null,
    @SerializedName("id"     ) var id     : Int?    = null

)