package com.example.androidui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
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



@Composable
fun HomeScreen(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    )
    {
        val mockData1 = listOf("123", "Motor5", "Motor", "Checked Out", "", "", "", "", "", "", "", "", "","", "","", "", "", "")
        val mockData2 = listOf("345", "Drone1", "Drone", "Checked In", "", "", "", "", "", "", "", "", "","", "","", "", "", "")


        val allData = listOf(mockData1, mockData2)


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
            navController,
        allData)

        Table(
            "flight_log",
            "Flight Logs In Progress",
            "Mission ID",
            "Pilot ID",
            "Date",
            "Success",
            400,
            200,
            navController,
        allData)

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
        val mockData1 = listOf("123", "Motor5", "Motor", "Checked Out", "", "", "", "", "", "", "", "", "","", "","", "", "", "")
        val mockData2 = listOf("345", "Drone1", "Drone", "Checked In", "", "", "", "", "", "", "", "", "","", "","", "", "", "")

        val allData = listOf(mockData1, mockData2)

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
            navController,
        allData)

        Table(
            "asset",
            "Recently Viewed",
            "Serial #",
            "Name",
            "Asset Type",
            "Status",
            375,
            150,
            navController,
        allData)

    }
}

@Composable
fun FlightLogsScreen(navController: NavController) {
    val mockData1 = listOf("123", "Motor5", "Motor", "Checked Out", "", "", "", "", "", "", "", "", "","", "","", "", "", "")
    val mockData2 = listOf("345", "Drone1", "Drone", "Checked In", "", "", "", "", "", "", "", "", "","", "","", "", "", "")

    val allData = listOf(mockData1, mockData2)

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
            navController,
        allData)

        Table(
            "flight_log",
            "Recently Viewed",
            "Mission ID",
            "Pilot ID",
            "Date",
            "Success",
            375,
            150,
            navController,
        allData)

    }
}

@Composable
fun CheckInOutScreen(navController: NavController) {
    val mockData1 = listOf("123", "Motor5", "Motor", "Checked Out", "", "", "", "", "", "", "", "", "","", "","", "", "", "")
    val mockData2 = listOf("345", "Drone1", "Drone", "Checked In", "", "", "", "", "", "", "", "", "","", "","", "", "", "")

    val allData = listOf(mockData1, mockData2)

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
            navController, allData)

    }
}

@Composable
fun MaintenanceLogScreen(navController: NavController) {

    val mockData1 = listOf("123", "Motor5", "Motor", "Checked Out", "", "", "", "", "", "", "", "", "","", "","", "", "", "")
    val mockData2 = listOf("345", "Drone1", "Drone", "Checked In", "", "", "", "", "", "", "", "", "","", "","", "", "", "")

    val allData = listOf(mockData1, mockData2)

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
            navController, allData)
    }

}


@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun ParentSelectionScreen(navController: NavController){

    val openDialog = remember{ mutableStateOf(true) }

    val mockData1 = listOf("123", "Motor5", "Motor", "Checked Out", "", "", "", "", "", "", "", "", "","", "","", "", "", "")
    val mockData2 = listOf("345", "Drone1", "Drone", "Checked In", "", "", "", "", "", "", "", "", "","", "","", "", "", "")

    val allData = listOf(mockData1, mockData2)

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
                    navController, allData
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






