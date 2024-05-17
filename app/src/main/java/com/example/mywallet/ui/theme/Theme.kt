package com.example.mywallet.ui.theme

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

private val DarkColorScheme = darkColorScheme(
    primary = Orange80, // in dark theme, has to be bright
    onPrimary = Orange20, // contrast with primary
    primaryContainer = Orange30, // container
    onPrimaryContainer = Orange90, // text on container
    inversePrimary = Orange90, // contrast with primary as subtitle
    secondary = DarkOrange80,
    onSecondary = DarkOrange20,
    secondaryContainer  = DarkOrange30,
    onSecondaryContainer  = DarkOrange90,
    error = Red80,
    onError = Red20,
    errorContainer = Red30,
    onErrorContainer = Red90,
    background = Grey10,
    onBackground = Grey90,
    surface = OrangeGrey30, // background (the whole surface of the app)
    onSurface = OrangeGrey80,
    inverseSurface = Grey90,
    inverseOnSurface = Grey10,
    surfaceVariant = OrangeGrey30,
    onSurfaceVariant = OrangeGrey80,
    outline = OrangeGrey80
)

private val LightColorScheme = lightColorScheme(
    primary = Orange40, // in light theme, has to be dark
    onPrimary = Color.White, // contrast with primary
    primaryContainer = Orange90, // container
    onPrimaryContainer = Orange10, // text on container
    inversePrimary = Orange80, // contrast with primary as subtitle
    secondary = DarkOrange40,
    onSecondary = Color.White,
    secondaryContainer  = DarkOrange90,
    onSecondaryContainer  = DarkOrange10,
    error = Red40,
    onError = Color.White,
    errorContainer = Red90,
    onErrorContainer = Red10,
    background = Grey99,
    onBackground = Grey10,
    surface = OrangeGrey90, // background (the whole surface of the app)
    onSurface = OrangeGrey30,
    inverseSurface = Grey20,
    inverseOnSurface = Grey95,
    surfaceVariant = OrangeGrey90,
    onSurfaceVariant = OrangeGrey30,
    outline = OrangeGrey50
)

@Composable
fun MyWalletTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = true,
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
    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            window.statusBarColor = colorScheme.primary.toArgb()
            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = darkTheme
        }
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}