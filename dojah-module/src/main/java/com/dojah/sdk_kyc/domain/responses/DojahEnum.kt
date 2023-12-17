package com.dojah.sdk_kyc.domain.responses


import com.google.gson.annotations.SerializedName

data class DojahEnum(
    val bvn: EnumAttr,
    val nin: EnumAttr,
    val vnin: EnumAttr,
    val dl: EnumAttr,
    val passport: EnumAttr,
    val national: EnumAttr,
    val permit: EnumAttr,
    val custom: EnumAttr,
    val voter: EnumAttr,
    val mobile: EnumAttr,
    @SerializedName("NG-DLI")
    val ngDli: EnumAttr,
    @SerializedName("NG-PASS")
    val ngPass: EnumAttr,
    @SerializedName("NG-NAT")
    val ngNat: EnumAttr,
    @SerializedName("UK-RP")
    val ukRp: EnumAttr,
    @SerializedName("NG-CUSTOM")
    val ngCustom: EnumAttr,
    @SerializedName("NG-VCARD")
    val ngVcard: EnumAttr,
    @SerializedName("NG-NIN-SLIP")
    val ngNinSlip: EnumAttr,
    val selfie: EnumAttr,
    val otp: EnumAttr,
    @SerializedName("gh-dl")
    val ghDl: EnumAttr,
    @SerializedName("gh-voter")
    val ghVoter: EnumAttr,
    @SerializedName("tz-nin")
    val tzNin: EnumAttr,
    @SerializedName("ug-id")
    val ugId: EnumAttr,
    @SerializedName("ug-telco")
    val ugTelco: EnumAttr,
    @SerializedName("ke-dl")
    val keDl: EnumAttr,
    @SerializedName("ke-id")
    val keId: EnumAttr,
    @SerializedName("ke-kra")
    val keKra: EnumAttr,
    @SerializedName("sa-dl")
    val saDl: EnumAttr,
    @SerializedName("sa-id")
    val saId: EnumAttr,
    val cac: EnumAttr,
    val tin: EnumAttr,
) {
    fun toMap(): Map<String, EnumAttr> {
        return mapOf(
            "bvn" to bvn,
            "nin" to nin,
            "vnin" to vnin,
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

data class EnumAttr(
    override val name: String?,
    override val abbr: String?,
    override val subtext: String?,
    override val subtext2: String?,
    override val placeholder: String?,
    override val enum: String?,
    override val spanid: String?,
    override val inputType: String?,
    override val inputMode: String?,
    override val minLength: String?,
    override val maxLength: String?,
    override val id: String?,
    override val idName: String?,
    override val value: String?,
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
