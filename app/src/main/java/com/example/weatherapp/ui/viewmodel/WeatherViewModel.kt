package com.example.weatherapp.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherapp.data.local.SharedPreferencesManager
import com.example.weatherapp.data.repository.WeatherRepository
import com.example.weatherapp.domain.model.Resource
import com.example.weatherapp.domain.model.Weather
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update

data class WeatherUiState(
    val isLoading: Boolean = false,
    val weather: Weather? = null,
    val error: String? = null,
    val searchInput: String = ""
)

class WeatherViewModel(
    private val repository: WeatherRepository,
    private val prefsManager: SharedPreferencesManager // Bonus
) : ViewModel() {

    private val _uiState = MutableStateFlow(WeatherUiState())
    val uiState = _uiState.asStateFlow()

    init {
        // Bonus: Load and fetch weather for the last searched city on startup
        val lastCity = prefsManager.getLastSearchedCity()
        if (!lastCity.isNullOrBlank()) {
            onSearchInputChanged(lastCity)
            fetchWeather(lastCity)
        }
    }

    fun onSearchInputChanged(input: String) {
        _uiState.update { it.copy(searchInput = input) }
    }

    fun fetchWeather(city: String = _uiState.value.searchInput) {
        if (city.isBlank()) {
            _uiState.update { it.copy(error = "City name cannot be empty.") }
            return
        }

        repository.getWeather(city).onEach { result ->
            val newState = when (result) {
                is Resource.Loading -> WeatherUiState(isLoading = true, searchInput = city)
                is Resource.Success -> {
                    prefsManager.saveLastSearchedCity(city) // Bonus: Save successful search
                    WeatherUiState(weather = result.data, searchInput = city)
                }
                is Resource.Error -> WeatherUiState(error = result.message, searchInput = city)
            }
            _uiState.value = newState
        }.launchIn(viewModelScope)
    }
}
