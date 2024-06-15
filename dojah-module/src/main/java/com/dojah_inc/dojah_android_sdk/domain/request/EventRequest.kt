package com.dojah_inc.dojah_android_sdk.domain.request

import com.google.gson.annotations.SerializedName


data class EventRequest(

    @SerializedName("verification_id") var verificationId: Int? = null,
    @SerializedName("step_number") var stepNumber: Int? = null,
    @SerializedName("event_type") var eventType: String? = null,
    @SerializedName("event_value") var eventValue: String? = null,
    @SerializedName("services") var services: List<String> = listOf(),
    @SerializedName("session_id") var sessionId: String? = null,
    @SerializedName("app_id") var appId: String? = null,
    @SerializedName("cost") var cost: Int? = 0,
    var pageKey: String? = null,

    )