package com.dojah_inc.dojah_android_sdk.domain.request

import com.dojah_inc.dojah_android_sdk.domain.responses.Config
import com.dojah_inc.dojah_android_sdk.domain.responses.Widget
import com.google.gson.annotations.SerializedName


data class AuthRequest(

    @SerializedName("public_key") var publicKey: String? = null,
    @SerializedName("app_id") var appId: String? = null,
    @SerializedName("type") var type: String? = null,
    @SerializedName("review_process") var reviewProcess: String? = null,
    @SerializedName("steps") var steps: List<AuthReqSteps> = listOf(),
    @SerializedName("duplicate_check") var duplicateCheck: Boolean? = null,
    @SerializedName("direct_feedback") var directFeedback: Boolean? = null,
    @SerializedName("rules") var rules: Widget.Rules? = Widget.Rules(),
    @SerializedName("reference_id") var referenceId: String? = null,
    @SerializedName("email") var email: String? = null,

    )


data class AuthReqSteps(

    @SerializedName("config") var authReqConfigConfig: Config? = Config(),
    @SerializedName("name") var name: String? = null,
    @SerializedName("id") var id: Int? = null

)