package com.dojah.kyc_sdk_kotlin.domain.request

import com.dojah.kyc_sdk_kotlin.ui.main.fragment.datacollection.customquestions.QuestionType
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

data class QuestionsEventRequest(
    @SerializedName("verification_id") var verificationId: Int? = null,
    @SerializedName("step_number") var stepNumber: Int? = null,
    @SerializedName("event_type") var eventType: String? = null,
    @SerializedName("event_value") var eventValue: List<AnswerRequest>? = null,
    @SerializedName("services") var services: List<String> = listOf(),
    @SerializedName("session_id") var sessionId: String? = null,
    @SerializedName("app_id") var appId: String? = null,
    @SerializedName("cost") var cost: Int? = 0,
    var pageKey: String? = null
)

data class AnswerRequest(
    @SerializedName("text")
    val text: String,

    @SerializedName("type")
    val type: QuestionType,

    @SerializedName("options")
    val options: List<String>? = null,

    @SerializedName("answer")
    val answer: Any?
)
