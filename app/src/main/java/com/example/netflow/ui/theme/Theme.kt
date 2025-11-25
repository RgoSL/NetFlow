package com.example.netflow.ui.theme

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext

private val LightColorScheme = lightColorScheme(
    primary = MintGreen,
    secondary = PastelBlue,
    background = OffWhite,
    surface = OffWhite,
    onBackground = LightGray,
    onPrimary = OffWhite,
    onSecondary = OffWhite
)

private val DarkColorScheme = darkColorScheme(
    primary = MintGreen,
    secondary = PastelBlue,
    background = LightGray,
    surface = LightGray,
    onBackground = OffWhite,
    onPrimary = LightGray,
    onSecondary = LightGray
)

@Composable
fun NetFlowTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
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

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}