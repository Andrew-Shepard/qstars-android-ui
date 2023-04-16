package com.example.androidui

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.Popup
import androidx.navigation.NavController

@Composable
fun AssetFilterPopup(
    navController: NavController,
    searchViewModel: SearchViewModel
){
    Dialog(onDismissRequest = {  }) {
        Box(modifier = Modifier
            .offset(y = 50.dp)
            .padding(20.dp)
            .border(width = 2.dp, color = Color.LightGray, shape = RectangleShape)
            .background(color = Color.White)
        ) {

            val context = LocalContext.current


            Column{
                Box(modifier = Modifier
                    .background(Color.LightGray)
                    .fillMaxWidth()
                    .offset(x = 10.dp)
                ){

                    Button(
                        onClick = {
                            navController.popBackStack()
                        },
                        modifier = Modifier
                            .align(Alignment.TopEnd)
                            .width(50.dp)
                            .offset(x = -15.dp)
                    ) {
                        Icon(Icons.Filled.Close, "")
                    }

                    //Title
                    Text(
                        text = "Apply Filters",
                        modifier = Modifier
                            .align(Alignment.TopStart)
                            .offset(y = 10.dp),
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Medium
                    )
                }
                Spacer(modifier = Modifier.height(10.dp))

                Box(modifier = Modifier.offset(x = 15.dp)){
                    Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {

                        //Asset Name Filter
                        Box {
                            Column {
                                Text(text = "Name")
                                AppTextField(
                                    text = searchViewModel.nameSearch,
                                    onChange = { searchViewModel.onNameSearchChange(it) },
                                    placeholder = "Name",
                                    width = 250
                                )
                            }
                        }


                        // Asset Type Filter
                        Box {
                            Column {
                                val assetTypeDropDown = listOf(
                                    "Drone",
                                    "Motor",
                                    "Battery",
                                    "Other"
                                )
                                Text(text = "Asset Type")
                                AppTextFieldDropDown(
                                    selectedText = searchViewModel.assetTypeSearch,
                                    placeholder = "-Select-",
                                    onSelectedTextChange = {
                                        searchViewModel.onAssetTypeSearchChange(
                                            it
                                        )
                                    },
                                    dropDownItems = assetTypeDropDown,
                                    width = 250
                                )
                            }
                        }


                        // Asset Status Filter
                        Box {
                            Column {
                                val assetStatusDropDown = listOf(
                                    "Active",
                                    "In Maintenance",
                                    "Out of Commission"
                                )
                                Text(text = "Asset Status")
                                AppTextFieldDropDown(
                                    selectedText = searchViewModel.assetStatusSearch,
                                    placeholder = "-Select-",
                                    onSelectedTextChange = {
                                        searchViewModel.onAssetStatusSearchChange(
                                            it
                                        )
                                    },
                                    dropDownItems = assetStatusDropDown,
                                    width = 250
                                )
                            }
                        }

                        //Last Maintenance Date
                        Box {
                            Column {
                                Text(text = "Last Maintenance Date")
                                AppTextField(
                                    modifier = Modifier.clickable {
                                        searchViewModel.showDatePickerDialog(context)
                                    },
                                    text = searchViewModel.lastMaintenanceDate,
                                    placeholder = "MM-DD-YYYY",
                                    onChange = {
                                        searchViewModel.lastMaintenanceDate = it
                                    },
                                    isEnabled = false,
                                    width = 250
                                )
                            }

                        }


                        //Purchase Date
                        Box {
                            Column {
                                Text(text = "Purchase Date")
                                AppTextField(
                                    modifier = Modifier.clickable {
                                        searchViewModel.showDatePickerDialog1(context)
                                    },
                                    text = searchViewModel.purchaseDate,
                                    placeholder = "MM-DD-YYYY",
                                    onChange = {
                                        searchViewModel.purchaseDate = it
                                    },
                                    isEnabled = false,
                                    width = 250
                                )
                            }
                        }
                    }
                }
                //Clear FIlter and Apply Filter Buttons
                Box(modifier = Modifier.offset(x = 15.dp).padding(vertical = 15.dp)){
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ){
                        Button(onClick = {
                            searchViewModel.nameSearch = ""
                            searchViewModel.assetTypeSearch = ""
                            searchViewModel.assetStatusSearch = ""
                            searchViewModel.lastMaintenanceDate = ""
                            searchViewModel.purchaseDate = ""
                        }) {
                            Text("Clear Filters")
                        }

                        Button(onClick = { navController.popBackStack() }) {
                            Text("Apply Filters")
                        }

                    }
                }

            }
        }
    }
}