package com.example.weatherapp.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.weatherapp.data.local.SharedPreferencesManager
import com.example.weatherapp.data.repository.WeatherRepository

class WeatherViewModelFactory(
    private val repository: WeatherRepository,
    private val prefsManager: SharedPreferencesManager
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(WeatherViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return WeatherViewModel(repository, prefsManager) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
