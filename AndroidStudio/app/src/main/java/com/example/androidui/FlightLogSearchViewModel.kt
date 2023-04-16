package com.example.androidui

import android.app.DatePickerDialog
import android.content.Context
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import java.text.SimpleDateFormat
import java.util.*

class FlightLogSearchViewModel: ViewModel() {
    var flightLogID by mutableStateOf("")
    var pilotID by mutableStateOf("")
    var pilotName by mutableStateOf("")
    var droneSerialNum by mutableStateOf("")
    var date by mutableStateOf("")
    var success by mutableStateOf("")

    fun onFlightLogIDChange(newSearch: String){
       flightLogID = newSearch
    }

    fun onPilotIDChange(newSearch: String){
       pilotID = newSearch
    }

    fun onPilotNameChange(newSearch: String){
        pilotName = newSearch
    }

    fun onDroneSerialNumChange(newSearch:String){
        droneSerialNum = newSearch
    }

    fun onSuccessChange(newSearch:String){
        success = newSearch
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