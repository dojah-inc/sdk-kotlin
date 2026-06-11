package com.dojah.kyc_sdk_kotlin.ui.main.fragment.datacollection.capturesignature.composable

import android.graphics.Bitmap
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.dojah.kyc_sdk_kotlin.R
import com.dojah.kyc_sdk_kotlin.data.io.SharedPreferenceManager
import com.dojah.kyc_sdk_kotlin.ui.main.fragment.datacollection.capturesignature.CaptureSignatureUIData
import com.dojah.kyc_sdk_kotlin.ui.main.fragment.datacollection.customquestions.composables.QuestionBackground
import com.dojah.kyc_sdk_kotlin.ui.main.fragment.datacollection.customquestions.composables.QuestionBorder
import com.dojah.kyc_sdk_kotlin.ui.utils.normaliseColor
import com.dojah.kyc_sdk_kotlin.ui.utils.toTitleCase
import com.dojah.kyc_sdk_kotlin.ui.utils.widget.DojahButton
import com.dojah.kyc_sdk_kotlin.utils.scaleToMax
import com.dojah.kyc_sdk_kotlin.utils.toBase64
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import se.warting.signaturepad.SignaturePadAdapter
import se.warting.signaturepad.SignaturePadView

private val ActiveColor = Color(0xFF3F7CDB)

@Composable
internal fun CaptureSignatureScreen(
    data: CaptureSignatureUIData,
    modifier: Modifier = Modifier,
    onSubmit: (String, String) -> Unit = { _, _ -> }
) {
    var signaturePadAdapter: SignaturePadAdapter? by remember { mutableStateOf(null) }

    var textValue: TextFieldValue by remember { mutableStateOf(TextFieldValue()) }
    var signaturePadEmpty: Boolean by remember { mutableStateOf(true) }
    val coroutineScope = rememberCoroutineScope()
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

    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
    ) {
        Text(
            text = data.title.toTitleCase(),
            style = MaterialTheme.typography.titleMedium.copy(
                fontSize = 16.sp,
                color = Color(0xFF1B2A4E)
            ),
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(bottom = 28.dp)
        )

        Text(
            text = data.description.toTitleCase(),
            style = MaterialTheme.typography.titleSmall.copy(
                fontSize = 16.sp,
                color = Color(0xFF4A4A68)
            ),
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(bottom = 28.dp)
        )

        Text(
            text = stringResource(R.string.full_name_text),
            style = MaterialTheme.typography.labelMedium.copy(
                fontSize = 16.sp,
                color = Color(0xFF1B2A4E)
            ),
            modifier = Modifier
                .align(Alignment.Start)
                .padding(bottom = 10.dp)
        )
        OutlinedTextField(
            onValueChange = {
                textValue = it
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 28.dp),
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
            ),
            value = textValue
        )

        Text(
            text = stringResource(R.string.signature_text),
            style = MaterialTheme.typography.labelMedium.copy(
                fontSize = 16.sp,
                color = Color(0xFF1B2A4E)
            ),
            modifier = Modifier
                .align(Alignment.Start)
                .padding(bottom = 10.dp)
        )

        Surface(
            modifier = Modifier
                .fillMaxWidth()
                .height(125.dp),
            color = Color(0xFFF1F0EF),
            shape = RoundedCornerShape(4.dp)
        ) {
            Box(modifier = Modifier.fillMaxWidth()) {
                SignaturePadView(
                    onReady = {
                        signaturePadAdapter = it
                    },
                    clearOnDoubleClick = true,
                    onSigned = {
                        signaturePadEmpty = false
                    },
                    onClear = {
                        signaturePadEmpty = true
                    },
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }

        Text(
            text = stringResource(R.string.clear_signature),
            style = MaterialTheme.typography.labelMedium.copy(
                fontSize = 16.sp,
                textDecoration = TextDecoration.Underline,
                color = Color(0xFF4A4A68)
            ),
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(vertical = 16.dp)
                .clickable {
                    signaturePadAdapter?.clear()
                }
        )

        DojahButton(
            text = stringResource(R.string.confirm_signature),
            enabled = textValue.text.isNotBlank() && !signaturePadEmpty,
            modifier = Modifier.padding(vertical = 16.dp),
            onClick = {
                signaturePadAdapter?.getSignatureBitmap()?.let { signature ->
                    coroutineScope.launch(Dispatchers.Default) {
                        val base64 = signature.scaleToMax(1024, 1024)
                            .toBase64(Bitmap.CompressFormat.JPEG, 80)
                        withContext(Dispatchers.Main) {
                            onSubmit(textValue.text, base64)
                        }
                    }
                }
            })
    }
}