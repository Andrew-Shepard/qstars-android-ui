package com.example.androidui

import android.util.FloatMath
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
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState


@Composable
fun TableComponent(
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
    searchViewModel: SearchViewModel
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

    var listOfSearchFields by remember {
        mutableStateOf(false)
    }

    parentButton = currentRoute == "parent-table"
    childButton = currentRoute == "child-table"

    addAssetButton = currentRoute == "add-asset-table"

    var filterFieldsEmpty: Boolean = true

    filterFieldsEmpty = searchViewModel.userSearch.isEmpty() &&
            searchViewModel.nameSearch.isEmpty() &&
            searchViewModel.assetTypeSearch.isEmpty() &&
            searchViewModel.assetStatusSearch.isEmpty() &&
            searchViewModel.lastMaintenanceDate.isEmpty() &&
            searchViewModel.purchaseDate.isEmpty()

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

                assetIDMatch = searchViewModel.userSearch == asset.assetID
                nameMatch = searchViewModel.nameSearch == asset.assetName
                typeMatch = searchViewModel.assetTypeSearch == asset.assetType
                statusMatch = searchViewModel.assetStatusSearch == asset.assetStatus
                lastMaintenanceDateMatch = searchViewModel.lastMaintenanceDate == asset.lastMaintenanceDate
                purchaseDateMatch = searchViewModel.purchaseDate == asset.datePurchased

                // stores ID of row to pass it
                val assetID = asset.assetID

                if (assetIDMatch){

                    if (searchViewModel.nameSearch.isEmpty() &&
                            searchViewModel.assetTypeSearch.isEmpty() &&
                            searchViewModel.assetStatusSearch.isEmpty() &&
                            searchViewModel.lastMaintenanceDate.isEmpty() &&
                            searchViewModel.purchaseDate.isEmpty()){
                        TableComponent(
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


                    if(searchViewModel.nameSearch.isNotEmpty() && nameMatch
                    ){
                        TableComponent(
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


                    if (searchViewModel.assetTypeSearch.isNotEmpty() && typeMatch){
                        TableComponent(
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

                    if (searchViewModel.assetStatusSearch.isNotEmpty() && statusMatch){
                        TableComponent(
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

                    if (searchViewModel.lastMaintenanceDate.isNotEmpty() && lastMaintenanceDateMatch){
                        TableComponent(
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

                    if (searchViewModel.purchaseDate.isNotEmpty() && purchaseDateMatch) {
                        TableComponent(
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
                        searchViewModel.assetTypeSearch.isEmpty() &&
                        searchViewModel.assetStatusSearch.isEmpty() &&
                        searchViewModel.lastMaintenanceDate.isEmpty() &&
                        searchViewModel.purchaseDate.isEmpty()){
                        TableComponent(
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

                    if (searchViewModel.assetTypeSearch.isNotEmpty() && typeMatch){
                        TableComponent(
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

                    if (searchViewModel.assetStatusSearch.isNotEmpty() && statusMatch){
                        TableComponent(
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

                    if (searchViewModel.lastMaintenanceDate.isNotEmpty() && lastMaintenanceDateMatch){
                        TableComponent(
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

                    if (searchViewModel.purchaseDate.isNotEmpty() && purchaseDateMatch) {
                        TableComponent(
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

                    if (searchViewModel.nameSearch.isEmpty() &&
                        searchViewModel.assetTypeSearch.isEmpty() &&
                        searchViewModel.lastMaintenanceDate.isEmpty() &&
                        searchViewModel.purchaseDate.isEmpty()){
                        TableComponent(
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


                    if(searchViewModel.nameSearch.isNotEmpty() && nameMatch
                    ){
                        TableComponent(
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


                    if (searchViewModel.assetTypeSearch.isNotEmpty() && typeMatch){
                        TableComponent(
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

                    if (searchViewModel.lastMaintenanceDate.isNotEmpty() && lastMaintenanceDateMatch){
                        TableComponent(
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

                    if (searchViewModel.purchaseDate.isNotEmpty() && purchaseDateMatch) {
                        TableComponent(
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

                if (lastMaintenanceDateMatch && searchViewModel.lastMaintenanceDate.isNotEmpty()){

                    if (searchViewModel.nameSearch.isEmpty() &&
                        searchViewModel.assetTypeSearch.isEmpty() &&
                        searchViewModel.assetStatusSearch.isEmpty() &&
                        searchViewModel.purchaseDate.isEmpty()){
                        TableComponent(
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


                    if(searchViewModel.nameSearch.isNotEmpty() && nameMatch
                    ){
                        TableComponent(
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


                    if (searchViewModel.assetTypeSearch.isNotEmpty() && typeMatch){
                        TableComponent(
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

                    if (searchViewModel.assetStatusSearch.isNotEmpty() && statusMatch){
                        TableComponent(
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

                    if (searchViewModel.purchaseDate.isNotEmpty() && purchaseDateMatch) {
                        TableComponent(
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

                    if (searchViewModel.nameSearch.isEmpty() &&
                        searchViewModel.assetTypeSearch.isEmpty() &&
                        searchViewModel.assetStatusSearch.isEmpty() &&
                        searchViewModel.lastMaintenanceDate.isEmpty()){
                        TableComponent(
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


                    if(searchViewModel.nameSearch.isNotEmpty() && nameMatch
                    ){
                        TableComponent(
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


                    if (searchViewModel.assetTypeSearch.isNotEmpty() && typeMatch){
                        TableComponent(
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

                    if (searchViewModel.assetStatusSearch.isNotEmpty() && statusMatch){
                        TableComponent(
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

                    if (searchViewModel.lastMaintenanceDate.isNotEmpty() && lastMaintenanceDateMatch) {
                        TableComponent(
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


                /*
                if (nameMatch){

                    if (searchViewModel.nameSearch.isEmpty() &&
                        searchViewModel.assetTypeSearch.isEmpty() &&
                        searchViewModel.assetStatusSearch.isEmpty() &&
                        searchViewModel.lastMaintenanceDate.isEmpty() &&
                        searchViewModel.purchaseDate.isEmpty()){
                        TableComponent(
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


                    if(searchViewModel.nameSearch.isNotEmpty() && nameMatch
                    ){
                        TableComponent(
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


                    if (searchViewModel.assetTypeSearch.isNotEmpty() && typeMatch){
                        TableComponent(
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

                    if (searchViewModel.assetStatusSearch.isNotEmpty() && statusMatch){
                        TableComponent(
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

                    if (searchViewModel.lastMaintenanceDate.isNotEmpty() && lastMaintenanceDateMatch){
                        TableComponent(
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

                    if (searchViewModel.purchaseDate.isNotEmpty() && purchaseDateMatch) {
                        TableComponent(
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

                 */


                /*
                if (searchViewModel.nameSearch == asset.assetName && (
                            searchViewModel.userSearch == asset.assetID ||
                                    searchViewModel.assetTypeSearch == asset.assetType ||
                                    searchViewModel.assetStatusSearch == asset.assetStatus ||
                                    searchViewModel.lastMaintenanceDate == asset.lastMaintenanceDate ||
                                    searchViewModel.purchaseDate == asset.datePurchased
                            )){
                    TableComponent(
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

                if (searchViewModel.assetTypeSearch == asset.assetType && (
                            searchViewModel.nameSearch == asset.assetName ||
                                    searchViewModel.userSearch == asset.assetID ||
                                    searchViewModel.assetStatusSearch == asset.assetStatus ||
                                    searchViewModel.lastMaintenanceDate == asset.lastMaintenanceDate ||
                                    searchViewModel.purchaseDate == asset.datePurchased
                            )){
                    TableComponent(
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

                if (searchViewModel.assetStatusSearch == asset.assetStatus && (
                            searchViewModel.nameSearch == asset.assetName ||
                                    searchViewModel.assetTypeSearch == asset.assetType ||
                                    searchViewModel.userSearch == asset.assetID ||
                                    searchViewModel.lastMaintenanceDate == asset.lastMaintenanceDate ||
                                    searchViewModel.purchaseDate == asset.datePurchased
                            )) {
                    TableComponent(
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

                /*
                if (searchViewModel.lastMaintenanceDate == asset.lastMaintenanceDate){
                    TableComponent(
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

                 */

                if (searchViewModel.purchaseDate == asset.datePurchased && (
                            searchViewModel.nameSearch == asset.assetName ||
                                    searchViewModel.assetTypeSearch == asset.assetType ||
                                    searchViewModel.assetStatusSearch == asset.assetStatus ||
                                    searchViewModel.lastMaintenanceDate == asset.lastMaintenanceDate ||
                                    searchViewModel.userSearch == asset.assetID
                            )){
                    TableComponent(
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

                 */


                if (filterFieldsEmpty){
                    TableComponent(
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