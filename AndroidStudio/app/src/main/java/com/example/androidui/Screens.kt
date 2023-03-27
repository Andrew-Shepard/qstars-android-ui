package com.example.androidui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.compose.ui.window.Popup
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument

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
        topBar = { TopBar(scope = scope, scaffoldState = scaffoldState, navController) },
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
        Spacer(modifier = Modifier.height(8.dp))

        Table(
            "asset",
            "Assets Checked Out",
            "Serial #",
            "Name",
            "Asset Type",
            "Status",
            400,
            200,
            navController)

        Table(
            "flight_log",
            "Flight Logs In Progress",
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

        Table(
            "asset",
            "Assets",
            "Serial #",
            "Name",
            "Asset Type",
            "Status",
            375,
            400,
            navController)

        Table(
            "asset",
            "Recently Viewed",
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
fun FlightLogsScreen(navController: NavController) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    )
    {

        SearchBar()

        Spacer(modifier = Modifier.height(8.dp))

        Table(
            "flight_log",
            "Flight Logs",
            "Mission ID",
            "Pilot ID",
            "Date",
            "Success",
            375,
            400,
            navController)

        Table(
            "flight_log",
            "Recently Viewed",
            "Mission ID",
            "Pilot ID",
            "Date",
            "Success",
            375,
            150,
            navController)

    }
}

@Composable
fun CheckInOutScreen(navController: NavController) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    )
    {

        SearchBar()

        Spacer(modifier = Modifier.height(8.dp))

        Table(
            "check_in_out",
            "Checked In/Checked Out",
            "ID",
            "Last Check In Date",
            "Check Out Date",
            "Success",
            375,
            550,
            navController)

    }
}

@Composable
fun MaintenanceLogScreen(navController: NavController) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    )
    {
        SearchBar()

        Spacer(modifier = Modifier.height(8.dp))

        Table(
            "maintenance_log",
            "Maintenance Logs",
            "M-ID",
            "Asset ID",
            "Date",
            "Type",
            550,
            400,
            navController)
    }

}


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


