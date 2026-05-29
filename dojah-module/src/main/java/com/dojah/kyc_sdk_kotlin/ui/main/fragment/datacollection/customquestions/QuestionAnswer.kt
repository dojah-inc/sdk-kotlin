package com.dojah.kyc_sdk_kotlin.ui.main.fragment.datacollection.customquestions

data class QuestionsOutput(
    val eventType: String = "questions",
    val eventValue: List<QuestionAnswer>
)

enum class QuestionType {
    text,
    single,
    multiple
}

data class QuestionAnswer(
    val text: String,
    val type: QuestionType,
    val options: List<Pair<String, String>>? = null,
    val answer: Any
)

data class Question(
    val text: String,
    val type: QuestionType,
    val options: List<Pair<String, String>> = emptyList()
)

data class QuestionConfig(
    val title: String,
    val questions: List<Question>
)