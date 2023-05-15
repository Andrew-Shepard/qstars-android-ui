package com.example.androidui

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.navigation.NavController
// this file contains the screen for the filter popups
@Composable
fun AssetFilterPopup(
    navController: NavController,
    assetSearchViewModel: AssetSearchViewModel
){
    Dialog(onDismissRequest = {  }) {
        Box(modifier = Modifier
            .offset(y = 50.dp)
            .padding(20.dp)
            .border(width = 2.dp, color = Color.LightGray, shape = RectangleShape)
            .background(color = Color.White)
        ) {

            val context = LocalContext.current


            Column{
                Box(modifier = Modifier
                    .background(Color.LightGray)
                    .fillMaxWidth()
                    .offset(x = 10.dp)
                ){

                    Button(
                        onClick = {
                            navController.popBackStack()
                        },
                        modifier = Modifier
                            .align(Alignment.TopEnd)
                            .width(50.dp)
                            .offset(x = -15.dp)
                    ) {
                        Icon(Icons.Filled.Close, "")
                    }

                    //Title
                    Text(
                        text = "Apply Filters",
                        modifier = Modifier
                            .align(Alignment.TopStart)
                            .offset(y = 10.dp),
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Medium
                    )
                }
                Spacer(modifier = Modifier.height(10.dp))

                Box(modifier = Modifier.offset(x = 15.dp)){
                    Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {

                        //Asset Name Filter
                        Box {
                            Column {
                                Text(text = "Name")
                                AppTextField(
                                    text = assetSearchViewModel.assetNameSearch,
                                    onChange = { assetSearchViewModel.onNameSearchChange(it) },
                                    placeholder = "Name",
                                    width = 250
                                )
                            }
                        }


                        // Asset Type Filter
                        Box {
                            Column {
                                val assetTypeDropDown = listOf(
                                    "Battery",
                                    "Case",
                                    "Drone",
                                    "Electronics",
                                    "Engine",
                                    "Frame",
                                    "Motor"
                                )
                                Text(text = "Asset Type")
                                AppTextFieldDropDown(
                                    selectedText = assetSearchViewModel.assetTypeSearch,
                                    placeholder = "-Select-",
                                    onSelectedTextChange = {
                                        assetSearchViewModel.onAssetTypeSearchChange(
                                            it
                                        )
                                    },
                                    dropDownItems = assetTypeDropDown,
                                    width = 250
                                )
                            }
                        }


                        // Asset Status Filter
                        Box {
                            Column {
                                val assetStatusDropDown = listOf(
                                    "Active",
                                    "In Maintenance",
                                    "Out of Commission"
                                )
                                Text(text = "Asset Status")
                                AppTextFieldDropDown(
                                    selectedText = assetSearchViewModel.assetStatusSearch,
                                    placeholder = "-Select-",
                                    onSelectedTextChange = {
                                        assetSearchViewModel.onAssetStatusSearchChange(
                                            it
                                        )
                                    },
                                    dropDownItems = assetStatusDropDown,
                                    width = 250
                                )
                            }
                        }

                        //Last Maintenance Date
                        Box {
                            Column {
                                Text(text = "Last Maintenance Date")
                                AppTextField(
                                    modifier = Modifier.clickable {
                                        assetSearchViewModel.showDatePickerDialog(context)
                                    },
                                    text = assetSearchViewModel.lastMaintenanceDateSearch,
                                    placeholder = "MM-DD-YYYY",
                                    onChange = {
                                        assetSearchViewModel.lastMaintenanceDateSearch = it
                                    },
                                    isEnabled = false,
                                    width = 250                                )
                            }

                        }


                        //Purchase Date
                        Box {
                            Column {
                                Text(text = "Purchase Date")
                                AppTextField(
                                    modifier = Modifier.clickable {
                                        assetSearchViewModel.showDatePickerDialog1(context)
                                    },
                                    text = assetSearchViewModel.purchaseDateSearch,
                                    placeholder = "MM-DD-YYYY",
                                    onChange = {
                                        assetSearchViewModel.purchaseDateSearch = it
                                    },
                                    isEnabled = false,
                                    width = 250
                                )
                            }
                        }
                    }
                }
                //Clear FIlter and Apply Filter Buttons
                Box(modifier = Modifier
                    .offset(x = 15.dp)
                    .padding(vertical = 15.dp)){
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ){
                        Button(onClick = {
                            assetSearchViewModel.assetNameSearch = ""
                            assetSearchViewModel.assetTypeSearch = ""
                            assetSearchViewModel.assetStatusSearch = ""
                            assetSearchViewModel.lastMaintenanceDateSearch = ""
                            assetSearchViewModel.purchaseDateSearch = ""
                        }) {
                            Text("Clear Filters")
                        }

                        Button(onClick = { navController.popBackStack() }) {
                            Text("Apply Filters")
                        }

                    }
                }

            }
        }
    }
}


