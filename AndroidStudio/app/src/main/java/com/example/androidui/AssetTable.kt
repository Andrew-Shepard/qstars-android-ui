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

// This file contains that asset table component and asset table composable functions


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
                    //when asset clicked, navigate to asset details popup
                    navController.navigate("details-popup" + "/$assetID" + "/$parentButton" + "/$childButton" + "/$addAssetButton")
                }
            )
        }
    ) {
        // controls the asset column and the type of data
        TableCell(text = asset.assetID, weight = column1Weight)
        TableCell(text = asset.assetName, weight = column2Weight)
        TableCell(text = asset.assetType, weight = column3Weight)
        TableCell(text = asset.assetStatus, weight = column4Weight)

    }
}


// sticky headers is an experimental feature
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun AssetTable(
    width: Int,
    height: Int,
    navController: NavController,
    assetTableViewModel: AssetTableViewModel,
    assetSearchViewModel: AssetSearchViewModel
) {

    // holds the previous navigation route
    val previousRoute = navController.previousBackStackEntry?.destination?.route

    // weights of columns
    val column1Weight = .3f
    val column2Weight = .3f
    val column3Weight = .2f
    val column4Weight = .2f

    // variables that determine whether to show a button
    var showParentButton = false
    var showChildButton = false
    var showAddAssetButton = false

    // logic that determines whether to show Add Parent/Child/Asset Button based off previous route
    when (previousRoute){
        "parent-table" -> showParentButton = true

        "child-table" -> showChildButton = true

        "add-asset-table" -> showAddAssetButton = true

        else -> {
            showParentButton = false
            showChildButton = false
            showAddAssetButton = false
        }
    }

    // variable that determines if the filter fields are empty
    var filterFieldsEmpty = assetSearchViewModel.assetIDSearch.isEmpty() &&
            assetSearchViewModel.assetNameSearch.isEmpty() &&
            assetSearchViewModel.assetTypeSearch.isEmpty() &&
            assetSearchViewModel.assetStatusSearch.isEmpty() &&
            assetSearchViewModel.lastMaintenanceDateSearch.isEmpty() &&
            assetSearchViewModel.purchaseDateSearch.isEmpty()

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
            // Go through all assets
            items(assetTableViewModel.allAssets) { asset ->

                assetIDMatch = assetSearchViewModel.assetIDSearch == asset.assetID
                nameMatch = assetSearchViewModel.assetNameSearch == asset.assetName
                typeMatch = assetSearchViewModel.assetTypeSearch == asset.assetType
                statusMatch = assetSearchViewModel.assetStatusSearch == asset.assetStatus
                lastMaintenanceDateMatch = assetSearchViewModel.lastMaintenanceDateSearch == asset.lastMaintenanceDate
                purchaseDateMatch = assetSearchViewModel.purchaseDateSearch == asset.datePurchased

                // stores ID of row to pass it
                val assetID = asset.assetID

                // search by asset ID
                if (assetIDMatch){

                    if (assetSearchViewModel.assetNameSearch.isEmpty() &&
                            assetSearchViewModel.assetTypeSearch.isEmpty() &&
                            assetSearchViewModel.assetStatusSearch.isEmpty() &&
                            assetSearchViewModel.lastMaintenanceDateSearch.isEmpty() &&
                            assetSearchViewModel.purchaseDateSearch.isEmpty()){
                        AssetTableComponent(
                            navController = navController,
                            assetID = assetID,
                            parentButton = showParentButton,
                            childButton = showChildButton,
                            addAssetButton = showAddAssetButton,
                            asset = asset,
                            column1Weight = column1Weight ,
                            column2Weight = column2Weight,
                            column3Weight = column3Weight,
                            column4Weight = column4Weight
                        )
                    }
                }

                // search by asset name and any filters
                if (nameMatch){

                    // search by just asset name
                    if (
                        assetSearchViewModel.assetTypeSearch.isEmpty() &&
                        assetSearchViewModel.assetStatusSearch.isEmpty() &&
                        assetSearchViewModel.lastMaintenanceDateSearch.isEmpty() &&
                        assetSearchViewModel.purchaseDateSearch.isEmpty()){
                        AssetTableComponent(
                            navController = navController,
                            assetID = assetID,
                            parentButton = showParentButton,
                            childButton = showChildButton,
                            addAssetButton = showAddAssetButton,
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
                            parentButton = showParentButton,
                            childButton = showChildButton,
                            addAssetButton = showAddAssetButton,
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
                            parentButton = showParentButton,
                            childButton = showChildButton,
                            addAssetButton = showAddAssetButton,
                            asset = asset,
                            column1Weight = column1Weight ,
                            column2Weight = column2Weight,
                            column3Weight = column3Weight,
                            column4Weight = column4Weight
                        )
                    }

                    if (assetSearchViewModel.lastMaintenanceDateSearch.isNotEmpty() && lastMaintenanceDateMatch){
                        AssetTableComponent(
                            navController = navController,
                            assetID = assetID,
                            parentButton = showParentButton,
                            childButton = showChildButton,
                            addAssetButton = showAddAssetButton,
                            asset = asset,
                            column1Weight = column1Weight ,
                            column2Weight = column2Weight,
                            column3Weight = column3Weight,
                            column4Weight = column4Weight
                        )
                    }

                    if (assetSearchViewModel.purchaseDateSearch.isNotEmpty() && purchaseDateMatch) {
                        AssetTableComponent(
                            navController = navController,
                            assetID = assetID,
                            parentButton = showParentButton,
                            childButton = showChildButton,
                            addAssetButton = showAddAssetButton,
                            asset = asset,
                            column1Weight = column1Weight,
                            column2Weight = column2Weight,
                            column3Weight = column3Weight,
                            column4Weight = column4Weight
                        )
                    }
                }

                // search by asset type
                if (typeMatch){

                    // search by just asset type
                    if (
                        assetSearchViewModel.assetNameSearch.isEmpty() &&
                        assetSearchViewModel.assetStatusSearch.isEmpty() &&
                        assetSearchViewModel.lastMaintenanceDateSearch.isEmpty() &&
                        assetSearchViewModel.purchaseDateSearch.isEmpty()){
                        AssetTableComponent(
                            navController = navController,
                            assetID = assetID,
                            parentButton = showParentButton,
                            childButton = showChildButton,
                            addAssetButton = showAddAssetButton,
                            asset = asset,
                            column1Weight = column1Weight ,
                            column2Weight = column2Weight,
                            column3Weight = column3Weight,
                            column4Weight = column4Weight
                        )
                    }

                    if (assetSearchViewModel.assetNameSearch.isNotEmpty() && nameMatch){
                        AssetTableComponent(
                            navController = navController,
                            assetID = assetID,
                            parentButton = showParentButton,
                            childButton = showChildButton,
                            addAssetButton = showAddAssetButton,
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
                            parentButton = showParentButton,
                            childButton = showChildButton,
                            addAssetButton = showAddAssetButton,
                            asset = asset,
                            column1Weight = column1Weight ,
                            column2Weight = column2Weight,
                            column3Weight = column3Weight,
                            column4Weight = column4Weight
                        )
                    }

                    if (assetSearchViewModel.lastMaintenanceDateSearch.isNotEmpty() && lastMaintenanceDateMatch){
                        AssetTableComponent(
                            navController = navController,
                            assetID = assetID,
                            parentButton = showParentButton,
                            childButton = showChildButton,
                            addAssetButton = showAddAssetButton,
                            asset = asset,
                            column1Weight = column1Weight ,
                            column2Weight = column2Weight,
                            column3Weight = column3Weight,
                            column4Weight = column4Weight
                        )
                    }

                    if (assetSearchViewModel.purchaseDateSearch.isNotEmpty() && purchaseDateMatch) {
                        AssetTableComponent(
                            navController = navController,
                            assetID = assetID,
                            parentButton = showParentButton,
                            childButton = showChildButton,
                            addAssetButton = showAddAssetButton,
                            asset = asset,
                            column1Weight = column1Weight,
                            column2Weight = column2Weight,
                            column3Weight = column3Weight,
                            column4Weight = column4Weight
                        )
                    }
                }

                // search by asset status
                if (statusMatch){

                    // search by just asset status
                    if (assetSearchViewModel.assetNameSearch.isEmpty() &&
                        assetSearchViewModel.assetTypeSearch.isEmpty() &&
                        assetSearchViewModel.lastMaintenanceDateSearch.isEmpty() &&
                        assetSearchViewModel.purchaseDateSearch.isEmpty()){
                        AssetTableComponent(
                            navController = navController,
                            assetID = assetID,
                            parentButton = showParentButton,
                            childButton = showChildButton,
                            addAssetButton = showAddAssetButton,
                            asset = asset,
                            column1Weight = column1Weight ,
                            column2Weight = column2Weight,
                            column3Weight = column3Weight,
                            column4Weight = column4Weight
                        )
                    }

                    if(assetSearchViewModel.assetNameSearch.isNotEmpty() && nameMatch
                    ){
                        AssetTableComponent(
                            navController = navController,
                            assetID = assetID,
                            parentButton = showParentButton,
                            childButton = showChildButton,
                            addAssetButton = showAddAssetButton,
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
                            parentButton = showParentButton,
                            childButton = showChildButton,
                            addAssetButton = showAddAssetButton,
                            asset = asset,
                            column1Weight = column1Weight ,
                            column2Weight = column2Weight,
                            column3Weight = column3Weight,
                            column4Weight = column4Weight
                        )
                    }

                    if (assetSearchViewModel.lastMaintenanceDateSearch.isNotEmpty() && lastMaintenanceDateMatch){
                        AssetTableComponent(
                            navController = navController,
                            assetID = assetID,
                            parentButton = showParentButton,
                            childButton = showChildButton,
                            addAssetButton = showAddAssetButton,
                            asset = asset,
                            column1Weight = column1Weight ,
                            column2Weight = column2Weight,
                            column3Weight = column3Weight,
                            column4Weight = column4Weight
                        )
                    }

                    if (assetSearchViewModel.purchaseDateSearch.isNotEmpty() && purchaseDateMatch) {
                        AssetTableComponent(
                            navController = navController,
                            assetID = assetID,
                            parentButton = showParentButton,
                            childButton = showChildButton,
                            addAssetButton = showAddAssetButton,
                            asset = asset,
                            column1Weight = column1Weight,
                            column2Weight = column2Weight,
                            column3Weight = column3Weight,
                            column4Weight = column4Weight
                        )
                    }

                }

                // search by last maintenance date
                if (lastMaintenanceDateMatch && assetSearchViewModel.lastMaintenanceDateSearch.isNotEmpty()){

                    // search by just last maintenance date
                    if (assetSearchViewModel.assetNameSearch.isEmpty() &&
                        assetSearchViewModel.assetTypeSearch.isEmpty() &&
                        assetSearchViewModel.assetStatusSearch.isEmpty() &&
                        assetSearchViewModel.purchaseDateSearch.isEmpty()){
                        AssetTableComponent(
                            navController = navController,
                            assetID = assetID,
                            parentButton = showParentButton,
                            childButton = showChildButton,
                            addAssetButton = showAddAssetButton,
                            asset = asset,
                            column1Weight = column1Weight ,
                            column2Weight = column2Weight,
                            column3Weight = column3Weight,
                            column4Weight = column4Weight
                        )
                    }

                    if(assetSearchViewModel.assetNameSearch.isNotEmpty() && nameMatch
                    ){
                        AssetTableComponent(
                            navController = navController,
                            assetID = assetID,
                            parentButton = showParentButton,
                            childButton = showChildButton,
                            addAssetButton = showAddAssetButton,
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
                            parentButton = showParentButton,
                            childButton = showChildButton,
                            addAssetButton = showAddAssetButton,
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
                            parentButton = showParentButton,
                            childButton = showChildButton,
                            addAssetButton = showAddAssetButton,
                            asset = asset,
                            column1Weight = column1Weight ,
                            column2Weight = column2Weight,
                            column3Weight = column3Weight,
                            column4Weight = column4Weight
                        )
                    }

                    if (assetSearchViewModel.purchaseDateSearch.isNotEmpty() && purchaseDateMatch) {
                        AssetTableComponent(
                            navController = navController,
                            assetID = assetID,
                            parentButton = showParentButton,
                            childButton = showChildButton,
                            addAssetButton = showAddAssetButton,
                            asset = asset,
                            column1Weight = column1Weight,
                            column2Weight = column2Weight,
                            column3Weight = column3Weight,
                            column4Weight = column4Weight
                        )
                    }

                }

                // search by purchase date
                if (purchaseDateMatch){

                    // search by just the purchase date
                    if (assetSearchViewModel.assetNameSearch.isEmpty() &&
                        assetSearchViewModel.assetTypeSearch.isEmpty() &&
                        assetSearchViewModel.assetStatusSearch.isEmpty() &&
                        assetSearchViewModel.lastMaintenanceDateSearch.isEmpty()){
                        AssetTableComponent(
                            navController = navController,
                            assetID = assetID,
                            parentButton = showParentButton,
                            childButton = showChildButton,
                            addAssetButton = showAddAssetButton,
                            asset = asset,
                            column1Weight = column1Weight ,
                            column2Weight = column2Weight,
                            column3Weight = column3Weight,
                            column4Weight = column4Weight
                        )
                    }


                    if(assetSearchViewModel.assetNameSearch.isNotEmpty() && nameMatch
                    ){
                        AssetTableComponent(
                            navController = navController,
                            assetID = assetID,
                            parentButton = showParentButton,
                            childButton = showChildButton,
                            addAssetButton = showAddAssetButton,
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
                            parentButton = showParentButton,
                            childButton = showChildButton,
                            addAssetButton = showAddAssetButton,
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
                            parentButton = showParentButton,
                            childButton = showChildButton,
                            addAssetButton = showAddAssetButton,
                            asset = asset,
                            column1Weight = column1Weight ,
                            column2Weight = column2Weight,
                            column3Weight = column3Weight,
                            column4Weight = column4Weight
                        )
                    }

                    if (assetSearchViewModel.lastMaintenanceDateSearch.isNotEmpty() && lastMaintenanceDateMatch) {
                        AssetTableComponent(
                            navController = navController,
                            assetID = assetID,
                            parentButton = showParentButton,
                            childButton = showChildButton,
                            addAssetButton = showAddAssetButton,
                            asset = asset,
                            column1Weight = column1Weight,
                            column2Weight = column2Weight,
                            column3Weight = column3Weight,
                            column4Weight = column4Weight
                        )
                    }
                }

                // show all assets when the filter fields are empty
                if (filterFieldsEmpty){

                    Row(modifier = Modifier
                        .fillMaxWidth()
                        .pointerInput(Unit)
                        {
                            detectTapGestures(
                                onTap = {
                                    //when asset clicked, show asset details popup
                                    navController.navigate("details-popup" + "/$assetID" + "/$showParentButton" + "/$showChildButton" + "/$showAddAssetButton")
                                }
                            )
                        }
                    ) {
                        // controls the asset column and the type of data
                        TableCell(text = asset.assetID, weight = column1Weight)
                        TableCell(text = asset.assetName, weight = column2Weight)
                        TableCell(text = asset.assetType, weight = column3Weight)
                        TableCell(text = asset.assetStatus, weight = column4Weight)

                    }
                }
            }
        }
    }
}