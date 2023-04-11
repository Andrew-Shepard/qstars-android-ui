package com.example.androidui

import android.app.DatePickerDialog
import android.content.Context
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import java.text.SimpleDateFormat
import java.util.*

class FlightLogFormViewModel: ViewModel() {
    var flightLogID by mutableStateOf("")
    var pilotID: String by mutableStateOf("")
    var pilotName: String by mutableStateOf("")
    var dateOfLog: String by mutableStateOf("")
    var success: String by mutableStateOf("")
    var totalTime: String by mutableStateOf("")
    var observerID: String by mutableStateOf("")
    var testMission: String by mutableStateOf("")
    var droneID: String by mutableStateOf("")
    var numOfLandings: String by mutableStateOf("")
    var numOfCycles: String by mutableStateOf("")
    var summary: String by mutableStateOf("")


    fun onFlightLogIDChange(newID: String){
        flightLogID = newID
    }

    fun onPilotIDChange(newPilot: String){
        pilotID = newPilot
    }

    fun onPilotNameChange(newName: String){
        pilotName = newName

    }

    fun onSuccessChange(newSuccess: String){
        success = newSuccess
    }

    fun onTotalTimeChange(newTime: String){
        totalTime = newTime
    }

    fun onObserverIDChange(newID: String){
        observerID = newID
    }

    fun onTestMissionChange(newTest: String){
        testMission = newTest
    }

    fun onDroneIDChange(newDroneID: String){
        droneID = newDroneID
    }

    fun onNumLandingsChange(newLandings: String){
        numOfLandings = newLandings
    }

    fun onCyclesChange(newCycles: String){
        numOfCycles = newCycles
    }

    fun summary(newSummary: String){
        summary = newSummary
    }

    private var dateFormat = "MM-dd-yyyy"

    fun showDatePickerDialog(context: Context) {
        val calendar = getCalendar()
        DatePickerDialog(
            context, { _, year, month, day ->
                dateOfLog = getPickedDateAsString( month, day,year)
            },
            calendar.get(Calendar.YEAR),
            calendar.get(Calendar.MONTH),
            calendar.get(Calendar.DAY_OF_MONTH)
        )
            .show()
    }

    private fun getCalendar(): Calendar {
        return if (dateOfLog.isEmpty())
            Calendar.getInstance()
        else
            getLastPickedDateCalendar()
    }


    private fun getLastPickedDateCalendar(): Calendar {
        val dateFormat = SimpleDateFormat(dateFormat)
        val calendar = Calendar.getInstance()
        calendar.time = dateFormat.parse(dateOfLog)
        return calendar
    }

    private fun getPickedDateAsString( month: Int, day: Int, year: Int): String {
        val calendar = Calendar.getInstance()
        calendar.set(year, month, day)
        val dateFormat = SimpleDateFormat(dateFormat)
        return dateFormat.format(calendar.time)
    }


}