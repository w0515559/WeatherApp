package com.example.weatherapp.models

import java.time.LocalDate
import java.util.Date

data class Weather(
    val date: LocalDate,
    val condition: String, // Cloud/Sun/Rain/
    val temperatureMax: Int, // Celsius
    val temperatureMin: Int, // Celsius
    val feelsLike: Int, // Feels like temperature
    val humidity: Int, // %
    val windDirection: String, // N/NE/E/SE/S/SW/W/NW/
    val maxWindSpeed: Int, // KM/H
    val rainChance: Int, // %
    val rainAmount: Int, // mm
    )