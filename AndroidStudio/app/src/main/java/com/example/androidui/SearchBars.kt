package com.example.androidui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.width
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

// This file contains the composable functions for the
// asset search bar
// flight log search bar
// check in out screen search bars
// maintenance log search bars


@Composable
fun AssetSearchBar(
    assetSearchViewModel: AssetSearchViewModel
)
{
    Column(Modifier.offset(15.dp,15.dp))
    {
        OutlinedTextField(
            value = assetSearchViewModel.assetIDSearch,
            onValueChange = { assetSearchViewModel.onAssetIDChange(it)},
            modifier = Modifier
                .width(250.dp)
                .height(50.dp),
            placeholder = { Text(text = "Search by Asset ID", style = TextStyle(fontSize = 18.sp, color = Color.LightGray)) }
        )
    }
}


@Composable
fun FlightLogSearchBar(
    flightLogSearchViewModel: FlightLogSearchViewModel
)
{
    Column(Modifier.offset(15.dp,15.dp))
    {
        OutlinedTextField(
            value = flightLogSearchViewModel.flightLogID,
            onValueChange = { flightLogSearchViewModel.onFlightLogIDChange(it)},
            modifier = Modifier
                .width(250.dp)
                .height(50.dp),
            placeholder = { Text(text = "Search by Flight Log ID", style = TextStyle(fontSize = 18.sp, color = Color.LightGray)) }
        )
    }
}


@Composable
fun CheckInSearchBar(
    checkInSearchViewModel: CheckInSearchViewModel
){
    Column(Modifier.offset(15.dp,15.dp))
    {
        OutlinedTextField(
            value = checkInSearchViewModel.checkInOutID,
            onValueChange = { checkInSearchViewModel.onCheckInOutIDChange(it)},
            modifier = Modifier
                .width(250.dp)
                .height(50.dp),
            placeholder = { Text(text = "Search by ID", style = TextStyle(fontSize = 18.sp, color = Color.LightGray)) }
        )
    }
}

@Composable
fun CheckOutSearchBar(
    checkOutSearchViewModel: CheckOutSearchViewModel
){
    Column(Modifier.offset(15.dp,15.dp))
    {
        OutlinedTextField(
            value = checkOutSearchViewModel.checkInOutID,
            onValueChange = { checkOutSearchViewModel.onCheckInOutIDChange(it)},
            modifier = Modifier
                .width(250.dp)
                .height(50.dp),
            placeholder = { Text(text = "Search by ID", style = TextStyle(fontSize = 18.sp, color = Color.LightGray)) }
        )
    }
}


@Composable
fun MaintenanceSearchBar(
    maintenanceSearchViewModel: MaintenanceSearchViewModel
){
    Column(Modifier.offset(15.dp,15.dp))
    {
        OutlinedTextField(
            value = maintenanceSearchViewModel.ID,
            onValueChange = { maintenanceSearchViewModel.onIDChange(it) },
            modifier = Modifier
                .width(250.dp)
                .height(50.dp),
            placeholder = { Text(text = "Search by ID", style = TextStyle(fontSize = 18.sp, color = Color.LightGray)) }
        )
    }
}