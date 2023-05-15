package com.example.androidui

import android.app.DatePickerDialog
import android.content.Context
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList


// This file contains the form view model for asset
// Its purpose is to handle the asset data
class AssetFormViewModel : ViewModel() {
    var assetID by mutableStateOf("")
    var assetName by mutableStateOf("")
    var assetType by mutableStateOf("")
    var assetStatus by mutableStateOf("")
    var parents: ArrayList<String> = arrayListOf()
    var children: ArrayList<String> = arrayListOf()
    var datePurchased by mutableStateOf("")
    var totalHoursUsed by mutableStateOf("")
    var lastMaintenanceDate by mutableStateOf("")
    var lastCheckOut by mutableStateOf("")
    var currentLocation by mutableStateOf("")
    var description by mutableStateOf("")

    // this function clears the text
    fun clearTextFields() {
        assetID = ""
        assetName = ""
        assetType = ""
        assetStatus = ""
        datePurchased = ""
        currentLocation = ""
        description = ""
        parents = arrayListOf()
        children = arrayListOf()
    }

    // sets assetID to new asset ID
    fun onAssetIDChange(newID: String) {
        assetID = newID
    }


    // sets asset name to new asset name
    fun onAssetNameChange(newName: String) {
        assetName = newName
    }

    // sets asset type to new asset type
    fun onAssetTypeChange(newType: String) {
        assetType = newType
    }

    // sets asset status to new status
    fun onStatusChange(newStatus: String) {
        assetStatus = newStatus
    }

    // adds parent
    fun addParent(newParent: String) {
        parents.add(newParent)
    }

    // sets location to new location
    fun locationChange(newLocation: String) {
        currentLocation = newLocation
    }

    // sets description to new description
    fun onDescriptionChange(newDescription: String) {
        description = newDescription
    }

    // format for date of purchase
    private var dateFormat = "MM-dd-yyyy"

    // function for date of purchase
    fun showDatePickerDialog(context: Context) {
        val calendar = getCalendar()
        DatePickerDialog(
            context, { _, year, month, day ->
                datePurchased = getPickedDateAsString(month, day, year)
            },
            calendar.get(Calendar.YEAR),
            calendar.get(Calendar.MONTH),
            calendar.get(Calendar.DAY_OF_MONTH)
        )
            .show()
    }

    // function for date of purchase
    private fun getCalendar(): Calendar {
        return if (datePurchased.isEmpty())
            Calendar.getInstance()
        else
            getLastPickedDateCalendar()
    }


    // function for date of purchase
    private fun getLastPickedDateCalendar(): Calendar {
        val dateFormat = SimpleDateFormat(dateFormat)
        val calendar = Calendar.getInstance()
        calendar.time = dateFormat.parse(datePurchased)
        return calendar
    }

    // function for date of purchase
    private fun getPickedDateAsString(month: Int, day: Int, year: Int): String {
        val calendar = Calendar.getInstance()
        calendar.set(year, month, day)
        val dateFormat = SimpleDateFormat(dateFormat)
        return dateFormat.format(calendar.time)
    }

}