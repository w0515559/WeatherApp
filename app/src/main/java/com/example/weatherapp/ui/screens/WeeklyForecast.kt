package com.example.weatherapp.ui.screens

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.layout.fillMaxWidth
import com.example.weatherapp.MainViewModel
import com.example.weatherapp.ui.Components.WeeklyCard



@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun WeeklyForecast(viewModel: MainViewModel) {
    val weather = viewModel.weather
    weather?.let {


        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(20.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(weather.forecast.forecastDay) { forecast ->

                // temp icons
                val weatherIcon = forecast.day.condition.icon


                WeeklyCard(
                    forecast = forecast,
                    icon = weatherIcon,
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }
    }
}