package com.example.weatherapp.ui.screens

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.weatherapp.R
import com.example.weatherapp.models.Weather
import java.time.LocalDate

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun DailyForecast() {

    val weather = Weather(
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
    );

    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
    ) {


        Text(
            text = weather.condition,
            style = MaterialTheme.typography.headlineMedium
        )

        Spacer(modifier = Modifier.height(10.dp))

        Text("Abilities:")

        Text(weather.feelsLike.toString())
    }
}