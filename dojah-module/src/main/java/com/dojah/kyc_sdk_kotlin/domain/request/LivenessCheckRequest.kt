package com.dojah.kyc_sdk_kotlin.domain.request


import com.google.gson.annotations.SerializedName

data class LivenessCheckRequest(
    val image: String? = null,
    val image2: String? = null,
    @SerializedName("verification_id") val verificationId: Int? = null, // 17567
    @SerializedName("step_number") val stepNumber: Int? = null, // 3
    val param: String?, // face
    @SerializedName("selfie_type") val selfieType: String? = null, // single
    @SerializedName("doc_type") val docType: String? = null, // image
    @SerializedName("continue_verification") val continueVerification: Boolean? = null, // true
    @SerializedName("utility_bill_image") val utilityBillImage: String? = null, // true
    @SerializedName("front_of_house_image") val frontOfHouseImage: String? = null, // true
    @SerializedName("outside_gate_image") val outsideGateImage: String? = null, // true
    @SerializedName("street_image") val streetImage: String? = null, // true
)
