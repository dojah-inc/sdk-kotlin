package com.dojah.kyc_sdk_kotlin.domain.responses

import com.google.gson.annotations.SerializedName

data class LivenessResponse(
    @SerializedName(value = "responseBlob", alternate = ["response_blob"])
    val responseBlob: String? = null,
)

data class FaceTecSessionResponse(
    val entity: Entity? = null,
    @SerializedName("liveness_response") val livenessResponse: LivenessResponse? = null,
    @SerializedName(value = "responseBlob", alternate = ["response_blob"])
    val responseBlob: String? = null,
) {
    data class Entity(
        @SerializedName("liveness_response") val livenessResponse: LivenessResponse? = null,
        @SerializedName(value = "responseBlob", alternate = ["response_blob"])
        val responseBlob: String? = null,
    )

    fun blob(): String? =
        livenessResponse?.responseBlob
            ?: entity?.livenessResponse?.responseBlob
            ?: entity?.responseBlob
            ?: responseBlob
}
