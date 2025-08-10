package com.example.weatherapp.data.local

import android.content.Context

class SharedPreferencesManager(context: Context) {
    private val sharedPreferences = context.getSharedPreferences("WeatherAppPrefs", Context.MODE_PRIVATE)

    fun saveLastSearchedCity(city: String) {
        sharedPreferences.edit().putString("LAST_CITY", city).apply()
    }

    fun getLastSearchedCity(): String? {
        return sharedPreferences.getString("LAST_CITY", null)
    }
}

