package com.example.weatherapp.data.remote.dto

import com.squareup.moshi.Json

data class WeatherResponse(
    val name: String,
    val main: Main,
    val weather: List<WeatherInfo>
)

data class Main(
    val temp: Double
)

data class WeatherInfo(
    val main: String,
    val description: String,
    val icon: String
)
