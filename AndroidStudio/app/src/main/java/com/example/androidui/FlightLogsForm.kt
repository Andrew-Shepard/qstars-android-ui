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
                Text(text = "Flight Log ID")
                AppTextField(
                    text = flightLogFormViewModel.flightLogID,
                    onChange = { flightLogFormViewModel.onFlightLogIDChange(it) },
                    placeholder = "Flight Log ID")
            }

            item {
                Text(text = "Pilot ID")
                AppTextField(
                    text = flightLogFormViewModel.pilotID,
                    onChange = { flightLogFormViewModel.onPilotIDChange(it) },
                    placeholder = "Pilot ID")
            }

            item {
                Text(text = "Pilot Name")
                AppTextField(
                    text = flightLogFormViewModel.pilotName,
                    onChange = { flightLogFormViewModel.onPilotNameChange(it) },
                    placeholder = "Pilot Name")
            }

            item {

                val context = LocalContext.current

                Text(text = "Date of Log")
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
                Text(text = "Success")

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
                Text(text = "Total Time")
                AppTextField(
                    text = flightLogFormViewModel.totalTime,
                    onChange = { flightLogFormViewModel.onTotalTimeChange(it) },
                    placeholder = "Total Time")
            }

            item {
                Text(text = "Observer ID")
                AppTextField(
                    text = flightLogFormViewModel.observerID,
                    onChange = { flightLogFormViewModel.onObserverIDChange(it) },
                    placeholder = "Observer ID")
            }

            item {
                Text(text = "Test Mission")

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
                Text(text = "Drone ID")
                AppTextField(
                    text = flightLogFormViewModel.droneID,
                    onChange = { flightLogFormViewModel.onDroneIDChange(it) },
                    placeholder = "Drone ID")
            }


            item {
                Text(text = "# of Landings")
                AppTextField(
                    text = flightLogFormViewModel.numOfLandings,
                    onChange = { flightLogFormViewModel.onNumLandingsChange(it) },
                    placeholder = "# of Landings"
                )
            }

            item {
                Text(text = "# of Cycles")
                AppTextField(
                    text = flightLogFormViewModel.numOfCycles,
                    onChange = { flightLogFormViewModel.onCyclesChange(it) },
                    placeholder = "# of Cycles"
                )
            }

            item {
                Text(text = "Summary")
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
                        Text("Create Flight Log")
                    }
                }
                Spacer(modifier = Modifier.height(20.dp))
            }

        }
    }
}