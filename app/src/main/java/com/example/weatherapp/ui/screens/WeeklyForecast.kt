package com.example.weatherapp.ui.screens

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.weatherapp.models.Weather
import com.example.weatherapp.ui.Components.WeeklyCard
import java.time.LocalDate
import androidx.compose.foundation.layout.fillMaxWidth
import java.util.Locale
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Cloud
import androidx.compose.material.icons.filled.WaterDrop
import androidx.compose.material.icons.filled.WbSunny
import com.example.weatherapp.MainViewModel


@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun WeeklyForecast(viewModel: MainViewModel) {

    val today = LocalDate.now()



    val weatherForecast = listOf(
        Weather(
            date = today,
            condition = "Cloudy",
            temperatureMax = 19,
            temperatureMin = 9,
            feelsLike = 18,
            humidity = 90,
            windDirection = "NE",
            maxWindSpeed = 19,
            rainChance = 5,
            rainAmount = 0
        ),
        Weather(
            date = today.plusDays(1),
            condition = "Rain",
            temperatureMax = 14,
            temperatureMin = 6,
            feelsLike = 11,
            humidity = 95,
            windDirection = "N",
            maxWindSpeed = 45,
            rainChance = 90,
            rainAmount = 3
        ),
        Weather(
            date = today.plusDays(2),
            condition = "Sunny",
            temperatureMax = 18,
            temperatureMin = 11,
            feelsLike = 17,
            humidity = 70,
            windDirection = "E",
            maxWindSpeed = 20,
            rainChance = 0,
            rainAmount = 0
        ),
        Weather(
            date = today.plusDays(3),
            condition = "Cloudy",
            temperatureMax = 16,
            temperatureMin = 8,
            feelsLike = 14,
            humidity = 80,
            windDirection = "SW",
            maxWindSpeed = 25,
            rainChance = 20,
            rainAmount = 0
        ),
        Weather(
            date = today.plusDays(4),
            condition = "Rain",
            temperatureMax = 13,
            temperatureMin = 7,
            feelsLike = 10,
            humidity = 92,
            windDirection = "W",
            maxWindSpeed = 32,
            rainChance = 75,
            rainAmount = 5
        ),
        Weather(
            date = today.plusDays(5),
            condition = "Sunny",
            temperatureMax = 20,
            temperatureMin = 12,
            feelsLike = 19,
            humidity = 65,
            windDirection = "S",
            maxWindSpeed = 15,
            rainChance = 0,
            rainAmount = 0
        ),
        Weather(
            date = today.plusDays(6),
            condition = "Cloudy",
            temperatureMax = 17,
            temperatureMin = 10,
            feelsLike = 15,
            humidity = 78,
            windDirection = "SE",
            maxWindSpeed = 22,
            rainChance = 30,
            rainAmount = 1
        ),
        Weather(
            date = today.plusDays(7),
            condition = "Rain",
            temperatureMax = 14,
            temperatureMin = 6,
            feelsLike = 11,
            humidity = 95,
            windDirection = "N",
            maxWindSpeed = 45,
            rainChance = 90,
            rainAmount = 3
        ),
        Weather(
            date = today.plusDays(8),
            condition = "Sunny",
            temperatureMax = 18,
            temperatureMin = 11,
            feelsLike = 17,
            humidity = 70,
            windDirection = "E",
            maxWindSpeed = 20,
            rainChance = 0,
            rainAmount = 0
        ),
        Weather(
            date = today.plusDays(9),
            condition = "Cloudy",
            temperatureMax = 16,
            temperatureMin = 8,
            feelsLike = 14,
            humidity = 80,
            windDirection = "SW",
            maxWindSpeed = 25,
            rainChance = 20,
            rainAmount = 0
        ),

    )


    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(20.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        items(weatherForecast) { weather ->

            val title = when (weather.date) {
                today -> "Today"
                today.plusDays(1) -> "Tomorrow"
                else -> weather.date.dayOfWeek.getDisplayName(
                    java.time.format.TextStyle.FULL,
                    Locale.getDefault()
                )
            }

            // temp icons
            val weatherIcon = when (weather.condition) {
                "Sunny" -> Icons.Default.WbSunny
                "Rain" -> Icons.Default.WaterDrop
                else -> Icons.Default.Cloud
            }

            WeeklyCard(
                weather = weather,
                title = title,
                icon = weatherIcon,
                modifier = Modifier.fillMaxWidth()
            )

        }
    }
}