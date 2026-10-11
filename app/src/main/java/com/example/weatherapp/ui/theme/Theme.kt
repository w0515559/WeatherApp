package com.example.weatherapp.ui.theme

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext


private val DarkColorScheme = darkColorScheme(
    primary = Color(0xFF1C1125),
    onPrimary = Color(0xFFFFFFFF),

    primaryContainer = Color(0xFF0D0116),
    onPrimaryContainer = Color(0xFFFFFFFF),

    background = Color(0xFF11051A),
    onBackground = Color(0xFFFFFFFF),

    surface = Color(0xFF0F0318),
    onSurface = Color(0xFFFFFFFF),

    surfaceVariant = Color(0xFF1C1125),
    onSurfaceVariant = Color(0xFFFFFFFF)
)

private val LightColorScheme = lightColorScheme(
    primary = Color(0xFF205EA6),
    onPrimary = Color.White,

    primaryContainer = Color(0xFFD8E9FF),
    onPrimaryContainer = Color(0xFF102D50),

    background = Color(0xFFF5F8FC),
    onBackground = Color(0xFF182535),

    surface = Color.White,
    onSurface = Color(0xFF182535),

    surfaceVariant = Color(0xFFE7EEF7),
    onSurfaceVariant = Color(0xFF465569)
)

@Composable
fun WeatherAppTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = false,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }

        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}