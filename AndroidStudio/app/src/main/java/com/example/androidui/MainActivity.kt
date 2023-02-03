package com.example.androidui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.rememberCoroutineScope
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val scaffoldState = rememberScaffoldState()
            val scope = rememberCoroutineScope()
            Scaffold(
                scaffoldState = scaffoldState,
                topBar = {
                    AppBar(
                        onNavigationIconClick = {
                            scope.launch{
                                scaffoldState.drawerState.open()
                            }
                        }
                    )
                },
                drawerContent = {
                    DrawerHeader()
                    DrawerBody(items = listOf(
                        MenuItem(
                            id = "home",
                            title = "Home",
                            contentDescription = "Go to home screen",
                            icon = Icons.Default.Home
                        ),
                        MenuItem(
                            id = "assets",
                            title = "Assets",
                            contentDescription = "Go to assets screen",
                            icon = Icons.Default.WebAsset
                        ),
                        MenuItem(
                            id = "flight logs",
                            title = "Flight Logs",
                            contentDescription = "Go to flight logs screen",
                            icon = Icons.Default.AirplanemodeActive
                        ),
                        MenuItem(
                            id = "checkin/checkout",
                            title = "Check In/Check Out",
                            contentDescription = "Go to checkin/checkout screen",
                            icon = Icons.Default.SwapHoriz
                        ),
                        MenuItem(
                            id = "maintenance logs",
                            title = "Maintenance Logs",
                            contentDescription = "Go to maintenance log screen",
                            icon = Icons.Default.FactCheck
                        )
                    ),
                        onItemClick = {
                        println("Clicked on ${it.title}")
                    } )
                }
            )
            {

            }
            ProfileView()
        }
    }
}