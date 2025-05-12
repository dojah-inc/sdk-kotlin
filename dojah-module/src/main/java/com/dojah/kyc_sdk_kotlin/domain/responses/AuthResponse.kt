package com.dojah.kyc_sdk_kotlin.domain.responses

import com.dojah.kyc_sdk_kotlin.ui.utils.BusinessType
import com.dojah.kyc_sdk_kotlin.ui.utils.GovDocType
import com.dojah.kyc_sdk_kotlin.ui.utils.KycPages
import com.dojah.kyc_sdk_kotlin.ui.utils.StepStatus
import com.google.gson.annotations.SerializedName


data class AuthResponse(

    @SerializedName("session_id") var sessionId: String? = null,
    @SerializedName("company_name") var companyName: String? = null,
    @SerializedName("white_label") var whiteLabel: Boolean? = null,
    @SerializedName("ucode") var ucode: String? = null,
    @SerializedName("environment") var environment: String? = null,
    @SerializedName("init_data") var initData: InitData? = InitData(),
    @SerializedName("app") var app: App? = App(),

    ) {

    fun copyUpdateFromEmail(emailResponse: SimpleResponse): AuthResponse {
        val emailAuthData = emailResponse.entity?.data

        val emailPages = emailAuthData?.filteredSteps?.toMutableList()


        val emailIdOptionPage = emailPages?.findLast {
            it.name == KycPages.ID_OPTION.serverKey
        }
        val noGovIdOptions = emailIdOptionPage?.config?.govIds?.isEmpty() != false
        if (emailIdOptionPage != null && noGovIdOptions
            && emailIdOptionPage.status != StepStatus.DONE.serverKey
        ) {
            //add options from idPage to id options page
            val emailIdPage = emailPages.findLast { it.name == KycPages.ID.serverKey }
            val oneIdEnabled = emailIdPage?.config?.ids?.reduce() { previous, next ->
                previous == true || next == true
            } == true

            if (emailIdPage != null && oneIdEnabled) {
                ///if one id is enabled, add id option page
                // before id page
                val idConfig = emailIdPage.config
                val idOptionsIndex = (emailPages.indexOf(emailIdOptionPage)).coerceAtLeast(0)
                emailPages[idOptionsIndex] = emailIdOptionPage.copy(
                    config = emailIdOptionPage.config?.copy(
                        passport = idConfig?.passport,
                        dl = idConfig?.dl,
                        voter = idConfig?.voter,
                        vnin = idConfig?.vnin,
                        bvn = idConfig?.bvn,
                        national = idConfig?.national,
                        nin = idConfig?.nin,
                        cac = idConfig?.cac,
                    ),
                )
            }
        }

        val govDataVerifyPage = emailPages?.findLast {
            it.name == KycPages.GOVERNMENT_DATA_VERIFICATION.serverKey
        }
        val hasNoGovModeOptions =
            govDataVerifyPage?.config?.let {
                it.mode?.isBlank() ?: true ||
                        it.phone?.isBlank() ?: true
            } ?: true

        if (govDataVerifyPage != null && hasNoGovModeOptions) {
            val govDataPage = emailPages.findLast {
                it.name == KycPages.GOVERNMENT_DATA.serverKey
            }
            val govDataHasModeOptions =
                govDataPage?.config?.let {
                    it.mode?.isNotBlank() ?: false ||
                            it.phone?.isNotBlank() ?: false
                } ?: false
            if (govDataPage != null && govDataHasModeOptions) {
                val govDataConfig = govDataPage.config
                val govDataVerifyIndex = (emailPages.indexOf(govDataVerifyPage)).coerceAtLeast(0)
                emailPages[govDataVerifyIndex] = govDataVerifyPage.copy(
                    config = govDataVerifyPage.config?.copy(
                        mode = govDataVerifyPage.config?.mode ?: govDataConfig?.mode,
                        phone = govDataVerifyPage.config?.phone ?: govDataConfig?.phone,
                    ),
                )
            }
        }

        sessionId = emailAuthData?.sessionId
        initData = this.initData?.copy(
            authData = emailAuthData?.copy(steps = emailPages ?: listOf())
        )

        return this
    }
}

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
    @SerializedName("tin") var tin: Boolean? = null,
    @SerializedName("verification") var verification: Boolean? = null,
    @SerializedName("type") var type: String? = null,
    @SerializedName("version") var version: Int? = null,
    @SerializedName("instruction") var instruction: String? = null,
    @SerializedName("information") var information: String? = null,
    @SerializedName("title") var title: String? = null,
    @SerializedName("brightnessThreshold") var brightnessThreshold: Int = 40,
    @SerializedName("glassesCheck") var glassesCheck: Boolean = true,
    @SerializedName("disposable") var disposable: Boolean? = null,
    @SerializedName("freeProvider") var freeProvider: Boolean? = null,
    @SerializedName("mode") var mode: String? = null,
    @SerializedName("phone") var phone: String? = null,
    @SerializedName("flipCamera") var flipCamera: Boolean? = null,
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
                    add(GovDocType.PASSPORT.serverKey)
                if (dl == true)
                    add(GovDocType.DL.serverKey)
                if (bvn == true)
                    add(GovDocType.BVN.serverKey)
                if (voter == true)
                    add(GovDocType.VOTER.serverKey)
                if (nin == true)
                    add(GovDocType.NIN.serverKey)
                if (vnin == true)
                    add(GovDocType.VNIN.serverKey)
                if (national == true)
                    add(GovDocType.NATIONAL.serverKey)
            }
        }
    val verificationMethods: List<String>
        get() {
            return mutableListOf<String>(
            ).apply {
                if (otp == true /*&& dl == false*/) {
                    //add otp screen if enabled except for driver licence
                    add("otp")
                }
                if (selfie == true)
                    if (version == 3)
                        add("selfie")
                    else
                        add("selfie-video")
            }
        }
    val businessTypes: List<String>
        get() {
            return mutableListOf<String>(
            ).apply {
                if (cac == true)
                    add(BusinessType.CAC.serverKey)
                if (tin == true)
                    add(BusinessType.TIN.serverKey)
            }
        }
}

