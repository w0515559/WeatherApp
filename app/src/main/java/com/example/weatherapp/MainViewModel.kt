package com.example.weatherapp

import android.annotation.SuppressLint
import android.app.Application
import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherapp.data.Dao.Weather
import com.example.weatherapp.data.WeatherApi
import com.google.android.gms.location.LocationServices
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import android.location.Location
import com.google.android.gms.location.Priority


class MainViewModel(app: Application) : AndroidViewModel(app) {

    private val fusedLocationClient = LocationServices.getFusedLocationProviderClient(app)

    var location by mutableStateOf<Location?>(null)
        private set

    @SuppressLint("MissingPermission")
    fun refreshLocation() {
        fusedLocationClient
            .getCurrentLocation(Priority.PRIORITY_HIGH_ACCURACY, null)
            .addOnSuccessListener { result ->
                location = result
                val latlng = "lat: ${location?.latitude}, lng: ${location?.longitude}"
                Log.i("MainViewModel", "Phone is location at $latlng.")
            }
    }

    // Stores the JSON data returned from API
    var weather by mutableStateOf<Weather?>(null)
        private set

    var error by mutableStateOf<String?>(null)
    private set

    private val api = Retrofit.Builder()
        .baseUrl("https://api.weatherapi.com/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(WeatherApi::class.java)

    /* functions */

    fun getWeather(location: String) {

            viewModelScope.launch {
                try {
                weather = api.getWeather(
                    q = location,
                    key = BuildConfig.API_KEY
                )
            }catch (e: Exception){
                    Log.e("MainViewModel", e.toString())
                    error = "Weather was not loaded"
                }
        }
    }


}