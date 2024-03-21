package com.dojah.sdk_kyc.domain.request


import com.google.gson.annotations.SerializedName

data class LivenessVerifyRequest(
    @SerializedName("app_id") val appId: String?, // 65379278cb72d65ba457f93f
    @SerializedName("session_id") val sessionId: String?, // 65b0f14c909cc10031733141
    @SerializedName("verification_id") val verificationId: Int? // 17567
)