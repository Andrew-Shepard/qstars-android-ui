package com.example.androidui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun AssetFormScreen(formViewModel: FormViewModel = viewModel()){

    Column{

        LazyColumn(
            modifier = Modifier.padding(10.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ){

            var requiredFieldsFilled = true

            //Serial Number
            item {
                Text(text = "Asset ID")
                AppTextField(
                    text = formViewModel.assetID,
                    onChange = { formViewModel.onAssetIDChange(it) },
                    placeholder = "Asset ID")
            }

            //Asset Name
            item {
                Text(text = "Asset Name")
                AppTextField(
                    text = formViewModel.assetName,
                    onChange = { formViewModel.onAssetNameChange(it)},
                    placeholder = "Asset Name"
                )
            }

            //Asset Type
            item {
                Text(text = "Asset Type")

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
                Text(text = "Status")

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
                    //update clickCount
                    formViewModel.parentClickCount++

                    //navigate to parent screen

                }) {
                    Text("+ Parent")
                }
            }

            // Prints parents
            items(formViewModel.parentClickCount){ click ->

                Text(formViewModel.parents[click])

            }

            //Child Button
            item {

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

                Text(text = "Purchase Date")
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
                    if (
                        formViewModel.assetID.isNotEmpty() &&
                        formViewModel.assetName.isNotEmpty() &&
                        formViewModel.assetType.isNotEmpty() &&
                        formViewModel.assetStatus.isNotEmpty() &&
                        formViewModel.datePurchased.isNotEmpty() &&
                        formViewModel.currentLocation.isNotEmpty()
                    ){
                        requiredFieldsFilled = true
                    }
                    else{
                        requiredFieldsFilled = false
                    }


                    Button( modifier = Modifier.align(CenterHorizontally),
                        enabled = requiredFieldsFilled,
                        onClick = {
                            //navigate to previous screen
                            //pass all values to create new asset
                        }) {
                        Text("Create Asset")
                    }
                }
                Spacer(modifier = Modifier.height(20.dp))
            }

        }
    }
}