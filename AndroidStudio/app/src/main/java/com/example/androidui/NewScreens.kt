package com.example.androidui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun Home(
    navController: NavController,
    assetTableViewModel: AssetTableViewModel,
    flightLogTableViewModel: FlightLogTableViewModel
){
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ){

        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
        ){

            Spacer(modifier = Modifier.height(8.dp))

            AssetTable(
                width = 375,
                height = 225,
                navController = navController,
                assetTableViewModel = assetTableViewModel
            )

            Spacer(modifier = Modifier.height(8.dp))

            FlightLogTable(
                width = 375,
                height = 225,
                navController = navController,
                flightLogTableViewModel = flightLogTableViewModel
            )
        }

    }

}


@Composable
fun Assets(
    navController: NavController,
    assetTableViewModel: AssetTableViewModel
){

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ){

        SearchBar()

        Spacer(modifier = Modifier.height(8.dp))

        AssetTable(
            width = 375,
            height = 250,
            navController = navController,
            assetTableViewModel = assetTableViewModel
        )
    }
}

@Composable
fun FlightLogs(
    navController: NavController,
    flightLogTableViewModel: FlightLogTableViewModel
){
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ){

        SearchBar()

        Spacer(modifier = Modifier.height(8.dp))

        FlightLogTable(
            width = 375,
            height = 550,
            navController = navController,
            flightLogTableViewModel = flightLogTableViewModel
        )

    }
}


@Composable
fun CheckInOut(
    navController: NavController,
    checkInOutTableViewModel: CheckInOutTableViewModel
){
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ){

        SearchBar()

        Spacer(modifier = Modifier.height(8.dp))

        CheckInTable(
            width = 375,
            height = 250,
            navController = navController,
            checkInOutTableViewModel = checkInOutTableViewModel)

        SearchBar()

        Spacer(modifier = Modifier.height(8.dp))

        CheckOutTable(
            width = 375,
            height = 250,
            navController = navController,
            checkInOutTableViewModel = checkInOutTableViewModel)


    }

}


@Composable
fun MaintenanceLog(
    navController: NavController,
    maintenanceTableViewModel: MaintenanceTableViewModel
){
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ){

        SearchBar()

        Spacer(modifier = Modifier.height(8.dp))

        MaintenanceLogTable(
            width = 375,
            height = 550,
            navController = navController,
            maintenanceLogTableViewModel = maintenanceTableViewModel
        )

    }

}