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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

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

        LazyColumn(
            modifier = Modifier.padding(10.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            item {
                Text(text = "ID")
                AppTextField(
                    text = checkInOutFormViewModel.ID,
                    onChange = { checkInOutFormViewModel.onIDChange(it) },
                    placeholder = "Flight Log ID")
            }

            item {
                Text(text = "Asset ID")
                AppTextField(
                    text = checkInOutFormViewModel.assetID,
                    onChange = { checkInOutFormViewModel.onAssetIDChange(it) },
                    placeholder = "Asset ID")
            }

            item {
                Text(text = "Employee ID")
                AppTextField(
                    text = checkInOutFormViewModel.employeeID,
                    onChange = { checkInOutFormViewModel.onEmployeeIDChange(it) },
                    placeholder = "Employee ID")
            }

            item {
                Text(text = "Employee Name")
                AppTextField(
                    text = checkInOutFormViewModel.employeeName,
                    onChange = { checkInOutFormViewModel.onEmployeeNameChange(it) },
                    placeholder = "Employee Name")
            }

            item {
                val context = LocalContext.current

                Text(text = "Check Out Date")
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

            item {
                Text(text = "Current Location")
                AppTextField(
                    text = checkInOutFormViewModel.currentLocation,
                    onChange = { checkInOutFormViewModel.onCurrentLocationChange(it) },
                    placeholder = "Current Location")
            }

            item {
                Text(text = "Description")
                AppTextField(
                    text = checkInOutFormViewModel.description,
                    onChange = { checkInOutFormViewModel.onDescriptionChange(it) },
                    placeholder = "Description")
            }

            item{
                Spacer(modifier = Modifier.height(20.dp))
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight(),
                    verticalArrangement = Arrangement.Center
                ) {

                    //checks whether any of the required fields are empty
                    var requiredFieldsFilled = checkInOutFormViewModel.ID.isNotEmpty() &&
                            checkInOutFormViewModel.assetID.isNotEmpty() &&
                            checkInOutFormViewModel.employeeID.isNotEmpty() &&
                            checkInOutFormViewModel.employeeName.isNotEmpty() &&
                            checkInOutFormViewModel.checkOutDate.isNotEmpty() &&
                            checkInOutFormViewModel.currentLocation.isNotEmpty() &&
                            checkInOutFormViewModel.description.isNotEmpty()


                    Button( modifier = Modifier.align(Alignment.CenterHorizontally),
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
                        Text("Check out Asset")
                    }
                }
                Spacer(modifier = Modifier.height(20.dp))
            }




        }

        Spacer(modifier = Modifier.height(20.dp))
    }
}