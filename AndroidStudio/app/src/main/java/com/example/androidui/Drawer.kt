package com.example.androidui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.ScaffoldState
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

sealed class DrawerItems(var route: String, var title: String)
{
    object Home: DrawerItems("home","Home")
    object Assets: DrawerItems("assets", "Assets")
    object FlightLogs: DrawerItems("flight_logs", "Flight Logs")
    object CheckInOut: DrawerItems("check_in_out", "Check In/Check Out")
    object MaintenenceLogs: DrawerItems("maintenence_log", "Maintenence Logs")
}

@Composable
fun Drawer(scope: CoroutineScope, scaffoldState: ScaffoldState, navController: NavController)
{
    val items = listOf(
        DrawerItems.Home,
        DrawerItems.Assets,
        DrawerItems.FlightLogs,
        DrawerItems.CheckInOut,
        DrawerItems.MaintenenceLogs
    )
    Column(
        modifier = Modifier
            .background(Color.LightGray)
    ){
        //Header for navigation drawer
        Text(
            text="Overhead Intelligence",
            color = Color.Black,
            textAlign = TextAlign.Center,
        )
        Spacer(
            modifier = Modifier
                .height(10.dp)
                .fillMaxWidth()
                .height(5.dp)
        )

        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route

        //goes through each item in the DrawerItems
        items.forEach{ item ->
            DrawerItems(item = item, selected = currentRoute == item.route, onItemClick =
            {
                navController.navigate(item.route)
                {
                    // Pop up to the start destination of the graph to
                    // avoid building up a large stack of destinations
                    // on the back stack as users select items
                    navController.graph.startDestinationRoute?.let{ route ->
                        popUpTo(route) {
                            saveState = true
                        }
                    }
                    // Avoid multiple copies of the same destination when
                    // reselecting the same item
                    launchSingleTop = true
                    // Restore state when reselecting a previously selected item
                    restoreState = true
                }
                //close the drawer
                scope.launch {
                    scaffoldState.drawerState.close()
                }
            }
            )
        }
    }
}

@Composable
fun DrawerItems(item: DrawerItems, selected: Boolean, onItemClick: (DrawerItems) -> Unit)
{
    val background = if (selected) Color.Cyan else Color.Transparent
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = { onItemClick(item) })
            .height(45.dp)
            .background(Color.LightGray)
            .padding(start = 10.dp)
    )
    {
        Text(
            text = item.title,
            fontSize = 18.sp,
            color = Color.Black,
        )
    }

}