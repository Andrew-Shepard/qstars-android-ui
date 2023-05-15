package com.example.androidui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.Icon
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.unit.toSize

// This file contains the formatting for the custom text field drop down AppTextFieldDropDown
@Composable
fun AppTextFieldDropDown(
    selectedText: String,
    placeholder: String,
    onSelectedTextChange: (String) -> Unit,
    imeAction: ImeAction = ImeAction.Next,
    keyboardType: KeyboardType = KeyboardType.Text,
    width: Int = 250,
    dropDownItems: List<String>
) {
    var expanded by remember { mutableStateOf(false) } // remembers whether drop down is expanded or not
    var textFieldSize by remember { mutableStateOf(Size.Zero) } // remembers drop down size


    // if drop down is expanded, change icon to up
    // else change icon to down
    val icon = if (expanded)
        Icons.Filled.KeyboardArrowUp
    else
        Icons.Filled.KeyboardArrowDown

    Box {

        OutlinedTextField(
            value = selectedText,
            onValueChange = { },
            keyboardOptions = KeyboardOptions(imeAction = imeAction, keyboardType = keyboardType),
            modifier = Modifier
                .width(width.dp)
                .onGloballyPositioned { coordinates ->
                    textFieldSize = coordinates.size.toSize()
                },
            trailingIcon = {
                Icon(icon, "contentDescription",
                    Modifier.clickable {
                        expanded = !expanded
                    }) //when icon is clicked, switch enabled value
            },
            placeholder = {
                Text(
                    text = placeholder,
                    style = TextStyle(fontSize = 18.sp, color = Color.LightGray)
                )
            }
        )

        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            modifier = Modifier.width(with(LocalDensity.current) { textFieldSize.width.toDp() })
        ) {

            dropDownItems.forEach { label ->
                DropdownMenuItem(onClick = {
                    onSelectedTextChange(label)
                    expanded = false
                }) {
                    Text(text = label)
                }
            }

        }
    }
}