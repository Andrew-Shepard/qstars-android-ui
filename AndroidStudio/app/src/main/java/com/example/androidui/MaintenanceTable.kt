package com.example.androidui

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun MaintenanceLogTable(
    width: Int,
    height: Int,
    navController: NavController,
    maintenanceLogTableViewModel: MaintenanceTableViewModel
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
            text = "Maintenance Logs",
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
                    TableCell(text = "ID", weight = column1Weight)
                    TableCell(text = "Asset ID", weight = column2Weight)
                    TableCell(text = "Date of Maintenance", weight = column3Weight)
                    TableCell(text = "Type of Maintenance", weight = column4Weight)
                }
            }

            // The table data
            items(maintenanceLogTableViewModel.allMaintenanceLogs) { log ->

                // stores ID of row to pass it
                val ID = log.ID

                Row(modifier = Modifier
                    .fillMaxWidth()
                    .pointerInput(Unit)
                    {
                        detectTapGestures(
                            onTap = {
                                //when asset clicked, show asset details popup
                                navController.navigate("maintenance-log-details-popup" + "/$ID")
                            }
                        )
                    }
                ) {
                    TableCell(text = log.ID, weight = column1Weight)
                    TableCell(text = log.assetID, weight = column2Weight)
                    TableCell(text = log.dateOfMaintenance, weight = column3Weight)
                    TableCell(text = log.typeOfMaintenane, weight = column4Weight)

                }
            }
        }
    }
}