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


// This table holds the check in out logs
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun CheckOutTable(
    width: Int,
    height: Int,
    navController: NavController,
    checkInOutTableViewModel: CheckInOutTableViewModel,
    checkOutSearchViewModel: CheckOutSearchViewModel
) {

    val column1Weight = .2f
    val column2Weight = .25f
    val column3Weight = .25f
    val column4Weight = .3f

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    var filterFieldsEmpty = checkOutSearchViewModel.checkInOutID.isEmpty() &&
            checkOutSearchViewModel.checkOutDate.isEmpty() &&
            checkOutSearchViewModel.checkInDate.isEmpty() &&
            checkOutSearchViewModel.assetSerialNum.isEmpty() &&
            checkOutSearchViewModel.employeeName.isEmpty()

    var checkInOutIDMatch: Boolean
    var checkOutDateMatch: Boolean
    var checkInDateMatch: Boolean
    var assetIDMatch: Boolean
    var employeeNameMatch: Boolean

    Column()
    {
        // Table title
        Text(
            text = "Check In Out Logs",
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
                    TableCell(text = "Check Out Date", weight = column3Weight)
                    TableCell(text = "Check In Date", weight = column4Weight)
                }
            }

            // The table data
            items(checkInOutTableViewModel.allCheckInOutLogs) { log ->

                checkInOutIDMatch = checkOutSearchViewModel.checkInOutID == log.ID
                checkOutDateMatch = checkOutSearchViewModel.checkOutDate == log.checkOutDate
                checkInDateMatch = checkOutSearchViewModel.checkInDate == log.checkInDate
                assetIDMatch = checkOutSearchViewModel.assetSerialNum == log.assetID
                employeeNameMatch = checkOutSearchViewModel.employeeName == log.employeeName


                // stores ID of row to pass it
                val logID = log.ID

                if ( log.checkInDate != "") {
                    if (checkInOutIDMatch) {
                        if (checkOutSearchViewModel.checkOutDate.isEmpty() &&
                            checkOutSearchViewModel.checkInDate.isEmpty() &&
                            checkOutSearchViewModel.assetSerialNum.isEmpty() &&
                            checkOutSearchViewModel.employeeName.isEmpty()
                        ) {
                            Row(modifier = Modifier
                                .fillMaxWidth()
                                .pointerInput(Unit)
                                {
                                    detectTapGestures(
                                        onTap = {
                                            //when asset clicked, show asset details popup
                                            navController.navigate("checkinout-details-popup" + "/$logID")
                                        }
                                    )
                                }
                            ) {
                                TableCell(text = log.ID, weight = column1Weight)
                                TableCell(text = log.assetID, weight = column2Weight)
                                TableCell(text = log.checkOutDate, weight = column3Weight)
                                TableCell(text = log.checkInDate, weight = column4Weight)
                            }
                        }
                    }


                    if (checkOutDateMatch) {
                        if (
                            checkOutSearchViewModel.checkInDate.isEmpty() &&
                            checkOutSearchViewModel.assetSerialNum.isEmpty() &&
                            checkOutSearchViewModel.employeeName.isEmpty()
                        ) {
                            Row(modifier = Modifier
                                .fillMaxWidth()
                                .pointerInput(Unit)
                                {
                                    detectTapGestures(
                                        onTap = {
                                            //when asset clicked, show asset details popup
                                            navController.navigate("checkinout-details-popup" + "/$logID")
                                        }
                                    )
                                }
                            ) {
                                TableCell(text = log.ID, weight = column1Weight)
                                TableCell(text = log.assetID, weight = column2Weight)
                                TableCell(text = log.checkOutDate, weight = column3Weight)
                                TableCell(text = log.checkInDate, weight = column4Weight)

                            }
                        }

                        if (checkInDateMatch) {
                            Row(modifier = Modifier
                                .fillMaxWidth()
                                .pointerInput(Unit)
                                {
                                    detectTapGestures(
                                        onTap = {
                                            //when asset clicked, show asset details popup
                                            navController.navigate("checkinout-details-popup" + "/$logID")
                                        }
                                    )
                                }
                            ) {
                                TableCell(text = log.ID, weight = column1Weight)
                                TableCell(text = log.assetID, weight = column2Weight)
                                TableCell(text = log.checkOutDate, weight = column3Weight)
                                TableCell(text = log.checkInDate, weight = column4Weight)

                            }
                        }


                        if (assetIDMatch) {
                            Row(modifier = Modifier
                                .fillMaxWidth()
                                .pointerInput(Unit)
                                {
                                    detectTapGestures(
                                        onTap = {
                                            //when asset clicked, show asset details popup
                                            navController.navigate("checkinout-details-popup" + "/$logID")
                                        }
                                    )
                                }
                            ) {
                                TableCell(text = log.ID, weight = column1Weight)
                                TableCell(text = log.assetID, weight = column2Weight)
                                TableCell(text = log.checkOutDate, weight = column3Weight)
                                TableCell(text = log.checkInDate, weight = column4Weight)

                            }
                        }

                        if (employeeNameMatch) {
                            Row(modifier = Modifier
                                .fillMaxWidth()
                                .pointerInput(Unit)
                                {
                                    detectTapGestures(
                                        onTap = {
                                            //when asset clicked, show asset details popup
                                            navController.navigate("checkinout-details-popup" + "/$logID")
                                        }
                                    )
                                }
                            ) {
                                TableCell(text = log.ID, weight = column1Weight)
                                TableCell(text = log.assetID, weight = column2Weight)
                                TableCell(text = log.checkOutDate, weight = column3Weight)
                                TableCell(text = log.checkInDate, weight = column4Weight)

                            }
                        }
                    }

                    if (checkInDateMatch) {
                        if (
                            checkOutSearchViewModel.checkOutDate.isEmpty() &&
                            checkOutSearchViewModel.assetSerialNum.isEmpty() &&
                            checkOutSearchViewModel.employeeName.isEmpty()
                        ) {
                            Row(modifier = Modifier
                                .fillMaxWidth()
                                .pointerInput(Unit)
                                {
                                    detectTapGestures(
                                        onTap = {
                                            //when asset clicked, show asset details popup
                                            navController.navigate("checkinout-details-popup" + "/$logID")
                                        }
                                    )
                                }
                            ) {
                                TableCell(text = log.ID, weight = column1Weight)
                                TableCell(text = log.assetID, weight = column2Weight)
                                TableCell(text = log.checkOutDate, weight = column3Weight)
                                TableCell(text = log.checkInDate, weight = column4Weight)

                            }
                        }

                        if (checkOutDateMatch) {
                            Row(modifier = Modifier
                                .fillMaxWidth()
                                .pointerInput(Unit)
                                {
                                    detectTapGestures(
                                        onTap = {
                                            //when asset clicked, show asset details popup
                                            navController.navigate("checkinout-details-popup" + "/$logID")
                                        }
                                    )
                                }
                            ) {
                                TableCell(text = log.ID, weight = column1Weight)
                                TableCell(text = log.assetID, weight = column2Weight)
                                TableCell(text = log.checkOutDate, weight = column3Weight)
                                TableCell(text = log.checkInDate, weight = column4Weight)

                            }
                        }


                        if (assetIDMatch) {
                            Row(modifier = Modifier
                                .fillMaxWidth()
                                .pointerInput(Unit)
                                {
                                    detectTapGestures(
                                        onTap = {
                                            //when asset clicked, show asset details popup
                                            navController.navigate("checkinout-details-popup" + "/$logID")
                                        }
                                    )
                                }
                            ) {
                                TableCell(text = log.ID, weight = column1Weight)
                                TableCell(text = log.assetID, weight = column2Weight)
                                TableCell(text = log.checkOutDate, weight = column3Weight)
                                TableCell(text = log.checkInDate, weight = column4Weight)

                            }
                        }

                        if (employeeNameMatch) {
                            Row(modifier = Modifier
                                .fillMaxWidth()
                                .pointerInput(Unit)
                                {
                                    detectTapGestures(
                                        onTap = {
                                            //when asset clicked, show asset details popup
                                            navController.navigate("checkinout-details-popup" + "/$logID")
                                        }
                                    )
                                }
                            ) {
                                TableCell(text = log.ID, weight = column1Weight)
                                TableCell(text = log.assetID, weight = column2Weight)
                                TableCell(text = log.checkOutDate, weight = column3Weight)
                                TableCell(text = log.checkInDate, weight = column4Weight)

                            }
                        }
                    }



                    if (assetIDMatch) {
                        if (
                            checkOutSearchViewModel.checkOutDate.isEmpty() &&
                            checkOutSearchViewModel.checkInDate.isEmpty() &&
                            checkOutSearchViewModel.employeeName.isEmpty()
                        ) {
                            Row(modifier = Modifier
                                .fillMaxWidth()
                                .pointerInput(Unit)
                                {
                                    detectTapGestures(
                                        onTap = {
                                            //when asset clicked, show asset details popup
                                            navController.navigate("checkinout-details-popup" + "/$logID")
                                        }
                                    )
                                }
                            ) {
                                TableCell(text = log.ID, weight = column1Weight)
                                TableCell(text = log.assetID, weight = column2Weight)
                                TableCell(text = log.checkOutDate, weight = column3Weight)
                                TableCell(text = log.checkInDate, weight = column4Weight)

                            }
                        }

                        if (checkOutDateMatch) {
                            Row(modifier = Modifier
                                .fillMaxWidth()
                                .pointerInput(Unit)
                                {
                                    detectTapGestures(
                                        onTap = {
                                            //when asset clicked, show asset details popup
                                            navController.navigate("checkinout-details-popup" + "/$logID")
                                        }
                                    )
                                }
                            ) {
                                TableCell(text = log.ID, weight = column1Weight)
                                TableCell(text = log.assetID, weight = column2Weight)
                                TableCell(text = log.checkOutDate, weight = column3Weight)
                                TableCell(text = log.checkInDate, weight = column4Weight)

                            }
                        }
                        if (checkInDateMatch) {
                            Row(modifier = Modifier
                                .fillMaxWidth()
                                .pointerInput(Unit)
                                {
                                    detectTapGestures(
                                        onTap = {
                                            //when asset clicked, show asset details popup
                                            navController.navigate("checkinout-details-popup" + "/$logID")
                                        }
                                    )
                                }
                            ) {
                                TableCell(text = log.ID, weight = column1Weight)
                                TableCell(text = log.assetID, weight = column2Weight)
                                TableCell(text = log.checkOutDate, weight = column3Weight)
                                TableCell(text = log.checkInDate, weight = column4Weight)

                            }
                        }


                        if (employeeNameMatch) {
                            Row(modifier = Modifier
                                .fillMaxWidth()
                                .pointerInput(Unit)
                                {
                                    detectTapGestures(
                                        onTap = {
                                            //when asset clicked, show asset details popup
                                            navController.navigate("checkinout-details-popup" + "/$logID")
                                        }
                                    )
                                }
                            ) {
                                TableCell(text = log.ID, weight = column1Weight)
                                TableCell(text = log.assetID, weight = column2Weight)
                                TableCell(text = log.checkOutDate, weight = column3Weight)
                                TableCell(text = log.checkInDate, weight = column4Weight)

                            }
                        }
                    }

                    if (employeeNameMatch) {
                        if (
                            checkOutSearchViewModel.checkOutDate.isEmpty() &&
                            checkOutSearchViewModel.checkInDate.isEmpty() &&
                            checkOutSearchViewModel.assetSerialNum.isEmpty()
                        ) {
                            Row(modifier = Modifier
                                .fillMaxWidth()
                                .pointerInput(Unit)
                                {
                                    detectTapGestures(
                                        onTap = {
                                            //when asset clicked, show asset details popup
                                            navController.navigate("checkinout-details-popup" + "/$logID")
                                        }
                                    )
                                }
                            ) {
                                TableCell(text = log.ID, weight = column1Weight)
                                TableCell(text = log.assetID, weight = column2Weight)
                                TableCell(text = log.checkOutDate, weight = column3Weight)
                                TableCell(text = log.checkInDate, weight = column4Weight)

                            }
                        }

                        if (checkOutDateMatch) {
                            Row(modifier = Modifier
                                .fillMaxWidth()
                                .pointerInput(Unit)
                                {
                                    detectTapGestures(
                                        onTap = {
                                            //when asset clicked, show asset details popup
                                            navController.navigate("checkinout-details-popup" + "/$logID")
                                        }
                                    )
                                }
                            ) {
                                TableCell(text = log.ID, weight = column1Weight)
                                TableCell(text = log.assetID, weight = column2Weight)
                                TableCell(text = log.checkOutDate, weight = column3Weight)
                                TableCell(text = log.checkInDate, weight = column4Weight)

                            }
                        }

                        if (checkInDateMatch) {
                            Row(modifier = Modifier
                                .fillMaxWidth()
                                .pointerInput(Unit)
                                {
                                    detectTapGestures(
                                        onTap = {
                                            //when asset clicked, show asset details popup
                                            navController.navigate("checkinout-details-popup" + "/$logID")
                                        }
                                    )
                                }
                            ) {
                                TableCell(text = log.ID, weight = column1Weight)
                                TableCell(text = log.assetID, weight = column2Weight)
                                TableCell(text = log.checkOutDate, weight = column3Weight)
                                TableCell(text = log.checkInDate, weight = column4Weight)

                            }
                        }

                        if (assetIDMatch) {
                            Row(modifier = Modifier
                                .fillMaxWidth()
                                .pointerInput(Unit)
                                {
                                    detectTapGestures(
                                        onTap = {
                                            //when asset clicked, show asset details popup
                                            navController.navigate("checkinout-details-popup" + "/$logID")
                                        }
                                    )
                                }
                            ) {
                                TableCell(text = log.ID, weight = column1Weight)
                                TableCell(text = log.assetID, weight = column2Weight)
                                TableCell(text = log.checkOutDate, weight = column3Weight)
                                TableCell(text = log.checkInDate, weight = column4Weight)

                            }
                        }
                    }


                    if (filterFieldsEmpty) {
                        Row(modifier = Modifier
                            .fillMaxWidth()
                            .pointerInput(Unit)
                            {
                                detectTapGestures(
                                    onTap = {
                                        //when asset clicked, show asset details popup
                                        navController.navigate("checkinout-details-popup" + "/$logID")
                                    }
                                )
                            }
                        ) {
                            TableCell(text = log.ID, weight = column1Weight)
                            TableCell(text = log.assetID, weight = column2Weight)
                            TableCell(text = log.checkOutDate, weight = column3Weight)
                            TableCell(text = log.checkInDate, weight = column4Weight)

                        }
                    }
                }
            }
        }
    }
}


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun CheckInTable(
    width: Int,
    height: Int,
    navController: NavController,
    checkInOutTableViewModel : CheckInOutTableViewModel,
    checkInSearchViewModel: CheckInSearchViewModel
) {

    val column1Weight = .2f
    val column2Weight = .25f
    val column3Weight = .25f
    val column4Weight = .3f

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    var filterFieldsEmpty = checkInSearchViewModel.checkInOutID.isEmpty() &&
            checkInSearchViewModel.checkOutDate.isEmpty() &&
            checkInSearchViewModel.assetSerialNum.isEmpty() &&
            checkInSearchViewModel.employeeName.isEmpty()

    var checkInOutIDMatch: Boolean
    var checkOutDateMatch: Boolean
    var assetIDMatch: Boolean
    var employeeNameMatch: Boolean

    Column()
    {
        // Table title
        Text(
            text = "Check In an Asset",
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
                    TableCell(text = "Check Out Date", weight = column3Weight)
                    TableCell(text = "Employee Name", weight = column4Weight)
                }
            }

            // The table data
            items(checkInOutTableViewModel.allCheckInOutLogs) { log ->

                checkInOutIDMatch = checkInSearchViewModel.checkInOutID == log.ID
                checkOutDateMatch = checkInSearchViewModel.checkOutDate == log.checkOutDate
                assetIDMatch = checkInSearchViewModel.assetSerialNum == log.assetID
                employeeNameMatch = checkInSearchViewModel.employeeName == log.employeeName

                // stores ID of row to pass it
                val logID = log.ID

                // takes all logs do not have a check in date and display them in table
                if ( log.checkInDate == "") {

                    if (checkInOutIDMatch) {
                        if (checkInSearchViewModel.checkOutDate.isEmpty() &&
                            checkInSearchViewModel.assetSerialNum.isEmpty() &&
                            checkInSearchViewModel.employeeName.isEmpty()
                        ) {
                            Row(modifier = Modifier
                                .fillMaxWidth()
                                .pointerInput(Unit)
                                {
                                    detectTapGestures(
                                        onTap = {
                                            //when asset clicked, show asset details popup
                                            navController.navigate("checkin-details-popup" + "/$logID")
                                        }
                                    )
                                }
                            ) {
                                TableCell(text = log.ID, weight = column1Weight)
                                TableCell(text = log.assetID, weight = column2Weight)
                                TableCell(text = log.checkOutDate, weight = column3Weight)
                                TableCell(text = log.employeeName, weight = column4Weight)

                            }
                        }
                    }


                    if (checkOutDateMatch) {
                        if (
                            checkInSearchViewModel.assetSerialNum.isEmpty() &&
                            checkInSearchViewModel.employeeName.isEmpty()
                        ) {
                            Row(modifier = Modifier
                                .fillMaxWidth()
                                .pointerInput(Unit)
                                {
                                    detectTapGestures(
                                        onTap = {
                                            //when asset clicked, show asset details popup
                                            navController.navigate("checkin-details-popup" + "/$logID")
                                        }
                                    )
                                }
                            ) {
                                TableCell(text = log.ID, weight = column1Weight)
                                TableCell(text = log.assetID, weight = column2Weight)
                                TableCell(text = log.checkOutDate, weight = column3Weight)
                                TableCell(text = log.employeeName, weight = column4Weight)

                            }
                        }

                        if (assetIDMatch) {
                            Row(modifier = Modifier
                                .fillMaxWidth()
                                .pointerInput(Unit)
                                {
                                    detectTapGestures(
                                        onTap = {
                                            //when asset clicked, show asset details popup
                                            navController.navigate("checkin-details-popup" + "/$logID")
                                        }
                                    )
                                }
                            ) {
                                TableCell(text = log.ID, weight = column1Weight)
                                TableCell(text = log.assetID, weight = column2Weight)
                                TableCell(text = log.checkOutDate, weight = column3Weight)
                                TableCell(text = log.employeeName, weight = column4Weight)

                            }
                        }

                        if (employeeNameMatch) {
                            Row(modifier = Modifier
                                .fillMaxWidth()
                                .pointerInput(Unit)
                                {
                                    detectTapGestures(
                                        onTap = {
                                            //when asset clicked, show asset details popup
                                            navController.navigate("checkin-details-popup" + "/$logID")
                                        }
                                    )
                                }
                            ) {
                                TableCell(text = log.ID, weight = column1Weight)
                                TableCell(text = log.assetID, weight = column2Weight)
                                TableCell(text = log.checkOutDate, weight = column3Weight)
                                TableCell(text = log.employeeName, weight = column4Weight)

                            }
                        }
                    }

                    if (assetIDMatch) {
                        if (
                            checkInSearchViewModel.checkOutDate.isEmpty() &&
                            checkInSearchViewModel.employeeName.isEmpty()
                        ) {
                            Row(modifier = Modifier
                                .fillMaxWidth()
                                .pointerInput(Unit)
                                {
                                    detectTapGestures(
                                        onTap = {
                                            //when asset clicked, show asset details popup
                                            navController.navigate("checkin-details-popup" + "/$logID")
                                        }
                                    )
                                }
                            ) {
                                TableCell(text = log.ID, weight = column1Weight)
                                TableCell(text = log.assetID, weight = column2Weight)
                                TableCell(text = log.checkOutDate, weight = column3Weight)
                                TableCell(text = log.employeeName, weight = column4Weight)

                            }
                        }

                        if (checkOutDateMatch) {
                            Row(modifier = Modifier
                                .fillMaxWidth()
                                .pointerInput(Unit)
                                {
                                    detectTapGestures(
                                        onTap = {
                                            //when asset clicked, show asset details popup
                                            navController.navigate("checkin-details-popup" + "/$logID")
                                        }
                                    )
                                }
                            ) {
                                TableCell(text = log.ID, weight = column1Weight)
                                TableCell(text = log.assetID, weight = column2Weight)
                                TableCell(text = log.checkOutDate, weight = column3Weight)
                                TableCell(text = log.employeeName, weight = column4Weight)

                            }
                        }

                        if (employeeNameMatch) {
                            Row(modifier = Modifier
                                .fillMaxWidth()
                                .pointerInput(Unit)
                                {
                                    detectTapGestures(
                                        onTap = {
                                            //when asset clicked, show asset details popup
                                            navController.navigate("checkin-details-popup" + "/$logID")
                                        }
                                    )
                                }
                            ) {
                                TableCell(text = log.ID, weight = column1Weight)
                                TableCell(text = log.assetID, weight = column2Weight)
                                TableCell(text = log.checkOutDate, weight = column3Weight)
                                TableCell(text = log.employeeName, weight = column4Weight)

                            }
                        }
                    }

                    if (employeeNameMatch) {
                        if (
                            checkInSearchViewModel.checkOutDate.isEmpty() &&
                            checkInSearchViewModel.assetSerialNum.isEmpty()
                        ) {
                            Row(modifier = Modifier
                                .fillMaxWidth()
                                .pointerInput(Unit)
                                {
                                    detectTapGestures(
                                        onTap = {
                                            //when asset clicked, show asset details popup
                                            navController.navigate("checkin-details-popup" + "/$logID")
                                        }
                                    )
                                }
                            ) {
                                TableCell(text = log.ID, weight = column1Weight)
                                TableCell(text = log.assetID, weight = column2Weight)
                                TableCell(text = log.checkOutDate, weight = column3Weight)
                                TableCell(text = log.employeeName, weight = column4Weight)

                            }
                        }

                        if (checkOutDateMatch) {
                            Row(modifier = Modifier
                                .fillMaxWidth()
                                .pointerInput(Unit)
                                {
                                    detectTapGestures(
                                        onTap = {
                                            //when asset clicked, show asset details popup
                                            navController.navigate("checkin-details-popup" + "/$logID")
                                        }
                                    )
                                }
                            ) {
                                TableCell(text = log.ID, weight = column1Weight)
                                TableCell(text = log.assetID, weight = column2Weight)
                                TableCell(text = log.checkOutDate, weight = column3Weight)
                                TableCell(text = log.employeeName, weight = column4Weight)

                            }
                        }

                        if (assetIDMatch) {
                            Row(modifier = Modifier
                                .fillMaxWidth()
                                .pointerInput(Unit)
                                {
                                    detectTapGestures(
                                        onTap = {
                                            //when asset clicked, show asset details popup
                                            navController.navigate("checkin-details-popup" + "/$logID")
                                        }
                                    )
                                }
                            ) {
                                TableCell(text = log.ID, weight = column1Weight)
                                TableCell(text = log.assetID, weight = column2Weight)
                                TableCell(text = log.checkOutDate, weight = column3Weight)
                                TableCell(text = log.employeeName, weight = column4Weight)

                            }
                        }
                    }


                    if (filterFieldsEmpty) {
                        Row(modifier = Modifier
                            .fillMaxWidth()
                            .pointerInput(Unit)
                            {
                                detectTapGestures(
                                    onTap = {
                                        //when asset clicked, show asset details popup
                                        navController.navigate("checkin-details-popup" + "/$logID")
                                    }
                                )
                            }
                        ) {
                            TableCell(text = log.ID, weight = column1Weight)
                            TableCell(text = log.assetID, weight = column2Weight)
                            TableCell(text = log.checkOutDate, weight = column3Weight)
                            TableCell(text = log.employeeName, weight = column4Weight)

                        }
                    }
                }
            }
        }
    }
}