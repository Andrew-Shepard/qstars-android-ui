package com.example.androidui

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument

//WHERE I LEFT OFF
// TRYING TO SEE HOW NAVIGATION WORKS
// BETWEEN ASSET FORM -> PARENT SCREEN -> ASSET DETAILS

/*
@Composable
fun mockNavigation(
    navController: NavHostController = rememberNavController(),
    assetTableViewModel: AssetTableViewModel = viewModel(),
    assetFormViewModel: FormViewModel = viewModel()
){

    NavHost(
        navController,
        startDestination = "start"
    ){
        composable("start"){
            testScreen(navController = navController)
        }

        // nvaigate to asset form
        composable(
            "asset-form"
        ){
            AssetFormScreen(
                navController,
                assetFormViewModel,
                assetTableViewModel
            )
        }

        // navigate to parent table screen
        composable("parent-table"){
            AddParentScreen(
                navController = navController,
                assetTableViewModel
            )
        }

        // Navigate to details popup
        composable(
            "details-popup" + "/{assetID}",
            arguments = listOf(
                navArgument("assetID"){
                    type = NavType.StringType
                }
            )
        ){ lambdaParameter ->

            var AssetInformationRows = listOf(
                "Serial #",
                "Name",
                "Asset Type",
                "Status",
                "Parents",
                "Children",
                "Date Purchased",
                "Total Hours of Usage",
                "Last Maintenance Date",
                "Last Employee Check Out",
                "Current Location",
                "Description"
            )

            DetailsPopUp(
                "Asset Information",
                AssetInformationRows,
                navController,
                lambdaParameter.arguments?.getString("assetID"),
                assetTableViewModel.allAssets,
                assetFormViewlModel = assetFormViewModel,
                addParentButton = true
            )

        }



    }

}
*/