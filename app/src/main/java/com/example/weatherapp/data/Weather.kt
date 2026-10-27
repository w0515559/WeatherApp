package com.example.weatherapp.data.Dao

import java.time.LocalDate

data class Weather(
    val date: LocalDate,
    val condition: String, // Cloud/Sun/Rain/
    val temperatureCurrent: Int? = null,// optional current temp
    val temperatureMax: Int, // Celsius
    val temperatureMin: Int, // Celsius
    val feelsLike: Int, // Apparent temperature
    val humidity: Int, // %
    val windDirection: String, // N/NE/E/SE/S/SW/W/NW/
    val maxWindSpeed: Int, // KM/H
    val rainChance: Int, // %
    val rainAmount: Int, // mm
)
// Most of this is subject to change depending on future api