@Composable
fun FlightLogFilterPopup(
    navController: NavController,
    flightLogSearchViewModel: FlightLogSearchViewModel
){
    Dialog(onDismissRequest = {  }) {
        Box(modifier = Modifier
            .offset(y = 50.dp)
            .padding(20.dp)
            .border(width = 2.dp, color = Color.LightGray, shape = RectangleShape)
            .background(color = Color.White)
        ) {

            val context = LocalContext.current

            Column{
                Box(modifier = Modifier
                    .background(Color.LightGray)
                    .fillMaxWidth()
                    .offset(x = 10.dp)
                ){

                    Button(
                        onClick = {
                            navController.popBackStack()
                        },
                        modifier = Modifier
                            .align(Alignment.TopEnd)
                            .width(50.dp)
                            .offset(x = -15.dp)
                    ) {
                        Icon(Icons.Filled.Close, "")
                    }

                    //Title
                    Text(
                        text = "Apply Filters",
                        modifier = Modifier
                            .align(Alignment.TopStart)
                            .offset(y = 10.dp),
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Medium
                    )
                }
                Spacer(modifier = Modifier.height(10.dp))

                Box(modifier = Modifier.offset(x = 15.dp)){
                    Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {

                        //Pilot ID Filter
                        Box {
                            Column {
                                Text(text = "Pilot ID")
                                AppTextField(
                                    text = flightLogSearchViewModel.pilotID,
                                    onChange = { flightLogSearchViewModel.onPilotIDChange(it) },
                                    placeholder = "Pilot ID",
                                    width = 250
                                )
                            }
                        }


                        // Pilot Name Filter
                        Box {
                            Column {
                                Text(text = "Pilot Name")
                                AppTextField(
                                    text = flightLogSearchViewModel.pilotName,
                                    onChange = { flightLogSearchViewModel.onPilotNameChange(it) },
                                    placeholder = "Pilot Name",
                                    width = 250
                                )
                            }
                        }


                        // Drone Serial #
                        Box {
                            Column {

                                Text(text = "Drone Serial #")
                                AppTextField(
                                    text = flightLogSearchViewModel.droneSerialNum,
                                    onChange = { flightLogSearchViewModel.onDroneSerialNumChange(it) },
                                    placeholder = "Drone Serial #",
                                    width = 250
                                )
                            }
                        }

                        //Date
                        Box {
                            Column {
                                Text(text = "Date")
                                AppTextField(
                                    modifier = Modifier.clickable {
                                        flightLogSearchViewModel.showDatePickerDialog(context)
                                    },
                                    text = flightLogSearchViewModel.date,
                                    onChange = { flightLogSearchViewModel.date = it },
                                    placeholder = "MM-DD-YYYY",
                                    width = 250,
                                    isEnabled = false
                                )
                            }
                        }

                        // Success
                        Box {
                            val flightTestMissionDropDown = listOf(
                                "Yes",
                                "No"
                            )

                            Column {
                                Text(text = "Mission Success")
                                AppTextFieldDropDown(
                                    selectedText = flightLogSearchViewModel.success,
                                    placeholder = "-Select-",
                                    onSelectedTextChange = {
                                        flightLogSearchViewModel.onSuccessChange(it)
                                    },
                                    dropDownItems = flightTestMissionDropDown,
                                    width = 250
                                )
                            }
                        }

                        }
                    }
                //Clear FIlter and Apply Filter Buttons
                Box(modifier = Modifier
                    .offset(x = 15.dp)
                    .padding(vertical = 15.dp)){
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ){
                        Button(onClick = {
                            flightLogSearchViewModel.pilotID = ""
                            flightLogSearchViewModel.pilotName = ""
                            flightLogSearchViewModel.droneSerialNum = ""
                            flightLogSearchViewModel.date = ""
                            flightLogSearchViewModel.success = ""
                        }) {
                            Text("Clear Filters")
                        }

                        Button(onClick = { navController.popBackStack() }) {
                            Text("Apply Filters")
                        }

                    }
                }

            }
        }
    }
}