@Composable
fun DetailsPopUp(popupTitle: String, fieldList: List<String>, navController: NavController, data: String?, listOfAssets: List<List<String>>){


    Popup(
        alignment = Alignment.Center,
    ){

        Box(modifier = Modifier
            .offset(y = 100.dp)
            .padding(20.dp)
            .fillMaxWidth()
            .height(550.dp)
            .background(color = Color.LightGray)
        ) {

            Column{
                Box(modifier = Modifier
                    .fillMaxWidth()
                    .offset(x = 10.dp)){

                    //Close Button
                    Button(
                        onClick = { navController.popBackStack() },
                        modifier = Modifier
                            .align(Alignment.TopEnd)
                            .width(50.dp)
                            .offset(x = -10.dp)
                    ) {
                        Icon(Icons.Filled.Close, "")
                    }

                    //Title
                    Text(
                        text = popupTitle,
                        modifier = Modifier
                            .align(Alignment.TopStart)
                            .offset(y = 10.dp),
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Medium
                    )

                }

                LazyColumn(verticalArrangement = Arrangement.spacedBy(10.dp)){

                    itemsIndexed(fieldList){ i, field ->
                        Row(horizontalArrangement = Arrangement.spacedBy(65.dp)){
                            Box(modifier = Modifier
                                .width(120.dp)
                                .height(40.dp)
                                .offset(x = 10.dp)) {
                                Text(field)
                            }
                            //for all assets
                            for ( asset in listOfAssets){

                                //if asset id == id passed
                                if (asset[0] == data){
                                    Text(asset[i])
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
fun Navigation(navController: NavHostController) {

    NavHost(navController, startDestination = DrawerItems.Home.route)
    {
        composable(DrawerItems.Home.route) {
            HomeScreen(navController)
        }

        composable(DrawerItems.Assets.route) {
            AssetsScreen(navController)
        }

        composable(DrawerItems.FlightLogs.route) {
            FlightLogsScreen(navController)
        }

        composable(DrawerItems.CheckInOut.route) {
            CheckInOutScreen(navController)
        }

        composable(DrawerItems.MaintenanceLogs.route) {
            MaintenanceLogScreen(navController)
        }

        composable(ScreenRoutes.AssetCreation.route){
            AssetCreationScreen(navController)
        }

        composable(ScreenRoutes.FlightLogCreation.route){
            FlightLogCreationScreen(navController)
        }

        composable(ScreenRoutes.CheckInOutCreation.route){
            CheckInCreationScreen(navController)
        }

        composable(ScreenRoutes.MaintenanceLogCreation.route){
            MaintenanceLogCreationScreen(navController)
        }

        composable(
            route = ScreenRoutes.AssetDetails.route + "/{assetID}",
            arguments = listOf(
                navArgument("assetID"){
                    type = NavType.StringType
                }
            )
        ) { lambdaParameter ->
            var AssetInformationRows = listOf(
                "Serial #",
                "Name",
                "Asset Type",
                "Status",
                "Parents",
                "Children",
                "Date Purchased",
                "Total Hours of Usage",
                "Last Maintenance Date",
                "Last Employee Check Out",
                "Current Location",
                "Description"
            )

            val asset1 = listOf("1", "Motor1", "In Maintenance", "Gone", "","","","","","","","")
            val asset2 = listOf("2", "Drone", "Field", "Gone")

            val assets = listOf(asset1, asset2)

            DetailsPopUp(
                "Asset Information",
                AssetInformationRows,
                navController,
                lambdaParameter.arguments?.getString("assetID"),
                assets
            )
        }

        composable(
            route = ScreenRoutes.FlightLogDetails.route + "/{data}",
            arguments = listOf(
                navArgument("data"){
                    type = NavType.StringType
                }
            )
        ){ lambdaParameter ->

            var FlightLogInformationRows = listOf(
                "Mission ID",
                "Pilot ID",
                "Pilot Name",
                "Success",
                "Total Time",
                "Observer ID",
                "Observer Name",
                "Test Mission",
                "Drone Serial #",
                "# of Landings",
                "# of Cycles",
                "Summary"
            )

            /*
            DetailsPopUp(
                "Flight Log Information",
                FlightLogInformationRows,
                navController,
                lambdaParameter.arguments?.getString("data")
            )

             */
        }

        composable(
            route = ScreenRoutes.CheckInOutDetails.route + "/{data}",
            arguments = listOf(
                navArgument("data"){
                    type = NavType.StringType
                }
            )
        ){ lambdaParameter ->

            var CheckInOutInformationRows = listOf(
                "ID",
                "Asset ID",
                "Employee ID",
                "Employee Name",
                "Last Check In Date",
                "Last Check Out Date",
                "Current Location",
                "Description",
            )
            /*
            DetailsPopUp(
                "Check In/Out Information",
                CheckInOutInformationRows,
                navController,
                lambdaParameter.arguments?.getString("data")
            )

             */
        }

        composable(
            route = ScreenRoutes.MaintenanceLogDetails.route + "/{data}",
            arguments = listOf(
                navArgument("data"){
                    type = NavType.StringType
                }
            )
        ){ lambdaParameter ->

            var MaintenanceLogInformationRows = listOf(
                "M-ID",
                "Asset ID",
                "Employee ID",
                "Employee Name",
                "Date",
                "Maintenance Type",
            )

            /*
            DetailsPopUp(
                "Maintenance Log Information",
                MaintenanceLogInformationRows,
                navController,
                lambdaParameter.arguments?.getString("data")
            )

             */
        }

        composable(route = ScreenRoutes.ParentSelection.route){
            ParentSelectionScreen(navController = navController)
        }

    }

}


@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun ParentSelectionScreen(navController: NavController){

    val openDialog = remember{ mutableStateOf(true) }

    Dialog(
        properties = DialogProperties(usePlatformDefaultWidth = false),
        onDismissRequest = { openDialog.value = false}){

        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
        ) {

            Column {

                // Close Button of Asset Creation Screen
                Box(modifier = Modifier.fillMaxWidth()) {
                    Button(onClick = { navController.popBackStack() }, modifier = Modifier
                        .align(Alignment.TopEnd)
                        .padding(10.dp)
                        .width(55.dp)) {
                        Icon(Icons.Filled.Close, "")
                    }
                    Text(
                        "Choose Parents",
                        Modifier
                            .align(Alignment.TopCenter)
                            .offset(y = 20.dp),
                        fontSize = 25.sp,
                        fontWeight = FontWeight.Medium
                    )
                }

                Spacer(modifier = Modifier.height(15.dp))

                SearchBar()

                Spacer(modifier = Modifier.height(8.dp))

                Table(
                    "asset",
                    "Assets",
                    "Serial #",
                    "Name",
                    "Asset Type",
                    "Status",
                    375,
                    500,
                    navController
                )

                Box(modifier = Modifier.fillMaxWidth(),
                    contentAlignment = Alignment.Center){
                    Button(onClick = {}){
                        Text("Add Parent Asset")
                    }
                }
            }

        }
    }
}






