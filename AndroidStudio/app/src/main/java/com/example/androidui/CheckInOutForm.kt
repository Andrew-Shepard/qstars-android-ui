package com.example.androidui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

// This file contains the screen for the Check In Form
@Composable
fun CheckInFormScreen(
    navController: NavController,
    checkInOutFormViewModel: CheckInOutFormViewModel,
    checkInOutTableViewModel: CheckInOutTableViewModel
) {
    Column {

        //Title and Close Button
        Box(modifier = Modifier.fillMaxWidth()) {
            Button(
                onClick = {

                    //clear textfields
                    checkInOutFormViewModel.ID = ""
                    checkInOutFormViewModel.assetID = ""
                    checkInOutFormViewModel.employeeID = ""
                    checkInOutFormViewModel.employeeName = ""
                    checkInOutFormViewModel.checkOutDate = ""
                    checkInOutFormViewModel.currentLocation = ""
                    checkInOutFormViewModel.description = ""

                    navController.popBackStack()
                },
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .padding(10.dp)
                    .width(55.dp)
            ) {
                Icon(Icons.Filled.Close, "")
            }
            Text(
                "Check Out Asset",
                Modifier
                    .align(Alignment.TopCenter)
                    .offset(y = 20.dp),
                fontSize = 25.sp,
                fontWeight = FontWeight.Medium
            )
        }

        // A LazyColumn for all the fields
        LazyColumn(
            modifier = Modifier.padding(10.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            // ID Field
            item {
                MultiStyleText(text1 = "ID", color1 = Color.Black, text2 = "*", color2 = Color.Red)
                AppTextField(
                    text = checkInOutFormViewModel.ID,
                    onChange = { checkInOutFormViewModel.onIDChange(it) },
                    placeholder = "ID"
                )
            }

            // + Asset Button
            item {
                Button(onClick = {
                    navController.navigate("add-asset-table-checkin")
                }) {
                    Text(text = "+ Asset", fontSize = 20.sp)
                }
            }

            // Asset ID Text
            item {
                Text("Asset ID: " + checkInOutFormViewModel.assetID, fontSize = 15.sp)
            }

            // Employee ID
            item {
                MultiStyleText(
                    text1 = "Employee ID",
                    color1 = Color.Black,
                    text2 = "*",
                    color2 = Color.Red
                )
                AppTextField(
                    text = checkInOutFormViewModel.employeeID,
                    onChange = { checkInOutFormViewModel.onEmployeeIDChange(it) },
                    placeholder = "Employee ID"
                )
            }

            // Employee Name
            item {
                MultiStyleText(
                    text1 = "Employee Name",
                    color1 = Color.Black,
                    text2 = "*",
                    color2 = Color.Red
                )
                AppTextField(
                    text = checkInOutFormViewModel.employeeName,
                    onChange = { checkInOutFormViewModel.onEmployeeNameChange(it) },
                    placeholder = "Employee Name"
                )
            }

            // Check Out Date Field
            item {
                val context = LocalContext.current

                MultiStyleText(
                    text1 = "Date of Check Out",
                    color1 = Color.Black,
                    text2 = "*",
                    color2 = Color.Red
                )
                AppTextField(
                    modifier = Modifier.clickable {
                        checkInOutFormViewModel.showDatePickerDialog(context)
                    },
                    text = checkInOutFormViewModel.checkOutDate,
                    placeholder = "MM-DD-YYYY",
                    onChange = {
                        checkInOutFormViewModel.checkOutDate = it
                    },
                    isEnabled = false
                )
            }

            // This was commented out because the sponsor did not want the current location to be during check out
            // They wanted it during check in
            /*
            item {
                MultiStyleText(text1 = "Current Location", color1 = Color.Black, text2 = "*", color2 = Color.Red)
                AppTextField(
                    text = checkInOutFormViewModel.currentLocation,
                    onChange = { checkInOutFormViewModel.onCurrentLocationChange(it) },
                    placeholder = "Current Location")
            }

             */

            // Description Field
            item {
                Text(text = "Description", fontSize = 20.sp)
                AppTextField(
                    text = checkInOutFormViewModel.description,
                    onChange = { checkInOutFormViewModel.onDescriptionChange(it) },
                    placeholder = "Description"
                )
            }

            // Check Out Button to create new check out log
            item {
                Spacer(modifier = Modifier.height(20.dp))
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight(),
                    verticalArrangement = Arrangement.Center
                ) {

                    // checks whether any of the required fields are empty
                    var requiredFieldsFilled = checkInOutFormViewModel.ID.isNotEmpty() &&
                            checkInOutFormViewModel.assetID.isNotEmpty() &&
                            checkInOutFormViewModel.employeeID.isNotEmpty() &&
                            checkInOutFormViewModel.employeeName.isNotEmpty() &&
                            checkInOutFormViewModel.checkOutDate.isNotEmpty()
                    //checkInOutFormViewModel.currentLocation.isNotEmpty()


                    Button(modifier = Modifier.align(Alignment.CenterHorizontally),
                        enabled = requiredFieldsFilled,
                        onClick = {

                            //pass all values to create new flight log
                            checkInOutTableViewModel.newCheckInOutLog(
                                checkInOutFormViewModel.ID,
                                checkInOutFormViewModel.assetID,
                                checkInOutFormViewModel.employeeID,
                                checkInOutFormViewModel.employeeName,
                                checkInOutFormViewModel.checkOutDate,
                                checkInOutFormViewModel.checkInDate,
                                checkInOutFormViewModel.currentLocation,
                                checkInOutFormViewModel.description
                            )

                            //clear textfields
                            checkInOutFormViewModel.ID = ""
                            checkInOutFormViewModel.assetID = ""
                            checkInOutFormViewModel.employeeID = ""
                            checkInOutFormViewModel.employeeName = ""
                            checkInOutFormViewModel.checkOutDate = ""
                            checkInOutFormViewModel.currentLocation = ""
                            checkInOutFormViewModel.description = ""

                            //navigate to asset screen
                            navController.navigate("check-in-out-logs")


                        }) {
                        Text("Check out Asset", fontSize = 20.sp)
                    }
                }
                Spacer(modifier = Modifier.height(20.dp))
            }
        }
        Spacer(modifier = Modifier.height(20.dp))
    }
}