package com.example.weatherapp.data

import com.example.weatherapp.data.Dao.Weather
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApi {

    @GET("v1/forecast.json")
    suspend fun getWeather(
        @Query("key") key: String,
        @Query("q") q: String,
        @Query("days") days: Int = 7,
        @Query("aqi") aqi: String = "no",
        @Query("alerts") alerts: String = "no"
    ): Weather
}