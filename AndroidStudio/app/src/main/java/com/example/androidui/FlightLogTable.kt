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
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun FlightLogTable(
    width: Int,
    height: Int,
    navController: NavController,
    flightLogTableViewModel: FlightLogTableViewModel
){
    val column1Weight = .3f
    val column2Weight = .3f
    val column3Weight = .2f
    val column4Weight = .2f

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    Column{
        // Table title
        Text(
            text = "Flight Logs",
            fontSize = 25.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .offset(x = 15.dp)
                .offset(y = 10.dp)
        )

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
                    TableCell(text = "Flight Log ID", weight = column1Weight)
                    TableCell(text = "Pilot ID", weight = column2Weight)
                    TableCell(text = "Date", weight = column3Weight)
                    TableCell(text = "Success", weight = column4Weight)
                }
            }

            //The table data
            items(flightLogTableViewModel.allFlightLogs) { flightLog ->

                // stores ID of row to pass it
                val flightLogID = flightLog.flightLogID

                Row(modifier = Modifier
                    .fillMaxWidth()
                    .pointerInput(Unit)
                    {
                        detectTapGestures(
                            onTap = {
                                //when flight log clicked, show flight log details popup
                                //navController.navigate("flight-logs-detail-popup" + "/$flightLogID")
                                navController.navigate("flight-logs-popup" + "/$flightLogID")
                            }
                        )
                    }
                ) {
                    TableCell(text = flightLog.flightLogID, weight = column1Weight)
                    TableCell(text = flightLog.pilotID, weight = column2Weight)
                    TableCell(text = flightLog.dateOfLog, weight = column3Weight)
                    TableCell(text = flightLog.success, weight = column4Weight)

                }
            }
        }
    }
}