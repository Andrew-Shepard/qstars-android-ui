package com.example.androidui

import android.app.DatePickerDialog
import android.content.Context
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.geometry.Size
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.NonDisposableHandle.parent
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class FormViewModel : ViewModel() {
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

    // variables for the drop box text fields
    var dropDownExpanded by mutableStateOf(false)
    var textFieldSize by mutableStateOf(Size.Zero)

    var parentClickCount by mutableStateOf(0)
    var childrenClickCount by mutableStateOf(0)

    var test by mutableStateOf(false)

    fun settingThing(){
        test = !test
    }

    // sets assetID to new asset ID
    fun onAssetIDChange(newID: String){
        assetID = newID
    }

    // sets asset name to new asset name
    fun onAssetNameChange(newName: String){
        assetName = newName
    }

    fun onAssetTypeChange(newType: String){
        assetType = newType
    }

    fun onStatusChange(newStatus: String){
        assetStatus = newStatus
    }

    fun addParent(newParent: String){
        parents.add(newParent)
    }

    fun clearParents(i: Int){
        parents.drop(i)
    }

    fun locationChange(newLocation: String){
        currentLocation = newLocation
    }


    private var dateFormat = "MM-dd-yyyy"

    fun showDatePickerDialog(context: Context) {
        val calendar = getCalendar()
        DatePickerDialog(
            context, { _, year, month, day ->
                datePurchased = getPickedDateAsString( month, day,year)
            },
            calendar.get(Calendar.YEAR),
            calendar.get(Calendar.MONTH),
            calendar.get(Calendar.DAY_OF_MONTH)
        )
            .show()
    }

    private fun getCalendar(): Calendar {
        return if (datePurchased.isEmpty())
            Calendar.getInstance()
        else
            getLastPickedDateCalendar()
    }


    private fun getLastPickedDateCalendar(): Calendar {
        val dateFormat = SimpleDateFormat(dateFormat)
        val calendar = Calendar.getInstance()
        calendar.time = dateFormat.parse(datePurchased)
        return calendar
    }

    private fun getPickedDateAsString( month: Int, day: Int, year: Int): String {
        val calendar = Calendar.getInstance()
        calendar.set(year, month, day)
        val dateFormat = SimpleDateFormat(dateFormat)
        return dateFormat.format(calendar.time)
    }

    fun onDescriptionChange(newDescription: String){
        description = newDescription
    }

}