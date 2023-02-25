package com.example.androidui

// data class that holds the string, width, height, and dropDown data of the input fields
data class InputFieldData(
    val name: String,
    val width: Int = 230,
    val height: Int = 40,
    var dropDown: Boolean = false
)