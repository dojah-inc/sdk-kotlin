package com.dojah.kyc_sdk_kotlin.domain.request


import com.google.gson.annotations.SerializedName

 data class BaseAddressRequest(
    @SerializedName("app_id") val appId: String?, // 65379278cb72d65ba457f93f
    val latitude: Double?, // 8.9628944
    val longitude: Double?, // 7.383398499999998
    val name: String?, // Goshen Close, Lugbe Abuja Municipal Area Council, Nigeria
    @SerializedName("session_id") val sessionId: String? // 65c37ea22a88c900308a4ae7
)