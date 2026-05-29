package com.dojah.kyc_sdk_kotlin.ui.main.fragment.datacollection.customquestions.composables

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun OptionContainer(
    onClick: () -> Unit,
    content: @Composable RowScope.() -> Unit
) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .height(52.dp)
            .clickable { onClick() },
        shape = RoundedCornerShape(12.dp),
        color = QuestionBackground,
        border = BorderStroke(
            width = 1.dp,
            color = QuestionBorder
        )
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 12.dp),
            verticalAlignment = Alignment.CenterVertically,
            content = content
        )
    }
}
