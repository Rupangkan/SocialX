package com.example.quantumapp.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class AppViewModel: ViewModel() {
    private val _errorMessage: MutableLiveData<String?> = MutableLiveData()
    var errorMessage: LiveData<String?> = _errorMessage

    fun validateForm(name: String, phone: String, checkBox: Boolean) {
        if(name.length < 4 || name.length > 20) {
            _errorMessage.value = "Name length should be more than 4 and less than 20 characters"
        } else if(phone.length != 10) {
            _errorMessage.value =  "Enter valid Phone no"
        } else if(!checkBox) {
            _errorMessage.value =  "Please check the terms and conditions"
        } else {
            _errorMessage.value = null
        }
    }
}