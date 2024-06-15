package com.dojah_inc.dojah_android_sdk.domain.responses


import com.dojah_inc.dojah_android_sdk.ui.utils.GovDocType
import com.google.gson.annotations.SerializedName

data class DocImageAnalysisResponse(
    val entity: DocEntity?
) {
    data class DocEntity(
        val id: DocId?
    ) {
        data class DocId(
            val details: DocDetails?,
            @SerializedName("labels_detected") val labelsDetected: Boolean?, // true
            val message: String? // labels detected
        ) {
            data class DocDetails(
                val document: Double?, // 97.05933380126953
                @SerializedName("driving_license") val drivingLicense: Double?, // 72.14076232910156
                @SerializedName("id_cards") val idCards: Double?, // 92.19563293457031
                val passport: Double?, // 91.81082153320312
                val national: Double?, // 91.81082153320312
                val voter: Double?, // 91.81082153320312
                val text: Double? // 99.99883270263672
            ) {

                fun docSucceed(docType: GovDocType?): Boolean {
                    if (docType == null) {
                        throw Exception("DocType can't be null")
                    }
                    val isDocID = idCards != null && document != null
                    return when (docType) {
                        GovDocType.NIN -> isDocID
                        GovDocType.VNIN -> isDocID
                        GovDocType.DL -> drivingLicense != null || isDocID
                        GovDocType.PASSPORT -> isDocID || passport != null
                        GovDocType.VOTER -> isDocID || voter != null
                        GovDocType.NATIONAL -> isDocID || national != null
                        GovDocType.BUSINESS -> isDocID
                        else -> false
                    }

                }

                fun analysisErrorMsg(docType: GovDocType?): String {
                    if (docType == null) {
                        throw Exception("DocType can't be null")
                    }

                    val prefix = when (docType) {
                        GovDocType.NIN -> "No NIN"
                        GovDocType.VNIN -> "No VNIN"
                        GovDocType.DL -> "No Driver's License"
                        GovDocType.PASSPORT -> "No Passport"
                        GovDocType.VOTER -> "No Voter's card"
                        GovDocType.NATIONAL -> "No National ID"
                        else -> "No ID card"
                    }
                    return "$prefix detected, please try again"

                }
            }
        }
    }
}