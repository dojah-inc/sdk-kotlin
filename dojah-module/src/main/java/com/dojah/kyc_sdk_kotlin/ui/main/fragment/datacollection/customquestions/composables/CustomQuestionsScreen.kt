package com.dojah.kyc_sdk_kotlin.ui.main.fragment.datacollection.customquestions.composables

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.dojah.kyc_sdk_kotlin.R
import com.dojah.kyc_sdk_kotlin.data.io.SharedPreferenceManager
import com.dojah.kyc_sdk_kotlin.ui.main.fragment.datacollection.customquestions.Question
import com.dojah.kyc_sdk_kotlin.ui.main.fragment.datacollection.customquestions.QuestionAnswer
import com.dojah.kyc_sdk_kotlin.ui.main.fragment.datacollection.customquestions.QuestionConfig
import com.dojah.kyc_sdk_kotlin.ui.main.fragment.datacollection.customquestions.QuestionType
import com.dojah.kyc_sdk_kotlin.ui.main.fragment.datacollection.customquestions.QuestionsOutput
import com.dojah.kyc_sdk_kotlin.ui.utils.normaliseColor
import com.dojah.kyc_sdk_kotlin.ui.utils.toTitleCase
import com.dojah.kyc_sdk_kotlin.ui.utils.widget.DojahButton

internal val QuestionBackground = Color(0xFFF9F9F9)
internal val QuestionBorder = Color(0xFFE3E3E6)
private val ActiveColor = Color(0xFF3F7CDB)

@Composable
internal fun CustomQuestionsScreen(
    input: QuestionConfig,
    modifier: Modifier = Modifier,
    onContinue: (QuestionsOutput) -> Unit
) {
    val answers = remember(input.title) {
        mutableStateMapOf<Int, Any>()
    }

    val context = LocalContext.current
    val activeColor: Color = remember(context) {
        try {
            SharedPreferenceManager(context).getMaterialButtonBgColor
                ?.normaliseColor()
                ?.let { Color(it) }
        } catch (_: Exception) {
            null
        } ?: ActiveColor
    }

    val allAnswered by remember {
        derivedStateOf {
            input.questions.indices.all { index ->
                when (val answer = answers[index]) {
                    is String -> answer.isNotBlank()
                    is Pair<*, *> -> true
                    is List<*> -> answer.isNotEmpty()
                    else -> false
                }
            }
        }
    }

    // fillMaxWidth only — the parent is a NestedScrollView (layout_height="wrap_content"),
    // which measures children with MeasureSpec.UNSPECIFIED (unbounded height).
    // fillMaxSize() would request Int.MAX_VALUE height, exceeding Compose's IntSize
    // 24-bit limit and crashing with "Size(w x 2147483647) is out of range".
    // The NestedScrollView already handles vertical scrolling at the View layer.
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
    ) {
        Text(
            text = input.title.toTitleCase(),
            style = MaterialTheme.typography.titleMedium.copy(fontSize = 16.sp),
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(bottom = 28.dp)
        )

        input.questions.forEachIndexed { index, question ->
            QuestionItem(
                question = question,
                answer = answers[index],
                activeColor = activeColor,
                onAnswerChanged = { answers[index] = it }
            )

            HorizontalDivider(
                modifier = Modifier.padding(vertical = 20.dp),
                color = MaterialTheme.colorScheme.outline.copy(alpha = 0.2f)
            )
        }

        Box(modifier = Modifier.height(8.dp))

        DojahButton(text = stringResource(R.string.submit), onClick = {
            val output = QuestionsOutput(
                eventValue = input.questions.mapIndexed { index, question ->
                    QuestionAnswer(
                        text = question.text.toTitleCase(),
                        type = question.type,
                        options = question.options.takeIf {
                            question.type != QuestionType.text
                        },
                        answer = answers[index] ?: ""
                    )
                }
            )

            onContinue(output)
        })

        Box(modifier = Modifier.height(16.dp))
    }
}

@Preview(showBackground = true)
@Composable
private fun CustomQuestionsScreenPreview() {
    MaterialTheme {
        CustomQuestionsScreen(
            input = QuestionConfig(
                title = "Compliance Questions",
                questions = listOf(
                    Question(
                        text = "what is your name?",
                        type = QuestionType.text
                    ),
                    Question(
                        text = "are you here?",
                        type = QuestionType.single,
                        options = listOf()
                    ),
                    Question(
                        text = "you are a citizen of which country?",
                        type = QuestionType.multiple,
                        options = listOf()
                    )
                )
            ), onContinue = {}
        )
    }
}