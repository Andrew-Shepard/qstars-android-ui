package com.example.androidui

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState


// sticky headers is an experimental feature
// creates a custom table
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun AssetTable(
    width: Int,
    height: Int,
    navController: NavController,
    assetTableViewModel: AssetTableViewModel
) {

    val column1Weight = .3f
    val column2Weight = .3f
    val column3Weight = .2f
    val column4Weight = .2f

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    Column()
    {
        // Table title
        Text(
            text = "Assets",
            fontSize = 25.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .offset(x = 15.dp)
                .offset(y = 10.dp)
        )

        //Contents of table
        LazyColumn(
            Modifier
                .size(width = width.dp, height = height.dp)
                .padding(15.dp)
                .border(2.dp, Color.Gray))
        {
            //Table Sticky Header
            stickyHeader {
                Row(Modifier.background(Color.Gray))
                {
                    TableCell(text = "Asset ID", weight = column1Weight)
                    TableCell(text = "Name", weight = column2Weight)
                    TableCell(text = "Asset Type", weight = column3Weight)
                    TableCell(text = "Status", weight = column4Weight)
                }
            }

            // The table data
            items(assetTableViewModel.allAssets) { asset ->

                // stores ID of row to pass it
                val assetID = asset.assetID

                Row(modifier = Modifier
                    .fillMaxWidth()
                    .pointerInput(Unit)
                    {
                        detectTapGestures(
                            onTap = {
                                //when asset clicked, show asset deatils popup
                                if (currentRoute == "parent-table"){
                                    navController.navigate("asset-form")
                                }
                                    //navController.navigate(ScreenRoutes.AssetDetails.withArgs(assetID))
                                    navController.navigate("details-popup" + "/$assetID")
                            }
                        )
                    }
                ) {
                    TableCell(text = asset.assetID, weight = column1Weight)
                    TableCell(text = asset.assetName, weight = column2Weight)
                    TableCell(text = asset.assetType, weight = column3Weight)
                    TableCell(text = asset.assetStatus, weight = column4Weight)

                }
            }
        }
    }
}