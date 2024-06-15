package com.dojah_inc.dojah_android_sdk.domain.responses


import com.google.gson.annotations.SerializedName

data class DojahPricing(
    @SerializedName("additional-document") val additionalDocument: Any? = null,
    val address: DojahPricingVerification?,
    val aml: DojahPricingAml?,
    @SerializedName("business-data") val businessData: DojahPricingBusinessData?,
    @SerializedName("business-id") val businessId: DojahPricingDefault?,
    val countries: Any? = null,
    val email: DojahPricingVerification?,
    @SerializedName("government-data") val governmentData: DojahPricingGovernmentData?,
    @SerializedName("government-data-verification") val governmentDataVerification: DojahPricingGovernmentDataVerification?,
    val id: DojahPricingDefault?,
    val index: Any? = null,
    @SerializedName("phone-number") val phoneNumber: DojahPricingVerification?,
    val selfie: DojahPricingSelfie?,
    val signature: Any? = null
) {
    fun verificationMap(): Map<String, DojahPricingVerification?> {
        return mapOf(
            "address" to address,
            "email" to email,
            "phone-number" to phoneNumber
        )
    }

    fun defaultMap(): Map<String, DojahPricingDefault?> {
        return mapOf(
            "business-id" to businessId,
            "id" to id,
        )
    }

    data class DojahPricingVerification(
        val verification: String? // kyc_address_verification
    )

    data class DojahPricingAml(
        val aml: String? // aml
    )

    data class DojahPricingBusinessData(
        val cac: String? // kyc_cac
    )

    data class DojahPricingDefault(
        val default: String? // kyc_document_analysis
    )


    data class DojahPricingGovernmentData(
        @SerializedName("ao-nin") val aoNin: String?, // angola_kyc_nin
        val bvn: String?, // kyc_bvn_full
        val bvnAdvance: String?, // kyc_bvn_advance
        val dl: String?, // kyc_drivers_license
        @SerializedName("gh-dl") val ghDl: String?, // ghana_kyc_dl
        @SerializedName("gh-voter") val ghVoter: String?, // ghana_kyc_voter
        @SerializedName("ke-dl") val keDl: String?, // kenya_kyc_dl
        @SerializedName("ke-id") val keId: String?, // kenya_kyc_id
        @SerializedName("ke-kra") val keKra: String?, // kenya_kyc_kra
        val mobile: String?, // kyc_mobile
        val nin: String?, // kyc_nin
        val vnin: String?, // kyc_nin
        @SerializedName("za-id") val zaId: String? // za_kyc_id
    ) {
        fun toMap(): Map<String, String?> {
            return mapOf(
                "bvn" to bvn,
                "bvnAdvance" to bvnAdvance,
                "vnin" to vnin,
                "nin" to nin,
                "dl" to dl,
                "mobile" to mobile,
                "gh-dl" to ghDl,
                "gh-voter" to ghVoter,
                "ke-kra" to keKra,
                "ke-id" to keId,
                "ke-dl" to keDl,
                "ao-nin" to aoNin,
                "za-id" to zaId
            )
        }
    }

    data class DojahPricingGovernmentDataVerification(
        val emailOtp: String?, // email_otp
        val otp: String?, // sms
        val selfie: String?, // kyc_liveness_selfie
        val video: String? // kyc_liveness_video
    ) {
        fun toMap(): Map<String, String?> {
            return mapOf(
                "selfie" to selfie,
                "video" to video,
                "otp" to otp,
                "emailOtp" to emailOtp
            )
        }
    }

    data class DojahPricingSelfie(
        val selfie: String?, // kyc_liveness_selfie
        val video: String? // kyc_liveness_video
    ) {
        fun toMap(): Map<String, String?> {
            return mapOf(
                "selfie" to selfie,
                "selfie-video" to video,
            )
        }
    }
}