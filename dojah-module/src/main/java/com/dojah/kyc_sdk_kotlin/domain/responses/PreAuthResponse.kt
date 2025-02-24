package com.dojah.kyc_sdk_kotlin.domain.responses

import com.dojah.kyc_sdk_kotlin.domain.request.AuthReqSteps
import com.dojah.kyc_sdk_kotlin.domain.request.AuthRequest
import com.dojah.kyc_sdk_kotlin.ui.utils.*
import com.google.gson.annotations.SerializedName
import java.lang.Exception

private val USER_DATA = "user-data"

private val governmentData = KycPages.GOVERNMENT_DATA.serverKey
private val idPage = KycPages.ID.serverKey
private val emailPage = KycPages.EMAIL.serverKey
private const val COUNTRY_PAGE = "countries"
private const val INDEX_PAGE = "index"
private val idOptionPage = KycPages.ID_OPTION.serverKey

private val govVerification = KycPages.GOVERNMENT_DATA_VERIFICATION.serverKey

data class PreAuthResponse(
    @SerializedName("widget") var widget: Widget = Widget(),
    @SerializedName("public_key") var publicKey: String? = null,
    @SerializedName("app") var app: App? = App()
) {
    fun toAuthRequest(referenceId: String? = null, email: String? = null): AuthRequest {
        val preAuthPages = widget.pages
        return AuthRequest(
            publicKey = publicKey,
            appId = app?.id,
            type = "kyc",
            reviewProcess = "Automatic",
            rules = widget.rules,
            duplicateCheck = widget.duplicateCheck,
            directFeedback = widget.directFeedback,
            referenceId = referenceId,
            email = email,
            steps = mutableListOf(
                AuthReqSteps(
                    name = INDEX_PAGE, authReqConfigConfig = Config(default = "")
                ),
                AuthReqSteps(
                    name = COUNTRY_PAGE, authReqConfigConfig = Config(default = "")
                )

            ).plus(preAuthPages.map { preAuthPage ->
                val config = preAuthPage.config ?: throw Exception("Config can't be null")
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
                        information = config.information,
                        title = config.title,
                        brightnessThreshold = config.brightnessThreshold,
                        glassesCheck = config.glassesCheck,
                        disposable = config.disposable,
                        freeProvider = config.freeProvider,
                        flipCamera = config.flipCamera
                    )
                )
            }).toMutableList().apply {
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
                val emailStep = this.findLast { it.name == emailPage }
                val nearestFirstIndex = getNearestFirstIndex()
                val checkDuplicate = widget.duplicateCheck == true || widget.directFeedback == true
                if (checkDuplicate && emailStep != null) {
                    //if email page is present, move it to the nearest first index
                    if (nearestFirstIndex != -1) {
                        this.remove(emailStep)
                        this.add(nearestFirstIndex + 1, emailStep)
                    }
                } else if (checkDuplicate) {
                    //if email page is not present, add it to the nearest first index
                    if (nearestFirstIndex != -1) {
                        this.add(
                            nearestFirstIndex + 1,
                            AuthReqSteps(name = emailPage, authReqConfigConfig = Config())
                        )
                    }

                }

            }.mapIndexed { index, it -> it.copy(id = index) }

        )
    }

    private fun MutableList<AuthReqSteps>.getNearestFirstIndex(): Int {
//        val countryPage = this.findLast { it.name == COUNTRY_PAGE }
//        return if (countryPage != null) indexOf(countryPage) else indexOf(indexPage)
        val indexPage = this.findLast { it.name == INDEX_PAGE }
        return indexOf(indexPage)
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
    @SerializedName("duplicate_check") var duplicateCheck: Boolean? = null,
    @SerializedName("direct_feedback") var directFeedback: Boolean? = null,
    @SerializedName("rules") var rules: Rules? = Rules(),
) {
    data class AmlScreening(
        @SerializedName("action_returned") var actionReturned: String? = null,
    )

    data class IpScreening(
        @SerializedName("action_blacklisted") var actionBlacklisted: String? = null,
    )


    data class Rules(

        @SerializedName("user_data") var userData: UserData? = UserData(),
        @SerializedName("aml_screening") var amlScreening: AmlScreening? = null,
        @SerializedName("ip_screening") var ipScreening: IpScreening? = null,
        @SerializedName("liveness_match") var livenessMatch: LivenessMatch? = LivenessMatch()

    ) {

        data class UserData(
            @SerializedName("fields") var fields: ArrayList<String> = arrayListOf(),
            @SerializedName("action") var action: String? = null
        )

        data class LivenessMatch(
            @SerializedName("glassesCheck") var glassesCheck: Boolean? = null,
            @SerializedName("brightnessThreshold") var brightnessThreshold: Int? = null
        )
    }
}

data class App(
    @SerializedName("name") var name: String? = null,
    @SerializedName("logo") var logo: String? = null,
    @SerializedName("color_code") var colorCode: String? = null,
    @SerializedName("id") var id: String? = null
)