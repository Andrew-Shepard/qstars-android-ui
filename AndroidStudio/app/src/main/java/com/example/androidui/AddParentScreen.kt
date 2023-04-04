package com.example.androidui

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController

@Composable
fun AddParentScreen(
    navController: NavController,
    assetTableViewModel: AssetTableViewModel
){

    Column{
        AssetTable(width = 400,
            height = 500,
            navController = navController,
            assetTableViewModel
        )
    }


}