package com.dojah.demoapp.ui.widget

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.sharp.KeyboardArrowDown
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.dojah.demoapp.R
import com.dojah.demoapp.ui.theme.Dojahtest2Theme

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun DojahDropDownInputField(
    textFieldModifier: Modifier? = null,
    columnModifier: Modifier? = null,
    dropdownBoxModifier: Modifier? = null,
    titleText: String? = null,
    labelText: String? = null,
    options: List<String>,
    containerColor: Color = Color.Transparent,
    dropDownColor: Color? = null,
    borderWidth: Dp = 0.4.dp,
    indicatorColor: Color = Color.Transparent,
    labelTextColor: Color = Color.Gray,
    borderShape: RoundedCornerShape = RoundedCornerShape(8.dp),
    leadingContent: @Composable (() -> Unit)? = null,
    trailingContent: @Composable (() -> Unit)? = null,
    onValueChange: (String) -> Unit = {}
) {
    var expanded by remember { mutableStateOf(false) }
    var selectedOptionText by remember { mutableStateOf<String?>(null) }
    Dojahtest2Theme {

        Column(
            modifier = columnModifier ?: Modifier,
            verticalArrangement = Arrangement.Center
        ) {
            if (titleText != null) {
                Text(
                    text = titleText,
                    style = MaterialTheme.typography.titleMedium.copy(
                        color = Color.Gray,
                        fontSize = 12.sp,
                    ),
                    modifier = Modifier.padding(bottom = 6.dp)
                )
            }
            ExposedDropdownMenuBox(
                modifier = dropdownBoxModifier ?: Modifier
                    .border(
                        width = borderWidth,
                        color = indicatorColor,
                        shape = borderShape
                    )
                    .wrapContentHeight(),
                expanded = expanded,
                onExpandedChange = {
                    expanded = !expanded
                },
            ) {
                TextField(
                    readOnly = true,
                    value = "",
                    enabled = false,
                    onValueChange = {
                        selectedOptionText = it
                        onValueChange.invoke(it)
                    },
                    modifier = if (textFieldModifier != null)
                        Modifier
                            .menuAnchor()
                            .then(textFieldModifier) else
                        Modifier
                            .menuAnchor(),
                    label = {
                        Text(
                            selectedOptionText ?: labelText ?: "",
                            style = MaterialTheme.typography.titleMedium.copy(
                                fontWeight = FontWeight.Normal,
                                color = labelTextColor,
                                fontSize = 14.sp,
                            )
                        )
                    },
                    leadingIcon = leadingContent,
                    trailingIcon = {
                        IconButton(onClick = { }, modifier = Modifier.size(20.dp)) {
                            Icon(
                                Icons.Sharp.KeyboardArrowDown,
                                contentDescription = "close button",
                                tint = Color.Gray
                            )
                        }
                    },
                    shape = borderShape,
                    textStyle = MaterialTheme.typography.titleMedium.copy(color = Color.Gray),
                    colors = TextFieldDefaults.textFieldColors(
                        focusedIndicatorColor = indicatorColor,
                        unfocusedIndicatorColor = indicatorColor,
                        disabledIndicatorColor = indicatorColor,
                        containerColor = containerColor,
//                        focusedContainerColor = containerColor,
//                        unfocusedContainerColor = containerColor,
//                        disabledContainerColor = containerColor,
                    ),
                )
                DropdownMenu(
                    expanded = expanded,
                    onDismissRequest = {
                        expanded = false
                    },
                    modifier = Modifier
//                        .border(
//                            width = 0.4.dp,
//                            color = indicatorColor,
//                            shape = RoundedCornerShape(16.dp)
//                        )
//                        .exposedDropdownSize(matchTextFieldWidth = true)
                        .background(color = dropDownColor ?: containerColor),
                ) {
                    options.forEach { selectionOption ->
                        DropdownMenuItem(
                            text = {
                                Text(
                                    text = selectionOption,
                                    style = MaterialTheme.typography.headlineSmall.copy(
                                        color = colorResource(
                                            id = R.color.white
                                        )
                                    )
                                )
                            },
                            onClick = {
                                selectedOptionText = selectionOption
                                onValueChange.invoke(selectionOption)
                                expanded = false
                            }
                        )
                    }
                }
            }
        }
    }
}
