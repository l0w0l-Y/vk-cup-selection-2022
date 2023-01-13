package com.kaleksandra.coretheme

import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color

val primary = Color(0xFFA80404)
val secondary = Color(0xFFE4EAF1)
val correct = Color(0xFF4EB54F)
val correctBackground = Color(0xFFA6EAB7)
val uncorrect = Color(0xFFB63737)
val uncorrectBackground = Color(0xFFE79191)

@Immutable
data class VKColors(
    val primary: Color,
    val secondary: Color,
    val correct: Color,
    val correctBackground: Color,
    val uncorrect: Color,
    val uncorrectBackground: Color,
)

val LocalVKColors = staticCompositionLocalOf { LightTheme }

val LightTheme = VKColors(
    primary = primary,
    secondary = secondary,
    correct = correct,
    correctBackground = correctBackground,
    uncorrect = uncorrect,
    uncorrectBackground = uncorrectBackground,
)

val DarkTheme = VKColors(
    primary = primary,
    secondary = secondary,
    correct = correct,
    correctBackground = correctBackground,
    uncorrect = uncorrect,
    uncorrectBackground = uncorrectBackground,
)

internal val DarkColors = darkColorScheme(
    primary = primary
)
internal val LightColors = lightColorScheme(
    primary = primary
)