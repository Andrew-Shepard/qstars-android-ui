package com.example.androidui

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Lightbulb
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.PlusOne
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
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
        actions = {
            IconButton(onClick = {}){
                Icon(Icons.Filled.Add,"")
            }
            ProfilePicture()
        }
    )
}