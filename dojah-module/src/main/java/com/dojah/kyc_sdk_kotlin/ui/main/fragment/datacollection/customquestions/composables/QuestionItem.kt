package com.dojah.kyc_sdk_kotlin.ui.main.fragment.datacollection.customquestions.composables

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.dojah.kyc_sdk_kotlin.ui.main.fragment.datacollection.customquestions.Question
import com.dojah.kyc_sdk_kotlin.ui.main.fragment.datacollection.customquestions.QuestionType
import com.dojah.kyc_sdk_kotlin.ui.utils.toTitleCase

@Composable
fun QuestionItem(
    question: Question,
    answer: Any?,
    activeColor: Color,
    onAnswerChanged: (Any) -> Unit
) {
    Column {
        Text(
            text = question.text.toTitleCase(),
            style = MaterialTheme.typography.bodyMedium.copy(fontSize = 14.sp)
        )

        Box(modifier = Modifier.height(12.dp))

        when (question.type) {
            QuestionType.text -> {
                OutlinedTextField(
                    value = (answer as? String) ?: "",
                    onValueChange = onAnswerChanged,
                    modifier = Modifier.fillMaxWidth(),
                    singleLine = true,
                    shape = RoundedCornerShape(6.dp),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedContainerColor = QuestionBackground,
                        unfocusedContainerColor = QuestionBackground,
                        disabledContainerColor = QuestionBackground,

                        focusedBorderColor = activeColor,
                        unfocusedBorderColor = QuestionBorder,
                        disabledBorderColor = QuestionBorder,
                    ),
                    keyboardOptions = KeyboardOptions(
                        imeAction = ImeAction.Next
                    )
                )
            }

            QuestionType.single -> {
                val selected = answer as? Pair<*, *>

                question.options.forEach { option ->
                    OptionContainer(
                        onClick = { onAnswerChanged(option) }
                    ) {
                        RadioButton(
                            selected = selected?.first == option.first,
                            onClick = { onAnswerChanged(option) },
                            colors = RadioButtonDefaults.colors(
                                selectedColor = activeColor,
                                unselectedColor = Color.Gray
                            )
                        )

                        Text(
                            text = option.second.toTitleCase(),
                            style = MaterialTheme.typography.bodyMedium
                        )
                    }

                    Box(modifier = Modifier.height(12.dp))
                }
            }

            QuestionType.multiple -> {
                val selected = answer as? List<Pair<String, String>> ?: emptyList()

                question.options.forEach { option ->
                    val checked = selected.any { it.first == option.first }

                    OptionContainer(
                        onClick = {
                            val updated = if (checked) {
                                selected - option
                            } else {
                                selected + option
                            }

                            onAnswerChanged(updated)
                        }
                    ) {
                        Checkbox(
                            checked = checked,
                            colors = CheckboxDefaults.colors(
                                checkedColor = activeColor,
                                uncheckedColor = Color.Gray,
                                checkmarkColor = Color.White
                            ),
                            onCheckedChange = {
                                val updated = if (checked) {
                                    selected - option
                                } else {
                                    selected + option
                                }

                                onAnswerChanged(updated)
                            }
                        )

                        Text(
                            text = option.second.toTitleCase(),
                            style = MaterialTheme.typography.bodyMedium
                        )
                    }

                    Box(modifier = Modifier.height(12.dp))
                }
            }
        }
    }
}
