package com.example.androidui

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument

@Composable
fun Navigation(navController: NavHostController) {

    NavHost(navController, startDestination = DrawerItems.Home.route)
    {
        composable(DrawerItems.Home.route){
            HomeScreen(navController)
        }

        composable(DrawerItems.Assets.route) {
            AssetsScreen(navController)
        }

        composable(DrawerItems.FlightLogs.route) {
            FlightLogsScreen(navController)
        }

        composable(DrawerItems.CheckInOut.route) {
            CheckInOutScreen(navController)
        }

        composable(DrawerItems.MaintenanceLogs.route) {
            MaintenanceLogScreen(navController)
        }

        composable(ScreenRoutes.AssetCreation.route){
            AssetCreationScreen(navController)
        }

        composable(ScreenRoutes.FlightLogCreation.route){
            FlightLogCreationScreen(navController)
        }

        composable(ScreenRoutes.CheckInOutCreation.route){
            CheckInCreationScreen(navController)
        }

        composable(ScreenRoutes.MaintenanceLogCreation.route){
            MaintenanceLogCreationScreen(navController)
        }

        composable(
            route = ScreenRoutes.AssetDetails.route + "/{assetID}",
            arguments = listOf(
                navArgument("assetID"){
                    type = NavType.StringType
                }
            )
        ) { lambdaParameter ->
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

            val mockData1 = listOf("123", "Motor5", "Motor", "Checked Out", "", "", "", "", "", "", "", "", "","", "","", "", "", "")
            val mockData2 = listOf("345", "Drone1", "Drone", "Checked In", "", "", "", "", "", "", "", "", "","", "","", "", "", "")

            val allData = listOf(mockData1, mockData2)

            DetailsPopUp(
                "Asset Information",
                AssetInformationRows,
                navController,
                lambdaParameter.arguments?.getString("assetID"),
                allData
            )
        }

        composable(
            route = ScreenRoutes.FlightLogDetails.route + "/{data}",
            arguments = listOf(
                navArgument("data"){
                    type = NavType.StringType
                }
            )
        ){ lambdaParameter ->

            var FlightLogInformationRows = listOf(
                "Mission ID",
                "Pilot ID",
                "Pilot Name",
                "Success",
                "Total Time",
                "Observer ID",
                "Observer Name",
                "Test Mission",
                "Drone Serial #",
                "# of Landings",
                "# of Cycles",
                "Summary"
            )

            /*
            DetailsPopUp(
                "Flight Log Information",
                FlightLogInformationRows,
                navController,
                lambdaParameter.arguments?.getString("data")
            )

             */
        }

        composable(
            route = ScreenRoutes.CheckInOutDetails.route + "/{data}",
            arguments = listOf(
                navArgument("data"){
                    type = NavType.StringType
                }
            )
        ){ lambdaParameter ->

            var CheckInOutInformationRows = listOf(
                "ID",
                "Asset ID",
                "Employee ID",
                "Employee Name",
                "Last Check In Date",
                "Last Check Out Date",
                "Current Location",
                "Description",
            )
            /*
            DetailsPopUp(
                "Check In/Out Information",
                CheckInOutInformationRows,
                navController,
                lambdaParameter.arguments?.getString("data")
            )

             */
        }

        composable(
            route = ScreenRoutes.MaintenanceLogDetails.route + "/{data}",
            arguments = listOf(
                navArgument("data"){
                    type = NavType.StringType
                }
            )
        ){ lambdaParameter ->

            var MaintenanceLogInformationRows = listOf(
                "M-ID",
                "Asset ID",
                "Employee ID",
                "Employee Name",
                "Date",
                "Maintenance Type",
            )

            /*
            DetailsPopUp(
                "Maintenance Log Information",
                MaintenanceLogInformationRows,
                navController,
                lambdaParameter.arguments?.getString("data")
            )

             */
        }

        composable(route = ScreenRoutes.ParentSelection.route){
            ParentSelectionScreen(navController = navController)
        }

    }

}