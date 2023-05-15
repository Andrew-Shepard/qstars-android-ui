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
import androidx.navigation.NavController

// This file contains the asset form screen
@Composable
fun AssetFormScreen(
    navController: NavController,
    assetFormViewModel: AssetFormViewModel,
    assetTableViewModel: AssetTableViewModel
) {
    Column {

        //Title and Close Button Box
        Box(modifier = Modifier.fillMaxWidth()) {
            // Close button
            Button(
                onClick = {
                    assetFormViewModel.clearTextFields()
                    navController.popBackStack()
                }, modifier = Modifier
                    .align(Alignment.TopEnd)
                    .padding(10.dp)
                    .width(55.dp)
            ) {
                Icon(Icons.Filled.Close, "")
            }
            // Title
            Text(
                text = "Create New Asset",
                modifier = Modifier
                    .align(Alignment.TopCenter)
                    .offset(y = 20.dp),
                fontSize = 25.sp,
                fontWeight = FontWeight.Medium
            )
        }

        // All TextFields and DropDowns
        LazyColumn(
            modifier = Modifier.padding(10.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {

            //Serial Number
            item {
                MultiStyleText(
                    text1 = "Asset ID",
                    color1 = Color.Black,
                    text2 = "*",
                    color2 = Color.Red
                )
                AppTextField(
                    text = assetFormViewModel.assetID,
                    onChange = { assetFormViewModel.onAssetIDChange(it) },
                    placeholder = "Asset ID"
                )
            }

            //Asset Name
            item {
                MultiStyleText(
                    text1 = "Asset Name",
                    color1 = Color.Black,
                    text2 = "*",
                    color2 = Color.Red
                )
                AppTextField(
                    text = assetFormViewModel.assetName,
                    onChange = { assetFormViewModel.onAssetNameChange(it) },
                    placeholder = "Asset Name"
                )
            }

            //Asset Type
            item {
                MultiStyleText(
                    text1 = "Asset Type",
                    color1 = Color.Black,
                    text2 = "*",
                    color2 = Color.Red
                )

                val assetTypeDropDown = listOf(
                    "Battery",
                    "Case",
                    "Drone",
                    "Electronics",
                    "Engine",
                    "Frame",
                    "Motor"
                )

                AppTextFieldDropDown(
                    selectedText = assetFormViewModel.assetType,
                    placeholder = "-Select-",
                    dropDownItems = assetTypeDropDown,
                    onSelectedTextChange = { assetFormViewModel.onAssetTypeChange(it) }
                )
            }

            // Status
            item {
                MultiStyleText(
                    text1 = "Status",
                    color1 = Color.Black,
                    text2 = "*",
                    color2 = Color.Red
                )

                val assetStatusDropDown = listOf(
                    "Active",
                    "In Maintenance",
                    "Out of Commission"
                )

                AppTextFieldDropDown(
                    selectedText = assetFormViewModel.assetStatus,
                    placeholder = "-Select-",
                    dropDownItems = assetStatusDropDown,
                    onSelectedTextChange = { assetFormViewModel.onStatusChange(it) }
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

            // Prints parent selected with "+ Parent" button
            items(assetFormViewModel.parents) { parent ->
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

            // Prints children selected with "+ Children" button
            items(assetFormViewModel.children) { child ->
                Text(child, fontSize = 15.sp)
            }

            // Location
            item {
                Text(text = "Location")
                AppTextField(
                    text = assetFormViewModel.currentLocation,
                    placeholder = "Location",
                    onChange = { assetFormViewModel.locationChange(it) })
            }

            // Purchase Date
            item {

                val context = LocalContext.current

                MultiStyleText(
                    text1 = "Purchase Date",
                    color1 = Color.Black,
                    text2 = "*",
                    color2 = Color.Red
                )
                AppTextField(
                    modifier = Modifier.clickable {
                        assetFormViewModel.showDatePickerDialog(context)
                    },
                    text = assetFormViewModel.datePurchased,
                    placeholder = "MM-DD-YYYY",
                    onChange = {
                        assetFormViewModel.datePurchased = it
                    },
                    isEnabled = false
                )
            }

            // Description
            item {
                Text(text = "Description")
                AppTextField(
                    text = assetFormViewModel.description,
                    placeholder = "Description",
                    modifier = Modifier.height(150.dp),
                    onChange = { assetFormViewModel.onDescriptionChange(it) }
                )
            }

            // Create Asset Button
            item {
                Spacer(modifier = Modifier.height(20.dp))
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight(),
                    verticalArrangement = Arrangement.Center
                ) {

                    //checks whether any of the required fields are empty
                    var requiredFieldsFilled = assetFormViewModel.assetID.isNotEmpty() &&
                            assetFormViewModel.assetName.isNotEmpty() &&
                            assetFormViewModel.assetType.isNotEmpty() &&
                            assetFormViewModel.assetStatus.isNotEmpty() &&
                            assetFormViewModel.datePurchased.isNotEmpty()


                    // create asset button
                    Button(modifier = Modifier.align(CenterHorizontally),
                        enabled = requiredFieldsFilled,
                        onClick = {

                            //pass all values to create new asset
                            assetTableViewModel.newAsset(
                                assetID = assetFormViewModel.assetID,
                                assetName = assetFormViewModel.assetName,
                                assetType = assetFormViewModel.assetType,
                                assetStatus = assetFormViewModel.assetStatus,
                                parents = assetFormViewModel.parents,
                                children = assetFormViewModel.children,
                                datePurchased = assetFormViewModel.datePurchased,
                                currentLocation = assetFormViewModel.currentLocation,
                                description = assetFormViewModel.description
                            )

                            //clear text fields
                            assetFormViewModel.clearTextFields()

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