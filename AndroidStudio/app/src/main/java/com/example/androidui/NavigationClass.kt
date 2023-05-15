package com.example.androidui

// screen routes used for some of the navigation
sealed class ScreenRoutes(var route: String){
    object AssetDetails: ScreenRoutes("asset_details")
    object FlightLogDetails: ScreenRoutes("flight_log_details")
    object CheckInOutDetails: ScreenRoutes("check_in_out_details")
    object MaintenanceLogDetails: ScreenRoutes("maintenance_log_details")

    object ParentSelection: ScreenRoutes("parent")

    fun withArgs(vararg args: String): String{
        return buildString {
            append(route)
            args.forEach { arg ->
                append("/$arg")
            }
        }
    }

}