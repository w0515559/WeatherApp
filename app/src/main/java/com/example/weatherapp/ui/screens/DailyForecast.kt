package com.example.weatherapp.ui.screens

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Air
import androidx.compose.material.icons.filled.Thermostat
import androidx.compose.material.icons.filled.WaterDrop
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import com.example.weatherapp.MainViewModel
import java.time.format.DateTimeFormatter
import java.util.Locale
import com.example.weatherapp.ui.Components.WeatherDetailCard
import kotlin.math.roundToInt

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun DailyForecast(viewModel: MainViewModel) {
    val weather = viewModel.weather



    weather?.let {



        val dateFormatter = remember {
            DateTimeFormatter.ofPattern(
                "EEEE, MMMM d",
                Locale.getDefault()
            )
        }



        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
                .verticalScroll(rememberScrollState())
                .padding(20.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // Screen heading
            Column(
                verticalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                Text(
                    text = "Daily forecast",
                    style = MaterialTheme.typography.headlineMedium,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onBackground
                )

                Text(
                    text = weather.forecast.forecastDay[0].date.format(dateFormatter),
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }

            // Main weather card
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(28.dp),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.surfaceVariant,
                    contentColor = MaterialTheme.colorScheme.onSurfaceVariant
                )
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(28.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    AsyncImage(
                        model = "https:" + weather.current.condition.icon,
                        contentDescription = "a description of image",
                        modifier = Modifier.size(88.dp)
                    )

                    Text(
                        text = weather.current.condition.text,
                        style = MaterialTheme.typography.headlineSmall
                    )

                    Text(
                        text = weather.current.tempC.roundToInt().toString() + "°C",
                        fontSize = 64.sp,
                        fontWeight = FontWeight.Bold
                    )

                    Text(
                        text = "Current Temperature",
                        style = MaterialTheme.typography.labelLarge
                    )

                    Row(horizontalArrangement = Arrangement.spacedBy(24.dp))
                    {
                        Column() {
                            Text(
                                text = "High: ${weather.forecast.forecastDay[0].day.maxTempC.roundToInt()}°C",
                                style = MaterialTheme.typography.titleMedium
                            )
                        }

                        Column() {
                            Text(
                                text = "Low: ${weather.forecast.forecastDay[0].day.minTempC.roundToInt()}°C",
                                style = MaterialTheme.typography.titleMedium
                            )
                        }

                    }
                }
            }

            Text(
                text = "Weather details",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.SemiBold,
                color = MaterialTheme.colorScheme.onBackground
            )

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                WeatherDetailCard(
                    icon = Icons.Default.Thermostat,
                    title = "Feels like",
                    value = "${weather.current.feelsLikeC.roundToInt()}°C",
                    detail = "Apparent temperature",

                    modifier = Modifier.weight(1f)
                )

                WeatherDetailCard(
                    icon = Icons.Default.WaterDrop,
                    title = "Rainfall",
                    value = "${weather.current.rainChance}%",
                    detail = "${weather.current.precipitationAmount} mm expected",

                    modifier = Modifier.weight(1f)
                )
            }

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                WeatherDetailCard(
                    icon = Icons.Default.Air,
                    title = "Wind",
                    value = "${weather.current.windKPH} km/h",
                    detail = "Direction: ${weather.current.windDirection}",

                    modifier = Modifier.weight(1f)
                )

                WeatherDetailCard(
                    icon = Icons.Default.WaterDrop,
                    title = "Humidity",
                    value = "${weather.current.humidity}%",
                    detail = "Relative humidity",

                    modifier = Modifier.weight(1f)
                )
            }
        }
    }
}
