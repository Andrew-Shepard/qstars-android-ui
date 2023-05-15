package com.example.androidui

import android.app.DatePickerDialog
import android.content.Context
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import java.text.SimpleDateFormat
import java.util.*
// this file contains the view model that stores and handles all the data of the maintenance log search bar and filters
class MaintenanceSearchViewModel: ViewModel() {
    var ID by mutableStateOf("")
    var assetID by mutableStateOf("")
    var maintenanceType by mutableStateOf("")
    var date by mutableStateOf("")
    var employeeName by mutableStateOf("")

    fun onIDChange(newSearch: String){
        ID = newSearch
    }

    fun onAssetIDChange(newSearch: String){
        assetID = newSearch
    }

    fun onMaintenanceTypeChange(newSearch: String){
        maintenanceType = newSearch
    }

    fun onEmployeeNameChange(newSearch: String){
        employeeName = newSearch
    }

    private var dateFormat = "MM-dd-yyyy"

    fun showDatePickerDialog(context: Context) {
        val calendar = getCalendar()
        DatePickerDialog(
            context, { _, year, month, day ->
                date = getPickedDateAsString( month, day,year)
            },
            calendar.get(Calendar.YEAR),
            calendar.get(Calendar.MONTH),
            calendar.get(Calendar.DAY_OF_MONTH)
        )
            .show()
    }

    private fun getCalendar(): Calendar {
        return if (date.isEmpty())
            Calendar.getInstance()
        else
            getLastPickedDateCalendar()
    }


    private fun getLastPickedDateCalendar(): Calendar {
        val dateFormat = SimpleDateFormat(dateFormat)
        val calendar = Calendar.getInstance()
        calendar.time = dateFormat.parse(date)
        return calendar
    }

    private fun getPickedDateAsString( month: Int, day: Int, year: Int): String {
        val calendar = Calendar.getInstance()
        calendar.set(year, month, day)
        val dateFormat = SimpleDateFormat(dateFormat)
        return dateFormat.format(calendar.time)
    }


}