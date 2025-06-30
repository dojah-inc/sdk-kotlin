package com.dojah.kyc_sdk_kotlin.domain.request


import com.google.gson.annotations.SerializedName

data class LivenessCheckRequest(
    val image: String?,
    val image2: String?,
    @SerializedName("verification_id") val verificationId: Int?, // 17567
    @SerializedName("step_number") val stepNumber: Int?, // 3
    val param: String?, // face
    @SerializedName("selfie_type") val selfieType: String?, // single
    @SerializedName("doc_type") val docType: String?, // image
    @SerializedName("continue_verification") val continueVerification: Boolean?, // true

)