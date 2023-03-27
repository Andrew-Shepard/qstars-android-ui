package com.example.androidui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Popup
import androidx.navigation.NavController

@Composable
fun DetailsPopUp(popupTitle: String, fieldList: List<String>, navController: NavController, data: String?, listOfAssets: List<List<String>>){


    Popup(
        alignment = Alignment.Center,
    ){

        Box(modifier = Modifier
            .offset(y = 100.dp)
            .padding(20.dp)
            .fillMaxWidth()
            .height(550.dp)
            .background(color = Color.LightGray)
        ) {

            Column{
                Box(modifier = Modifier
                    .fillMaxWidth()
                    .offset(x = 10.dp)){

                    //Close Button
                    Button(
                        onClick = { navController.popBackStack() },
                        modifier = Modifier
                            .align(Alignment.TopEnd)
                            .width(50.dp)
                            .offset(x = -10.dp)
                    ) {
                        Icon(Icons.Filled.Close, "")
                    }

                    //Title
                    Text(
                        text = popupTitle,
                        modifier = Modifier
                            .align(Alignment.TopStart)
                            .offset(y = 10.dp),
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Medium
                    )

                }

                LazyColumn(verticalArrangement = Arrangement.spacedBy(10.dp)){

                    itemsIndexed(fieldList){ i, field ->
                        Row(horizontalArrangement = Arrangement.spacedBy(65.dp)){
                            Box(modifier = Modifier
                                .width(120.dp)
                                .height(40.dp)
                                .offset(x = 10.dp)) {
                                Text(field)
                            }
                            //for all assets
                            for ( asset in listOfAssets){

                                //if asset id == id passed
                                if (asset[0] == data){
                                    Text(asset[i])
                                }

                            }
                        }

                    }
                }

            }
        }
    }
}