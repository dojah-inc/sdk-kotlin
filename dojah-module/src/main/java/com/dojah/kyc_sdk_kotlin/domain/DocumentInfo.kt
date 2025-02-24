package com.dojah.kyc_sdk_kotlin.domain

data class DocumentInfo (
    val docName: String,
    val docType: String,
){
    val fullName: String
        get() = "$docName.$docType"

    override fun toString(): String {
        return "DocumentInfo(docName='$docName', docType='$docType')"
    }
}