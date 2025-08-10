package com.example.weatherapp.data.repository

import com.example.weatherapp.data.remote.RetrofitInstance
import com.example.weatherapp.domain.model.Resource
import com.example.weatherapp.domain.model.Weather
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException

class WeatherRepository {
    fun getWeather(city: String): Flow<Resource<Weather>> = flow {
        emit(Resource.Loading())
        try {
            val response = RetrofitInstance.api.getWeather(cityName = city)
            val weather = Weather(
                cityName = response.name,
                temperature = response.main.temp,
                condition = response.weather.firstOrNull()?.main ?: "Unknown",
                iconUrl = "https://openweathermap.org/img/wn/${response.weather.firstOrNull()?.icon}@2x.png"
            )
            emit(Resource.Success(weather))
        } catch (e: HttpException) {
            val message = when (e.code()) {
                404 -> "City not found"
                else -> "An unexpected error occurred"
            }
            emit(Resource.Error(message))
        } catch (e: IOException) {
            emit(Resource.Error("Network failure. Please check your internet connection."))
        } catch (e: Exception) {
            emit(Resource.Error("An unknown error occurred."))
        }
    }
}
