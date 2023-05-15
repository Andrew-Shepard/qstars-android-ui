package com.example.androidui

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.*
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

@Composable
fun navigation(
    navController: NavHostController = rememberNavController(),
    assetTableViewModel: AssetTableViewModel = viewModel(),
    assetFormViewModel: AssetFormViewModel = viewModel(),
    flightLogTableViewModel: FlightLogTableViewModel = viewModel(),
    flightLogFormViewModel: FlightLogFormViewModel = viewModel(),
    checkInOutTableViewModel: CheckInOutTableViewModel = viewModel(),
    checkInOutFormViewModel: CheckInOutFormViewModel = viewModel(),
    maintenanceLogTableViewModel: MaintenanceTableViewModel = viewModel(),
    maintenanceFormViewModel: MaintenanceLogFormViewModel = viewModel(),
    assetSearchViewModel: AssetSearchViewModel = viewModel(),
    flightLogSearchViewModel: FlightLogSearchViewModel = viewModel(),
    checkInSearchViewModel: CheckInSearchViewModel = viewModel(),
    checkOutSearchViewModel: CheckOutSearchViewModel = viewModel(),
    maintenanceSearchViewModel: MaintenanceSearchViewModel = viewModel()
){
    NavHost(
        navController = navController,
        startDestination = "login"){

        composable("login"){
            LoginScreen(navController = navController)
        }


        // home screen
        composable("home"){
            Home(
                navController = navController,
                assetTableViewModel = assetTableViewModel,
                flightLogTableViewModel = flightLogTableViewModel,
                assetSearchViewModel = assetSearchViewModel,
                flightLogSearchViewModel = flightLogSearchViewModel
            )
        }
        
        composable("profile-screen"){
            ProfileScreen(navController = navController)
        }

        //assets screen
        composable("assets"){
            Assets(
                navController = navController,
                assetTableViewModel,
                assetSearchViewModel
            )
        }

        composable("asset-filters"){
            AssetFilterPopup(navController = navController, assetSearchViewModel = assetSearchViewModel)
        }

        //asset form screen
        composable(
            "asset-form"
        ){
            AssetFormScreen(
                navController,
                assetFormViewModel,
                assetTableViewModel
            )
        }

        // parent table for asset form
        composable("parent-table"){
            AddParentScreen(
                navController = navController,
                assetTableViewModel
            )
        }

        composable("child-table"){
            AddChildScreen(navController = navController,
                assetTableViewModel = assetTableViewModel )
        }

        // asset details popup
        composable(
            "details-popup" + "/{assetID}" + "/{parentButton}" + "/{childButton}" + "/{addAssetButton}",
            arguments = listOf(
                navArgument("assetID"){
                    type = NavType.StringType
                },
                navArgument("parentButton"){
                    type = NavType.BoolType
                },
                navArgument("childButton"){
                    type = NavType.BoolType
                },
                navArgument("addAssetButton"){
                    type = NavType.BoolType
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

            AssetDetailsPopUp(
                "Asset Information",
                AssetInformationRows,
                navController,
                lambdaParameter.arguments?.getString("assetID"),
                assetTableViewModel.allAssets,
                assetFormViewModel = assetFormViewModel,
                assetTableViewModel,
                checkInOutFormViewModel,
                maintenanceFormViewModel,
                flightLogFormViewModel
            )
        }

        // flight logs screen
        composable("flight-logs"){
            FlightLogs(
                navController = navController,
                flightLogSearchViewModel = flightLogSearchViewModel,
                flightLogTableViewModel = flightLogTableViewModel
            )
        }

        // flight log filters
        composable("flight-log-filters"){
            FlightLogFilterPopup(navController = navController, flightLogSearchViewModel = flightLogSearchViewModel)
        }

        //  add asset table for flight logs
        composable("add-asset-table-flightlog"){
            AddAssetScreen(
                navController = navController,
                assetTableViewModel,
                assetSearchViewModel
            )
        }


        // flight logs popup
        composable(
            "flight-logs-popup" + "/{flightLogID}",
            arguments = listOf(
                navArgument("flightLogID"){
                    type = NavType.StringType
                })
        ){ lambdaParameter ->

            var flightLogFieldList = listOf(
                "Mission ID",
                "Pilot ID",
                "Pilot Name",
                "Date of Log",
                "Success",
                "Total Time",
                "Observer ID",
                "Test Mission",
                "Drone ID",
                "# of Landings",
                "# of Cycles",
                "Field Notes"
            )


            FlightLogDetailsPopUp(
                popupTitle = "Flight Log Information",
                fieldList = flightLogFieldList,
                navController = navController,
                data = lambdaParameter.arguments?.getString("flightLogID"),
                listOfFlightLogs = flightLogTableViewModel.allFlightLogs
            )
        }

        composable("flight-logs-form"){
            FlightLogFormScreen(
                navController = navController,
                flightLogFormViewModel = flightLogFormViewModel,
                flightLogTableViewModel = flightLogTableViewModel
            )
        }

        composable("check-in-out-logs"){
            CheckInOut(
                navController = navController,
                checkInOutTableViewModel = checkInOutTableViewModel,
                checkInSearchViewModel = checkInSearchViewModel,
                checkOutSearchViewModel = checkOutSearchViewModel
            )
        }


        composable("check-in-filters"){
            CheckInFilterPopup(navController, checkInSearchViewModel)
        }

        composable("check-out-filters"){
            CheckOutFilterPopup(navController = navController, checkOutSearchViewModel = checkOutSearchViewModel)
        }

        composable(
            "checkinout-details-popup" + "/{logID}",
            arguments = listOf(
                navArgument("logID"){
                    type = NavType.StringType
                })
        ){ lambdaParameter ->
            var CheckInOutInformationRows = listOf(
                "ID",
                "Asset ID",
                "Employee ID",
                "Employee Name",
                "Check Out Date",
                "Check In Date",
                "Current Location",
                "Description",
            )

            CheckOutLogDetailsPopUp(
                popupTitle = "Check Out",
                fieldList = CheckInOutInformationRows,
                navController = navController,
                data = lambdaParameter.arguments?.getString("logID"),
                listOfCheckInOut = checkInOutTableViewModel.allCheckInOutLogs
            )
        }

        composable(
            "checkin-details-popup" + "/{logID}",
            arguments = listOf(
                navArgument("logID"){
                    type = NavType.StringType
                })
        ){ lambdaParameter ->
            var CheckInOutInformationRows = listOf(
                "ID",
                "Asset ID",
                "Employee ID",
                "Employee Name",
                "Check Out Date",
                "Check In Date",
                "Current Location",
                "Description",
            )

            CheckInLogDetailsPopUp(
                popupTitle = "Check In Asset",
                fieldList = CheckInOutInformationRows,
                navController = navController,
                data = lambdaParameter.arguments?.getString("logID"),
                listOfCheckInOut = checkInOutTableViewModel.allCheckInOutLogs
            )
        }

        composable("check-in-out-form"){
            CheckInFormScreen(
                navController = navController,
                checkInOutFormViewModel = checkInOutFormViewModel,
                checkInOutTableViewModel = checkInOutTableViewModel
            )
        }

        composable("add-asset-table-checkin"){
            AddAssetScreen(
                navController = navController,
                assetTableViewModel,
                assetSearchViewModel
            )
        }

        composable("add-asset-table-maintenance"){
            AddAssetScreen(
                navController = navController,
                assetTableViewModel,
                assetSearchViewModel
            )
        }

        composable("maintenance-logs"){
            MaintenanceLog(
                navController,
                maintenanceLogTableViewModel,
                maintenanceSearchViewModel
            )
        }

        composable("maintenance-filters"){
            MaintenanceFilterPopup(navController = navController, maintenanceSearchViewModel = maintenanceSearchViewModel)
        }

        composable(
            "maintenance-log-details-popup" + "/{logID}",
            arguments = listOf(
                navArgument("logID"){
                    type = NavType.StringType
                })
        ){ lambdaParameter ->
            var MaintenanceLogInformationRows = listOf(
                "ID",
                "Asset ID",
                "Employee ID",
                "Employee Name",
                "Date of Maintenance",
                "Maintenance Type",
                "Additional Details"
            )

            MaintenanceLogDetailsPopUp(
                popupTitle = "Maintenance Log",
                fieldList = MaintenanceLogInformationRows,
                navController = navController,
                data = lambdaParameter.arguments?.getString("logID"),
                maintenanceLogTableViewModel.allMaintenanceLogs
            )
        }

        composable("maintenance-log-form"){
            MaintenanceLogFormScreen(
                navController,
                maintenanceFormViewModel,
                maintenanceLogTableViewModel
            )
        }

    }
}