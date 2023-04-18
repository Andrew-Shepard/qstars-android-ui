package com.example.androidui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@Composable
fun FlightLogFormScreen(
    navController: NavController,
    flightLogFormViewModel: FlightLogFormViewModel,
    flightLogTableViewModel: FlightLogTableViewModel
){
    Column {

        //Title and Close Button
        Box(modifier = Modifier.fillMaxWidth()) {
            Button(
                onClick = {

                    //clear textfields
                    flightLogFormViewModel.flightLogID = ""
                    flightLogFormViewModel.pilotID = ""
                    flightLogFormViewModel.pilotName = ""
                    flightLogFormViewModel.dateOfLog = ""
                    flightLogFormViewModel.success = ""
                    flightLogFormViewModel.totalTime = ""
                    flightLogFormViewModel.observerID = ""
                    flightLogFormViewModel.testMission = ""
                    flightLogFormViewModel.droneID = ""
                    flightLogFormViewModel.numOfLandings = ""
                    flightLogFormViewModel.numOfCycles = ""
                    flightLogFormViewModel.summary = ""

                    navController.popBackStack() },
                    modifier = Modifier
                        .align(Alignment.TopEnd)
                        .padding(10.dp)
                        .width(55.dp)
            ) {
                Icon(Icons.Filled.Close, "")
            }
            Text(
                "Create New Flight Log",
                Modifier
                    .align(Alignment.TopCenter)
                    .offset(y = 20.dp),
                fontSize = 25.sp,
                fontWeight = FontWeight.Medium
            )
        }

        LazyColumn(
            modifier = Modifier.padding(10.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ){
            item {
                MultiStyleText(text1 = "Flight Log ID", color1 = Color.Black, text2 = "*", color2 = Color.Red)
                AppTextField(
                    text = flightLogFormViewModel.flightLogID,
                    onChange = { flightLogFormViewModel.onFlightLogIDChange(it) },
                    placeholder = "Flight Log ID")
            }

            item {
                MultiStyleText(text1 = "Pilot ID", color1 = Color.Black, text2 = "*", color2 = Color.Red)
                AppTextField(
                    text = flightLogFormViewModel.pilotID,
                    onChange = { flightLogFormViewModel.onPilotIDChange(it) },
                    placeholder = "Pilot ID")
            }

            item {
                MultiStyleText(text1 = "Pilot Name", color1 = Color.Black, text2 = "*", color2 = Color.Red)
                AppTextField(
                    text = flightLogFormViewModel.pilotName,
                    onChange = { flightLogFormViewModel.onPilotNameChange(it) },
                    placeholder = "Pilot Name")
            }

            item {

                val context = LocalContext.current

                MultiStyleText(text1 = "Date of Log", color1 = Color.Black, text2 = "*", color2 = Color.Red)
                AppTextField(
                    modifier = Modifier.clickable {
                        flightLogFormViewModel.showDatePickerDialog(context)
                    },
                    text = flightLogFormViewModel.dateOfLog,
                    placeholder = "MM-DD-YYYY",
                    onChange = {
                        flightLogFormViewModel.dateOfLog = it
                    },
                    isEnabled = false
                )
            }

            item{
                MultiStyleText(text1 = "Success", color1 = Color.Black, text2 = "*", color2 = Color.Red)

                val flightLogSuccessDropDown = listOf(
                    "Yes",
                    "No"
                )

                AppTextFieldDropDown(
                    selectedText = flightLogFormViewModel.success,
                    placeholder = "-Select-",
                    onSelectedTextChange = { flightLogFormViewModel.onSuccessChange(it) },
                    dropDownItems = flightLogSuccessDropDown
                )
            }

            item {
                MultiStyleText(text1 = "Total Time", color1 = Color.Black, text2 = "*", color2 = Color.Red)
                AppTextField(
                    text = flightLogFormViewModel.totalTime,
                    onChange = { flightLogFormViewModel.onTotalTimeChange(it) },
                    placeholder = "Total Time")
            }

            item {
                Text(text = "Observer ID", fontSize = 20.sp)
                AppTextField(
                    text = flightLogFormViewModel.observerID,
                    onChange = { flightLogFormViewModel.onObserverIDChange(it) },
                    placeholder = "Observer ID")
            }

            item {
                MultiStyleText(text1 = "Test Mission", color1 = Color.Black, text2 = "*", color2 = Color.Red)

                val flightTestMissionDropDown = listOf(
                    "Yes",
                    "No"
                )

                AppTextFieldDropDown(
                    selectedText = flightLogFormViewModel.testMission,
                    placeholder = "-Select-",
                    onSelectedTextChange = { flightLogFormViewModel.onTestMissionChange(it) },
                    dropDownItems = flightTestMissionDropDown
                )
            }

            item {
                Button(onClick = {
                    navController.navigate("add-asset-table-flightlog")
                }) {
                    Text(text = "+ Drone", fontSize = 20.sp)
                }
            }

            item {
                Text("Drone ID: " + flightLogFormViewModel.droneID, fontSize = 15.sp)
            }


            item {
                MultiStyleText(text1 = "# of Landings", color1 = Color.Black, text2 = "*", color2 = Color.Red)
                AppTextField(
                    text = flightLogFormViewModel.numOfLandings,
                    onChange = { flightLogFormViewModel.onNumLandingsChange(it) },
                    placeholder = "# of Landings"
                )
            }

            item {
                MultiStyleText(text1 = "# of Cycles", color1 = Color.Black, text2 = "*", color2 = Color.Red)
                AppTextField(
                    text = flightLogFormViewModel.numOfCycles,
                    onChange = { flightLogFormViewModel.onCyclesChange(it) },
                    placeholder = "# of Cycles"
                )
            }

            item {
                Text(text = "Summary", fontSize = 20.sp)
                AppTextField(
                    text = flightLogFormViewModel.summary,
                    onChange = { flightLogFormViewModel.summary(it) },
                    placeholder = "Summary"
                )
            }

            item{
                Spacer(modifier = Modifier.height(20.dp))
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight(),
                    verticalArrangement = Arrangement.Center
                ) {

                    //checks whether any of the required fields are empty
                    var requiredFieldsFilled = flightLogFormViewModel.flightLogID.isNotEmpty() &&
                                flightLogFormViewModel.pilotID.isNotEmpty() &&
                            flightLogFormViewModel.pilotName.isNotEmpty() &&
                            flightLogFormViewModel.dateOfLog.isNotEmpty() &&
                            flightLogFormViewModel.success.isNotEmpty() &&
                            flightLogFormViewModel.totalTime.isNotEmpty() &&
                            flightLogFormViewModel.testMission.isNotEmpty() &&
                            flightLogFormViewModel.droneID.isNotEmpty() &&
                            flightLogFormViewModel.numOfLandings.isNotEmpty() &&
                            flightLogFormViewModel.numOfCycles.isNotEmpty()


                    Button( modifier = Modifier.align(Alignment.CenterHorizontally),
                        enabled = requiredFieldsFilled,
                        onClick = {

                            //pass all values to create new flight log
                            flightLogTableViewModel.newFlightLog(
                                flightLogFormViewModel.flightLogID,
                                flightLogFormViewModel.pilotID,
                                flightLogFormViewModel.pilotName,
                                flightLogFormViewModel.dateOfLog,
                                flightLogFormViewModel.success,
                                flightLogFormViewModel.totalTime,
                                flightLogFormViewModel.observerID,
                                flightLogFormViewModel.testMission,
                                flightLogFormViewModel.droneID,
                                flightLogFormViewModel.numOfLandings,
                                flightLogFormViewModel.numOfCycles,
                                flightLogFormViewModel.summary
                            )

                            //clear textfields
                            flightLogFormViewModel.flightLogID = ""
                            flightLogFormViewModel.pilotID = ""
                            flightLogFormViewModel.pilotName = ""
                            flightLogFormViewModel.dateOfLog = ""
                            flightLogFormViewModel.success = ""
                            flightLogFormViewModel.totalTime = ""
                            flightLogFormViewModel.observerID = ""
                            flightLogFormViewModel.testMission = ""
                            flightLogFormViewModel.droneID = ""
                            flightLogFormViewModel.numOfLandings = ""
                            flightLogFormViewModel.numOfCycles = ""
                            flightLogFormViewModel.summary = ""

                            //navigate to asset screen
                            navController.navigate("flight-logs")


                        }) {
                        Text("Create Flight Log", fontSize = 20.sp)
                    }
                }
                Spacer(modifier = Modifier.height(20.dp))
            }

        }
    }
}