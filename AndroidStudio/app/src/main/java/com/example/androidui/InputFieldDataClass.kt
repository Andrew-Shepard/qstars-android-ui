package com.example.androidui

// data class that holds the string, width, height, and dropDown data of the input fields
data class InputFieldData(
    val name: String,
    val width: Int = 250,
    val height: Int = 50,
    var dropDown: Boolean = false,
    var button: Boolean = false
)