package com.dojah.sdk_kyc.domain.responses

import com.google.gson.annotations.SerializedName


data class AuthResponse(

    @SerializedName("session_id") var sessionId: String? = null,
    @SerializedName("company_name") var companyName: String? = null,
    @SerializedName("white_label") var whiteLabel: Boolean? = null,
    @SerializedName("ucode") var ucode: String? = null,
    @SerializedName("environment") var environment: String? = null,
    @SerializedName("init_data") var initData: InitData? = InitData(),
    @SerializedName("app") var app: App? = App(),

    )

data class Config(

    @SerializedName("default") var default: String? = null,
    @SerializedName("passport") var passport: Boolean? = null,
    @SerializedName("dl") var dl: Boolean? = null,
    @SerializedName("voter") var voter: Boolean? = null,
    @SerializedName("vnin") var vnin: Boolean? = null,
    @SerializedName("bvn") var bvn: Boolean? = null,
    @SerializedName("selfie") var selfie: Boolean? = null,
    @SerializedName("otp") var otp: Boolean? = null,
    @SerializedName("national") var national: Boolean? = null,
    @SerializedName("nin") var nin: Boolean? = null,
    @SerializedName("cac") var cac: Boolean? = null,
    @SerializedName("verification") var verification: Boolean? = null,
    @SerializedName("type") var type: String? = null,
    @SerializedName("version") var version: Int? = null,
    @SerializedName("instruction") var instruction: String? = null,
    @SerializedName("brightnessThreshold") var brightnessThreshold: Int = 40,
    @SerializedName("glassesCheck") var glassesCheck: Boolean = true,
    ) {
    val ids: List<Boolean?>
        get() {
            return listOf(
                passport,
                dl,
                voter,
                vnin,
                bvn,
                national,
                nin,
            )
        }
    val govIds: List<String>
        get() {
            return mutableListOf<String>(
            ).apply {
                if (passport == true)
                    add("passport")
                if (dl == true)
                    add("dl")
                if (bvn == true)
                    add("bvn")
                if (voter == true)
                    add("voter")
                if (nin == true)
                    add("nin")
                if (vnin == true)
                    add("vnin")
                if (national == true)
                    add("national")
            }
        }
    val verificationMethods: List<String>
        get() {
            return mutableListOf<String>(
            ).apply {
                if (otp == true)
                    add("otp")
                if (selfie == true)
                    if (version == 3)
                        add("selfie")
                    else
                        add("selfie-video")
            }
        }
}

data class Step(

    @SerializedName("config") var config: Config? = Config(),
    @SerializedName("name") var name: String? = null,
    @SerializedName("id") var id: Int? = null,
)

data class AuthData(

    @SerializedName("step_number") var stepNumber: Int? = null,
    @SerializedName("reference_id") var referenceId: String? = null,
    @SerializedName("verification_id") var verificationId: Int? = null,
    @SerializedName("steps") var steps: List<Step> = listOf()

)

data class InitData(

    @SerializedName("success") var success: Boolean? = null,
    @SerializedName("msg") var msg: String? = null,
    @SerializedName("data") var authData: AuthData? = AuthData()

)