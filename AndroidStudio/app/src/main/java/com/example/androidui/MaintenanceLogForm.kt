package com.example.androidui

import android.app.DatePickerDialog
import android.content.Context
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import java.text.SimpleDateFormat
import java.util.*

@Composable
fun MaintenanceLogFormScreen(
    navController: NavController,
    maintenanceFormViewModel: MaintenanceLogFormViewModel,
    maintenanceTableViewModel: MaintenanceTableViewModel
) {
    Column {

        //Title and Close Button
        Box(modifier = Modifier.fillMaxWidth()) {
            Button(
                onClick = {

                    //clear textfields
                    maintenanceFormViewModel.ID = ""
                    maintenanceFormViewModel.assetID = ""
                    maintenanceFormViewModel.employeeID = ""
                    maintenanceFormViewModel.employeeName = ""
                    maintenanceFormViewModel.dateOfMaintenance = ""
                    maintenanceFormViewModel.typeOfMaintenane = ""
                    maintenanceFormViewModel.additionalDetails = ""

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
                "Create Maintenance Log",
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
                MultiStyleText(text1 = "ID", color1 = Color.Black, text2 = "*", color2 = Color.Red)
                AppTextField(
                    text = maintenanceFormViewModel.ID,
                    onChange = { maintenanceFormViewModel.onIDChange(it) },
                    placeholder = "ID")
            }

            item {
                Button(onClick = {
                    navController.navigate("add-asset-table-maintenance")
                }) {
                    Text(text = "+ Asset", fontSize = 20.sp)
                }
            }

            item {
                Text("Asset ID: " + maintenanceFormViewModel.assetID, fontSize = 15.sp)
            }

            item {
                MultiStyleText(text1 = "Employee ID", color1 = Color.Black, text2 = "*", color2 = Color.Red)
                AppTextField(
                    text = maintenanceFormViewModel.employeeID,
                    onChange = { maintenanceFormViewModel.onEmployeeIDChange(it) },
                    placeholder = "Employee ID")
            }

            item {
                MultiStyleText(text1 = "Employee Name", color1 = Color.Black, text2 = "*", color2 = Color.Red)
                AppTextField(
                    text = maintenanceFormViewModel.employeeName,
                    onChange = { maintenanceFormViewModel.onEmployeeNameChange(it) },
                    placeholder = "Employee Name")
            }

            item {
                val context = LocalContext.current

                MultiStyleText(text1 = "Date of Maintenance", color1 = Color.Black, text2 = "*", color2 = Color.Red)
                AppTextField(
                    modifier = Modifier.clickable {
                        maintenanceFormViewModel.showDatePickerDialog(context)
                    },
                    text = maintenanceFormViewModel.dateOfMaintenance,
                    placeholder = "MM-DD-YYYY",
                    onChange = {
                        maintenanceFormViewModel.dateOfMaintenance = it
                    },
                    isEnabled = false
                )
            }

            item {
                val maintenanceTypeDropDown = listOf(
                    "Motor Replacement",
                    "Battery Replacement",
                    "Etc"
                )

                MultiStyleText(text1 = "Maintenance Type", color1 = Color.Black, text2 = "*", color2 = Color.Red)
                AppTextFieldDropDown(
                    selectedText = maintenanceFormViewModel.typeOfMaintenane,
                    placeholder = "-Select-",
                    dropDownItems = maintenanceTypeDropDown,
                    onSelectedTextChange = { maintenanceFormViewModel.onTypeChange(it) }
                )
            }

            item {
                Text(text = "Additional Details", fontSize = 20.sp)
                AppTextField(
                    text = maintenanceFormViewModel.additionalDetails,
                    onChange = { maintenanceFormViewModel.onDetailsChange(it) },
                    placeholder = "Additional Details")
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
                    var requiredFieldsFilled = maintenanceFormViewModel.ID.isNotEmpty() &&
                            maintenanceFormViewModel.assetID.isNotEmpty() &&
                            maintenanceFormViewModel.employeeID.isNotEmpty() &&
                            maintenanceFormViewModel.employeeName.isNotEmpty() &&
                            maintenanceFormViewModel.dateOfMaintenance.isNotEmpty() &&
                            maintenanceFormViewModel.typeOfMaintenane.isNotEmpty() &&
                            maintenanceFormViewModel.additionalDetails.isNotEmpty()


                    Button( modifier = Modifier.align(Alignment.CenterHorizontally),
                        enabled = requiredFieldsFilled,
                        onClick = {

                            //pass all values to create new flight log
                            maintenanceTableViewModel.newMaintenanceLog(
                                maintenanceFormViewModel.ID,
                                maintenanceFormViewModel.assetID,
                                maintenanceFormViewModel.employeeID,
                                maintenanceFormViewModel.employeeName,
                                maintenanceFormViewModel.dateOfMaintenance,
                                maintenanceFormViewModel.typeOfMaintenane,
                                maintenanceFormViewModel.additionalDetails
                            )

                            //clear textfields
                            maintenanceFormViewModel.ID = ""
                            maintenanceFormViewModel.assetID = ""
                            maintenanceFormViewModel.employeeID = ""
                            maintenanceFormViewModel.employeeName = ""
                            maintenanceFormViewModel.dateOfMaintenance = ""
                            maintenanceFormViewModel.typeOfMaintenane = ""
                            maintenanceFormViewModel.additionalDetails = ""

                            //navigate to asset screen
                            navController.navigate("maintenance-logs")


                        }) {
                        Text("Create Maintenance Log", fontSize = 20.sp)
                    }
                }
                Spacer(modifier = Modifier.height(20.dp))
            }




        }

        Spacer(modifier = Modifier.height(20.dp))
    }
}