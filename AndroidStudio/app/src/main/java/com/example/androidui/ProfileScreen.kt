package com.example.androidui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIos
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.navigation.NavController
import kotlinx.coroutines.NonCancellable.start

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun ProfileScreen(navController: NavController){
    Dialog(
        onDismissRequest = { /*TODO*/ },
        DialogProperties(usePlatformDefaultWidth = false)) {
        Box(modifier = Modifier
            .fillMaxSize()
            .background(Color.White)) {
            Column{
                Box(modifier = Modifier
                    .background(color = Color(40, 95, 160))
                    .fillMaxWidth()
                    .height(244.dp)
                ){
                    IconButton(
                        modifier = Modifier.offset(x = 15.dp, y = 10.dp),
                        onClick = { navController.popBackStack() }
                    ) {
                        Icon(Icons.Filled.ArrowBackIos, "", modifier = Modifier.size(35.dp), tint = Color.White)
                    }
                    Column(
                        modifier = Modifier
                            .fillMaxSize(),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally

                    ) {
                        Text(
                            text = "Roger",
                            textAlign = TextAlign.Center,
                            color = Color.White,
                            style = TextStyle(
                                fontSize = 25.sp
                            ),
                            modifier = Modifier
                                .width(width = 200.dp)
                                .height(height = 32.dp)
                        )
                        Image(
                            painter = painterResource(id = R.drawable.roger_profilepic),
                            contentDescription = "Profile Picture",
                            contentScale = ContentScale.Crop,
                            modifier = Modifier
                                .padding(vertical = 10.dp)
                                .clip(CircleShape)
                                .size(150.dp)
                        )
                    }
                }
                Box(modifier = Modifier
                    .fillMaxWidth()
                    .background(color = Color.White)){
                    Column{
                        Row(modifier = Modifier
                            .padding(top = 30.dp)
                        ){
                            Image(
                                painter = painterResource(id = R.drawable.user),
                                contentDescription = "Profile Name",
                                colorFilter = ColorFilter.tint(Color(0xff3e3e3e)),
                                modifier = Modifier
                                    .padding(start = 30.dp)
                                    .padding(end = 30.dp)
                                    .height(40.dp)
                                    .width(40.dp)
                            )
                            Text(
                                text = "Roger",
                                color = Color.Black,
                                style = TextStyle(
                                    fontSize = 20.sp),
                                modifier = Modifier
                                    .padding(top = 10.dp)
                                    .width(width = 200.dp)
                                    .height(height = 32.dp)
                            )
                        }

                        Row(modifier = Modifier
                            .padding(top = 30.dp)
                        ){
                            Image(
                                painter = painterResource(id = R.drawable.mail),
                                contentDescription = "Email",
                                colorFilter = ColorFilter.tint(Color(0xff3e3e3e)),
                                modifier = Modifier
                                    .padding(start = 30.dp)
                                    .padding(end = 30.dp)
                                    .height(40.dp)
                                    .width(40.dp)
                            )
                            Text(
                                text = "roger@overheadintel.com",
                                color = Color.Black,
                                style = TextStyle(
                                    fontSize = 20.sp),
                                modifier = Modifier
                                    .width(width = 200.dp)
                                    .height(height = 32.dp)
                            )
                        }

                        Row(modifier = Modifier
                            .padding(top = 30.dp)
                        ){
                            Image(
                                painter = painterResource(id = R.drawable.settings),
                                contentDescription = "Settings",
                                colorFilter = ColorFilter.tint(Color(0xff3e3e3e)),
                                modifier = Modifier
                                    .padding(start = 30.dp)
                                    .padding(end = 30.dp)
                                    .height(40.dp)
                                    .width(40.dp)
                            )
                            Text(
                                text = "Settings",
                                color = Color.Black,
                                style = TextStyle(
                                    fontSize = 20.sp),
                                modifier = Modifier
                                    .width(width = 200.dp)
                                    .height(height = 32.dp)
                            )
                        }

                        Box(modifier = Modifier
                            .padding(top = 120.dp, bottom = 60.dp)){
                            Text(
                                text = "Logout",
                                color = Color(62,62,62),
                                style = TextStyle(
                                    fontSize = 25.sp, fontWeight = FontWeight.Bold),
                                modifier = Modifier
                                    .width(width = 200.dp)
                                    .height(height = 32.dp)
                                    .padding(start = 35.dp)
                            )
                        }
                    }
                }
            }
        }
    }
}
