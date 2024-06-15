package com.dojah_inc.dojah_android_sdk.domain.responses

import com.google.gson.annotations.SerializedName


data class SimpleResponse(
    @SerializedName("entity") var entity: SimpleResponseEntity? = SimpleResponseEntity()
)

data class SimpleResponseEntity(
    @SerializedName("success") var success: Boolean? = null,
    @SerializedName("msg") var msg: String? = null,
    @SerializedName("data") var data: AuthData? = null,
    @SerializedName("continue_verification") var continueVerification: Boolean = false,
    @SerializedName("duplicate_reference") var duplicateReference: Boolean = false,
    var result: Result? = null
)

data class Result(
    var entity: Entity? = null,
)

data class Entity(
    var metadata: Any? = null,
    var verificationStatus: String? = null
)