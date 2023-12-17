package com.dojah.sdk_kyc.ui.utils

import android.text.InputType
import com.dojah.sdk_kyc.ui.main.fragment.datacollection.BioDataFragment
import com.dojah.sdk_kyc.ui.main.fragment.datacollection.BizDocTypeFragment
import com.dojah.sdk_kyc.ui.main.fragment.datacollection.BusinessDataFragment
import com.dojah.sdk_kyc.ui.main.fragment.datacollection.CaptureDocumentFragment
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


enum class BusinessDataType(val value: String) {
    RC_NUMBER("RC Number"), TIN("TIN Number");

    companion object {
        fun enumOfValue(value: String): VerificationType? {
            return VerificationType.values().find { it.value == value }
        }
    }
}

enum class VerificationType(
    val value: String,
    val serverKey: String,
    val details: String? = "",
    val title: String = "",
    val preview: String = ""
) {
    Selfie(
        "Selfie", "Place your face in the circle and click Capture", "Preview your Selfie"
    ),
    Video(
        "Video KYC",
        "selfie",
        "Place your face in the circle and click Record",
        "Preview your Video"
    ),
    OTP("OTP", "otp"), PHONE_OTP("Phone Number OTP", "phone number"), EMAIL_OTP(
        "Email OTP", "email"
    ),
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
    val abbr: String = "",
    val subtext: String = "",
    val subtext2: String = "",
    val placeholder: String = "",
    val spanid: String = "",
    val inputType: Int = InputType.TYPE_CLASS_TEXT,
    val inputMode: String = "",
    val minLength: Int? = null,
    val maxLength: Int? = null,
    val id: String = "",
    val value: String = "",
) {

    DL(
        "Driver’s License",
        "dl",
        "Capture the Driver’s License Document",
        "Make sure your Driver’s License Document is properly placed, and hold it still for a few seconds",
        abbr = "Driver's License",
        placeholder = "ABC98765AA77",
        inputType = InputType.TYPE_CLASS_TEXT,
        inputMode = "text",
        id = "drivers-license",
        value = "NG-DLI",
        spanid = "d-none"
    ),
    BVN(
        "Bank Verification Number",
        "bvn",
        "Capture the CAC Document",
        "Make sure your CAC Document is properly placed, and hold it still for a few seconds",
        abbr = "BVN",
        placeholder = "22398337867",
        spanid = "d-none",
        inputType = InputType.TYPE_CLASS_NUMBER,
        inputMode = "numeric",
        minLength = 11,
        maxLength = 11,
    ),
    VOTER(
        "Voter’s Card",
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
        "Make sure your International Passpor Document is properly placed, and hold it still for a few seconds",
        value = "NG-PASS",
        id = "international-passport"
    ),
    NIN(
        "National Identification Number",
        "nin",
        "Capture the NIN Document",
        "Make sure your NIN Document is properly placed, and hold it still for a few seconds",
        abbr = "NIN",
        placeholder = "56743378909",
        spanid = "d-none",
        inputType = InputType.TYPE_CLASS_NUMBER,
        inputMode = "numeric",
        minLength = 11,
        maxLength = 11,
        id = "nin-slip",
        value = "NG-NIN-SLIP",
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
        abbr = "VNIN",
        subtext = "Dial *346*3*NIN*1138183# to generate your VNIN",
        subtext2 = "Note, you can only use the VNIN generated once",
        placeholder = "SE426838975455SC",
        spanid = "d-none",
        inputType = InputType.TYPE_CLASS_NUMBER,
        inputMode = "numeric",
        minLength = 11,
        maxLength = 11,
        id = "vnin",
        value = "NG-VNIN",
    );
//    BUSINESS(
//        "CAC",
//        "cac",
//        "Capture the CAC Document",
//        "Make sure your CAC Document is properly placed, and hold it still for a few seconds"
//    ),
//    OTHER(
//        "Other Doc",
//        "Upload Document",
//        "Make sure your Document is properly placed, and hold it still for a few seconds"
//    );

    companion object {
        fun enumOfValue(value: String): GovDocType? {
            return values().find { it.sName == value }
        }

        fun findEnumWithKey(serverKey: String?): GovDocType? {
            return values().find { it.serverKey == serverKey }
        }
    }

}

enum class KycPages(
    val serverKey: String,
    val fragmentClassName: String,
    val optionpages: List<Pair<String, String>>? = null
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
            VerificationType.Video.serverKey to SelfieDisclaimerFragment::class.java.name,
            VerificationType.OTP.serverKey to EnterOtpFragment::class.java.name,
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
        BizDocTypeFragment::class.java.name,
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
        CaptureDocumentFragment::class.java.name,
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
