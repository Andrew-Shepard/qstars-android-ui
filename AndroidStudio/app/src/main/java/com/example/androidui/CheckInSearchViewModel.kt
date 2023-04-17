package com.example.androidui

import android.app.DatePickerDialog
import android.content.Context
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import java.text.SimpleDateFormat
import java.util.*

class CheckInSearchViewModel: ViewModel() {
    var checkInOutID by mutableStateOf("")
    var checkOutDate by mutableStateOf("")
    var assetSerialNum by mutableStateOf("")
    var employeeName by mutableStateOf("")

    fun onCheckInOutIDChange(newSearch: String){
        checkInOutID = newSearch
    }

    fun onAssetSerialNumChange(newSearch: String){
        assetSerialNum = newSearch
    }

    fun onEmployeeNameChange(newSearch: String){
        employeeName = newSearch
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

class CheckOutSearchViewModel: ViewModel() {
    var checkInOutID by mutableStateOf("")
    var checkInDate by mutableStateOf("")
    var checkOutDate by mutableStateOf("")
    var assetSerialNum by mutableStateOf("")
    var employeeName by mutableStateOf("")

    fun onCheckInOutIDChange(newSearch: String){
        checkInOutID = newSearch
    }

    fun onAssetSerialNumChange(newSearch: String){
        assetSerialNum = newSearch
    }

    fun onEmployeeNameChange(newSearch: String){
        employeeName = newSearch
    }

    private var dateFormat = "MM-dd-yyyy"

    fun showDatePickerDialog(context: Context) {
        val calendar = getCalendar()
        DatePickerDialog(
            context, { _, year, month, day ->
                checkInDate = getPickedDateAsString( month, day,year)
            },
            calendar.get(Calendar.YEAR),
            calendar.get(Calendar.MONTH),
            calendar.get(Calendar.DAY_OF_MONTH)
        )
            .show()
    }

    private fun getCalendar(): Calendar {
        return if (checkInDate.isEmpty())
            Calendar.getInstance()
        else
            getLastPickedDateCalendar()
    }


    private fun getLastPickedDateCalendar(): Calendar {
        val dateFormat = SimpleDateFormat(dateFormat)
        val calendar = Calendar.getInstance()
        calendar.time = dateFormat.parse(checkInDate)
        return calendar
    }


    fun showDatePickerDialog1(context: Context) {
        val calendar = getCalendar1()
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

    private fun getCalendar1(): Calendar {
        return if (checkOutDate.isEmpty())
            Calendar.getInstance()
        else
            getLastPickedDateCalendar1()
    }


    private fun getLastPickedDateCalendar1(): Calendar {
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

