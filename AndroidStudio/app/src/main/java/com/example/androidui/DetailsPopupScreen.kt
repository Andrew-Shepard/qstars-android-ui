package com.example.androidui

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Popup
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

// this function makes the parents and children display themselves in a column
@Composable
fun ParentChildList(parentChildList: ArrayList<String> ){
    Column(verticalArrangement = Arrangement.spacedBy(5.dp)){
        for (parentOrChild in parentChildList){
            Text(parentOrChild)
        }
    }
}


@Composable
fun AssetDetailsPopUp(
    popupTitle: String,
    fieldList: List<String>,
    navController: NavController,
    data: String?,
    listOfAssets: ArrayList<Asset>,
    assetFormViewModel: FormViewModel,
    assetTableViewModel: AssetTableViewModel,
    checkInOutFormViewModel: CheckInOutFormViewModel,
    addParentButton: Boolean?,
    addChildButton: Boolean?,
    addAssetButton: Boolean?
){

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route
    var height: Int

    var assetID = ""
    var assetName = ""
    var addPButton:Boolean = false
    var addCButton: Boolean = false
    var addAButton: Boolean = false

    var deleteButtonVisibility: Boolean = true
    if (navController.previousBackStackEntry?.destination?.route == "parent-table"){
        addPButton = true
    }
    else if (navController.previousBackStackEntry?.destination?.route == "child-table"){
        addCButton = true
    }
    else if (navController.previousBackStackEntry?.destination?.route == "add-asset-table"){
        addAButton = true
    }
    else{
        addPButton = false
        addCButton = false
        addAButton = false
    }

    Popup(
        alignment = Alignment.TopCenter
    ){
        if (addCButton == true || addPButton == true || addAButton == true){
            height = 480
            deleteButtonVisibility = false
        }
        else{
            height = 520
            deleteButtonVisibility = true
        }

        Box(modifier = Modifier
            .offset(y = 25.dp)
            .padding(20.dp)
            .width(600.dp)
            .height(600.dp)
            .border(width = 2.dp, color = Color.LightGray, shape = RectangleShape)
            .background(color = Color.White)
        ) {

            Column{
                Box(modifier = Modifier
                    .background(Color.LightGray)
                    .fillMaxWidth()
                    .offset(x = 10.dp)
                ){

                    // Delete Button
                    if (deleteButtonVisibility){
                        Button(
                            onClick = {
                                var sizeOfAssetList: Int = listOfAssets.size - 1
                                var i: Int = 0
                                while (i in 0..sizeOfAssetList){
                                    if (listOfAssets[i].assetID == data){
                                        listOfAssets.remove(listOfAssets[i])
                                        sizeOfAssetList -= 1
                                        break
                                    }
                                    i++
                                }

                                navController.popBackStack()
                            },
                            modifier = Modifier
                                .align(Alignment.TopCenter)
                                .width(50.dp)
                                .offset(x = 25.dp)
                        ) {
                            Icon(Icons.Filled.Delete, "")
                        }
                    }


                    //Close Button
                    Button(
                        onClick = { navController.popBackStack() },
                        modifier = Modifier
                            .align(Alignment.TopEnd)
                            .width(50.dp)
                            .offset(x = -15.dp)
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
                Box(modifier = Modifier.height(height.dp)){
                    LazyColumn(verticalArrangement = Arrangement.spacedBy(10.dp)){

                        itemsIndexed(fieldList){ i, field ->

                            Row(horizontalArrangement = Arrangement.spacedBy(65.dp)){

                                // name of field
                                Box(modifier = Modifier
                                    .width(120.dp)
                                    .height(40.dp)
                                    .offset(x = 10.dp)) {
                                    Text(field)
                                }

                                //value of field
                                for ( asset in listOfAssets){
                                    //if asset id == id passed
                                    if (asset.assetID == data){
                                        assetName = asset.assetName
                                        assetID = asset.assetID
                                        when (i){
                                            0 -> Text(asset.assetID)
                                            1 -> Text(asset.assetName)
                                            2 -> Text(asset.assetType)
                                            3 -> Text(asset.assetStatus)
                                            4 -> ParentChildList(parentChildList = asset.parents)
                                            5 -> ParentChildList(parentChildList = asset.children)
                                            6 -> Text(asset.datePurchased)
                                            7 -> Text(asset.totalHoursUsed)
                                            8 -> Text(asset.lastMaintenanceDate)
                                            9 -> Text(asset.lastCheckOut)
                                            10 -> Text(asset.currentLocation)
                                            11 ->Text(asset. description)
                                        }
                                    }
                                }
                            }
                        }
                    }
                }

                if (addCButton == true){
                    Spacer(Modifier.height(10.dp))
                    Box(
                        modifier = Modifier.fillMaxWidth()
                    ){
                        Button(onClick = {
                            assetFormViewModel.children.add(assetName)
                            //navController.navigate("asset-form")
                            navController.popBackStack()
                                         },
                            modifier = Modifier.align(Alignment.BottomCenter)) {
                            Text("Add Child")
                        }
                    }
                }


                if (addPButton == true) {
                    Spacer(Modifier.height(10.dp))
                    Box(
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Button(
                            onClick = {
                                assetFormViewModel.parents.add(assetName)
                                //navController.navigate("asset-form")
                                navController.popBackStack()
                            },
                            modifier = Modifier.align(Alignment.BottomCenter)
                        ) {
                            Text("Add Parent")
                        }
                    }
                }

                if (addAButton == true){
                    Spacer(Modifier.height(10.dp))
                    Box(
                        modifier = Modifier.fillMaxWidth()
                    ){
                        Button(onClick = {
                            checkInOutFormViewModel.assetID = assetID
                            //navController.navigate("check-in-out-form")
                            navController.popBackStack()
                            },
                            modifier = Modifier.align(Alignment.BottomCenter)) {
                            Text("Add Asset")
                        }
                    }
                }

            }
        }
    }
}

@Composable
fun FlightLogDetailsPopUp(
    popupTitle: String,
    fieldList: List<String>,
    navController: NavController,
    data: String?,
    listOfFlightLogs: List<FlightLog>
){

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route
    var height: Int = 520

    Popup(
        alignment = Alignment.TopCenter
    ){
        Box(modifier = Modifier
            .offset(y = 25.dp)
            .padding(20.dp)
            .width(600.dp)
            .height(600.dp)
            .border(width = 2.dp, color = Color.LightGray, shape = RectangleShape)
            .background(color = Color.White)
        ) {

            Column{
                Box(modifier = Modifier
                    .background(Color.LightGray)
                    .fillMaxWidth()
                    .offset(x = 10.dp)
                ){

                    //Close Button
                    Button(
                        onClick = { navController.popBackStack() },
                        modifier = Modifier
                            .align(Alignment.TopEnd)
                            .width(50.dp)
                            .offset(x = -15.dp)
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
                Box(modifier = Modifier.height(height.dp)){
                    LazyColumn(verticalArrangement = Arrangement.spacedBy(10.dp)){

                        itemsIndexed(fieldList){ i, field ->

                            Row(horizontalArrangement = Arrangement.spacedBy(65.dp)){

                                // name of field
                                Box(modifier = Modifier
                                    .width(120.dp)
                                    .height(40.dp)
                                    .offset(x = 10.dp)) {
                                    Text(field)
                                }

                                //value of field
                                for ( flightLog in listOfFlightLogs){

                                    if (flightLog.flightLogID == data){
                                        when (i){
                                            0 -> Text(flightLog.flightLogID)
                                            1 -> Text(flightLog.pilotID)
                                            2 -> Text(flightLog.pilotName)
                                            3 -> Text(flightLog.dateOfLog)
                                            4 -> Text(flightLog.success)
                                            5 -> Text(flightLog.totalTime)
                                            6 -> Text(flightLog.observerID)
                                            7 -> Text(flightLog.testMission)
                                            8 -> Text(flightLog.droneID)
                                            9 -> Text(flightLog.numOfLandings)
                                            10 -> Text(flightLog.numOfCycles)
                                            11 ->Text(flightLog.summary)
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}


@Composable
fun CheckOutLogDetailsPopUp(
    popupTitle: String,
    fieldList: List<String>,
    navController: NavController,
    data: String?,
    listOfCheckInOut: List<CheckInOutLog>
){
    var height: Int = 520

    Popup(
        alignment = Alignment.TopCenter
    ){
        Box(modifier = Modifier
            .offset(y = 25.dp)
            .padding(20.dp)
            .width(600.dp)
            .height(600.dp)
            .border(width = 2.dp, color = Color.LightGray, shape = RectangleShape)
            .background(color = Color.White)
        ) {

            Column{
                Box(modifier = Modifier
                    .background(Color.LightGray)
                    .fillMaxWidth()
                    .offset(x = 10.dp)
                ){

                    //Close Button
                    Button(
                        onClick = { navController.popBackStack() },
                        modifier = Modifier
                            .align(Alignment.TopEnd)
                            .width(50.dp)
                            .offset(x = -15.dp)
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
                Box(modifier = Modifier.height(height.dp)){
                    LazyColumn(verticalArrangement = Arrangement.spacedBy(10.dp)){

                        itemsIndexed(fieldList){ i, field ->

                            Row(horizontalArrangement = Arrangement.spacedBy(65.dp)){

                                // name of field
                                Box(modifier = Modifier
                                    .width(120.dp)
                                    .height(40.dp)
                                    .offset(x = 10.dp)) {
                                    Text(field)
                                }

                                //value of field
                                for (log in listOfCheckInOut){

                                    if (log.ID == data){
                                        when (i){
                                            0 -> Text(log.ID)
                                            1 -> Text(log.assetID)
                                            2 -> Text(log.employeeID)
                                            3 -> Text(log.employeeName)
                                            4 -> Text(log.checkOutDate)
                                            5 -> Text(log.checkInDate)
                                            6 -> Text(log.currentLocation)
                                            7 -> Text(log.description)
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}


@Composable
fun CheckInLogDetailsPopUp(
    popupTitle: String,
    fieldList: List<String>,
    navController: NavController,
    data: String?,
    listOfCheckInOut: List<CheckInOutLog>,

){
    var height: Int = 480

    val sdf = SimpleDateFormat("MM-dd-yyyy")
    val currentDate = sdf.format(Date())

    Popup(
        alignment = Alignment.TopCenter
    ){
        Box(modifier = Modifier
            .offset(y = 25.dp)
            .padding(20.dp)
            .width(600.dp)
            .height(600.dp)
            .border(width = 2.dp, color = Color.LightGray, shape = RectangleShape)
            .background(color = Color.White)
        ) {

            Column{
                Box(modifier = Modifier
                    .background(Color.LightGray)
                    .fillMaxWidth()
                    .offset(x = 10.dp)
                ){

                    //Close Button
                    Button(
                        onClick = { navController.popBackStack() },
                        modifier = Modifier
                            .align(Alignment.TopEnd)
                            .width(50.dp)
                            .offset(x = -15.dp)
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
                Box(modifier = Modifier.height(height.dp)){
                    LazyColumn(verticalArrangement = Arrangement.spacedBy(10.dp)){

                        itemsIndexed(fieldList){ i, field ->

                            Row(horizontalArrangement = Arrangement.spacedBy(65.dp)){

                                // name of field
                                Box(modifier = Modifier
                                    .width(120.dp)
                                    .height(40.dp)
                                    .offset(x = 10.dp)) {
                                    Text(field)
                                }

                                //value of field
                                for (log in listOfCheckInOut){
                                    if (log.ID == data){
                                        when (i){
                                            0 -> Text(log.ID)
                                            1 -> Text(log.assetID)
                                            2 -> Text(log.employeeID)
                                            3 -> Text(log.employeeName)
                                            4 -> Text(log.checkOutDate)
                                            5 -> Text(log.checkInDate)
                                            6 -> Text(log.currentLocation)
                                            7 -> Text(log.description)
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
                //Spacer(Modifier.height(10.dp))
                Box(
                    modifier = Modifier.fillMaxWidth()
                ){
                    Button(onClick = {
                        for (log in listOfCheckInOut) {
                            if (log.ID == data) {
                                log.checkInDate = currentDate
                                navController.popBackStack()
                            }
                        }

                    },
                        modifier = Modifier.align(Alignment.BottomCenter)) {
                        Text("Check In Asset")
                    }
                }
            }
        }
    }
}


@Composable
fun MaintenanceLogDetailsPopUp(
    popupTitle: String,
    fieldList: List<String>,
    navController: NavController,
    data: String?,
    listOfMaintenanceLog: List<MaintenanceLog>
){
    var height: Int = 520

    Popup(
        alignment = Alignment.TopCenter
    ){
        Box(modifier = Modifier
            .offset(y = 25.dp)
            .padding(20.dp)
            .width(600.dp)
            .height(600.dp)
            .border(width = 2.dp, color = Color.LightGray, shape = RectangleShape)
            .background(color = Color.White)
        ) {

            Column{
                Box(modifier = Modifier
                    .background(Color.LightGray)
                    .fillMaxWidth()
                    .offset(x = 10.dp)
                ){

                    //Close Button
                    Button(
                        onClick = { navController.popBackStack() },
                        modifier = Modifier
                            .align(Alignment.TopEnd)
                            .width(50.dp)
                            .offset(x = -15.dp)
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
                Box(modifier = Modifier.height(height.dp)){
                    LazyColumn(verticalArrangement = Arrangement.spacedBy(10.dp)){

                        itemsIndexed(fieldList){ i, field ->

                            Row(horizontalArrangement = Arrangement.spacedBy(65.dp)){

                                // name of field
                                Box(modifier = Modifier
                                    .width(120.dp)
                                    .height(40.dp)
                                    .offset(x = 10.dp)) {
                                    Text(field)
                                }

                                //value of field
                                for (log in listOfMaintenanceLog){

                                    if (log.ID == data){
                                        when (i){
                                            0 -> Text(log.ID)
                                            1 -> Text(log.assetID)
                                            2 -> Text(log.employeeID)
                                            3 -> Text(log.employeeName)
                                            4 -> Text(log.dateOfMaintenance)
                                            5 -> Text(log.typeOfMaintenane)
                                            6 -> Text(log.additionalDetails)
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}