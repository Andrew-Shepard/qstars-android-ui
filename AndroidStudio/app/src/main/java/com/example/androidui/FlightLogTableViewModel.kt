package com.example.androidui

import androidx.lifecycle.ViewModel

class FlightLog {
    var flightLogID: String = ""
    var pilotID: String = ""
    var pilotName: String = ""
    var dateOfLog: String = ""
    var success: String = ""
    var totalTime: String = ""
    var observerID: String = ""
    var testMission: String = ""
    var droneID: String = ""
    var numOfLandings: String = ""
    var numOfCycles: String = ""
    var summary: String = ""

    constructor(){}

    constructor(
        flightLogID: String,
        pilotID: String,
        pilotName: String,
        dateOfLog: String,
        success: String,
        totalTime: String,
        observerID: String,
        testMission: String,
        droneID: String,
        numOfLandings: String,
        numOfCycles: String,
        summary: String

    ) {
        this.flightLogID = flightLogID
        this.pilotID = pilotID
        this.pilotName = pilotName
        this.dateOfLog = dateOfLog
        this.success = success
        this.totalTime = totalTime
        this.observerID = observerID
        this.testMission = testMission
        this.droneID = droneID
        this.numOfLandings = numOfLandings
        this.numOfCycles = numOfCycles
        this.summary = summary

    }
}

class FlightLogTableViewModel: ViewModel(){
    var allFlightLogs: ArrayList<FlightLog> = arrayListOf(
        FlightLog("1", "p123", "John Doe", "03-04-2023","true", "3.5 hrs", "p34", "true", "1", "2", "2", "")
    )

    fun newFlightLog(
        flightLogID: String,
        pilotID: String,
        pilotName: String,
        dateOfLog: String,
        success: String,
        totalTime: String,
        observerID: String,
        testMission: String,
        droneID: String,
        numOfLandings: String,
        numOfCycles: String,
        summary: String
    ){

        allFlightLogs.add(
            FlightLog(
                flightLogID,
                pilotID,
                pilotName,
                dateOfLog,
                success,
                totalTime,
                observerID,
                testMission,
                droneID,
                numOfLandings,
                numOfCycles,
                summary
            )
        )
    }
}