@Composable
fun CheckInFilterPopup(
    navController: NavController,
    checkInSearchViewModel: CheckInSearchViewModel
){
    Dialog(onDismissRequest = {  }) {
        Box(
            modifier = Modifier
                .offset(y = 50.dp)
                .padding(20.dp)
                .border(width = 2.dp, color = Color.LightGray, shape = RectangleShape)
                .background(color = Color.White)
        ) {

            val context = LocalContext.current

            Column {
                Box(
                    modifier = Modifier
                        .background(Color.LightGray)
                        .fillMaxWidth()
                        .offset(x = 10.dp)
                ) {

                    Button(
                        onClick = {
                            navController.popBackStack()
                        },
                        modifier = Modifier
                            .align(Alignment.TopEnd)
                            .width(50.dp)
                            .offset(x = -15.dp)
                    ) {
                        Icon(Icons.Filled.Close, "")
                    }

                    //Title
                    Text(
                        text = "Apply Filters",
                        modifier = Modifier
                            .align(Alignment.TopStart)
                            .offset(y = 10.dp),
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Medium
                    )
                }
                Spacer(modifier = Modifier.height(10.dp))

                Box(modifier = Modifier.offset(x = 15.dp)) {
                    Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {

                        //Check Out Date Filter
                        Box {
                            Column {
                                Text(text = "Check Out Date")
                                AppTextField(
                                    modifier = Modifier.clickable {
                                        checkInSearchViewModel.showDatePickerDialog(context)
                                    },
                                    text = checkInSearchViewModel.checkOutDate,
                                    onChange = { checkInSearchViewModel.checkOutDate = it },
                                    placeholder = "MM-DD-YYYY",
                                    width = 250,
                                    isEnabled = false
                                )
                            }
                        }

                        //Asset ID Filter
                        Box {
                            Column {
                                Text(text = "Asset ID")
                                AppTextField(
                                    text = checkInSearchViewModel.assetSerialNum,
                                    onChange = { checkInSearchViewModel.onAssetSerialNumChange(it) },
                                    placeholder = "Asset ID",
                                    width = 250
                                )
                            }
                        }

                        //Employee Name Filter
                        Box {
                            Column {
                                Text(text = "Employee Name")
                                AppTextField(
                                    text = checkInSearchViewModel.employeeName,
                                    onChange = { checkInSearchViewModel.onEmployeeNameChange(it) },
                                    placeholder = "Employee Name",
                                    width = 250
                                )
                            }
                        }
                        //Clear Filter and Apply Filter Buttons
                        Box(modifier = Modifier
                            //.offset(x = 10.dp)
                            .padding(vertical = 15.dp)){
                            Row(
                                horizontalArrangement = Arrangement.spacedBy(8.dp)
                            ){
                                Button(onClick = {
                                    checkInSearchViewModel.checkOutDate = ""
                                    checkInSearchViewModel.assetSerialNum = ""
                                    checkInSearchViewModel.employeeName = ""
                                }) {
                                    Text("Clear Filters")
                                }

                                Button(onClick = { navController.popBackStack() }) {
                                    Text("Apply Filters")
                                }

                            }
                        }
                    }
                }
            }
        }
    }
}


