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


@Composable
fun FlightLogTableComponent(
    navController: NavController,
    flightLog: FlightLog,
    flightLogID: String,
    column1Weight: Float,
    column2Weight: Float,
    column3Weight: Float,
    column4Weight: Float
){
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

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun FlightLogTable(
    width: Int,
    height: Int,
    navController: NavController,
    flightLogTableViewModel: FlightLogTableViewModel,
    flightLogSearchViewModel: FlightLogSearchViewModel
){
    val column1Weight = .3f
    val column2Weight = .3f
    val column3Weight = .2f
    val column4Weight = .2f

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    var filterFieldsEmpty = flightLogSearchViewModel.flightLogID.isEmpty() &&
            flightLogSearchViewModel.pilotID.isEmpty() &&
            flightLogSearchViewModel.pilotName.isEmpty() &&
            flightLogSearchViewModel.droneSerialNum.isEmpty() &&
            flightLogSearchViewModel.date.isEmpty() &&
            flightLogSearchViewModel.success.isEmpty()

    //variables that will check whether the user field input matches a flight log
    var flightLogIDMatch: Boolean
    var pilotIDMatch: Boolean
    var pilotNameMatch: Boolean
    var droneSerialNumIDMatch: Boolean
    var dateMatch: Boolean
    var successMatch: Boolean

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

        // Table data
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

            //The actual table data
            items(flightLogTableViewModel.allFlightLogs) { flightLog ->

                flightLogIDMatch = flightLogSearchViewModel.flightLogID == flightLog.flightLogID
                pilotIDMatch = flightLogSearchViewModel.pilotID == flightLog.pilotID
                pilotNameMatch = flightLogSearchViewModel.pilotName == flightLog.pilotName
                droneSerialNumIDMatch = flightLogSearchViewModel.droneSerialNum == flightLog.droneID
                dateMatch = flightLogSearchViewModel.date == flightLog.dateOfLog
                successMatch = flightLogSearchViewModel.success == flightLog.success


                // stores ID of row to pass it
                val flightLogID = flightLog.flightLogID

                // FLight Log ID Search
                if (flightLogIDMatch){
                    FlightLogTableComponent(
                        navController = navController,
                        flightLog = flightLog,
                        flightLogID = flightLogID,
                        column1Weight = column1Weight,
                        column2Weight = column2Weight,
                        column3Weight = column3Weight,
                        column4Weight = column4Weight
                    )
                }

                // Pilot ID Search
                if (flightLogSearchViewModel.pilotID.isNotEmpty() && pilotIDMatch){

                    if (
                        flightLogSearchViewModel.pilotName.isEmpty() &&
                        flightLogSearchViewModel.droneSerialNum.isEmpty() &&
                        flightLogSearchViewModel.date.isEmpty() &&
                        flightLogSearchViewModel.success.isEmpty()
                    ){
                        FlightLogTableComponent(
                            navController = navController,
                            flightLog = flightLog,
                            flightLogID = flightLogID,
                            column1Weight = column1Weight,
                            column2Weight = column2Weight,
                            column3Weight = column3Weight,
                            column4Weight = column4Weight
                        )
                    }

                    if (flightLogSearchViewModel.pilotName.isNotEmpty() && pilotNameMatch){
                        FlightLogTableComponent(
                            navController = navController,
                            flightLog = flightLog,
                            flightLogID = flightLogID,
                            column1Weight = column1Weight,
                            column2Weight = column2Weight,
                            column3Weight = column3Weight,
                            column4Weight = column4Weight
                        )
                    }

                    if (flightLogSearchViewModel.droneSerialNum.isNotEmpty() && droneSerialNumIDMatch){
                        FlightLogTableComponent(
                            navController = navController,
                            flightLog = flightLog,
                            flightLogID = flightLogID,
                            column1Weight = column1Weight,
                            column2Weight = column2Weight,
                            column3Weight = column3Weight,
                            column4Weight = column4Weight
                        )
                    }

                    if (flightLogSearchViewModel.date.isNotEmpty() && dateMatch){
                        FlightLogTableComponent(
                            navController = navController,
                            flightLog = flightLog,
                            flightLogID = flightLogID,
                            column1Weight = column1Weight,
                            column2Weight = column2Weight,
                            column3Weight = column3Weight,
                            column4Weight = column4Weight
                        )
                    }

                    if (flightLogSearchViewModel.success.isNotEmpty() && successMatch){
                        FlightLogTableComponent(
                            navController = navController,
                            flightLog = flightLog,
                            flightLogID = flightLogID,
                            column1Weight = column1Weight,
                            column2Weight = column2Weight,
                            column3Weight = column3Weight,
                            column4Weight = column4Weight
                        )
                    }
                }

                if (flightLogSearchViewModel.pilotName.isNotEmpty() && pilotNameMatch){
                    if (
                        flightLogSearchViewModel.pilotID.isEmpty() &&
                        flightLogSearchViewModel.droneSerialNum.isEmpty() &&
                        flightLogSearchViewModel.date.isEmpty() &&
                        flightLogSearchViewModel.success.isEmpty()
                    ){
                        FlightLogTableComponent(
                            navController = navController,
                            flightLog = flightLog,
                            flightLogID = flightLogID,
                            column1Weight = column1Weight,
                            column2Weight = column2Weight,
                            column3Weight = column3Weight,
                            column4Weight = column4Weight
                        )
                    }

                    if (flightLogSearchViewModel.pilotID.isNotEmpty() && pilotIDMatch){
                        FlightLogTableComponent(
                            navController = navController,
                            flightLog = flightLog,
                            flightLogID = flightLogID,
                            column1Weight = column1Weight,
                            column2Weight = column2Weight,
                            column3Weight = column3Weight,
                            column4Weight = column4Weight
                        )
                    }

                    if (flightLogSearchViewModel.droneSerialNum.isNotEmpty() && droneSerialNumIDMatch){
                        FlightLogTableComponent(
                            navController = navController,
                            flightLog = flightLog,
                            flightLogID = flightLogID,
                            column1Weight = column1Weight,
                            column2Weight = column2Weight,
                            column3Weight = column3Weight,
                            column4Weight = column4Weight
                        )
                    }

                    if (flightLogSearchViewModel.date.isNotEmpty() && dateMatch){
                        FlightLogTableComponent(
                            navController = navController,
                            flightLog = flightLog,
                            flightLogID = flightLogID,
                            column1Weight = column1Weight,
                            column2Weight = column2Weight,
                            column3Weight = column3Weight,
                            column4Weight = column4Weight
                        )
                    }

                    if (flightLogSearchViewModel.success.isNotEmpty() && successMatch){
                        FlightLogTableComponent(
                            navController = navController,
                            flightLog = flightLog,
                            flightLogID = flightLogID,
                            column1Weight = column1Weight,
                            column2Weight = column2Weight,
                            column3Weight = column3Weight,
                            column4Weight = column4Weight
                        )
                    }
                }

                if (flightLogSearchViewModel.droneSerialNum.isNotEmpty() && droneSerialNumIDMatch){
                    if (
                        flightLogSearchViewModel.pilotID.isEmpty() &&
                        flightLogSearchViewModel.pilotName.isEmpty() &&
                        flightLogSearchViewModel.date.isEmpty() &&
                        flightLogSearchViewModel.success.isEmpty()
                    ){
                        FlightLogTableComponent(
                            navController = navController,
                            flightLog = flightLog,
                            flightLogID = flightLogID,
                            column1Weight = column1Weight,
                            column2Weight = column2Weight,
                            column3Weight = column3Weight,
                            column4Weight = column4Weight
                        )
                    }

                    if (flightLogSearchViewModel.pilotID.isNotEmpty() && pilotIDMatch){
                        FlightLogTableComponent(
                            navController = navController,
                            flightLog = flightLog,
                            flightLogID = flightLogID,
                            column1Weight = column1Weight,
                            column2Weight = column2Weight,
                            column3Weight = column3Weight,
                            column4Weight = column4Weight
                        )
                    }

                    if (flightLogSearchViewModel.pilotName.isNotEmpty() && pilotNameMatch) {
                        FlightLogTableComponent(
                            navController = navController,
                            flightLog = flightLog,
                            flightLogID = flightLogID,
                            column1Weight = column1Weight,
                            column2Weight = column2Weight,
                            column3Weight = column3Weight,
                            column4Weight = column4Weight
                        )
                    }

                    if (flightLogSearchViewModel.date.isNotEmpty() && dateMatch){
                        FlightLogTableComponent(
                            navController = navController,
                            flightLog = flightLog,
                            flightLogID = flightLogID,
                            column1Weight = column1Weight,
                            column2Weight = column2Weight,
                            column3Weight = column3Weight,
                            column4Weight = column4Weight
                        )
                    }

                    if (flightLogSearchViewModel.success.isNotEmpty() && successMatch){
                        FlightLogTableComponent(
                            navController = navController,
                            flightLog = flightLog,
                            flightLogID = flightLogID,
                            column1Weight = column1Weight,
                            column2Weight = column2Weight,
                            column3Weight = column3Weight,
                            column4Weight = column4Weight
                        )
                    }
                }

                if (flightLogSearchViewModel.date.isNotEmpty() && dateMatch){
                    if (
                        flightLogSearchViewModel.pilotID.isNotEmpty() &&
                        flightLogSearchViewModel.pilotName.isNotEmpty() &&
                        flightLogSearchViewModel.droneSerialNum.isNotEmpty() &&
                        flightLogSearchViewModel.success.isNotEmpty()
                    ){
                        FlightLogTableComponent(
                            navController = navController,
                            flightLog = flightLog,
                            flightLogID = flightLogID,
                            column1Weight = column1Weight,
                            column2Weight = column2Weight,
                            column3Weight = column3Weight,
                            column4Weight = column4Weight
                        )
                    }

                    if (flightLogSearchViewModel.pilotID.isNotEmpty() && pilotIDMatch){
                        FlightLogTableComponent(
                            navController = navController,
                            flightLog = flightLog,
                            flightLogID = flightLogID,
                            column1Weight = column1Weight,
                            column2Weight = column2Weight,
                            column3Weight = column3Weight,
                            column4Weight = column4Weight
                        )
                    }

                    if (flightLogSearchViewModel.pilotName.isNotEmpty() && pilotNameMatch){
                        FlightLogTableComponent(
                            navController = navController,
                            flightLog = flightLog,
                            flightLogID = flightLogID,
                            column1Weight = column1Weight,
                            column2Weight = column2Weight,
                            column3Weight = column3Weight,
                            column4Weight = column4Weight
                        )
                    }

                    if (flightLogSearchViewModel.droneSerialNum.isNotEmpty() && droneSerialNumIDMatch){
                        FlightLogTableComponent(
                            navController = navController,
                            flightLog = flightLog,
                            flightLogID = flightLogID,
                            column1Weight = column1Weight,
                            column2Weight = column2Weight,
                            column3Weight = column3Weight,
                            column4Weight = column4Weight
                        )
                    }

                    if (flightLogSearchViewModel.success.isNotEmpty() && successMatch){
                        FlightLogTableComponent(
                            navController = navController,
                            flightLog = flightLog,
                            flightLogID = flightLogID,
                            column1Weight = column1Weight,
                            column2Weight = column2Weight,
                            column3Weight = column3Weight,
                            column4Weight = column4Weight
                        )
                    }
                }

                if (flightLogSearchViewModel.success.isNotEmpty() && successMatch){
                    if (
                        flightLogSearchViewModel.pilotID.isEmpty() &&
                        flightLogSearchViewModel.pilotName.isEmpty() &&
                        flightLogSearchViewModel.droneSerialNum.isEmpty() &&
                        flightLogSearchViewModel.date.isEmpty()
                    ){
                        FlightLogTableComponent(
                            navController = navController,
                            flightLog = flightLog,
                            flightLogID = flightLogID,
                            column1Weight = column1Weight,
                            column2Weight = column2Weight,
                            column3Weight = column3Weight,
                            column4Weight = column4Weight
                        )
                    }
                    if (flightLogSearchViewModel.pilotID.isNotEmpty() && pilotIDMatch){
                        FlightLogTableComponent(
                            navController = navController,
                            flightLog = flightLog,
                            flightLogID = flightLogID,
                            column1Weight = column1Weight,
                            column2Weight = column2Weight,
                            column3Weight = column3Weight,
                            column4Weight = column4Weight
                        )
                    }

                    if (flightLogSearchViewModel.pilotName.isNotEmpty() && pilotNameMatch){
                        FlightLogTableComponent(
                            navController = navController,
                            flightLog = flightLog,
                            flightLogID = flightLogID,
                            column1Weight = column1Weight,
                            column2Weight = column2Weight,
                            column3Weight = column3Weight,
                            column4Weight = column4Weight
                        )
                    }

                    if (flightLogSearchViewModel.droneSerialNum.isNotEmpty() && droneSerialNumIDMatch){
                        FlightLogTableComponent(
                            navController = navController,
                            flightLog = flightLog,
                            flightLogID = flightLogID,
                            column1Weight = column1Weight,
                            column2Weight = column2Weight,
                            column3Weight = column3Weight,
                            column4Weight = column4Weight
                        )
                    }

                    if (flightLogSearchViewModel.date.isNotEmpty() && dateMatch){
                        FlightLogTableComponent(
                            navController = navController,
                            flightLog = flightLog,
                            flightLogID = flightLogID,
                            column1Weight = column1Weight,
                            column2Weight = column2Weight,
                            column3Weight = column3Weight,
                            column4Weight = column4Weight
                        )
                    }
                }

                if (filterFieldsEmpty){
                    FlightLogTableComponent(
                        navController = navController,
                        flightLog = flightLog,
                        flightLogID = flightLogID,
                        column1Weight = column1Weight,
                        column2Weight = column2Weight,
                        column3Weight = column3Weight,
                        column4Weight = column4Weight
                    )
                }
            }
        }
    }
}