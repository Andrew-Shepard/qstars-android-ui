package com.example.androidui

import android.os.Bundle
import android.provider.ContactsContract.Profile
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.androidui.ui.theme.AndroidUITheme
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.ui.res.painterResource
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AndroidUITheme {
                // A surface container using the 'background' color from the theme
                ProfileView()
            }
        }
    }
}
@Preview
@Composable
fun ProfileView(){
    Box(modifier = Modifier.fillMaxSize()){
        Row(modifier=Modifier.align(Alignment.TopEnd).padding(all = 8.dp)){
            ProfilePicture()
        }
    }
}


//Profile picture function
@Composable
fun ProfilePicture(){
    Image(
        painter = painterResource(R.drawable.roger_profilepic),
        contentDescription = "Profile Picture",
        contentScale = ContentScale.Crop,
        modifier = Modifier
            .size(65.dp)
            .clip(CircleShape)
    )
}