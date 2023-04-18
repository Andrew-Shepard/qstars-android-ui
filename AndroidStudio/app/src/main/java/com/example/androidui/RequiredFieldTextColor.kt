package com.example.androidui

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.sp

@Composable
fun MultiStyleText(text1: String, color1: Color, text2: String, color2: Color) {
    Text(buildAnnotatedString {
        withStyle(style = SpanStyle(color = color1)) {
            append(text1)
        }
        withStyle(style = SpanStyle(color = color2)) {
            append(text2)
        }
    },
        fontSize = 20.sp
    )
}