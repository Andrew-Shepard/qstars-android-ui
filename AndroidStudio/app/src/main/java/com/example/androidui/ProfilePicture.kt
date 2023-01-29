package com.example.androidui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp

@Composable
fun ProfileView(){
    Box(modifier = Modifier.fillMaxSize()){
        Row(modifier= Modifier.align(Alignment.TopEnd).padding(all = 8.dp)){
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