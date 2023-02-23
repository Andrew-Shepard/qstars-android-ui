package com.example.androidui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.Navigation

@Composable
fun MainScreen()
{
    //remember if the drawer is closed or not
    val scaffoldState = rememberScaffoldState(rememberDrawerState(DrawerValue.Closed))
    //be able to open/close the drawer
    val scope = rememberCoroutineScope()
    //navigate the views/Screens when drawer item pressed
    val navController = rememberNavController()

    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {TopBar(scope = scope, scaffoldState = scaffoldState)},
        drawerBackgroundColor = Color.LightGray,
        drawerContent= { Drawer(scope = scope, scaffoldState = scaffoldState, navController = navController)},
        backgroundColor = Color.White
    ){ padding ->
        Box(modifier = Modifier.padding(padding)){
            Navigation(navController = navController)
        }
    }
}

@Composable
fun HomeScreen()
{
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    )
    {
        SearchBar()
        Spacer(modifier = Modifier.height(8.dp))
        Table("Assets Checked Out", "Serial #", "Name", "Asset Type", "Status", 400, 200)
        //Spacer(modifier = Modifier.height(10.dp))
        Table("Flight Logs In Progress", "Mission ID", "Pilot ID", "Date", "Success", 400, 200)
    }
}

@Composable
fun AssetsScreen(){

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    )
    {
        SearchBar()
        Spacer(modifier = Modifier.height(8.dp))
        Table("Assets", "Serial #", "Name", "Asset Type", "Status", 375, 400)
        Table("Recently Viewed", "Serial #", "Name", "Asset Type", "Status", 375, 150)

    }
}

@Composable
fun FlightLogsScreen(){

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
fun CheckInOutScreen(){

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
fun MaintenenceLogScreen(){

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
fun Navigation(navController: NavHostController)
{
    NavHost(navController, startDestination = DrawerItems.Home.route)
    {
        composable(DrawerItems.Home.route)
        {
            HomeScreen()
        }
        composable(DrawerItems.Assets.route)
        {
            AssetsScreen()
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
            MaintenenceLogScreen()
        }
    }
}

