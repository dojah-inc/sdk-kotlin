package com.dojah.kyc_sdk_kotlin.domain.responses


import com.google.gson.annotations.SerializedName

 data class BizLookupResponse(
    val entity: BizLookupEntity?
) {
     data class BizLookupEntity(
        val address: String?, // 7,PIPELINELAYOU,BESIDEREFINARY,EKPAN,DELTA
        val business: String?, // 263c2027-793f-4c10-9d08-04abced2f1c7
        @SerializedName("company_name") val companyName: String?, // EFO GLOBAL SYSTEMS LIMITED
        @SerializedName("date_of_registration") val dateOfRegistration: String?, // 2009-03-04T23:00:00+00:00
        @SerializedName("rc_number") val rcNumber: String?, // 805396
        val tin: String?, // 805396
        val status: String?, // ACTIVE
        @SerializedName("type_of_company") val typeOfCompany: String? // COMPANY
    )
}