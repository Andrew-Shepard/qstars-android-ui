package com.example.androidui

import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavController


@Composable
fun testScreen(navController: NavController){

    Button(onClick = { navController.navigate("asset-form") }) {
        Text("+")
    }


}