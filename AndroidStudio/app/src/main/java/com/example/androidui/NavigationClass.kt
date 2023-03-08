package com.example.androidui

sealed class ScreenRoutes(var route: String){
    object AssetDetails: ScreenRoutes("asset_details")
    object FlightLogDetails: ScreenRoutes("flight_log_details")
}