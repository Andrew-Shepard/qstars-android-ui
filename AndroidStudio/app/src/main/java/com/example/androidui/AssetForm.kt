package com.example.androidui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController

@Composable
fun AssetFormScreen(
    navController: NavController,
    formViewModel: FormViewModel,
    assetTableViewModel: AssetTableViewModel
){
    Column{

        //Title and Close Button
        Box(modifier = Modifier.fillMaxWidth()) {
            Button(onClick = { navController.popBackStack() }, modifier = Modifier
                .align(Alignment.TopEnd)
                .padding(10.dp)
                .width(55.dp)) {
                Icon(Icons.Filled.Close, "")
            }
            Text(
                "Create New Asset",
                Modifier
                    .align(Alignment.TopCenter)
                    .offset(y=20.dp),
                fontSize = 25.sp,
                fontWeight = FontWeight.Medium
            )
        }

        // All Fields
        LazyColumn(
            modifier = Modifier.padding(10.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ){

            //Serial Number
            item {
                MultiStyleText(text1 = "Asset ID", color1 = Color.Black, text2 = "*", color2 = Color.Red)
                AppTextField(
                    text = formViewModel.assetID,
                    onChange = { formViewModel.onAssetIDChange(it) },
                    placeholder = "Asset ID")
            }

            //Asset Name
            item {
                MultiStyleText(text1 = "Asset Name", color1 = Color.Black, text2 = "*", color2 = Color.Red)
                AppTextField(
                    text = formViewModel.assetName,
                    onChange = { formViewModel.onAssetNameChange(it)},
                    placeholder = "Asset Name"
                )
            }

            //Asset Type
            item {
                MultiStyleText(text1 = "Asset Type", color1 = Color.Black, text2 = "*", color2 = Color.Red)

                val assetTypeDropDown = listOf(
                    "Drone",
                    "Motor",
                    "Battery",
                    "Other"
                )

                AppTextFieldDropDown(
                    selectedText = formViewModel.assetType,
                    placeholder = "-Select-",
                    dropDownItems = assetTypeDropDown,
                    onSelectedTextChange = { formViewModel.onAssetTypeChange(it) }
                )
            }

            // Status
            item {
                MultiStyleText(text1 = "Status", color1 = Color.Black, text2 = "*", color2 = Color.Red)

                val assetStatusDropDown = listOf(
                    "Active",
                    "In Maintenance",
                    "Out of Commission"
                )

                AppTextFieldDropDown(
                    selectedText = formViewModel.assetStatus,
                    placeholder = "-Select-",
                    dropDownItems = assetStatusDropDown,
                    onSelectedTextChange = { formViewModel.onStatusChange(it) }
                )
            }

            // Parent Button
            item {
                Button(onClick = {
                    //navigate to parent screen
                    navController.navigate("parent-table")

                }) {
                    Text("+ Parent", fontSize = 20.sp)
                }
            }

            // Prints parents
            items(formViewModel.parents){ parent ->
                Text(parent, fontSize = 15.sp)
            }

            //Child Button
            item {
                Button(onClick = {

                    //navigate to parent screen
                    navController.navigate("child-table")
7
                }) {
                    Text("+ Child", fontSize = 20.sp)
                }
            }

            // Prints children
            items(formViewModel.children){ child ->
                Text(child, fontSize = 15.sp)
            }

            // Location
            item {
                Text(text = "Location")
                AppTextField(
                    text = formViewModel.currentLocation,
                    placeholder = "Location",
                    onChange = { formViewModel.locationChange(it) })
            }

            // Purchase Date
            item {

                val context = LocalContext.current

                MultiStyleText(text1 = "Purchase Date", color1 = Color.Black, text2 = "*", color2 = Color.Red)
                AppTextField(
                    modifier = Modifier.clickable {
                        formViewModel.showDatePickerDialog(context)
                    },
                    text = formViewModel.datePurchased,
                    placeholder = "MM-DD-YYYY",
                    onChange = {
                        formViewModel.datePurchased = it
                    },
                    isEnabled = false
                )
            }

            // Description
            item {
                Text(text = "Description")
                AppTextField(
                    text = formViewModel.description,
                    placeholder = "Description",
                    modifier = Modifier.height(150.dp),
                    onChange = { formViewModel.onDescriptionChange(it) }
                )
            }

            // Create Asset Button
            item{
                Spacer(modifier = Modifier.height(20.dp))
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight(),
                    verticalArrangement = Arrangement.Center
                ) {

                    //checks whether any of the required fields are empty
                    var requiredFieldsFilled = formViewModel.assetID.isNotEmpty() &&
                            formViewModel.assetName.isNotEmpty() &&
                            formViewModel.assetType.isNotEmpty() &&
                            formViewModel.assetStatus.isNotEmpty() &&
                            formViewModel.datePurchased.isNotEmpty()


                    Button( modifier = Modifier.align(CenterHorizontally),
                        enabled = requiredFieldsFilled,
                        onClick = {

                            //pass all values to create new asset
                            assetTableViewModel.newAsset(
                                assetID = formViewModel.assetID,
                                assetName = formViewModel.assetName,
                                assetType = formViewModel.assetType,
                                assetStatus =  formViewModel.assetStatus,
                                parents = formViewModel.parents,
                                children = formViewModel.children,
                                datePurchased = formViewModel.datePurchased,
                                currentLocation = formViewModel.currentLocation,
                                description = formViewModel.description
                            )

                            //clear textfields
                            formViewModel.assetID = ""
                            formViewModel.assetName = ""
                            formViewModel.assetType= ""
                            formViewModel.assetStatus = ""
                            formViewModel.datePurchased = ""
                            formViewModel.currentLocation= ""
                            formViewModel.description = ""
                            formViewModel.parents = arrayListOf()
                            formViewModel.children = arrayListOf()

                            //navigate to asset screen
                            navController.navigate("assets")


                        }) {
                        Text("Create Asset")
                    }
                }
                Spacer(modifier = Modifier.height(20.dp))
            }

        }
    }
}