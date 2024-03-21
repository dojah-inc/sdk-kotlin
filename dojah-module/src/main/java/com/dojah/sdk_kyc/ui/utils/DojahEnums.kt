package com.dojah.sdk_kyc.ui.utils

import android.text.InputType
import com.dojah.sdk_kyc.core.Result
import com.dojah.sdk_kyc.domain.responses.EnumAttr
import com.dojah.sdk_kyc.ui.main.fragment.datacollection.BioDataFragment
import com.dojah.sdk_kyc.ui.main.fragment.datacollection.BusinessDataFragment
import com.dojah.sdk_kyc.ui.main.fragment.datacollection.CountryFragment
import com.dojah.sdk_kyc.ui.main.fragment.datacollection.DisclaimerFragment
import com.dojah.sdk_kyc.ui.main.fragment.datacollection.DocTypeFragment
import com.dojah.sdk_kyc.ui.main.fragment.datacollection.EmailOtpFragment
import com.dojah.sdk_kyc.ui.main.fragment.datacollection.EnterOtpFragment
import com.dojah.sdk_kyc.ui.main.fragment.datacollection.GovDataFragment
import com.dojah.sdk_kyc.ui.main.fragment.datacollection.HomeAddressFragment
import com.dojah.sdk_kyc.ui.main.fragment.datacollection.IndexDisclaimerFragment
import com.dojah.sdk_kyc.ui.main.fragment.datacollection.PhoneOtpFragment
import com.dojah.sdk_kyc.ui.main.fragment.datacollection.SelfieDisclaimerFragment
import com.dojah.sdk_kyc.ui.main.fragment.datacollection.SignatureFragment
import com.dojah.sdk_kyc.ui.main.viewmodel.GovDataViewModel


enum class VerificationType(
    val value: String,
    val serverKey: String,
    val actualServerKey: String = "",
    val type: String? = "",
    val title: String = "",
    val preview: String = ""
) {
    Selfie(
        "Selfie",
        "selfie",
        actualServerKey = "selfie",
        type = "capture",
        title = "Place your face in the circle and click Capture",
        preview = "Preview your Selfie",
    ),
    SelfieVideo(
        "Video KYC",
        "selfie-video",
        actualServerKey = "selfie",
        type = "video",
        title = "Place your face in the circle and click Record",
        preview = "Preview your Video"
    ),
    OTP("OTP", serverKey = "otp"),

    PHONE_OTP("Phone Number OTP", "phone number"),
    EMAIL_OTP("Email OTP", "email"),
    HOME_ADDRESS("Home Address", "home address");

    companion object {
        fun enumOfValue(value: String): VerificationType? {
            return VerificationType.values().find { it.value == value }
        }

        fun findEnumWithKey(serverKey: String?): VerificationType? {
            return VerificationType.values().find { it.serverKey == serverKey }
        }
    }
}


enum class GovDocType(
    val sName: String,
    val serverKey: String,
    val title: String = "",
    val info: String = "",
    val inputType: Int = InputType.TYPE_CLASS_TEXT,
    val id: String = "",
    val value: String = "",
    val hasBack: Boolean = true,
) {

    DL(
        "Driver\'s License",
        "dl",
        "Capture the Driver’s License Document",
        "Make sure your Driver’s License Document is properly placed, and hold it still for a few seconds",
        inputType = InputType.TYPE_CLASS_TEXT,
        id = "drivers-license",
        value = "NG-DLI",
    ),
    BVN(
        "Bank Verification Number",
        "bvn",
        "Capture the CAC Document",
        "Make sure your CAC Document is properly placed, and hold it still for a few seconds",
        inputType = InputType.TYPE_CLASS_NUMBER,
    ),
    VOTER(
        "Voter\'s Card",
        "voter",
        "Capture the Voter’s Card Document",
        "Make sure your Voter’s Card Document is properly placed, and hold it still for a few seconds",
        id = "voter-id",
        value = "NG-VCARD"
    ),
    PASSPORT(
        "International Passport",
        "passport",
        "Capture your International Passport",
        "Make sure your International Passport Document is properly placed, and hold it still for a few seconds",
        value = "NG-PASS",
        id = "international-passport",
        hasBack = false,
    ),
    NIN(
        "National Identification Number",
        "nin",
        "Capture the NIN Document",
        "Make sure your NIN Document is properly placed, and hold it still for a few seconds",
        inputType = InputType.TYPE_CLASS_NUMBER,
        id = "nin-slip",
        value = "NG-NIN-SLIP",
        hasBack = false,
    ),
    NATIONAL(
        "National ID",
        "national",
        "Capture the National ID Document",
        "Make sure your National ID Document is properly placed, and hold it still for a few seconds",
        value = "NG-NAT",
        id = "national-id"
    ),
    VNIN(
        "Virtual National Identification Number",
        "vnin",
        "Capture the VNIN Document",
        "Make sure your VNIN Document is properly placed, and hold it still for a few seconds",
        inputType = InputType.TYPE_CLASS_NUMBER,
        id = "vnin",
        value = "NG-VNIN",
    ),
    BUSINESS(
        "CAC",
        "cac",
        "Capture the CAC Document",
        "Make sure your CAC Document is properly placed, and hold it still for a few seconds",
        hasBack = false,
    ),
    OTHER(
        "Other Doc",
        "other",
        "Upload Document",
        hasBack = false,
    );

    companion object {
        fun enumOfValue(value: String): GovDocType? {
            return values().find { it.sName == value }
        }

        fun findEnumWithKey(serverKey: String?): GovDocType? {
            return values().find { it.serverKey == serverKey }
        }
    }

}


