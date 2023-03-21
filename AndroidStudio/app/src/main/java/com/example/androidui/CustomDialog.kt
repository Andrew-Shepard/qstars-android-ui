package com.example.androidui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.navigation.NavController

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun CustomDialog(
    dialogTitle: String,
    inputFieldList: List<InputFieldData>,
    dropDownList: List<List<String>>,
    buttonName: String,
    navController: NavController
){

    val openDialog = remember{ mutableStateOf(true) }

    Dialog(
        properties = DialogProperties(usePlatformDefaultWidth = false),
        onDismissRequest = { openDialog.value = false}){

        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
        ) {

            Column {

                // Close Button of Asset Creation Screen
                Box(modifier = Modifier.fillMaxWidth()) {
                    Button(onClick = { navController.popBackStack() }, modifier = Modifier
                        .align(Alignment.TopEnd)
                        .padding(10.dp)
                        .width(55.dp)) {
                        Icon(Icons.Filled.Close, "")
                    }
                    Text(
                        dialogTitle,
                        Modifier
                            .align(Alignment.TopCenter)
                            .offset(y=20.dp),
                        fontSize = 25.sp,
                        fontWeight = FontWeight.Medium
                    )
                }

                Spacer(modifier = Modifier.height(15.dp))

                // Creates all fields and drop-down fields
                AllInputFields(inputFieldList, dropDownList, buttonName)

                }

            }
        }

}