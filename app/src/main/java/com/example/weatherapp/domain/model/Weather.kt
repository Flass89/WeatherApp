package com.example.weatherapp.domain.model

data class Weather(
    val cityName: String,
    val temperature: Double,
    val condition: String,
    val iconUrl: String
)
