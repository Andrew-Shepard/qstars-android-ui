package com.example.androidui

import android.os.Build.ID
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
@Composable
fun MaintenanceTableComponent(
    navController: NavController,
    ID: String,
    log: MaintenanceLog,
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

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun MaintenanceLogTable(
    width: Int,
    height: Int,
    navController: NavController,
    maintenanceLogTableViewModel: MaintenanceTableViewModel,
    maintenanceSearchViewModel: MaintenanceSearchViewModel
) {

    val column1Weight = .3f
    val column2Weight = .3f
    val column3Weight = .2f
    val column4Weight = .2f

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route


    var filterFieldsEmpty = maintenanceSearchViewModel.ID.isEmpty() &&
            maintenanceSearchViewModel.assetID.isEmpty() &&
            maintenanceSearchViewModel.date.isEmpty() &&
            maintenanceSearchViewModel.maintenanceType.isEmpty() &&
            maintenanceSearchViewModel.employeeName.isEmpty()

    var IDMatch: Boolean
    var assetIDMatch: Boolean
    var dateMatch: Boolean
    var maintenanceTypeMatch: Boolean
    var employeeNameMatch: Boolean


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

                IDMatch = maintenanceSearchViewModel.ID == log.ID
                assetIDMatch = maintenanceSearchViewModel.assetID == log.assetID
                dateMatch = maintenanceSearchViewModel.date == log.dateOfMaintenance
                maintenanceTypeMatch = maintenanceSearchViewModel.maintenanceType == log.typeOfMaintenane
                employeeNameMatch = maintenanceSearchViewModel.employeeName == log.employeeName

                // stores ID of row to pass it
                val ID = log.ID

                if (IDMatch){
                    if (
                        maintenanceSearchViewModel.assetID.isEmpty() &&
                        maintenanceSearchViewModel.date.isEmpty() &&
                        maintenanceSearchViewModel.maintenanceType.isEmpty() &&
                        maintenanceSearchViewModel.employeeName.isEmpty()
                    ){
                        MaintenanceTableComponent(
                            navController = navController,
                            ID = ID,
                            log = log,
                            column1Weight = column1Weight,
                            column2Weight = column2Weight,
                            column3Weight = column3Weight,
                            column4Weight = column4Weight
                        )
                    }

                    if(assetIDMatch){
                        MaintenanceTableComponent(
                            navController = navController,
                            ID = ID,
                            log = log,
                            column1Weight = column1Weight,
                            column2Weight = column2Weight,
                            column3Weight = column3Weight,
                            column4Weight = column4Weight
                        )
                    }

                    if(dateMatch){
                        MaintenanceTableComponent(
                            navController = navController,
                            ID = ID,
                            log = log,
                            column1Weight = column1Weight,
                            column2Weight = column2Weight,
                            column3Weight = column3Weight,
                            column4Weight = column4Weight
                        )
                    }

                    if(maintenanceTypeMatch){
                        MaintenanceTableComponent(
                            navController = navController,
                            ID = ID,
                            log = log,
                            column1Weight = column1Weight,
                            column2Weight = column2Weight,
                            column3Weight = column3Weight,
                            column4Weight = column4Weight
                        )
                    }

                    if(employeeNameMatch){
                        MaintenanceTableComponent(
                            navController = navController,
                            ID = ID,
                            log = log,
                            column1Weight = column1Weight,
                            column2Weight = column2Weight,
                            column3Weight = column3Weight,
                            column4Weight = column4Weight
                        )
                    }
                }

                if(assetIDMatch){
                    if (
                        maintenanceSearchViewModel.date.isEmpty() &&
                        maintenanceSearchViewModel.maintenanceType.isEmpty() &&
                        maintenanceSearchViewModel.employeeName.isEmpty()
                    ){
                        MaintenanceTableComponent(
                            navController = navController,
                            ID = ID,
                            log = log,
                            column1Weight = column1Weight,
                            column2Weight = column2Weight,
                            column3Weight = column3Weight,
                            column4Weight = column4Weight
                        )
                    }

                    if(dateMatch){
                        MaintenanceTableComponent(
                            navController = navController,
                            ID = ID,
                            log = log,
                            column1Weight = column1Weight,
                            column2Weight = column2Weight,
                            column3Weight = column3Weight,
                            column4Weight = column4Weight
                        )
                    }

                    if(maintenanceTypeMatch){
                        MaintenanceTableComponent(
                            navController = navController,
                            ID = ID,
                            log = log,
                            column1Weight = column1Weight,
                            column2Weight = column2Weight,
                            column3Weight = column3Weight,
                            column4Weight = column4Weight
                        )
                    }

                    if(employeeNameMatch){
                        MaintenanceTableComponent(
                            navController = navController,
                            ID = ID,
                            log = log,
                            column1Weight = column1Weight,
                            column2Weight = column2Weight,
                            column3Weight = column3Weight,
                            column4Weight = column4Weight
                        )
                    }
                }

                if(dateMatch){
                    if (
                        maintenanceSearchViewModel.assetID.isEmpty() &&
                        maintenanceSearchViewModel.maintenanceType.isEmpty() &&
                        maintenanceSearchViewModel.employeeName.isEmpty()
                    ){
                        MaintenanceTableComponent(
                            navController = navController,
                            ID = ID,
                            log = log,
                            column1Weight = column1Weight,
                            column2Weight = column2Weight,
                            column3Weight = column3Weight,
                            column4Weight = column4Weight
                        )
                    }

                    if(assetIDMatch){
                        MaintenanceTableComponent(
                            navController = navController,
                            ID = ID,
                            log = log,
                            column1Weight = column1Weight,
                            column2Weight = column2Weight,
                            column3Weight = column3Weight,
                            column4Weight = column4Weight
                        )
                    }

                    if(maintenanceTypeMatch){
                        MaintenanceTableComponent(
                            navController = navController,
                            ID = ID,
                            log = log,
                            column1Weight = column1Weight,
                            column2Weight = column2Weight,
                            column3Weight = column3Weight,
                            column4Weight = column4Weight
                        )
                    }

                    if(employeeNameMatch){
                        MaintenanceTableComponent(
                            navController = navController,
                            ID = ID,
                            log = log,
                            column1Weight = column1Weight,
                            column2Weight = column2Weight,
                            column3Weight = column3Weight,
                            column4Weight = column4Weight
                        )
                    }
                }

                if(maintenanceTypeMatch){
                    if (
                        maintenanceSearchViewModel.assetID.isEmpty() &&
                        maintenanceSearchViewModel.date.isEmpty() &&
                        maintenanceSearchViewModel.employeeName.isEmpty()
                    ){
                        MaintenanceTableComponent(
                            navController = navController,
                            ID = ID,
                            log = log,
                            column1Weight = column1Weight,
                            column2Weight = column2Weight,
                            column3Weight = column3Weight,
                            column4Weight = column4Weight
                        )
                    }

                    if(assetIDMatch){
                        MaintenanceTableComponent(
                            navController = navController,
                            ID = ID,
                            log = log,
                            column1Weight = column1Weight,
                            column2Weight = column2Weight,
                            column3Weight = column3Weight,
                            column4Weight = column4Weight
                        )
                    }

                    if(dateMatch){
                        MaintenanceTableComponent(
                            navController = navController,
                            ID = ID,
                            log = log,
                            column1Weight = column1Weight,
                            column2Weight = column2Weight,
                            column3Weight = column3Weight,
                            column4Weight = column4Weight
                        )
                    }

                    if(employeeNameMatch){
                        MaintenanceTableComponent(
                            navController = navController,
                            ID = ID,
                            log = log,
                            column1Weight = column1Weight,
                            column2Weight = column2Weight,
                            column3Weight = column3Weight,
                            column4Weight = column4Weight
                        )
                    }
                }

                if(employeeNameMatch){
                    if (
                        maintenanceSearchViewModel.assetID.isEmpty() &&
                        maintenanceSearchViewModel.date.isEmpty() &&
                        maintenanceSearchViewModel.maintenanceType.isEmpty()
                    ){
                        MaintenanceTableComponent(
                            navController = navController,
                            ID = ID,
                            log = log,
                            column1Weight = column1Weight,
                            column2Weight = column2Weight,
                            column3Weight = column3Weight,
                            column4Weight = column4Weight
                        )
                    }

                    if(assetIDMatch){
                        MaintenanceTableComponent(
                            navController = navController,
                            ID = ID,
                            log = log,
                            column1Weight = column1Weight,
                            column2Weight = column2Weight,
                            column3Weight = column3Weight,
                            column4Weight = column4Weight
                        )
                    }

                    if(dateMatch){
                        MaintenanceTableComponent(
                            navController = navController,
                            ID = ID,
                            log = log,
                            column1Weight = column1Weight,
                            column2Weight = column2Weight,
                            column3Weight = column3Weight,
                            column4Weight = column4Weight
                        )
                    }

                    if(maintenanceTypeMatch){
                        MaintenanceTableComponent(
                            navController = navController,
                            ID = ID,
                            log = log,
                            column1Weight = column1Weight,
                            column2Weight = column2Weight,
                            column3Weight = column3Weight,
                            column4Weight = column4Weight
                        )
                    }
                }

                if(filterFieldsEmpty){
                    MaintenanceTableComponent(
                        navController = navController,
                        ID = ID,
                        log = log,
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