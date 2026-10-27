package com.example.weatherapp.data

import com.example.weatherapp.data.Dao.Weather
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApi {

    @GET("v1/forecast.json?key=<api key>&q={q}&days=7&aqi=no&alerts=no")
    suspend fun getWeather(
        @Query("q") query: String
    ): Weather
}