package com.example.weatherapp.ui

import android.Manifest
import android.os.Build
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.material.icons.filled.WbSunny
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarDefaults
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.weatherapp.MainViewModel
import com.example.weatherapp.ui.screens.DailyForecast
import com.example.weatherapp.ui.screens.WeeklyForecast
import kotlin.math.log


@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Navigation() {

    val navController = rememberNavController()
    var selectedIndex by remember { mutableIntStateOf(0) } // default set to 0

    // initialize ViewModel (Shared)
    val viewModel: MainViewModel = viewModel()

    // Begin: Ask permission to get device location.
    // Note: If permission is previously granted, the dialog won't appear and refreshLocation() will get called.
    val launcher = rememberLauncherForActivityResult(
        ActivityResultContracts.RequestMultiplePermissions()
    ) { results ->
        if (results.values.any { it }) {
            viewModel.refreshLocation() // call a function to get the current location for device
        }
    }

    LaunchedEffect(Unit) {
        launcher.launch(
            arrayOf(
                Manifest.permission.ACCESS_COARSE_LOCATION,
                Manifest.permission.ACCESS_FINE_LOCATION
            )
        )
    }
    // End: Ask permission to get device location.


    // wait for the location, then pass it to getWeather
    LaunchedEffect(viewModel.location) {
        viewModel.location?.let {
            val location = viewModel.location?.latitude.toString() + ", " + viewModel.location?.longitude.toString()
            viewModel.getWeather(location)
        } // kinda slow, probably a better way to do this
    }

    val weather = viewModel.weather
    weather?.let {
        Scaffold(
            topBar = {
                TopAppBar(
                    colors = topAppBarColors(
                        containerColor = MaterialTheme.colorScheme.primaryContainer,
                        titleContentColor = MaterialTheme.colorScheme.onPrimaryContainer,
                    ),
                    title = {
                        Text(weather.location.name + ", " + weather.location.region)
                    },

                )
            },
            bottomBar = {
                NavigationBar(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    windowInsets = NavigationBarDefaults.windowInsets)
                {

                    // daily weather
                    NavigationBarItem(
                        icon = { Icon(imageVector = Icons.Default.WbSunny, contentDescription = "DailyForecast") },
                        label = { Text("Today") },
                        selected = selectedIndex == 0,
                        onClick = {
                            selectedIndex = 0
                            navController.navigate(route="DailyForecast"){
                                popUpTo(navController.graph.findStartDestination().id) {
                                    saveState = true
                                }
                                launchSingleTop = true
                                restoreState = true
                            }
                        }
                    )

                    //  Weekly forecast
                    NavigationBarItem(
                        icon = { Icon(imageVector = Icons.Default.CalendarMonth, contentDescription = "WeeklyForecast") },
                        label = { Text("Weekly Forecast") },
                        selected = selectedIndex == 1,
                        onClick = {
                            selectedIndex = 1
                            navController.navigate(route="WeeklyForecast"){
                                popUpTo(navController.graph.findStartDestination().id) {
                                    saveState = true
                                }
                                launchSingleTop = true
                                restoreState = true
                            }
                        }
                    )

                }
            }



        )

        { innerPadding ->

            //content area
            NavHost(
                navController = navController,
                startDestination = "DailyForecast",
                modifier = Modifier.padding(innerPadding)
            ) {
                composable(route = "DailyForecast") {
                    DailyForecast(viewModel)
                }
                composable(route = "WeeklyForecast") {
                    WeeklyForecast(viewModel)
                }
            }
        }
    }


}
