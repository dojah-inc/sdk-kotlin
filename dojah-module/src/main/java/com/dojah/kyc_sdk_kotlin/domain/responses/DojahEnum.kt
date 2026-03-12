package com.dojah.kyc_sdk_kotlin.domain.responses


import com.google.gson.annotations.SerializedName

data class DojahEnum(
    val bvn: DojahEnumAttr,
    @SerializedName("za-id")
    val zaId: DojahEnumAttr,
    @SerializedName("ao-nin")
    val aoNin: DojahEnumAttr,
    val nin: DojahEnumAttr,
    val vnin: DojahEnumAttr,
    val dl: DojahEnumAttr,
    val passport: DojahEnumAttr,
    val national: DojahEnumAttr,
    val permit: DojahEnumAttr,
    val custom: DojahEnumAttr,
    val voter: DojahEnumAttr,
    val mobile: DojahEnumAttr,
    val utilityBill: DojahEnumAttr,
    @SerializedName("NG-DLI")
    val ngDli: DojahEnumAttr,
    @SerializedName("NG-PASS")
    val ngPass: DojahEnumAttr,
    @SerializedName("NG-NAT")
    val ngNat: DojahEnumAttr,
    @SerializedName("UK-RP")
    val ukRp: DojahEnumAttr,
    @SerializedName("NG-CUSTOM")
    val ngCustom: DojahEnumAttr,
    @SerializedName("NG-VCARD")
    val ngVcard: DojahEnumAttr,
    @SerializedName("NG-NIN-SLIP")
    val ngNinSlip: DojahEnumAttr,
    val selfie: DojahEnumAttr,
    val otp: DojahEnumAttr,
    @SerializedName("gh-dl")
    val ghDl: DojahEnumAttr,
    @SerializedName("gh-voter")
    val ghVoter: DojahEnumAttr,
    @SerializedName("tz-nin")
    val tzNin: DojahEnumAttr,
    @SerializedName("ug-id")
    val ugId: DojahEnumAttr,
    @SerializedName("ug-telco")
    val ugTelco: DojahEnumAttr,
    @SerializedName("ke-dl")
    val keDl: DojahEnumAttr,
    @SerializedName("ke-id")
    val keId: DojahEnumAttr,
    @SerializedName("ke-kra")
    val keKra: DojahEnumAttr,
    @SerializedName("sa-dl")
    val saDl: DojahEnumAttr,
    @SerializedName("sa-id")
    val saId: DojahEnumAttr,
    val cac: DojahEnumAttr,
    val tin: DojahEnumAttr,
    val other: DojahEnumAttr
) {
    fun toMap(): Map<String, DojahEnumAttr> {
        return mapOf(
            "bvn" to bvn,
            "other" to DojahEnumAttr(),
            "nin" to nin,
            "ao-nin" to aoNin,
            "za-id" to zaId,
            "vnin" to vnin,
            "utilityBill" to utilityBill,
            "dl" to dl,
            "passport" to passport,
            "national" to national,
            "permit" to permit,
            "custom" to custom,
            "voter" to voter,
            "mobile" to mobile,
            "NG-DLI" to ngDli,
            "NG-PASS" to ngPass,
            "NG-NAT" to ngNat,
            "UK-RP" to ukRp,
            "NG-CUSTOM" to ngCustom,
            "NG-VCARD" to ngVcard,
            "NG-NIN-SLIP" to ngNinSlip,
            "selfie" to selfie,
            "otp" to otp,
            "gh-dl" to ghDl,
            "gh-voter" to ghVoter,
            "tz-nin" to tzNin,
            "ug-id" to ugId,
            "ug-telco" to ugTelco,
            "ke-dl" to keDl,
            "ke-id" to keId,
            "ke-kra" to keKra,
            "sa-dl" to saDl,
            "sa-id" to saId,
            "cac" to cac,
            "tin" to tin,
        )
    }
}

data class DojahEnumAttr(
    override val name: String? = null,
    override val abbr: String? = null,
    override val subtext: String? = null,
    override val subtext2: String? = null,
    override val placeholder: String? = null,
    override val enum: String? = null,
    override val spanid: String? = null,
    override val inputType: String? = null,
    override val inputMode: String? = null,
    override val minLength: String? = null,
    override val maxLength: String? = null,
    override val id: String? = null,
    override val idName: String? = null,
    override val value: String? = null,
) : DojahEnumInterface

interface DojahEnumInterface {
    val id: String?
    val name: String?
    val idName: String?
    val abbr: String?
    val subtext: String?
    val subtext2: String?
    val placeholder: String?
    val enum: String?
    val spanid: String?
    val value: String?
    val inputType: String?
    val inputMode: String?
    val minLength: String?
    val maxLength: String?
}
