package com.dojah.sdk_kyc.domain.request

import com.dojah.sdk_kyc.domain.responses.Config
import com.dojah.sdk_kyc.domain.responses.Widget
import com.google.gson.annotations.SerializedName


data class AuthRequest(

    @SerializedName("public_key") var publicKey: String? = null,
    @SerializedName("app_id") var appId: String? = null,
    @SerializedName("type") var type: String? = null,
    @SerializedName("review_process") var reviewProcess: String? = null,
    @SerializedName("steps") var steps: List<AuthReqSteps> = listOf(),
    @SerializedName("reference_id") var referenceId: String? = null,
    @SerializedName("email") var email: String? = null,
    @SerializedName("duplicate_check") var duplicateCheck: Boolean? = null,
    @SerializedName("direct_feedback") var directFeedback: Boolean? = null,
    @SerializedName("rules") var rules: Widget.Rules? = Widget.Rules(),

    )


data class AuthReqSteps(

    @SerializedName("config") var authReqConfigConfig: Config? = Config(),
    @SerializedName("name") var name: String? = null,
    @SerializedName("id") var id: Int? = null

)