package com.example.androidui

import android.graphics.drawable.Icon
import android.renderscript.ScriptGroup
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.Alignment
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.runtime.*
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.toSize
import androidx.compose.ui.geometry.Size

// Create a regular input field or a drop down input field
@Composable
fun InputField(inputField: InputFieldData, dropDownItems: List<String>) {

    // UNSURE WHAT THIS DOES!!!!
    var mExpanded by remember { mutableStateOf(false) }
    var mSelectedText by remember { mutableStateOf("") }
    var mTextFieldSize by remember { mutableStateOf(Size.Zero) }

    // Up Icon when expanded and down icon when collapsed
    val icon = if (mExpanded)
        Icons.Filled.KeyboardArrowUp
    else
        Icons.Filled.KeyboardArrowDown

    // Wraps the input field content
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .offset(x = 15.dp)
    ) {

        Column() {
            //If the input field is of type drop down, create drop down
            if (inputField.dropDown) {

                Text(text = inputField.name)

                OutlinedTextField(
                    value = mSelectedText,
                    onValueChange = { mSelectedText = it },
                    modifier = Modifier
                        .width(inputField.width.dp)
                        .height(inputField.height.dp)
                        .onGloballyPositioned { coordinates ->
                            mTextFieldSize = coordinates.size.toSize()
                        },
                    trailingIcon = {
                        Icon(icon, "contentDescription",
                            Modifier.clickable { mExpanded = !mExpanded })
                    }
                )

                DropdownMenu(
                    expanded = mExpanded,
                    onDismissRequest = { mExpanded = false },
                    modifier = Modifier.width(with(LocalDensity.current) { mTextFieldSize.width.toDp() })
                ) {

                    dropDownItems.forEach { label ->
                        DropdownMenuItem(onClick = {
                            mSelectedText = label
                            mExpanded = false
                        }) {
                            Text(text = label)
                        }
                    }

                }
            }
            //if input field is not of type drop down, create regular input field
            else {
                Text(text = inputField.name)
                OutlinedTextField(
                    value = "",
                    onValueChange = {},
                    modifier = Modifier
                        .width(inputField.width.dp)
                        .height(inputField.height.dp)
                )
            }

        }
    }
}


//Generate a list of input fields
@Composable
fun AllInputFields(inputFieldList: List<InputFieldData>, dropDownList: List<List<String>>) {

    var index = 0 // goes through the lists in the drop down list
    val dropDownListSize = dropDownList.size

    LazyColumn(verticalArrangement = Arrangement.spacedBy(10.dp))
    {
        items(inputFieldList) { inputField ->
            if (inputField.dropDown) {
                InputField(inputField, dropDownList[index])
                index++
            } else {
                //Makes sure that the index doesn't go over the size of the list
                if (index >= dropDownListSize) {
                    InputField(inputField, dropDownList[index - 1])
                } else {
                    InputField(inputField, dropDownList[index])
                }
            }
        }
    }
}


@Composable
fun NewAssetCreationScreen() {

    val inputFieldList: List<InputFieldData> = listOf(
        InputFieldData("Asset Serial Number"),
        InputFieldData("Asset Name"),
        InputFieldData("Asset Type", 150, dropDown = true),
        InputFieldData("Status", 150, dropDown = true),
        InputFieldData("Location"),
        InputFieldData("Purchase Date", 175),
        InputFieldData("Description", height = 80)
    )

    val assetTypeDropDown = listOf(
        "Drone",
        "Motor",
        "Battery",
        "Other"
    )
    val assetStatusDropDown = listOf(
        "Active",
        "In Maintenance",
        "Out of Commission"
    )

    val dropDownLists: List<List<String>> =
        listOf(assetTypeDropDown, assetStatusDropDown)

    AllInputFields(inputFieldList, dropDownLists)
}