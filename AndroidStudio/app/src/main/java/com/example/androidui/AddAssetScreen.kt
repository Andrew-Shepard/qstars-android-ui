package com.example.androidui

import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIos
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun AddAssetScreen(
    navController: NavController,
    assetTableViewModel: AssetTableViewModel,
    assetSearchViewModel: AssetSearchViewModel
){

    Column{
        Box(modifier = Modifier
            .fillMaxWidth()
            .offset(x = 10.dp)
        ) {
            //Close Button
            Button(
                onClick = {
                    navController.popBackStack()
                          },
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .width(50.dp)
                    .offset(x = -15.dp)
            ) {
                Icon(Icons.Filled.ArrowBackIos, "")
            }
        }
        AssetTable(width = 400,
            height = 600,
            navController = navController,
            assetTableViewModel,
            assetSearchViewModel = assetSearchViewModel
        )
    }
}