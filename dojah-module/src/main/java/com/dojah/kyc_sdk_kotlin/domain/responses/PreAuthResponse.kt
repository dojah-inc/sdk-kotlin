package com.dojah.kyc_sdk_kotlin.domain.responses

import com.dojah.kyc_sdk_kotlin.domain.request.AuthReqSteps
import com.dojah.kyc_sdk_kotlin.domain.request.AuthRequest
import com.dojah.kyc_sdk_kotlin.ui.utils.*
import com.google.gson.JsonObject
import com.google.gson.annotations.SerializedName

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
        var verificationSteps = AuthRequest(
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
                )
            )
        )

        preAuthPages.findLast { it.page == governmentData }?.let {
            verificationSteps =
                verificationSteps.copy(steps = verificationSteps.steps.toMutableList().apply {
                    add(
                        AuthReqSteps(
                            name = COUNTRY_PAGE, authReqConfigConfig = Config(default = "")
                        )
                    )
                })
        }

        return verificationSteps.copy(
            steps = verificationSteps.steps.plus(
                preAuthPages.map { preAuthPage ->
                    val config = preAuthPage.config ?: throw Exception("Config can't be null")
                    AuthReqSteps(
                        name = preAuthPage.page,
                        authReqConfigConfig = Config(
                            default = config.default,
                            passport = config.passport,
                            dl = config.dl,
                            bvnAdvance = config.bvnAdvance,
                            whatsappVerification = config.whatsappVerification,
                            whatsappOtp = config.whatsappOtp,
                            voter = config.voter,
                            vnin = config.vnin,
                            bvn = config.bvn,
                            selfie = config.selfie,
                            otp = config.otp,
                            national = config.national,
                            nin = config.nin,
                            cac = config.cac,
                            keDl = config.keDl,
                            keId = config.keId,
                            keKra = config.keKra,
                            saDl = config.saDl,
                            saId = config.saId,
                            ghDl = config.ghDl,
                            ghVoter = config.ghVoter,
                            tzNin = config.tzNin,
                            verification = config.verification,
                            liveLocation = config.liveLocation,
                            utilityBill = config.utilityBill,
                            hideUpload = config.hideUpload,
                            type = config.type,
                            version = config.version,
                            instruction = config.instruction,
                            information = config.information,
                            title = config.title,
                            brightnessThreshold = config.brightnessThreshold,
                            glassesCheck = config.glassesCheck,
                            disposable = config.disposable,
                            freeProvider = config.freeProvider,
                            flipCamera = config.flipCamera,
                            questions = config.questions
                        )
                    )
                }).toMutableList().apply {
                val modifiedSteps = mutableListOf<AuthReqSteps>()

                this.forEachIndexed { index, data ->
                    // Always add the current step
                    modifiedSteps.add(data)

                    // Handle governmentData logic
                    if (data.name == governmentData) {
                        val verificationEnabled = data.authReqConfigConfig?.selfie == true ||
                                data.authReqConfigConfig?.otp == true

                        if (verificationEnabled) {
                            modifiedSteps.add(
                                AuthReqSteps(
                                    name = govVerification,
                                    authReqConfigConfig = Config(
                                        selfie = data.authReqConfigConfig?.selfie,
                                        otp = data.authReqConfigConfig?.otp,
                                        version = data.authReqConfigConfig?.version,
                                    )
                                )
                            )
                        }
                    }

                    // Handle ID page logic
                    if (data.name == idPage) {
                        val oneIdEnabled = data.authReqConfigConfig?.ids?.reduce { prev, next ->
                            prev == true || next == true
                        } == true

                        if (oneIdEnabled) {
                            modifiedSteps.add(
                                modifiedSteps.lastIndex.coerceAtLeast(0),
                                AuthReqSteps(
                                    name = idOptionPage,
                                    authReqConfigConfig = Config(
                                        passport = data.authReqConfigConfig?.passport,
                                        dl = data.authReqConfigConfig?.dl,
                                        voter = data.authReqConfigConfig?.voter,
                                        vnin = data.authReqConfigConfig?.vnin,
                                        bvn = data.authReqConfigConfig?.bvn,
                                        national = data.authReqConfigConfig?.national,
                                        nin = data.authReqConfigConfig?.nin,
                                        cac = data.authReqConfigConfig?.cac,
                                    )
                                )
                            )
                        }
                    }
                }

                // Process email step separately
                val checkDuplicate = widget.duplicateCheck == true || widget.directFeedback == true
                if (checkDuplicate) {
                    val emailStep = modifiedSteps.findLast { it.name == emailPage }
                    val nearestFirstIndex = modifiedSteps.getNearestFirstIndex()

                    if (emailStep != null) {
                        modifiedSteps.remove(emailStep)
                        modifiedSteps.add(nearestFirstIndex + 1, emailStep)
                    } else {
                        modifiedSteps.add(
                            nearestFirstIndex + 1,
                            AuthReqSteps(name = emailPage, authReqConfigConfig = Config())
                        )
                    }
                }

                // Apply modified steps
                this.clear()
                this.addAll(modifiedSteps)
            }.mapIndexed { index, it -> it.copy(id = index) })
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
    @SerializedName("rules") var rules: JsonObject? = null,
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