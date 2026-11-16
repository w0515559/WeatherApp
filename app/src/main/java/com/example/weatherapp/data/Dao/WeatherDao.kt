package com.example.weatherapp.data.Dao

import com.google.gson.annotations.SerializedName

data class Weather(
    val location: Location,
    val current: Current,
    val forecast: Forecast
)

data class Location(
    val name: String,
    val region: String,
    val country: String,
    val lat: Double,
    val lon: Double
)

data class Forecast(
    @SerializedName("forecastday") val forecastDay: List<ForecastDay>
)

data class ForecastDay(
    val date: String,
    val day: Day
)

data class Day(
    @SerializedName("maxtemp_c") val maxTempC: Double,
    @SerializedName("mintemp_c") val minTempC: Double,
    @SerializedName("maxwind_kph") val maxWindKPH: Double,
    @SerializedName("totalprecip_mm") val precipitationAmount: Double,
    @SerializedName("avghumidity") val avgHumidity: Double,
    @SerializedName("daily_chance_of_rain") val chanceRain: Int,
    val condition: Condition
)

data class Current(
    @SerializedName("temp_c") val tempC: Double,
    @SerializedName("feelslike_c") val feelsLikeC: Double,
    @SerializedName("wind_kph") val windKPH: Double,
    @SerializedName("wind_dir") val windDirection: String,
    @SerializedName("precip_mm") val precipitationAmount: Double,
    @SerializedName("chance_of_rain") val rainChance: Int,
    val humidity: Int,
    val condition: Condition
)

data class Condition(
    val text: String,
    val code: Int,
    val icon: String
)