@Composable
fun CheckOutFilterPopup(
    navController: NavController,
    checkOutSearchViewModel: CheckOutSearchViewModel
){
    Dialog(onDismissRequest = {  }) {
        Box(
            modifier = Modifier
                .offset(y = 50.dp)
                .padding(20.dp)
                .border(width = 2.dp, color = Color.LightGray, shape = RectangleShape)
                .background(color = Color.White)
        ) {

            val context = LocalContext.current

            Column {
                Box(
                    modifier = Modifier
                        .background(Color.LightGray)
                        .fillMaxWidth()
                        .offset(x = 10.dp)
                ) {

                    Button(
                        onClick = {
                            navController.popBackStack()
                        },
                        modifier = Modifier
                            .align(Alignment.TopEnd)
                            .width(50.dp)
                            .offset(x = -15.dp)
                    ) {
                        Icon(Icons.Filled.Close, "")
                    }

                    //Title
                    Text(
                        text = "Apply Filters",
                        modifier = Modifier
                            .align(Alignment.TopStart)
                            .offset(y = 10.dp),
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Medium
                    )
                }
                Spacer(modifier = Modifier.height(10.dp))

                Box(modifier = Modifier.offset(x = 15.dp)) {
                    Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {

                        //Check Out Date Filter
                        Box {
                            Column {
                                Text(text = "Check Out Date")
                                AppTextField(
                                    modifier = Modifier.clickable {
                                        checkOutSearchViewModel.showDatePickerDialog1(context)
                                    },
                                    text = checkOutSearchViewModel.checkOutDate,
                                    onChange = { checkOutSearchViewModel.checkOutDate = it },
                                    placeholder = "MM-DD-YYYY",
                                    width = 250,
                                    isEnabled = false
                                )
                            }
                        }

                        //Check In Date  Filter
                        Box {
                            Column {
                                Text(text = "Check In Date")
                                AppTextField(
                                    modifier = Modifier.clickable {
                                        checkOutSearchViewModel.showDatePickerDialog(context)
                                    },
                                    text = checkOutSearchViewModel.checkInDate,
                                    onChange = { checkOutSearchViewModel.checkInDate = it },
                                    placeholder = "MM-DD-YYYY",
                                    width = 250,
                                    isEnabled = false
                                )
                            }
                        }

                        //Asset ID Filter
                        Box {
                            Column {
                                Text(text = "Asset ID")
                                AppTextField(
                                    text = checkOutSearchViewModel.assetSerialNum,
                                    onChange = { checkOutSearchViewModel.onAssetSerialNumChange(it) },
                                    placeholder = "Asset ID",
                                    width = 250
                                )
                            }
                        }

                        //Employee Name Filter
                        Box {
                            Column {
                                Text(text = "Employee Name")
                                AppTextField(
                                    text = checkOutSearchViewModel.employeeName,
                                    onChange = { checkOutSearchViewModel.onEmployeeNameChange(it) },
                                    placeholder = "Employee Name",
                                    width = 250
                                )
                            }
                        }
                        //Clear Filter and Apply Filter Buttons
                        Box(modifier = Modifier
                            //.offset(x = 10.dp)
                            .padding(vertical = 15.dp)){
                            Row(
                                horizontalArrangement = Arrangement.spacedBy(8.dp)
                            ){
                                Button(onClick = {
                                    checkOutSearchViewModel.checkOutDate = ""
                                    checkOutSearchViewModel.checkInDate = ""
                                    checkOutSearchViewModel.assetSerialNum = ""
                                    checkOutSearchViewModel.employeeName = ""
                                }) {
                                    Text("Clear Filters")
                                }

                                Button(onClick = { navController.popBackStack() }) {
                                    Text("Apply Filters")
                                }

                            }
                        }
                    }
                }
            }
        }
    }
}


