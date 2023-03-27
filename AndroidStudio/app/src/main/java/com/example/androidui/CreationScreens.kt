package com.example.androidui

import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@Composable
fun AssetCreationScreen(navController: NavController) {

    // Asset Creation Fields
    val inputFieldList: List<InputFieldData> = listOf(
        InputFieldData("Asset Serial Number"),
        InputFieldData("Asset Name"),
        InputFieldData("Asset Type", 200, dropDown = true),
        InputFieldData("Status", 250, dropDown = true),
        InputFieldData("+ Parent", button = true),
        InputFieldData("+ Child", button = true),
        InputFieldData("Location"),
        InputFieldData("Purchase Date", 175),
        InputFieldData("Description", height = 80)
    )

    // Drop-down values for Asset Type
    val assetTypeDropDown = listOf(
        "Drone",
        "Motor",
        "Battery",
        "Other"
    )

    // Drop-down values for Asset Type
    val assetStatusDropDown = listOf(
        "Active",
        "In Maintenance",
        "Out of Commission"
    )

    // Make a list of the drop-down values
    val dropDownLists: List<List<String>> = listOf(assetTypeDropDown, assetStatusDropDown)

    // Asset Creation Screen
    CustomDialog(
        dialogTitle = "Create New Asset",
        inputFieldList = inputFieldList,
        dropDownList = dropDownLists,
        buttonName = "Create",
        navController = navController)

}


@Composable
fun FlightLogCreationScreen(navController: NavController) {

    val inputFieldList: List<InputFieldData> = listOf(
        InputFieldData("Mission ID"),
        InputFieldData("Pilot ID"),
        InputFieldData("Pilot Name"),
        InputFieldData("Success", dropDown = true, width = 175),
        InputFieldData("+ Start Time", button = true),
        InputFieldData("+ End Time", button = true),
        InputFieldData("Total Time", width = 175),
        InputFieldData("Observer ID"),
        InputFieldData("Observer Name"),
        InputFieldData("Test Mission", dropDown = true, width = 175),
        InputFieldData("Drone Serial #"),
        InputFieldData("# of Landings", width = 100),
        InputFieldData("# of Cycles", width = 100),
        InputFieldData("Summary", height = 80),
    )

    val successeDropDown = listOf(
        "Success",
        "Failure"
    )
    val testMissionDropDown = listOf(
        "Yes",
        "No"
    )

    val dropDownLists: List<List<String>> = listOf(successeDropDown, testMissionDropDown)

    CustomDialog(
        dialogTitle = "Create New Flight Log",
        inputFieldList = inputFieldList,
        dropDownList = dropDownLists,
        buttonName = "Create",
        navController = navController
    )

}


@Composable
fun CheckInCreationScreen(navController: NavController) {
    val inputFieldList: List<InputFieldData> = listOf(
        InputFieldData("Asset Serial Number"),
        InputFieldData("Employee ID"),
        InputFieldData("Check In Date", 175),
        InputFieldData("Description", height = 80)
    )

    val dropDownLists: List<List<String>> = listOf(listOf(""))

    CustomDialog(
        dialogTitle = "New Check In",
        inputFieldList = inputFieldList ,
        dropDownList = dropDownLists,
        buttonName = "Check In",
        navController = navController
    )
}


@Composable
fun CheckOutCreationScreen() {
    val inputFieldList: List<InputFieldData> = listOf(
        InputFieldData("Asset Serial Number"),
        InputFieldData("Employee ID"),
        InputFieldData("Check Out Date", 175),
        InputFieldData("Description", height = 80)
    )

    val emptyDropDown = listOf("")

    val dropDownLists: List<List<String>> = listOf(emptyDropDown)


    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(10.dp)
    ) {


        Column {

            // Close Button
            Box(modifier = Modifier.fillMaxWidth()) {
                Button(onClick = {}, modifier = Modifier
                    .align(Alignment.TopEnd)
                    .width(55.dp)) {
                    Icon(Icons.Filled.Close, "")
                }
            }

            // Title
            Box(modifier = Modifier.fillMaxWidth()) {
                Text(
                    "Check Out Asset",
                    Modifier.align(Alignment.TopCenter),
                    fontSize = 25.sp,
                    fontWeight = FontWeight.Medium
                )
            }

            Spacer(modifier = Modifier.height(15.dp))

            //AllInputFields(inputFieldList, dropDownLists, "Check Out")

        }
    }
}


@Composable
fun MaintenanceLogCreationScreen(navController: NavController) {
    val inputFieldList: List<InputFieldData> = listOf(
        InputFieldData("Maintenance ID"),
        InputFieldData("Asset Serial #"),
        InputFieldData("Employee ID"),
        InputFieldData("Employee Name"),
        InputFieldData("Date", width = 80),
        InputFieldData("Maintenance Type", dropDown = true),
        InputFieldData("Additional Details", height = 80)
    )

    val maintenanceTypeDropDown = listOf(
        "Motor Replacement",
        "Battery Replacement",
        "Etc"
    )

    val dropDownLists: List<List<String>> = listOf(maintenanceTypeDropDown)

    CustomDialog(
        dialogTitle = "New Maintenance Log",
        inputFieldList = inputFieldList,
        dropDownList = dropDownLists,
        buttonName = "Create",
        navController = navController
    )
}