data class Step(

    @SerializedName("config") var config: Config? = Config(),
    @SerializedName("name") var name: String? = null,
    @SerializedName("id") var id: Int? = null,
    @SerializedName("status") var status: String? = null,
)

data class AuthData(

    @SerializedName("step_number") var stepNumber: Int? = null,
    @SerializedName("reference_id") var referenceId: String? = null,
    @SerializedName("verification_id") var verificationId: Int? = null,
    @SerializedName("steps") var steps: List<Step> = listOf(),
    @SerializedName("user_data") var userData: UserData? = UserData(),
    @SerializedName("verification_type_selected") var verificationTypeSelected: String? = null,
    @SerializedName("session_id") var sessionId: String? = null,
    @SerializedName("country") var countryAlpha2Code: String? = null,


    ) {

    val filteredSteps: List<Step>
        get() {
            return steps
//            steps.toMutableList().apply {
//                val govData =
//                    find { it.name == KycPages.GOVERNMENT_DATA.serverKey }
//
//                val govDataVerify =
//                    find { it.name == KycPages.GOVERNMENT_DATA_VERIFICATION.serverKey }
//
//                if (govData?.config?.dl == true && govData.config?.otp == true && govData.config?.selfie == false) {
//                    this.remove(govDataVerify)
//                }
//                return this
//            }
        }
    val pages: List<Step>
        get() {
            filteredSteps.toMutableList().apply {

                val govDataVerify =
                    find { it.name == KycPages.GOVERNMENT_DATA_VERIFICATION.serverKey }

                if (govDataVerify != null && govDataVerify.status != StepStatus.DONE.serverKey) {
                    val hasNoGovModeOptions =
                        govDataVerify.config?.let {
                            it.mode?.isBlank() ?: true ||
                                    it.phone?.isBlank() ?: true
                        } ?: true

                    if (hasNoGovModeOptions) {
                        val govData = steps.find { it.name == KycPages.GOVERNMENT_DATA.serverKey }
                        val govDataHasModeOptions =
                            govData?.config?.let {
                                it.mode?.isNotBlank() ?: false ||
                                        it.phone?.isNotBlank() ?: false
                            } ?: false
                        if (govData != null && govDataHasModeOptions) {
                            val govDataConfig = govData.config
                            val govDataVerifyIndex =
                                (indexOf(govDataVerify)).coerceAtLeast(0)
                            this[govDataVerifyIndex] = govDataVerify.copy(
                                config = govDataVerify.config?.copy(
                                    mode = govDataVerify.config?.mode ?: govDataConfig?.mode,
                                    phone = govDataVerify.config?.phone ?: govDataConfig?.phone,
                                ),
                            )
                        }
                    }
                }
                return this.filter {
                    it.status == null || it.status != StepStatus.DONE.serverKey
                }
            }
        }


    data class UserData(
        @SerializedName("first_name") var firstName: String? = null,
        @SerializedName("last_name") var lastName: String? = null,
        @SerializedName("middle_name") var middleName: String? = null,
        @SerializedName("dob") var dob: String? = null,
        @SerializedName("email") var email: String? = null,
        @SerializedName("mobile") var mobile: String? = null,
        @SerializedName("nationality") var nationality: String? = null,
        @SerializedName("residence_country") var residenceCountry: String? = null
    )
}

data class InitData(

    @SerializedName("success") var success: Boolean? = null,
    @SerializedName("msg") var msg: String? = null,
    @SerializedName("data") var authData: AuthData? = AuthData()

)