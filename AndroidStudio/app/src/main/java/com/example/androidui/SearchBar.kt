package com.example.androidui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.width
import androidx.compose.material.OutlinedTextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun SearchBar()
{
    Column(Modifier.offset(15.dp,15.dp))
    {
        OutlinedTextField(
            value = "",
            onValueChange = {},
            modifier = Modifier
                .width(250.dp)
                .height(45.dp)
        )
    }
}