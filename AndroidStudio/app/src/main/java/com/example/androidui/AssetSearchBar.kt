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


class AssetSearchViewModel : ViewModel(){
    var userSearch by mutableStateOf("")
    var nameSearch by mutableStateOf("")
    var assetTypeSearch by mutableStateOf("")
    var assetStatusSearch by mutableStateOf("")
    var lastMaintenanceDate by mutableStateOf("")
    var purchaseDate by mutableStateOf("")

    fun onSearchChange(newSearch: String){
        userSearch = newSearch
    }

    fun onNameSearchChange(newSearch: String){
        nameSearch = newSearch
    }

    fun onAssetTypeSearchChange(newSeach: String){
        assetTypeSearch = newSeach
    }

    fun onAssetStatusSearchChange(newSearch: String){
        assetStatusSearch = newSearch
    }


    private var dateFormat = "MM-dd-yyyy"

    fun showDatePickerDialog(context: Context) {
        val calendar = getCalendar()
        DatePickerDialog(
            context, { _, year, month, day ->
                lastMaintenanceDate = getPickedDateAsString( month, day,year)
            },
            calendar.get(Calendar.YEAR),
            calendar.get(Calendar.MONTH),
            calendar.get(Calendar.DAY_OF_MONTH)
        )
            .show()
    }

    private fun getCalendar(): Calendar {
        return if (lastMaintenanceDate.isEmpty())
            Calendar.getInstance()
        else
            getLastPickedDateCalendar()
    }


    private fun getLastPickedDateCalendar(): Calendar {
        val dateFormat = SimpleDateFormat(dateFormat)
        val calendar = Calendar.getInstance()
        calendar.time = dateFormat.parse(lastMaintenanceDate)
        return calendar
    }


    fun showDatePickerDialog1(context: Context) {
        val calendar = getCalendar1()
        DatePickerDialog(
            context, { _, year, month, day ->
                purchaseDate = getPickedDateAsString( month, day,year)
            },
            calendar.get(Calendar.YEAR),
            calendar.get(Calendar.MONTH),
            calendar.get(Calendar.DAY_OF_MONTH)
        )
            .show()
    }

    private fun getCalendar1(): Calendar {
        return if (purchaseDate.isEmpty())
            Calendar.getInstance()
        else
            getLastPickedDateCalendar1()
    }


    private fun getLastPickedDateCalendar1(): Calendar {
        val dateFormat = SimpleDateFormat(dateFormat)
        val calendar = Calendar.getInstance()
        calendar.time = dateFormat.parse(purchaseDate)
        return calendar
    }

    private fun getPickedDateAsString( month: Int, day: Int, year: Int): String {
        val calendar = Calendar.getInstance()
        calendar.set(year, month, day)
        val dateFormat = SimpleDateFormat(dateFormat)
        return dateFormat.format(calendar.time)
    }

}



@Composable
fun AssetSearchBar(
    assetSearchViewModel: AssetSearchViewModel
)
{
    Column(Modifier.offset(15.dp,15.dp))
    {
        OutlinedTextField(
            value = assetSearchViewModel.userSearch,
            onValueChange = { assetSearchViewModel.onSearchChange(it)},
            modifier = Modifier
                .width(250.dp)
                .height(50.dp),
            placeholder = { Text(text = "Search by Asset ID", style = TextStyle(fontSize = 18.sp, color = Color.LightGray)) }
        )
    }
}


@Composable
fun FlightLogSearchBar(
    flightLogSearchViewModel: FlightLogSearchViewModel
)
{
    Column(Modifier.offset(15.dp,15.dp))
    {
        OutlinedTextField(
            value = flightLogSearchViewModel.flightLogID,
            onValueChange = { flightLogSearchViewModel.onFlightLogIDChange(it)},
            modifier = Modifier
                .width(250.dp)
                .height(50.dp),
            placeholder = { Text(text = "Search by Flight Log ID", style = TextStyle(fontSize = 18.sp, color = Color.LightGray)) }
        )
    }
}