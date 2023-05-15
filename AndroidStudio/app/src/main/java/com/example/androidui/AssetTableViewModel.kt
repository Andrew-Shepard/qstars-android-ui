package com.example.androidui

import androidx.compose.runtime.currentRecomposeScope
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

// This file contains the Asset class and the Asset Table View Model
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

    // Mock data for the sake of testing
    // Can be deleleted
    var allAssets: ArrayList<Asset> = arrayListOf(
        Asset("1", "Drone1", "Drone", "Active", arrayListOf("parent1", "parent2"), arrayListOf("child1", "child2"), "04-01-2023", "", "04-15-2023", "", "", ""),
        Asset("345", "Motor6", "Motor", "Checked Out", arrayListOf(""), arrayListOf(""), "03-25-2023", "", "", "", "", ""),
        Asset("335", "Motor7", "Motor", "Checked Out", arrayListOf(""), arrayListOf(""), "03-25-2023", "", "", "", "", ""),
        Asset("444", "Motor5", "Motor", "Checked Out", arrayListOf(""), arrayListOf(""), "02-24-2023", "", "", "", "", ""),
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
    }

    fun removeAsset(
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
        description: String) {
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