@Composable
fun MaintenanceFilterPopup(
    navController: NavController,
    maintenanceSearchViewModel: MaintenanceSearchViewModel
){
    Dialog(onDismissRequest = {  }) {
        Box(
            modifier = Modifier
                .offset(y = 50.dp)
                .padding(20.dp)
                .border(width = 2.dp, color = Color.LightGray, shape = RectangleShape)
                .background(color = Color.White)
        ) {
            val context = LocalContext.current


            Column {
                Box(
                    modifier = Modifier
                        .background(Color.LightGray)
                        .fillMaxWidth()
                        .offset(x = 10.dp)
                ) {

                    // Close Filter Popup Button
                    Button(
                        onClick = {
                            navController.popBackStack()
                        },
                        modifier = Modifier
                            .align(Alignment.TopEnd)
                            .width(50.dp)
                            .offset(x = -15.dp)
                    ) {
                        Icon(Icons.Filled.Close, "")
                    }

                    //Title
                    Text(
                        text = "Apply Filters",
                        modifier = Modifier
                            .align(Alignment.TopStart)
                            .offset(y = 10.dp),
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Medium
                    )
                }

                Spacer(modifier = Modifier.height(10.dp))

                Box(modifier = Modifier.offset(x = 15.dp)) {
                    Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {

                        //Asset ID Filter
                        Box {
                            Column {
                                Text(text = "Asset ID")
                                AppTextField(
                                    text = maintenanceSearchViewModel.assetID,
                                    onChange = {  maintenanceSearchViewModel.onAssetIDChange(it) },
                                    placeholder = "Asset ID",
                                    width = 250
                                )
                            }
                        }

                        // Date Filter
                        Box {
                            Column {
                                Text(text = "Date")
                                AppTextField(
                                    modifier = Modifier.clickable {
                                        maintenanceSearchViewModel.showDatePickerDialog(context)
                                    },
                                    text = maintenanceSearchViewModel.date,
                                    placeholder = "MM-DD-YYYY",
                                    onChange = {
                                        maintenanceSearchViewModel.date = it
                                    },
                                    isEnabled = false,
                                    width = 250
                                )
                            }
                        }

                        // Maintenance Type Filter
                        Box {
                            Column {
                                val maintenanceTypeDropDown = listOf(
                                    "Motor Replacement",
                                    "Battery Replacement",
                                    "Etc"
                                )
                                Text(text = "Maintenance Type")
                                AppTextFieldDropDown(
                                    selectedText = maintenanceSearchViewModel.maintenanceType,
                                    placeholder = "-Select-",
                                    onSelectedTextChange = {
                                        maintenanceSearchViewModel.onMaintenanceTypeChange(it)
                                    },
                                    dropDownItems = maintenanceTypeDropDown,
                                    width = 250
                                )
                            }
                        }

                        //Employee Name Filter
                        Box{
                            Column {
                                Text(text = "Employee Name")
                                AppTextField(
                                    text = maintenanceSearchViewModel.employeeName,
                                    onChange = {  maintenanceSearchViewModel.onEmployeeNameChange(it) },
                                    placeholder = "Name",
                                    width = 250
                                )
                            }
                        }

                        //Clear Filter and Apply Filter Buttons
                        Box(modifier = Modifier
                            .padding(vertical = 15.dp)){
                            Row(
                                horizontalArrangement = Arrangement.spacedBy(8.dp)
                            ){
                                Button(onClick = {
                                    maintenanceSearchViewModel.assetID = ""
                                    maintenanceSearchViewModel.date = ""
                                    maintenanceSearchViewModel.maintenanceType = ""
                                    maintenanceSearchViewModel.employeeName = ""
                                }) {
                                    Text("Clear Filters")
                                }

                                Button(onClick = { navController.popBackStack() }) {
                                    Text("Apply Filters")
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}