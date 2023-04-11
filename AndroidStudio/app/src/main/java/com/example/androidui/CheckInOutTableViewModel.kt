package com.example.androidui

import androidx.lifecycle.ViewModel


class CheckInOutLog {
    var ID: String = ""
    var assetID: String = ""
    var employeeID: String = ""
    var employeeName: String = ""
    var checkOutDate: String = ""
    var checkInDate: String = ""
    var currentLocation: String = ""
    var description: String = ""

    constructor()

    constructor(
        ID: String,
        assetID: String,
        employeeID: String,
        employeeName: String,
        checkOutDate: String,
        checkInDate: String,
        currentLocation: String,
        description: String
    ){
        this.ID = ID
        this.assetID = assetID
        this.employeeID = employeeID
        this.employeeName = employeeName
        this.checkOutDate = checkOutDate
        this.checkInDate = checkInDate
        this.currentLocation = currentLocation
        this.description = description
    }
}

class CheckInOutTableViewModel: ViewModel() {

    var allCheckInOutLogs : ArrayList<CheckInOutLog> = arrayListOf(
        CheckInOutLog("1","23","e4","John Doe","02-04-2002","","warehouse","for mission"),
        CheckInOutLog("2","22","e4","John Doe","02-04-2002","03-03-2002","warehouse","for mission")
    )


    fun newCheckInOutLog(
        ID: String,
        assetID: String,
        employeeID: String,
        employeeName: String,
        checkOutDate: String,
        checkInDate: String,
        currentLocation: String,
        description: String
    ){
        allCheckInOutLogs.add(
            CheckInOutLog(
                ID,
                assetID,
                employeeID,
                employeeName,
                checkOutDate,
                checkInDate,
                currentLocation,
                description
            )
        )
    }

}