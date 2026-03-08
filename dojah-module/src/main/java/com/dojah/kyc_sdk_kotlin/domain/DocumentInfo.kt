package com.dojah.kyc_sdk_kotlin.domain

import android.net.Uri

data class DocumentInfo (
    val docName: String,
    val docType: String,
    val docUri: Uri? = null
){
    val fullName: String
        get() = "$docName.$docType"

    override fun toString(): String {
        return "DocumentInfo(docName='$docName', docType='$docType', docUri=${docUri?.path})"
    }
}