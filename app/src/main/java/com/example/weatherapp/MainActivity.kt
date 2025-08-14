package com.example.weatherapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.weatherapp.data.network.RetrofitInstance
import com.example.weatherapp.data.repository.WeatherRepository
import com.example.weatherapp.ui.WeatherApp
import com.example.weatherapp.viewmodel.WeatherViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val prefs = getSharedPreferences("weather_prefs", MODE_PRIVATE)
        val lastCity = prefs.getString("last_city", "") ?: ""

        val repository = WeatherRepository(RetrofitInstance.api)
        val viewModel = WeatherViewModel(repository)

        if (lastCity.isNotEmpty()) {
            viewModel.search(lastCity, prefs)
        }

        setContent {
            WeatherApp(viewModel, prefs)
        }
    }
}