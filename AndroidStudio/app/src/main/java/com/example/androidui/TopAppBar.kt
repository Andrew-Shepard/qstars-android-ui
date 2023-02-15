package com.example.androidui

import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
fun TopBar(scope: CoroutineScope, scaffoldState: ScaffoldState)
{
    TopAppBar(
        title = { Text(text = "Overhead Intelligence", fontSize = 18.sp) },
        navigationIcon =
        {
            IconButton(onClick = {scope.launch{scaffoldState.drawerState.open()} })
            {
                Icon(Icons.Filled.Menu, "")
            }
        },
        backgroundColor = Color.LightGray,
        contentColor = Color.Black,
    )
}