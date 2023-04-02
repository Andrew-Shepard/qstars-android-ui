package com.example.androidui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.unit.toSize
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import kotlin.math.exp

@Composable
fun Main(){
    var navController = rememberNavController()
    NavHost(navController = navController , startDestination = "form_screen"){

        composable("form_screen"){
            FormScreen(navController)
        }

        composable("other_screen" + "/{data}"){ navBackStack ->
            val data = navBackStack.arguments?.getString("data")
            otherScreen(navController, data)
        }

    }

}

@Composable
fun FormScreen(
    navController: NavController,
    formViewModel: FormViewModel = viewModel()
){

    Column{
        val focusManager = LocalFocusManager.current

        Column{
            AppTextField(
                text = formViewModel.assetID,
                placeholder = "Asset ID",
                onChange = { formViewModel.onAssetIDChange(it) })

            var importantValue = formViewModel.assetID

            Button(onClick = { navController.navigate("other_screen" + "/$importantValue") }) {
                Text("Go")
            }

        }
    }
}

@Composable
fun otherScreen(
    navController: NavController,
    dataPassed: String?
){
    Column{
        Text("You made it!")

        Button(onClick = { navController.popBackStack() }) {
            Text("Go Back")
        }

        Text(dataPassed.toString())
    }
}

