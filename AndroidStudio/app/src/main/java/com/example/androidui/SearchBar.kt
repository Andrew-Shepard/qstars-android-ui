package com.example.androidui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.width
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.Visibility
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
import androidx.lifecycle.viewmodel.compose.viewModel


class SearchViewModel : ViewModel(){
    var userSearch by mutableStateOf("")

    fun onSearchChange(newSearch: String){
        userSearch = newSearch
    }
}



@Composable
fun SearchBar(
    searchViewModel: SearchViewModel
)
{
    Column(Modifier.offset(15.dp,15.dp))
    {
        OutlinedTextField(
            value = searchViewModel.userSearch,
            onValueChange = { searchViewModel.onSearchChange(it)},
            modifier = Modifier
                .width(250.dp)
                .height(50.dp),
            placeholder = { Text(text = "Search by Asset ID", style = TextStyle(fontSize = 18.sp, color = Color.LightGray)) }
        )
    }
}