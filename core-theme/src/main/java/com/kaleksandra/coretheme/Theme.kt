package com.kaleksandra.coretheme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.ReadOnlyComposable

@Composable
fun VkCupSelection2022Theme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colors = if (darkTheme) DarkTheme else LightTheme
    val materialColors = if (darkTheme) DarkColors else LightColors
    CompositionLocalProvider(LocalVKColors provides colors) {
        MaterialTheme(
            colorScheme = materialColors,
            typography = Typography,
            content = content,
        )
    }
}

object AppTheme {
    val colors: VKColors
        @Composable
        @ReadOnlyComposable
        get() = LocalVKColors.current
}