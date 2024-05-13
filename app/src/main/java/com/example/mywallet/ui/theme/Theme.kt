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
    primary = Blue80, // in dark theme, has to be bright
    onPrimary = Blue20, // contrast with primary
    primaryContainer = Blue30, // container
    onPrimaryContainer = Blue90, // text on container
    inversePrimary = Blue90, // contrast with primary as subtitle
    secondary = DarkBlue80,
    onSecondary = DarkBlue20,
    secondaryContainer  = DarkBlue30,
    onSecondaryContainer  = DarkBlue90,
    tertiary = Violet80,
    onTertiary = Violet20,
    tertiaryContainer  = Violet30,
    onTertiaryContainer  = Violet90,
    error = Red80,
    onError = Red20,
    errorContainer = Red30,
    onErrorContainer = Red90,
    background = Grey10,
    onBackground = Grey90,
    surface = BlueGrey30, // background (the whole surface of the app)
    onSurface = BlueGrey80,
    inverseSurface = Grey90,
    inverseOnSurface = Grey10,
    surfaceVariant = BlueGrey30,
    onSurfaceVariant = BlueGrey80,
    outline = BlueGrey80
)

private val LightColorScheme = lightColorScheme(
    primary = Blue40, // in light theme, has to be dark
    onPrimary = Color.White, // contrast with primary
    primaryContainer = Blue90, // container
    onPrimaryContainer = Blue10, // text on container
    inversePrimary = Blue80, // contrast with primary as subtitle
    secondary = DarkBlue40,
    onSecondary = Color.White,
    secondaryContainer  = DarkBlue90,
    onSecondaryContainer  = DarkBlue10,
    tertiary = Violet40,
    onTertiary = Color.White,
    tertiaryContainer  = Violet90,
    onTertiaryContainer  = Violet10,
    error = Red40,
    onError = Color.White,
    errorContainer = Red90,
    onErrorContainer = Red10,
    background = Grey99,
    onBackground = Grey10,
    surface = BlueGrey90, // background (the whole surface of the app)
    onSurface = BlueGrey30,
    inverseSurface = Grey20,
    inverseOnSurface = Grey95,
    surfaceVariant = BlueGrey90,
    onSurfaceVariant = BlueGrey30,
    outline = BlueGrey50
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