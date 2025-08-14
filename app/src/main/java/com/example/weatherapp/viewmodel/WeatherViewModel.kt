package com.example.weatherapp.viewmodel

import android.content.SharedPreferences
import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherapp.data.model.WeatherResponse
import com.example.weatherapp.data.repository.WeatherRepository
import kotlinx.coroutines.launch

class WeatherViewModel(private val repository: WeatherRepository) : ViewModel() {

    var city by mutableStateOf("")
    var weatherResponse by mutableStateOf<WeatherResponse?>(null)
    var errorMessage by mutableStateOf<String?>(null)
    var isLoading by mutableStateOf(false)

    fun search(cityName: String, prefs: SharedPreferences? = null) {
        viewModelScope.launch {
            isLoading = true
            errorMessage = null
            val result = repository.fetchWeather(cityName, "cb0039b08e3f50d19c8a33a818ae453e")
            result.fold(
                onSuccess = {
                    weatherResponse = it
                    prefs?.edit()?.putString("last_city", cityName)?.apply()
                },
                onFailure = {
                    errorMessage = it.message
                }
            )
            isLoading = false
        }
    }
}