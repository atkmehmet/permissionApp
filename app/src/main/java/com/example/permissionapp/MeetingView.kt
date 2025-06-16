package com.example.permissionapp

import android.widget.Toast
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MeetingView(private val dao: MeetingDao):ViewModel() {

     var _uistate by mutableStateOf(UIState())
         private set

     private val _meeting = MutableStateFlow<List<Meeting>>(emptyList())
     val meeting: StateFlow<List<Meeting>> = _meeting

    init {
        loadMeeting()
    }

    fun loadMeeting() {
        viewModelScope.launch {
            _meeting.value = dao.MeetingAll()
        }
    }

    fun onNameChange (newName:String){
        _uistate = _uistate.copy(driverName = newName)
    }

    fun onSurnameChange(newSurname:String){
        _uistate = _uistate.copy(driverSurName = newSurname)
    }

    fun onTimeChange(newTime:String){
        _uistate = _uistate.copy(driverTime = newTime)
    }

    fun onDateChange(newDate:String){
        _uistate = _uistate.copy(driverDate = newDate)
    }

    fun onHourChange(newHour:String){
        _uistate = _uistate.copy(driverHour = newHour)
        onTotalPrice()
    }
    fun onPriceChange(newPrice:String){
        _uistate = _uistate.copy(hourPrice = newPrice)
        onTotalPrice()
    }
    fun onisInsertChange(){
        _uistate = _uistate.copy(
            isInsert = false
        )
    }

    fun onTotalPrice(){
        val price = _uistate.hourPrice?.toDoubleOrNull() ?: 0.0
        val hours = _uistate.driverHour?.toDoubleOrNull() ?: 0.0
        val result = price * hours

        _uistate = _uistate.copy( totalPrice =  result.toString())
    }

    fun meetingInsert(formattedDate:String,timeFormet:String){
        viewModelScope.launch(Dispatchers.IO){
            try {

                dao.insertMeeting(Meeting(
                    name = _uistate.driverName,
                    surname = _uistate.driverSurName,
                    dateMeeting = formattedDate,
                    meetingDuration = _uistate.driverHour,
                    meetingStartTime = timeFormet,
                    hourPrice = _uistate.hourPrice
                ))

                _uistate = _uistate.copy(recordCount = dao.RecordCount())
                _uistate = _uistate.copy(isInsert = true)
                clearValue()

            }
            catch (ex:Exception){
            }
        }
    }


    fun clearValue(){
        _uistate = _uistate.copy(
            driverName = "",
            driverSurName = "",
            driverHour = "",
            hourPrice = "",
            driverTime = "",
            driverDate = "",
            totalPrice = ""
        )
    }
}
