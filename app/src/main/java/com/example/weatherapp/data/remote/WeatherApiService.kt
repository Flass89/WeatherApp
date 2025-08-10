package com.example.weatherapp.data.remote

import com.example.weatherapp.data.remote.dto.WeatherResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApiService {
    @GET("weather")
    suspend fun getWeather(
        @Query("q") cityName: String,
        @Query("appid") apiKey: String = API_KEY,
        @Query("units") units: String = "metric" // or "imperial" for Fahrenheit
    ): WeatherResponse
}
