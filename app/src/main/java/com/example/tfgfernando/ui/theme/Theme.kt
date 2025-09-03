package com.example.tfgfernando.ui.theme

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
    primary = Color(0xFF4FC3F7),      // Azul claro vibrante
    onPrimary = Color.Black,
    secondary = Color(0xFF0288D1),    // Azul medio
    onSecondary = Color.White,
    tertiary = Color(0xFFFFD54F),     // Amarillo cálido (para destacar)
    onTertiary = Color.Black,
    background = Color(0xFF121212),   // Fondo oscuro
    onBackground = Color(0xFFE0E0E0),
    surface = Color(0xFF1E1E1E),      // Superficies oscuras
    onSurface = Color(0xFFE0E0E0)
)


private val LightColorScheme = lightColorScheme(
    primary = Color(0xFF006E90),      // Azul petróleo
    onPrimary = Color.White,
    secondary = Color(0xFF89CFF0),    // Azul claro o cualquier color que no sea rosa
    onSecondary = Color.Black,
    tertiary = Color(0xFFF5A623),     // O usa el mismo que primary, si no quieres destacar tanto
    onTertiary = Color.Black,
    background = Color(0xFFFFFBFE),
    onBackground = Color(0xFF1C1B1F),
    surface = Color(0xFFFFFFFF),
    onSurface = Color(0xFF1C1B1F)
)


@Composable
fun TfgFernandoTheme(
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

        darkTheme -> LightColorScheme
        else -> LightColorScheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}