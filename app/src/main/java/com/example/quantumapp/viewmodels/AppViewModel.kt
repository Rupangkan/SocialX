package com.example.quantumapp.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.quantumapp.models.News
import com.example.quantumapp.network.repository.MainRepository
import com.google.android.gms.common.api.internal.ApiKey
import kotlinx.coroutines.*

class AppViewModel constructor(
    private val mainRepository: MainRepository
): ViewModel() {
    private val _errorMessage: MutableLiveData<String?> = MutableLiveData()
    var errorMessage: LiveData<String?> = _errorMessage

    private var job: Job? = null
    val newsList = MutableLiveData<News>()
    private val loading = MutableLiveData<Boolean>()
    private val error = MutableLiveData<String>()

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

    fun getAllNews(topic: String, apiKey: String) {
        job = viewModelScope.launch(Dispatchers.IO) {
            val response = mainRepository.getAllNews(topic, apiKey)
            withContext(Dispatchers.Main) {
                if (response.isSuccessful) {
                    newsList.postValue(response.body())
                    loading.value = false
                } else {
                    onError("Error : ${response.message()} ")
                }
            }
        }
    }

    private fun onError(message: String) {
        error.value = message
        loading.value = false
    }

    override fun onCleared() {
        super.onCleared()
        job?.cancel()
    }
}