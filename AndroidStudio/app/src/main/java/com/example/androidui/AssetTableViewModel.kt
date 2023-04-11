package com.example.androidui

import androidx.compose.runtime.currentRecomposeScope
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class Asset {
    var assetID : String = ""
    var assetName: String = ""
    var assetType: String= ""
    var assetStatus: String = ""
    var parents: ArrayList<String> = arrayListOf("")
    var children: ArrayList<String> = arrayListOf("")
    var datePurchased: String = ""
    var totalHoursUsed: String = ""
    var lastMaintenanceDate: String = ""
    var lastCheckOut: String = ""
    var currentLocation: String = ""
    var description: String = ""
    constructor() {}

    constructor(
        assetID : String,
        assetName: String,
        assetType: String,
        assetStatus: String,
        parents: ArrayList<String>,
        children: ArrayList<String>,
        datePurchased: String,
        totalHoursUsed: String = "",
        lastMaintenanceDate: String = "",
        lastCheckOut: String = "",
        currentLocation: String,
        description: String
    ) {
        this.assetID = assetID
        this.assetName = assetName
        this.assetType = assetType
        this.assetStatus = assetStatus
        this.parents = parents
        this.children = children
        this.datePurchased = datePurchased
        this.totalHoursUsed = totalHoursUsed
        this.lastMaintenanceDate = lastMaintenanceDate
        this.lastCheckOut = lastCheckOut
        this.currentLocation = currentLocation
        this.description = description
    }
}

class AssetTableViewModel : ViewModel() {
    var allAssets: ArrayList<Asset> = arrayListOf(
        Asset("123", "Motor5", "Motor", "Checked Out", arrayListOf("parent1", "parent2", "parent3", "parent4", "parent5"), arrayListOf("child1", "child2"), "", "", "", "", "", ""),
        Asset("345", "Motor5", "Motor", "Checked Out", arrayListOf(""), arrayListOf(""), "", "", "", "", "", "")
    )

    fun newAsset(
        assetID : String,
        assetName: String,
        assetType: String,
        assetStatus: String,
        parents: ArrayList<String>,
        children: ArrayList<String>,
        datePurchased: String,
        totalHoursUsed: String = "",
        lastMaintenanceDate: String = "",
        lastCheckOut: String = "",
        currentLocation: String,
        description: String){

        allAssets.add(
            Asset(
                assetID,
                assetName,
                assetType,
                assetStatus,
                parents,
                children,
                datePurchased,
                totalHoursUsed,
                lastMaintenanceDate,
                lastCheckOut,
                currentLocation,
                description
            )
        )

        allAssets.remove(
            Asset(
                assetID,
                assetName,
                assetType,
                assetStatus,
                parents,
                children,
                datePurchased,
                totalHoursUsed,
                lastMaintenanceDate,
                lastCheckOut,
                currentLocation,
                description
            )
        )
    }
}