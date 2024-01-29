package com.dojah.sdk_kyc.domain.request


import com.google.gson.annotations.SerializedName

data class UserDataRequest(
    @SerializedName("app_id") val appId: String?, // 65379278cb72d65ba457f93f
    @SerializedName("session_id") val sessionId: String?, // 65b0e536909cc10031732d1d
    val country: String?, // NG
    @SerializedName("step_number") val stepNumber: Int?, // 2
    @SerializedName("verification_id") val verificationId: Int?, // 17559
    val dob: String?, // 1996-04-30
    @SerializedName("first_name") val firstName: String?, // Osarumen
    @SerializedName("last_name") val lastName: String?, // Alohan
    @SerializedName("middle_name") val middleName: String?, // Eleojo
    val mobile: String?,
    @SerializedName("residence_country") val residenceCountry: String?, // NG
)