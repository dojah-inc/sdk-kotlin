package com.dojah_inc.dojah_android_sdk.domain.request


import com.google.gson.annotations.SerializedName

 data class AdditionalDocRequest(
    @SerializedName("file_base64") val fileBase64: String, // string
    @SerializedName("file_name") val fileName: String, // string
    @SerializedName("file_type") val fileType: String, // string
    @SerializedName("verification_id") val verificationId: Int, // 0
    val title : String // string
)