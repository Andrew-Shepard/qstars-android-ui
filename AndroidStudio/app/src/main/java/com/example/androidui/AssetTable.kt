package com.example.androidui

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState

// Prints out the rows of the table and navigates to asset details popup on click
@Composable
fun AssetTableComponent(
    navController: NavController,
    assetID: String,
    parentButton: Boolean,
    childButton: Boolean,
    addAssetButton: Boolean,
    asset: Asset,
    column1Weight: Float,
    column2Weight: Float,
    column3Weight: Float,
    column4Weight: Float
){
    Row(modifier = Modifier
        .fillMaxWidth()
        .pointerInput(Unit)
        {
            detectTapGestures(
                onTap = {
                    //when asset clicked, show asset details popup
                    navController.navigate("details-popup" + "/$assetID" + "/$parentButton" + "/$childButton" + "/$addAssetButton")
                }
            )
        }
    ) {
        TableCell(text = asset.assetID, weight = column1Weight)
        TableCell(text = asset.assetName, weight = column2Weight)
        TableCell(text = asset.assetType, weight = column3Weight)
        TableCell(text = asset.assetStatus, weight = column4Weight)

    }
}


// sticky headers is an experimental feature
// creates a custom table
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun AssetTable(
    width: Int,
    height: Int,
    navController: NavController,
    assetTableViewModel: AssetTableViewModel,
    assetSearchViewModel: AssetSearchViewModel
) {

    val column1Weight = .3f
    val column2Weight = .3f
    val column3Weight = .2f
    val column4Weight = .2f

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    var parentButton by remember{mutableStateOf(false)}
    var childButton by remember{mutableStateOf(false)}
    var addAssetButton by remember{mutableStateOf(false)}

    parentButton = currentRoute == "parent-table"
    childButton = currentRoute == "child-table"

    addAssetButton = currentRoute == "add-asset-table"

    var filterFieldsEmpty: Boolean = true

    filterFieldsEmpty = assetSearchViewModel.userSearch.isEmpty() &&
            assetSearchViewModel.nameSearch.isEmpty() &&
            assetSearchViewModel.assetTypeSearch.isEmpty() &&
            assetSearchViewModel.assetStatusSearch.isEmpty() &&
            assetSearchViewModel.lastMaintenanceDate.isEmpty() &&
            assetSearchViewModel.purchaseDate.isEmpty()

    //variables that will check whether the user field input matches an asset
    var assetIDMatch:Boolean
    var nameMatch: Boolean
    var typeMatch: Boolean
    var statusMatch: Boolean
    var lastMaintenanceDateMatch: Boolean
    var purchaseDateMatch: Boolean



    Column()
    {
        // Table title
        Text(
            text = "Assets",
            fontSize = 25.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .offset(x = 15.dp)
                .offset(y = 10.dp)
        )

        //Contents of table
        LazyColumn(
            Modifier
                .size(width = width.dp, height = height.dp)
                .padding(15.dp)
                .border(2.dp, Color.Gray))
        {
            //Table Sticky Header
            stickyHeader {
                Row(Modifier.background(Color.Gray))
                {
                    TableCell(text = "Asset ID", weight = column1Weight)
                    TableCell(text = "Name", weight = column2Weight)
                    TableCell(text = "Asset Type", weight = column3Weight)
                    TableCell(text = "Status", weight = column4Weight)
                }
            }

            // The table data
            items(assetTableViewModel.allAssets) { asset ->

                assetIDMatch = assetSearchViewModel.userSearch == asset.assetID
                nameMatch = assetSearchViewModel.nameSearch == asset.assetName
                typeMatch = assetSearchViewModel.assetTypeSearch == asset.assetType
                statusMatch = assetSearchViewModel.assetStatusSearch == asset.assetStatus
                lastMaintenanceDateMatch = assetSearchViewModel.lastMaintenanceDate == asset.lastMaintenanceDate
                purchaseDateMatch = assetSearchViewModel.purchaseDate == asset.datePurchased

                // stores ID of row to pass it
                val assetID = asset.assetID

                if (assetIDMatch){

                    if (assetSearchViewModel.nameSearch.isEmpty() &&
                            assetSearchViewModel.assetTypeSearch.isEmpty() &&
                            assetSearchViewModel.assetStatusSearch.isEmpty() &&
                            assetSearchViewModel.lastMaintenanceDate.isEmpty() &&
                            assetSearchViewModel.purchaseDate.isEmpty()){
                        AssetTableComponent(
                            navController = navController,
                            assetID = assetID,
                            parentButton = parentButton,
                            childButton = childButton,
                            addAssetButton = addAssetButton,
                            asset = asset,
                            column1Weight = column1Weight ,
                            column2Weight = column2Weight,
                            column3Weight = column3Weight,
                            column4Weight = column4Weight
                        )
                    }


                    if(assetSearchViewModel.nameSearch.isNotEmpty() && nameMatch
                    ){
                        AssetTableComponent(
                            navController = navController,
                            assetID = assetID,
                            parentButton = parentButton,
                            childButton = childButton,
                            addAssetButton = addAssetButton,
                            asset = asset,
                            column1Weight = column1Weight ,
                            column2Weight = column2Weight,
                            column3Weight = column3Weight,
                            column4Weight = column4Weight
                        )
                    }


                    if (assetSearchViewModel.assetTypeSearch.isNotEmpty() && typeMatch){
                        AssetTableComponent(
                            navController = navController,
                            assetID = assetID,
                            parentButton = parentButton,
                            childButton = childButton,
                            addAssetButton = addAssetButton,
                            asset = asset,
                            column1Weight = column1Weight ,
                            column2Weight = column2Weight,
                            column3Weight = column3Weight,
                            column4Weight = column4Weight
                        )
                    }

                    if (assetSearchViewModel.assetStatusSearch.isNotEmpty() && statusMatch){
                        AssetTableComponent(
                            navController = navController,
                            assetID = assetID,
                            parentButton = parentButton,
                            childButton = childButton,
                            addAssetButton = addAssetButton,
                            asset = asset,
                            column1Weight = column1Weight ,
                            column2Weight = column2Weight,
                            column3Weight = column3Weight,
                            column4Weight = column4Weight
                        )
                    }

                    if (assetSearchViewModel.lastMaintenanceDate.isNotEmpty() && lastMaintenanceDateMatch){
                        AssetTableComponent(
                            navController = navController,
                            assetID = assetID,
                            parentButton = parentButton,
                            childButton = childButton,
                            addAssetButton = addAssetButton,
                            asset = asset,
                            column1Weight = column1Weight ,
                            column2Weight = column2Weight,
                            column3Weight = column3Weight,
                            column4Weight = column4Weight
                        )
                    }

                    if (assetSearchViewModel.purchaseDate.isNotEmpty() && purchaseDateMatch) {
                        AssetTableComponent(
                            navController = navController,
                            assetID = assetID,
                            parentButton = parentButton,
                            childButton = childButton,
                            addAssetButton = addAssetButton,
                            asset = asset,
                            column1Weight = column1Weight,
                            column2Weight = column2Weight,
                            column3Weight = column3Weight,
                            column4Weight = column4Weight
                        )
                    }

                }

                if (nameMatch){

                    if (
                        assetSearchViewModel.assetTypeSearch.isEmpty() &&
                        assetSearchViewModel.assetStatusSearch.isEmpty() &&
                        assetSearchViewModel.lastMaintenanceDate.isEmpty() &&
                        assetSearchViewModel.purchaseDate.isEmpty()){
                        AssetTableComponent(
                            navController = navController,
                            assetID = assetID,
                            parentButton = parentButton,
                            childButton = childButton,
                            addAssetButton = addAssetButton,
                            asset = asset,
                            column1Weight = column1Weight ,
                            column2Weight = column2Weight,
                            column3Weight = column3Weight,
                            column4Weight = column4Weight
                        )
                    }

                    if (assetSearchViewModel.assetTypeSearch.isNotEmpty() && typeMatch){
                        AssetTableComponent(
                            navController = navController,
                            assetID = assetID,
                            parentButton = parentButton,
                            childButton = childButton,
                            addAssetButton = addAssetButton,
                            asset = asset,
                            column1Weight = column1Weight ,
                            column2Weight = column2Weight,
                            column3Weight = column3Weight,
                            column4Weight = column4Weight
                        )
                    }

                    if (assetSearchViewModel.assetStatusSearch.isNotEmpty() && statusMatch){
                        AssetTableComponent(
                            navController = navController,
                            assetID = assetID,
                            parentButton = parentButton,
                            childButton = childButton,
                            addAssetButton = addAssetButton,
                            asset = asset,
                            column1Weight = column1Weight ,
                            column2Weight = column2Weight,
                            column3Weight = column3Weight,
                            column4Weight = column4Weight
                        )
                    }

                    if (assetSearchViewModel.lastMaintenanceDate.isNotEmpty() && lastMaintenanceDateMatch){
                        AssetTableComponent(
                            navController = navController,
                            assetID = assetID,
                            parentButton = parentButton,
                            childButton = childButton,
                            addAssetButton = addAssetButton,
                            asset = asset,
                            column1Weight = column1Weight ,
                            column2Weight = column2Weight,
                            column3Weight = column3Weight,
                            column4Weight = column4Weight
                        )
                    }

                    if (assetSearchViewModel.purchaseDate.isNotEmpty() && purchaseDateMatch) {
                        AssetTableComponent(
                            navController = navController,
                            assetID = assetID,
                            parentButton = parentButton,
                            childButton = childButton,
                            addAssetButton = addAssetButton,
                            asset = asset,
                            column1Weight = column1Weight,
                            column2Weight = column2Weight,
                            column3Weight = column3Weight,
                            column4Weight = column4Weight
                        )
                    }
                }

                if (statusMatch){

                    if (assetSearchViewModel.nameSearch.isEmpty() &&
                        assetSearchViewModel.assetTypeSearch.isEmpty() &&
                        assetSearchViewModel.lastMaintenanceDate.isEmpty() &&
                        assetSearchViewModel.purchaseDate.isEmpty()){
                        AssetTableComponent(
                            navController = navController,
                            assetID = assetID,
                            parentButton = parentButton,
                            childButton = childButton,
                            addAssetButton = addAssetButton,
                            asset = asset,
                            column1Weight = column1Weight ,
                            column2Weight = column2Weight,
                            column3Weight = column3Weight,
                            column4Weight = column4Weight
                        )
                    }


                    if(assetSearchViewModel.nameSearch.isNotEmpty() && nameMatch
                    ){
                        AssetTableComponent(
                            navController = navController,
                            assetID = assetID,
                            parentButton = parentButton,
                            childButton = childButton,
                            addAssetButton = addAssetButton,
                            asset = asset,
                            column1Weight = column1Weight ,
                            column2Weight = column2Weight,
                            column3Weight = column3Weight,
                            column4Weight = column4Weight
                        )
                    }


                    if (assetSearchViewModel.assetTypeSearch.isNotEmpty() && typeMatch){
                        AssetTableComponent(
                            navController = navController,
                            assetID = assetID,
                            parentButton = parentButton,
                            childButton = childButton,
                            addAssetButton = addAssetButton,
                            asset = asset,
                            column1Weight = column1Weight ,
                            column2Weight = column2Weight,
                            column3Weight = column3Weight,
                            column4Weight = column4Weight
                        )
                    }

                    if (assetSearchViewModel.lastMaintenanceDate.isNotEmpty() && lastMaintenanceDateMatch){
                        AssetTableComponent(
                            navController = navController,
                            assetID = assetID,
                            parentButton = parentButton,
                            childButton = childButton,
                            addAssetButton = addAssetButton,
                            asset = asset,
                            column1Weight = column1Weight ,
                            column2Weight = column2Weight,
                            column3Weight = column3Weight,
                            column4Weight = column4Weight
                        )
                    }

                    if (assetSearchViewModel.purchaseDate.isNotEmpty() && purchaseDateMatch) {
                        AssetTableComponent(
                            navController = navController,
                            assetID = assetID,
                            parentButton = parentButton,
                            childButton = childButton,
                            addAssetButton = addAssetButton,
                            asset = asset,
                            column1Weight = column1Weight,
                            column2Weight = column2Weight,
                            column3Weight = column3Weight,
                            column4Weight = column4Weight
                        )
                    }

                }

                if (lastMaintenanceDateMatch && assetSearchViewModel.lastMaintenanceDate.isNotEmpty()){

                    if (assetSearchViewModel.nameSearch.isEmpty() &&
                        assetSearchViewModel.assetTypeSearch.isEmpty() &&
                        assetSearchViewModel.assetStatusSearch.isEmpty() &&
                        assetSearchViewModel.purchaseDate.isEmpty()){
                        AssetTableComponent(
                            navController = navController,
                            assetID = assetID,
                            parentButton = parentButton,
                            childButton = childButton,
                            addAssetButton = addAssetButton,
                            asset = asset,
                            column1Weight = column1Weight ,
                            column2Weight = column2Weight,
                            column3Weight = column3Weight,
                            column4Weight = column4Weight
                        )
                    }


                    if(assetSearchViewModel.nameSearch.isNotEmpty() && nameMatch
                    ){
                        AssetTableComponent(
                            navController = navController,
                            assetID = assetID,
                            parentButton = parentButton,
                            childButton = childButton,
                            addAssetButton = addAssetButton,
                            asset = asset,
                            column1Weight = column1Weight ,
                            column2Weight = column2Weight,
                            column3Weight = column3Weight,
                            column4Weight = column4Weight
                        )
                    }


                    if (assetSearchViewModel.assetTypeSearch.isNotEmpty() && typeMatch){
                        AssetTableComponent(
                            navController = navController,
                            assetID = assetID,
                            parentButton = parentButton,
                            childButton = childButton,
                            addAssetButton = addAssetButton,
                            asset = asset,
                            column1Weight = column1Weight ,
                            column2Weight = column2Weight,
                            column3Weight = column3Weight,
                            column4Weight = column4Weight
                        )
                    }

                    if (assetSearchViewModel.assetStatusSearch.isNotEmpty() && statusMatch){
                        AssetTableComponent(
                            navController = navController,
                            assetID = assetID,
                            parentButton = parentButton,
                            childButton = childButton,
                            addAssetButton = addAssetButton,
                            asset = asset,
                            column1Weight = column1Weight ,
                            column2Weight = column2Weight,
                            column3Weight = column3Weight,
                            column4Weight = column4Weight
                        )
                    }

                    if (assetSearchViewModel.purchaseDate.isNotEmpty() && purchaseDateMatch) {
                        AssetTableComponent(
                            navController = navController,
                            assetID = assetID,
                            parentButton = parentButton,
                            childButton = childButton,
                            addAssetButton = addAssetButton,
                            asset = asset,
                            column1Weight = column1Weight,
                            column2Weight = column2Weight,
                            column3Weight = column3Weight,
                            column4Weight = column4Weight
                        )
                    }

                }


                if (purchaseDateMatch){

                    if (assetSearchViewModel.nameSearch.isEmpty() &&
                        assetSearchViewModel.assetTypeSearch.isEmpty() &&
                        assetSearchViewModel.assetStatusSearch.isEmpty() &&
                        assetSearchViewModel.lastMaintenanceDate.isEmpty()){
                        AssetTableComponent(
                            navController = navController,
                            assetID = assetID,
                            parentButton = parentButton,
                            childButton = childButton,
                            addAssetButton = addAssetButton,
                            asset = asset,
                            column1Weight = column1Weight ,
                            column2Weight = column2Weight,
                            column3Weight = column3Weight,
                            column4Weight = column4Weight
                        )
                    }


                    if(assetSearchViewModel.nameSearch.isNotEmpty() && nameMatch
                    ){
                        AssetTableComponent(
                            navController = navController,
                            assetID = assetID,
                            parentButton = parentButton,
                            childButton = childButton,
                            addAssetButton = addAssetButton,
                            asset = asset,
                            column1Weight = column1Weight ,
                            column2Weight = column2Weight,
                            column3Weight = column3Weight,
                            column4Weight = column4Weight
                        )
                    }


                    if (assetSearchViewModel.assetTypeSearch.isNotEmpty() && typeMatch){
                        AssetTableComponent(
                            navController = navController,
                            assetID = assetID,
                            parentButton = parentButton,
                            childButton = childButton,
                            addAssetButton = addAssetButton,
                            asset = asset,
                            column1Weight = column1Weight ,
                            column2Weight = column2Weight,
                            column3Weight = column3Weight,
                            column4Weight = column4Weight
                        )
                    }

                    if (assetSearchViewModel.assetStatusSearch.isNotEmpty() && statusMatch){
                        AssetTableComponent(
                            navController = navController,
                            assetID = assetID,
                            parentButton = parentButton,
                            childButton = childButton,
                            addAssetButton = addAssetButton,
                            asset = asset,
                            column1Weight = column1Weight ,
                            column2Weight = column2Weight,
                            column3Weight = column3Weight,
                            column4Weight = column4Weight
                        )
                    }

                    if (assetSearchViewModel.lastMaintenanceDate.isNotEmpty() && lastMaintenanceDateMatch) {
                        AssetTableComponent(
                            navController = navController,
                            assetID = assetID,
                            parentButton = parentButton,
                            childButton = childButton,
                            addAssetButton = addAssetButton,
                            asset = asset,
                            column1Weight = column1Weight,
                            column2Weight = column2Weight,
                            column3Weight = column3Weight,
                            column4Weight = column4Weight
                        )
                    }
                }

                if (filterFieldsEmpty){
                    AssetTableComponent(
                        navController = navController,
                        assetID = assetID,
                        parentButton = parentButton,
                        childButton = childButton,
                        addAssetButton = addAssetButton,
                        asset = asset,
                        column1Weight = column1Weight ,
                        column2Weight = column2Weight,
                        column3Weight = column3Weight,
                        column4Weight = column4Weight
                    )
                }
            }
        }
    }
}