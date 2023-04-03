package com.example.androidui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            //MainScreen()
            //AssetFormScreen()
            //testDropDown()
            AssetTable(
                tableId = "asset",
                tableTitle = "Assets",
                column1Title = "ID",
                column2Title = "Name",
                column3Title = "Type",
                column4Title = "Status",
                width = 350,
                height = 200
            )
        }
    }
}
