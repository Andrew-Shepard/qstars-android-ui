package com.example.androidui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

@Composable
fun MainScreen() {
    // remember if the drawer is closed or not
    val scaffoldState = rememberScaffoldState(rememberDrawerState(DrawerValue.Closed))
    // be able to open/close the drawer
    val scope = rememberCoroutineScope()
    // navigate the views/Screens when drawer item pressed
    val navController = rememberNavController()

    Scaffold(
        scaffoldState = scaffoldState,
        topBar = { TopBar(scope = scope, scaffoldState = scaffoldState) },
        drawerBackgroundColor = Color.LightGray,
        drawerContent = {
            Drawer(
                scope = scope,
                scaffoldState = scaffoldState,
                navController = navController
            )
        },
        backgroundColor = Color.White
    ) { padding ->
        Box(modifier = Modifier.padding(padding)) {
            Navigation(navController = navController)
        }
    }
}


@Composable
fun HomeScreen(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    )
    {
        SearchBar()

        Spacer(modifier = Modifier.height(8.dp))

        Table("Assets Checked Out",
            "Serial #",
            "Name",
            "Asset Type",
            "Status",
            400,
            200,
            navController)

        Table("Flight Logs In Progress",
            "Mission ID",
            "Pilot ID",
            "Date",
            "Success",
            400,
            200,
            navController)

    }
}

@Composable
fun AssetsScreen(navController: NavController) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    )
    {
        SearchBar()

        Spacer(modifier = Modifier.height(8.dp))

        Table("Assets",
            "Serial #",
            "Name",
            "Asset Type",
            "Status",
            375,
            400,
            navController)

        Table("Recently Viewed",
            "Serial #",
            "Name",
            "Asset Type",
            "Status",
            375,
            150,
            navController)

    }
}

@Composable
fun FlightLogsScreen() {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    )
    {

        SearchBar()

        Spacer(modifier = Modifier.height(8.dp))

    }
}

@Composable
fun CheckInOutScreen() {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    )
    {

        SearchBar()

        Spacer(modifier = Modifier.height(8.dp))

    }
}

@Composable
fun MaintenanceLogScreen() {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    )
    {
        SearchBar()

        Spacer(modifier = Modifier.height(8.dp))
    }

}


@Composable
fun AssetCreationScreen() {

    val inputFieldList: List<InputFieldData> = listOf(
        InputFieldData("Asset Serial Number"),
        InputFieldData("Asset Name"),
        InputFieldData("Asset Type", 150, dropDown = true),
        InputFieldData("Status", 150, dropDown = true),
        InputFieldData("+ Parent", button = true),
        InputFieldData("+ Child", button = true),
        InputFieldData("Location"),
        InputFieldData("Purchase Date", 175),
        InputFieldData("Description", height = 80)
    )

    val assetTypeDropDown = listOf(
        "Drone",
        "Motor",
        "Battery",
        "Other"
    )
    val assetStatusDropDown = listOf(
        "Active",
        "In Maintenance",
        "Out of Commission"
    )

    val dropDownLists: List<List<String>> = listOf(assetTypeDropDown, assetStatusDropDown)

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(10.dp)
    ) {

        Column {

            Box(modifier = Modifier.fillMaxWidth()) {
                Button(onClick = {}, modifier = Modifier
                    .align(Alignment.TopEnd)
                    .width(55.dp)) {
                    Icon(Icons.Filled.Close, "")
                }
            }

            Box(modifier = Modifier.fillMaxWidth()) {
                Text(
                    "Create New Asset",
                    Modifier.align(Alignment.TopCenter),
                    fontSize = 25.sp,
                    fontWeight = FontWeight.Medium
                )
            }

            Spacer(modifier = Modifier.height(15.dp))

            AllInputFields(inputFieldList, dropDownLists, "Create Asset")

        }
    }
}


