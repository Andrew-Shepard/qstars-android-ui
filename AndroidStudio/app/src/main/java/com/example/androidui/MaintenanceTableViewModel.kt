package com.example.androidui

import androidx.lifecycle.ViewModel

class MaintenanceLog {
    var ID: String = ""
    var assetID: String = ""
    var employeeID: String = ""
    var employeeName: String = ""
    var dateOfMaintenance: String = ""
    var typeOfMaintenane: String = ""
    var additionalDetails: String = ""
    constructor()

    constructor(
        ID: String,
        assetID: String,
        employeeID: String,
        employeeName: String,
        dateOfMaintenance: String,
         typeOfMaintenane: String,
        additionalDetails: String,
    ){
        this.ID = ID
        this.assetID = assetID
        this.employeeID = employeeID
        this.employeeName = employeeName
        this.dateOfMaintenance = dateOfMaintenance
        this.typeOfMaintenane = typeOfMaintenane
        this.additionalDetails = additionalDetails
    }
}

class MaintenanceTableViewModel: ViewModel(){
    var allMaintenanceLogs: ArrayList<MaintenanceLog> = arrayListOf()

    fun newMaintenanceLog(
        ID: String,
        assetID: String,
        employeeID: String,
        employeeName: String,
        dateOfMaintenance: String,
        typeOfMaintenane: String,
        additionalDetails: String,
    ){
        allMaintenanceLogs.add(
            MaintenanceLog(
                ID,
                assetID,
                employeeID,
                employeeName,
                dateOfMaintenance,
                typeOfMaintenane,
                additionalDetails
            )
        )
    }

}
