package com.dojah.sdk_kyc.domain.request


import com.google.gson.annotations.SerializedName

 data class AddressRequest(
    @SerializedName("app_id") val appId: String, // 65379278cb72d65ba457f93f
    val latitude: Double, // 8.9628944
    val longitude: Double, // 7.383398499999998
    val match: Boolean, // false
    @SerializedName("session_id") val sessionId: String// 65c37ea22a88c900308a4ae7
)