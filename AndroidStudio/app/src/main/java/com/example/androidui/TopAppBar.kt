package com.example.androidui

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.SnackbarDefaults.backgroundColor
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.Composable
import androidx.compose.runtime.currentRecomposeScope
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController

@Composable
fun TopBar(scope: CoroutineScope,
           scaffoldState: ScaffoldState,
           navController: NavHostController = rememberNavController()) {

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    TopAppBar(
        title = { Text(text = "Overhead Intelligence", fontSize = 18.sp) },
        navigationIcon =
        {
            IconButton(onClick = { scope.launch { scaffoldState.drawerState.open() } })
            {
                Icon(Icons.Filled.Menu, "")
            }
        },
        actions = {

            if (currentRoute == "assets" ||
                        currentRoute == "flight-logs" ||
                        currentRoute == "check-in-out-logs" ||
                        currentRoute == "maintenance-logs"
                    ) {
                IconButton(
                    onClick = {

                        when (currentRoute) {
                            "assets" -> {
                                navController.navigate("asset-form")
                            }

                            "flight-logs" -> {
                                navController.navigate("flight-logs-form")
                            }

                            "check-in-out-logs" -> {
                                navController.navigate("check-in-out-form")
                            }

                            "maintenance-logs" -> {
                                navController.navigate("maintenance-log-form")
                            }
                        }

                    }
                ) {
                    Icon(
                        Icons.Filled.Add,
                        contentDescription = "Add Icon",
                        modifier = Modifier.size(50.dp)
                    )
                }
            }

            Spacer(Modifier.width(10.dp))

            Image(
                painter = painterResource(id = R.drawable.roger_profilepic),
                contentDescription = null,
                contentScale = ContentScale.Fit,
                modifier = Modifier
                    .fillMaxHeight()
                    .padding(vertical = 10.dp)
                    .clip(CircleShape)
                    .clickable {
                        navController.navigate("profile-screen")
                    }
            )

        },
        backgroundColor = Color.LightGray,
        contentColor = Color.Black,
    )

}
