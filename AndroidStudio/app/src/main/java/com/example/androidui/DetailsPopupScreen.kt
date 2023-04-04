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
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Popup
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState

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
fun DetailsPopUp(
    popupTitle: String,
    fieldList: List<String>,
    navController: NavController,
    data: String?,
    listOfAssets: List<Asset>,
    assetFormViewlModel: FormViewModel,
    addParentButton: Boolean = false,
    addChildButton: Boolean = false
){

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route
    var height: Int

    var assetName: String = ""

    Popup(
        alignment = Alignment.TopCenter
    ){
        if (addChildButton || addParentButton){
            height = 480
        }
        else{
            height = 520
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

                if (addChildButton){
                    Spacer(Modifier.height(10.dp))
                    Box(
                        modifier = Modifier.fillMaxWidth()
                    ){
                        Button(onClick = { },
                            modifier = Modifier.align(Alignment.BottomCenter)) {
                            Text("Add Child")
                        }
                    }
                }

                if (addParentButton){
                    Spacer(Modifier.height(10.dp))
                    Box(
                        modifier = Modifier.fillMaxWidth()
                    ){
                        Button(onClick = {
                            assetFormViewlModel.addParent(assetName)
                            navController.navigate("asset-form") },
                            modifier = Modifier.align(Alignment.BottomCenter)) {
                            Text("Add Parent")
                        }
                    }
                }

            }
        }
    }
}