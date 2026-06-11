package com.dojah.kyc_sdk_kotlin.ui.utils.widget

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.dojah.kyc_sdk_kotlin.data.io.SharedPreferenceManager
import com.dojah.kyc_sdk_kotlin.ui.utils.normaliseColor

private val ActiveColor = Color(0xFF3F7CDB)

@Composable
fun DojahButton(
    text: String,
    enabled: Boolean = true,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {


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
    Button(
        enabled = enabled,
        colors = ButtonDefaults.buttonColors(
            containerColor = activeColor,
            disabledContainerColor = activeColor.copy(alpha = 0.4f),
            contentColor = Color.White,
            disabledContentColor = Color.White.copy(alpha = 0.8f)
        ),
        onClick = onClick,
        modifier = modifier
            .fillMaxWidth()
            .height(52.dp),
        shape = RoundedCornerShape(8.dp)
    ) {
        Text(
            text,
            style = MaterialTheme.typography.titleMedium.copy(fontSize = 16.sp)
        )
    }
}