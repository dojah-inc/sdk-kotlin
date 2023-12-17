package com.dojah.sdk_kyc.domain.responses

import com.dojah.sdk_kyc.domain.request.AuthReqSteps
import com.dojah.sdk_kyc.domain.request.AuthRequest
import com.dojah.sdk_kyc.ui.utils.*
import com.google.gson.annotations.SerializedName
import okhttp3.logging.HttpLoggingInterceptor
import java.lang.Exception

private val USER_DATA = "user-data"

private val GOVERNMENT_DATA = KycPages.GOVERNMENT_DATA.serverKey
private val ID_PAGE = KycPages.ID.serverKey
private val ID_OPTION_PAGE = KycPages.ID_OPTION.serverKey

private val GOV_VERIFICATION = KycPages.GOVERNMENT_DATA_VERIFICATION.serverKey

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
                AuthReqSteps(
                    name = preAuthPage.page,
                    authReqConfigConfig = Config(
                        default = config?.default,
                        passport = config?.passport,
                        dl = config?.dl,
                        voter = config?.voter,
                        vnin = config?.vnin,
                        bvn = config?.bvn,
                        selfie = config?.selfie,
                        otp = config?.otp,
                        national = config?.national,
                        nin = config?.nin,
                        cac = config?.cac,
                        verification = config?.verification,
                        type = config?.type,
                        version = config?.version,
                        instruction = config?.instruction
                    )
                )
            }).toMutableList().apply {
//                HttpLoggingInterceptor.Logger.DEFAULT.log("PreAuthResponse toAuthRequest ${this}")
                val govDataPage = this.findLast { it.name == GOVERNMENT_DATA }
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
                            name = GOV_VERIFICATION,
                            authReqConfigConfig = Config(
                                selfie = config?.selfie,
                                otp = config?.otp,
                            ),
                        )
                    )
                }

                val idPage = this.findLast { it.name == ID_PAGE }
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
                            name = ID_OPTION_PAGE,
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
    @SerializedName("company") var company: Company? = Company()
)

data class App(
    @SerializedName("name") var name: String? = null,
    @SerializedName("logo") var logo: String? = null,
    @SerializedName("color_code") var colorCode: String? = null,
    @SerializedName("id") var id: String? = null
)