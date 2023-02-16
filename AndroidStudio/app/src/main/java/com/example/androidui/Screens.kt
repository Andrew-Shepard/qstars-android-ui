package com.example.androidui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
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
        Table("Assets Checked Out");
        Spacer(modifier = Modifier.height(20.dp))
        Table("Flight Logs in Progress")
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

        Text(
            text = "Assets View",
            color = Color.Black,
            modifier = Modifier.align(Alignment.CenterHorizontally),
            textAlign = TextAlign.Center,
            fontSize = 25.sp
        )
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
    }
}

