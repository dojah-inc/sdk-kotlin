package com.dojah.sdk_kyc.domain.responses

import com.dojah.sdk_kyc.domain.request.AuthReqSteps
import com.dojah.sdk_kyc.domain.request.AuthRequest
import com.dojah.sdk_kyc.ui.utils.*
import com.google.gson.annotations.SerializedName
import java.lang.Exception

private val USER_DATA = "user-data"

private val governmentData = KycPages.GOVERNMENT_DATA.serverKey
private val idPage = KycPages.ID.serverKey
private val idOptionPage = KycPages.ID_OPTION.serverKey

private val govVerification = KycPages.GOVERNMENT_DATA_VERIFICATION.serverKey

data class PreAuthResponse(
    @SerializedName("widget") var widget: Widget? = Widget(),
    @SerializedName("public_key") var publicKey: String? = null,
    @SerializedName("app") var app: App? = App()
) {
    fun toAuthRequest(): AuthRequest {
        if (widget == null) {
            throw Exception("Widget can't be null")
        }
        val preAuthPages = widget!!.pages
        return AuthRequest(
            publicKey = publicKey,
            appId = app?.id,
            type = "kyc",
            reviewProcess = "Automatic",
            steps = mutableListOf(
                AuthReqSteps(
                    name = "index", authReqConfigConfig = Config(default = "")
                ),
                AuthReqSteps(
                    name = "countries", authReqConfigConfig = Config(default = "")
                )

            ).plus(preAuthPages.map { preAuthPage ->
                val config = preAuthPage.config
                if (config == null) {
                    throw Exception("Config can't be null")
                }
                AuthReqSteps(
                    name = preAuthPage.page,
                    authReqConfigConfig = Config(
                        default = config.default,
                        passport = config.passport,
                        dl = config.dl,
                        voter = config.voter,
                        vnin = config.vnin,
                        bvn = config.bvn,
                        selfie = config.selfie,
                        otp = config.otp,
                        national = config.national,
                        nin = config.nin,
                        cac = config.cac,
                        verification = config.verification,
                        type = config.type,
                        version = config.version,
                        instruction = config.instruction,
                        brightnessThreshold = config.brightnessThreshold,
                        glassesCheck = config.glassesCheck,
                    )
                )
            }).toMutableList().apply {
//                HttpLoggingInterceptor.Logger.DEFAULT.log("PreAuthResponse toAuthRequest ${this}")
                val govDataPage = this.findLast { it.name == governmentData }
                val verificationEnabled =
                    govDataPage?.authReqConfigConfig?.selfie == true || govDataPage?.authReqConfigConfig?.otp == true

                if (govDataPage != null && verificationEnabled) {
                    ///if selfie or otp is enabled, add verification page
                    // after government data page
                    val config = govDataPage.authReqConfigConfig
                    val govVerifyIndex = indexOf(govDataPage) + 1
                    this.add(
                        govVerifyIndex,
                        AuthReqSteps(
                            name = govVerification,
                            authReqConfigConfig = Config(
                                selfie = config?.selfie,
                                otp = config?.otp,
                                version = config?.version,
                            ),
                        )
                    )
                }

                val idPage = this.findLast { it.name == idPage }
                val oneIdEnabled = idPage?.authReqConfigConfig?.ids?.reduce() { previous, next ->
                    previous == true || next == true
                } == true

                if (idPage != null && oneIdEnabled) {
                    ///if one id is enabled, add id option page
                    // before id page
                    val config = idPage.authReqConfigConfig
                    val idOptionsIndex = (indexOf(idPage)).coerceAtLeast(0)
                    this.add(
                        idOptionsIndex,
                        AuthReqSteps(
                            name = idOptionPage,
                            authReqConfigConfig = Config(
                                passport = config?.passport,
                                dl = config?.dl,
                                voter = config?.voter,
                                vnin = config?.vnin,
                                bvn = config?.bvn,
                                national = config?.national,
                                nin = config?.nin,
                                cac = config?.cac,
                            ),
                        )
                    )
                }
            }.mapIndexed { index, it -> it.copy(id = index) }

        )
    }
}

data class Pages(
    @SerializedName("page") var page: String? = null,
    @SerializedName("config") var config: Config? = Config(),

    )

data class Company(
    @SerializedName("prod_public_key") var prodPublicKey: String? = null
)

data class Widget(
    @SerializedName("published") var published: Boolean? = null,
    @SerializedName("pages") var pages: ArrayList<Pages> = arrayListOf(),
    @SerializedName("country") var country: ArrayList<String> = arrayListOf(),
    @SerializedName("env") var env: String? = null,
    @SerializedName("company") var company: Company? = Company(),
    @SerializedName("aml_screening") var amlScreening: AmlScreening? = AmlScreening(),
    @SerializedName("ip_screening") var ipScreening: IpScreening? = IpScreening(),
) {
    data class AmlScreening(
        @SerializedName("action_returned") var actionReturned: String? = null,
    )

    data class IpScreening(
        @SerializedName("action_blacklisted") var actionBlacklisted: String? = null,
    )
}

data class App(
    @SerializedName("name") var name: String? = null,
    @SerializedName("logo") var logo: String? = null,
    @SerializedName("color_code") var colorCode: String? = null,
    @SerializedName("id") var id: String? = null
)