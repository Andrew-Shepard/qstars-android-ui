package com.example.androidui

import android.app.DatePickerDialog
import android.content.Context
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.width
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModel
import java.text.SimpleDateFormat
import java.util.*


// This file contains the view model for asset search
// Its purpose is to handle the asset search bar data and filter data
class AssetSearchViewModel : ViewModel() {
    var assetIDSearch by mutableStateOf("")
    var assetNameSearch by mutableStateOf("")
    var assetTypeSearch by mutableStateOf("")
    var assetStatusSearch by mutableStateOf("")
    var lastMaintenanceDateSearch by mutableStateOf("")
    var purchaseDateSearch by mutableStateOf("")

    fun onAssetIDChange(newSearch: String) {
        assetIDSearch = newSearch
    }

    fun onNameSearchChange(newSearch: String) {
        assetNameSearch = newSearch
    }

    fun onAssetTypeSearchChange(newSeach: String) {
        assetTypeSearch = newSeach
    }

    fun onAssetStatusSearchChange(newSearch: String) {
        assetStatusSearch = newSearch
    }

    // date format
    private var dateFormat = "MM-dd-yyyy"

    // function for last maintenance date search
    fun showDatePickerDialog(context: Context) {
        val calendar = getCalendar()
        DatePickerDialog(
            context, { _, year, month, day ->
                lastMaintenanceDateSearch = getPickedDateAsString(month, day, year)
            },
            calendar.get(Calendar.YEAR),
            calendar.get(Calendar.MONTH),
            calendar.get(Calendar.DAY_OF_MONTH)
        )
            .show()
    }

    // function for last maintenance date search
    private fun getCalendar(): Calendar {
        return if (lastMaintenanceDateSearch.isEmpty())
            Calendar.getInstance()
        else
            getLastPickedDateCalendar()
    }

    // function for last maintenance date search
    private fun getLastPickedDateCalendar(): Calendar {
        val dateFormat = SimpleDateFormat(dateFormat)
        val calendar = Calendar.getInstance()
        calendar.time = dateFormat.parse(lastMaintenanceDateSearch)
        return calendar
    }

    // function for date of purchase search
    fun showDatePickerDialog1(context: Context) {
        val calendar = getCalendar1()
        DatePickerDialog(
            context, { _, year, month, day ->
                purchaseDateSearch = getPickedDateAsString(month, day, year)
            },
            calendar.get(Calendar.YEAR),
            calendar.get(Calendar.MONTH),
            calendar.get(Calendar.DAY_OF_MONTH)
        )
            .show()
    }

    // function for date of purchase search
    private fun getCalendar1(): Calendar {
        return if (purchaseDateSearch.isEmpty())
            Calendar.getInstance()
        else
            getLastPickedDateCalendar1()
    }

    // function for date of purchase search
    private fun getLastPickedDateCalendar1(): Calendar {
        val dateFormat = SimpleDateFormat(dateFormat)
        val calendar = Calendar.getInstance()
        calendar.time = dateFormat.parse(purchaseDateSearch)
        return calendar
    }

    private fun getPickedDateAsString(month: Int, day: Int, year: Int): String {
        val calendar = Calendar.getInstance()
        calendar.set(year, month, day)
        val dateFormat = SimpleDateFormat(dateFormat)
        return dateFormat.format(calendar.time)
    }

}