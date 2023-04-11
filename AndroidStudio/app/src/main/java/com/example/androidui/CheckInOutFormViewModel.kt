package com.example.androidui

import android.app.DatePickerDialog
import android.content.ClipDescription
import android.content.Context
import androidx.compose.runtime.currentRecomposeScope
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import java.text.SimpleDateFormat
import java.util.*

class CheckInOutFormViewModel: ViewModel() {
    var ID: String by mutableStateOf("")
    var assetID: String by mutableStateOf("")
    var employeeID: String by mutableStateOf("")
    var employeeName: String by mutableStateOf("")
    var checkOutDate: String by mutableStateOf("")
    var checkInDate: String by mutableStateOf("")
    var currentLocation: String by mutableStateOf("")
    var description: String by mutableStateOf("")

    fun onIDChange(newID: String){
        ID = newID
    }

    fun onAssetIDChange(newAssetID: String){
        assetID = newAssetID
    }

    fun onEmployeeIDChange(newEmployeeID: String){
        employeeID = newEmployeeID
    }

    fun onEmployeeNameChange(newEmployeeName: String){
        employeeName = newEmployeeName
    }

    fun onCurrentLocationChange(newLocation: String){
        currentLocation = newLocation
    }

    fun onDescriptionChange(newDescription: String){
        description = newDescription
    }

    private var dateFormat = "MM-dd-yyyy"

    fun showDatePickerDialog(context: Context) {
        val calendar = getCalendar()
        DatePickerDialog(
            context, { _, year, month, day ->
                checkOutDate = getPickedDateAsString( month, day,year)
            },
            calendar.get(Calendar.YEAR),
            calendar.get(Calendar.MONTH),
            calendar.get(Calendar.DAY_OF_MONTH)
        )
            .show()
    }

    private fun getCalendar(): Calendar {
        return if (checkOutDate.isEmpty())
            Calendar.getInstance()
        else
            getLastPickedDateCalendar()
    }


    private fun getLastPickedDateCalendar(): Calendar {
        val dateFormat = SimpleDateFormat(dateFormat)
        val calendar = Calendar.getInstance()
        calendar.time = dateFormat.parse(checkOutDate)
        return calendar
    }

    private fun getPickedDateAsString( month: Int, day: Int, year: Int): String {
        val calendar = Calendar.getInstance()
        calendar.set(year, month, day)
        val dateFormat = SimpleDateFormat(dateFormat)
        return dateFormat.format(calendar.time)
    }


}