package com.example.weatherapp.ui.screens

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.DividerDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.weatherapp.models.Weather
import java.time.LocalDate

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun WeeklyForecast(innerPadding: PaddingValues) {

    val weatherForecast = listOf(
        Weather(
            date = LocalDate.now(),
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
            date = LocalDate.now().plusDays(1),
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
            date = LocalDate.now().plusDays(2),
            condition = "Sunny",
            temperatureMax = 18,
            temperatureMin = 11,
            feelsLike = 17,
            humidity = 86,
            windDirection = "E",
            maxWindSpeed = 20,
            rainChance = 0,
            rainAmount = 0
        )

    )

    LazyColumn(
        modifier = Modifier.padding(innerPadding)
    ) {
        items(weatherForecast) { weather ->
            Row()
            {
//                Image(
//                    painter = painterResource(id = pokemon.resourceId),
//                    contentDescription = pokemon.name
//                )

                Text(weather.date.toString())

                Text(weather.condition)
            }

            HorizontalDivider(Modifier, DividerDefaults.Thickness, DividerDefaults.color)
        }
    }
}