enum class BusinessType(
    val serverKey: String,
) {
    CAC(
        "cac",
    ),
    TIN(
        "tin",
    ),
}

enum class KycPages(
    val serverKey: String,
    val fragmentClassName: String,
    val optionPages: List<Pair<String, String>>? = null
) {
    INDEX(
        "index", IndexDisclaimerFragment::class.java.name
    ),
    COUNTRY(
        "countries",
        CountryFragment::class.java.name,
    ),
    USER_DATA(
        "user-data",
        BioDataFragment::class.java.name,
    ),
    GOVERNMENT_DATA(
        "government-data",
        GovDataFragment::class.java.name,
    ),
    GOVERNMENT_DATA_VERIFICATION(
        "government-data-verification", "", listOf(
            Pair(
                VerificationType.Selfie.serverKey,
                SelfieDisclaimerFragment::class.java.name,
            ),
            Pair(
                VerificationType.SelfieVideo.serverKey,
                SelfieDisclaimerFragment::class.java.name,
            ),
            Pair(
                VerificationType.OTP.serverKey,
                EnterOtpFragment::class.java.name,
            ),
        )
    ),
    ID_OPTION(
        "id-options",
        DocTypeFragment::class.java.name,
    ),
    ID(
        "id",
        DisclaimerFragment::class.java.name,
    ),
    BUSINESS_DATA(
        "business-data",
        BusinessDataFragment::class.java.name,
    ),
    PHONE_NUMBER(
        "phone-number",
        PhoneOtpFragment::class.java.name,
    ),
    EMAIL(
        "email",
        EmailOtpFragment::class.java.name,
    ),
    BUSINESS_ID(
        "business-id",
        DisclaimerFragment::class.java.name,
    ),
    ADDRESS(
        "address",
        HomeAddressFragment::class.java.name,
    ),
    SELFIE(
        "selfie",
        SelfieDisclaimerFragment::class.java.name,
    ),
    OTHER_DOCUMENT(
        "additional-document",
        DisclaimerFragment::class.java.name,
    ),
    SIGNATURE(
        "signature",
        SignatureFragment::class.java.name,
    );

    companion object {
        fun findPageEnum(serverKey: String): KycPages? {
            return values().find { it.serverKey == serverKey }
        }
    }
}

enum class EventTypes(val serverKey: String) {
    STEP_COMPLETED("step_completed"),
    STEP_FAILED("step_failed"),
    COUNTRY_SELECTED("country_selected"),
    AML_CHECK_STARTED("aml_check"),
    PHONE_NUMBER_VALIDATION("phone_number_validation"),
    VERIFICATION_TYPE_SELECTED("verification_type_selected"),
    VERIFICATION_MODE_SELECTED("verification_mode_selected"),
    GOVERNMENT_IMAGE_COLLECTED("government_image_collected"),
    CUSTOMER_GOVERNMENT_DATA_COLLECTED("customer_government_data_collected"),
    EMAIL_COLLECTED("email_collected"),
    CUSTOMER_BUSINESS_DATA_COLLECTED("customer_business_data_collected"),
}

const val govIDTemplateKey = "%ID%"

enum class FailedReasons(val code: String, val message: String, val statusCode: Int = -1000) {
    LOW_BALANCE("00", "An error occurred. Try again later", 402),
    UNKNOWN("01", "An error occurred. Try again later", 500),
    ID_INVALID_NOT_FOUND(
        "02",
        "Invalid $govIDTemplateKey Number. Input a valid $govIDTemplateKey Number or try another means \nof Identification",
        404,
    ),
    THIRD_PARTY(
        "03",
        "$govIDTemplateKey Number is currently not available. Please try another means of identification",
        424,
    ),
    INVALID_OTP("04", "Invalid OTP entered. Please, input the correct OTP"),
    OTP_NOT_SENT("05", "OTP Could not be sent, please try again"),
    ID_FAILED_MAX_TIME("10", "Your verification is awaiting approval"),
    SELFIE_NO_CAPTURE("06", "Please move to a well lit environment and try again"),
    VIDEO_NO_CAPTURE("07", "Please move to a well lit environment and try again"),
    GOV_ID_CAPTURE("08", "Document is not clear enough, please try again"),
    WIDGET_NOT_AVAILABLE("15", "Widget is not supported in your country"),
    GOV_DATA_NOT_AVAILABLE("20", "Verification is not available \nin your country");


    fun getGovBizMsg(idType: EnumAttr?): String {
        if (this == ID_INVALID_NOT_FOUND || this == THIRD_PARTY) {
            val govIdName = idType?.name?.replace("Number", "")?.trim()
            return this.message.replace(govIDTemplateKey, govIdName ?: "ID")
        } else {
            throw Exception("This not ID_INVALID_NOT_FOUND Reason")
        }
    }

    companion object {
        fun getStatusCodeReason(
            error: Result.Error.ApiError,
        ): FailedReasons? {
            return when (error.code ?: -1000) {
                LOW_BALANCE.statusCode ->  LOW_BALANCE

                ID_INVALID_NOT_FOUND.statusCode -> ID_INVALID_NOT_FOUND

                THIRD_PARTY.statusCode ->  THIRD_PARTY

                in UNKNOWN.statusCode..599 ->  UNKNOWN


                else -> null
            }

        }
    }
}
