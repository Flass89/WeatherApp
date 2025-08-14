package com.example.weatherapp.ui

import android.content.SharedPreferences
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.weatherapp.viewmodel.WeatherViewModel

@Composable
fun WeatherApp(viewModel: WeatherViewModel, prefs: SharedPreferences) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        TextField(
            value = viewModel.city,
            onValueChange = { viewModel.city = it },
            label = { Text("Enter city") },
            modifier = Modifier.fillMaxWidth()
        )

        Button(
            onClick = { viewModel.search(viewModel.city, prefs) },
            modifier = Modifier.align(Alignment.End)
        ) {
            Text("Search")
        }

        if (viewModel.isLoading) {
            CircularProgressIndicator(modifier = Modifier.align(Alignment.CenterHorizontally))
        } else {
            viewModel.weatherResponse?.let {
                Card(modifier = Modifier.fillMaxWidth()) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Text("City: ${it.name}")
                        Text("Temperature: ${it.main.temp}°C")
                        Text("Condition: ${it.weather[0].main}")
                    }
                }
            }

            viewModel.errorMessage?.let {
                Text("Error: $it", color = MaterialTheme.colorScheme.error)
            }
        }
    }
}