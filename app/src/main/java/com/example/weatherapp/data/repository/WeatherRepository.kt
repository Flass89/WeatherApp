package com.example.weatherapp.data.repository

import com.example.weatherapp.data.model.WeatherResponse
import com.example.weatherapp.data.network.WeatherApi

class WeatherRepository(private val api: WeatherApi) {
    suspend fun fetchWeather(city: String, apiKey: String): Result<WeatherResponse> {
        return try {
            val response = api.getWeather(city, apiKey)
            if (response.isSuccessful && response.body() != null) {
                Result.success(response.body()!!)
            } else {
                Result.failure(Exception("City not found"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}