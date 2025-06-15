package com.example.permissionapp

data class UIState (
    val showTimeInput :String   = "",
    val datePickerState :String = "",
    val driverName:String       = "",
    val driverSurName:String    = "",
    val driverHour:String       = "",
    val hourPrice:String        = "0",
    val totalPrice:String       = "",
    val driverTime:String       = "",
    val driverDate:String       = "",
    val recordCount : Int       = 0,
    val isInsert:Boolean        = false

)