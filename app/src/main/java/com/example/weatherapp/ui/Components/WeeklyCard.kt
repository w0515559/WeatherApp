package com.example.weatherapp.ui.Components

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.example.weatherapp.data.Dao.ForecastDay
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.Locale
import kotlin.math.roundToInt

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun WeeklyCard(
    forecast: ForecastDay,
    // date, condition.text, min/max temp, max wind speed, precip % and amount
    icon: String,
    modifier: Modifier = Modifier
) {

    val dateFormatter = remember {
        DateTimeFormatter.ofPattern(
            "EEEE, MMMM d",
            Locale.getDefault()
        )
    }

    val dateText = LocalDate.parse(forecast.date).format(dateFormatter)

    Card(
        modifier = modifier,
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant,
            contentColor = MaterialTheme.colorScheme.onSurfaceVariant
        )
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .heightIn(min = 160.dp)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = forecast.day.condition.text,
                    style = MaterialTheme.typography.headlineSmall,
                    fontWeight = FontWeight.SemiBold,
                    color = MaterialTheme.colorScheme.onSurface
                )

                Text(
                    text = dateText,
                    style = MaterialTheme.typography.bodyMedium
                )
            }


            Row(
                modifier = Modifier.fillMaxWidth(),
            ) {
                Column() {
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(24.dp)
                    ) {
                        Text(
                            text = "High: ${forecast.day.maxTempC.roundToInt()}°C",
                            style = MaterialTheme.typography.titleMedium
                        )

                        Text(
                            text = "Low: ${forecast.day.minTempC.roundToInt()}°C",
                            style = MaterialTheme.typography.titleMedium
                        )
                    }

                    Text(
                        text = "Precipitation: ${forecast.day.chanceRain}%,  ${forecast.day.precipitationAmount} mm",
                        style = MaterialTheme.typography.bodyMedium
                    )

                    Text(
                        text = "Humidity: ${forecast.day.avgHumidity.roundToInt()}%",
                        style = MaterialTheme.typography.bodyMedium
                    )

                    Text(
                        text = "Wind: ${forecast.day.maxWindKPH.roundToInt()} km/h",
                        style = MaterialTheme.typography.bodyMedium
                    )
                }
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Absolute.Right
                ) {
                    AsyncImage(
                        model = "https:" + icon,
                        contentDescription = "Condition image",
                        modifier = Modifier.size(88.dp)
                    )
                }
            }
        }
    }
}