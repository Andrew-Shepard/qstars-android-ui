package com.example.androidui

import android.graphics.Color
import android.graphics.drawable.shapes.Shape
import android.os.Bundle
import android.provider.ContactsContract.Profile
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.ui.tooling.preview.Preview
import com.example.androidui.ui.theme.AndroidUITheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.draw.clip

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AndroidUITheme {
                // A surface container using the 'background' color from the theme
                TopBar()
                ProfileView()
            }
        }
    }
}

@Composable
fun TopBar(){
    Box(Modifier.fillMaxSize()){
        Box(
            Modifier
                .align(Alignment.TopCenter)
                .fillMaxWidth()
                .height(80.dp)
                .clip(shape = RoundedCornerShape(10.dp))
                .background(androidx.compose.ui.graphics.Color.LightGray))
    }
}