@Composable
fun FlightLogCreationScreen() {

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

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(10.dp)
    ) {

        Column {

            Box(modifier = Modifier.fillMaxWidth()) {
                Button(onClick = {}, modifier = Modifier
                    .align(Alignment.TopEnd)
                    .width(55.dp)) {
                    Icon(Icons.Filled.Close, "")
                }
            }

            Box(modifier = Modifier.fillMaxWidth()) {
                Text(
                    "Create New Flight Log",
                    Modifier.align(Alignment.TopCenter),
                    fontSize = 25.sp,
                    fontWeight = FontWeight.Medium
                )
            }

            Spacer(modifier = Modifier.height(15.dp))


            AllInputFields(inputFieldList, dropDownLists, "Create Flight Log")

        }
    }
}


@Composable
fun CheckInCreationScreen() {
    val inputFieldList: List<InputFieldData> = listOf(
        InputFieldData("Asset Serial Number"),
        InputFieldData("Employee ID"),
        InputFieldData("Check In Date", 175),
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

            Box(modifier = Modifier.fillMaxWidth()) {
                Button(onClick = {}, modifier = Modifier
                    .align(Alignment.TopEnd)
                    .width(55.dp)) {
                    Icon(Icons.Filled.Close, "")
                }
            }

            Box(modifier = Modifier.fillMaxWidth()) {
                Text(
                    "Check In Asset",
                    Modifier.align(Alignment.TopCenter),
                    fontSize = 25.sp,
                    fontWeight = FontWeight.Medium
                )
            }

            Spacer(modifier = Modifier.height(15.dp))

            AllInputFields(inputFieldList, dropDownLists, "Check In")

        }
    }
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

            Box(modifier = Modifier.fillMaxWidth()) {
                Button(onClick = {}, modifier = Modifier
                    .align(Alignment.TopEnd)
                    .width(55.dp)) {
                    Icon(Icons.Filled.Close, "")
                }
            }

            Box(modifier = Modifier.fillMaxWidth()) {
                Text(
                    "Check Out Asset",
                    Modifier.align(Alignment.TopCenter),
                    fontSize = 25.sp,
                    fontWeight = FontWeight.Medium
                )
            }

            Spacer(modifier = Modifier.height(15.dp))

            AllInputFields(inputFieldList, dropDownLists, "Check Out")

        }
    }
}


@Composable
fun MaintenanceLogCreationScreen() {
    val inputFieldList: List<InputFieldData> = listOf(
        InputFieldData("Maintenance ID"),
        InputFieldData("Asset Serial #"),
        InputFieldData("Employee ID"),
        InputFieldData("Employee Name"),
        InputFieldData("Date", width = 80),
        InputFieldData("Maintenance Type", width = 150, dropDown = true),
        InputFieldData("Additional Details", height = 80)
    )

    val maintenanceTypeDropDown = listOf(
        "Motor Replacement",
        "Battery Replacement",
        "Etc"
    )

    val dropDownLists: List<List<String>> = listOf(maintenanceTypeDropDown)


    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(10.dp)
    ) {


        Column {

            Box(modifier = Modifier.fillMaxWidth()) {
                Button(onClick = {}, modifier = Modifier
                    .align(Alignment.TopEnd)
                    .width(55.dp)) {
                    Icon(Icons.Filled.Close, "")
                }
            }

            Box(modifier = Modifier.fillMaxWidth()) {
                Text(
                    "Create New Maintenance Log",
                    Modifier.align(Alignment.TopCenter),
                    fontSize = 25.sp,
                    fontWeight = FontWeight.Medium
                )
            }

            Spacer(modifier = Modifier.height(15.dp))

            AllInputFields(inputFieldList, dropDownLists, "Create Asset")

        }
    }
}


@Composable
fun Navigation(navController: NavHostController) {
    NavHost(navController, startDestination = DrawerItems.Home.route)
    {
        composable(DrawerItems.Home.route)
        {
            HomeScreen(navController)
        }
        composable(DrawerItems.Assets.route)
        {
            AssetsScreen(navController)
        }
        composable(DrawerItems.FlightLogs.route)
        {
            FlightLogsScreen()
        }
        composable(DrawerItems.CheckInOut.route)
        {
            CheckInOutScreen()
        }
        composable(DrawerItems.MaintenenceLogs.route)
        {
            MaintenanceLogScreen()
        }
        composable(NavExp.TableStuff.route){
            TableDetails()
        }

    }
}



