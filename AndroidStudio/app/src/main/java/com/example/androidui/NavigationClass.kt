package com.example.androidui

sealed class ScreenRoutes(var route: String){
    object AssetDetails: ScreenRoutes("asset_details")
    object FlightLogDetails: ScreenRoutes("flight_log_details")
    object CheckInOutDetails: ScreenRoutes("check_in_out_details")
    object MaintenanceLogDetails: ScreenRoutes("maintenance_log_details")

    fun withArgs(vararg args: String): String{
        return buildString {
            append(route)
            args.forEach { arg ->
                append("/$arg")
            }
        }
    }

}