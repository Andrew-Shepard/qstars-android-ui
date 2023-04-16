package com.example.androidui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Filter
import androidx.compose.material.icons.filled.FilterAlt
import androidx.compose.material.icons.outlined.FilterAlt
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun Home(
    navController: NavController,
    assetTableViewModel: AssetTableViewModel,
    flightLogTableViewModel: FlightLogTableViewModel,
    searchViewModel: SearchViewModel
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
                assetTableViewModel = assetTableViewModel,
                searchViewModel = searchViewModel
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
    assetTableViewModel: AssetTableViewModel,
    searchViewModel: SearchViewModel
){

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ){

        Box(
            modifier = Modifier.fillMaxWidth(),
            contentAlignment = Alignment.TopStart
        ){
            Row{
                SearchBar(searchViewModel)
                IconButton(
                    modifier = Modifier.offset(x=20.dp, y = 15.dp),
                    onClick = {
                        navController.navigate("asset-filters")
                    }
                ) {
                    Icon(Icons.Outlined.FilterAlt, "", modifier = Modifier.size(50.dp), tint = Color.DarkGray)
                }
            }
        }

        Spacer(modifier = Modifier.height(8.dp))

        AssetTable(
            width = 375,
            height = 250,
            navController = navController,
            assetTableViewModel = assetTableViewModel,
            searchViewModel = searchViewModel
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

        //SearchBar()

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

        //SearchBar()

        Spacer(modifier = Modifier.height(8.dp))

        CheckInTable(
            width = 375,
            height = 250,
            navController = navController,
            checkInOutTableViewModel = checkInOutTableViewModel)

        //SearchBar()

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

        //SearchBar()

        Spacer(modifier = Modifier.height(8.dp))

        MaintenanceLogTable(
            width = 375,
            height = 550,
            navController = navController,
            maintenanceLogTableViewModel = maintenanceTableViewModel
        )

    }

}