package com.kaleksandra.coretheme

import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color

val primary = Color(0xFFFF5317)
val onPrimary = Color(0xFFFFFFFF)
val tertiary = Color(0xFFF06130)
val onBackground = Color(0xA3000000)
val surface = Color(0xFFE6E6E6)
val onSurface = Color(0xFF202020)
val background = Color(0xFFFFFFFF)

val darkBackground = Color(0xFF000000)
val darkOnBackground = Color(0x7AFFFFFF)
val darkSurface = Color(0x2EFFFFFF)
val darkOnSurface = Color(0xFFFFFFFF)
val secondary = Color(0xFFE4EAF1)
val darkSecondary = Color(0xFFCAD0D8)
val correct = Color(0xFF4EB54F)
val correctBackground = Color(0xFFA6EAB7)
val uncorrect = Color(0xFFB63737)
val uncorrectBackground = Color(0xFFE79191)

@Immutable
data class VKColors(
    val primary: Color,
    val secondary: Color,
    val tertiary: Color,
    val correct: Color,
    val correctBackground: Color,
    val uncorrect: Color,
    val uncorrectBackground: Color,
    val symbolPrimary: Color,
    val disabled: Color,
)

val LocalVKColors = staticCompositionLocalOf { LightTheme }

val LightTheme = VKColors(
    primary = primary,
    secondary = secondary,
    tertiary = tertiary,
    correct = correct,
    correctBackground = correctBackground,
    uncorrect = uncorrect,
    uncorrectBackground = uncorrectBackground,
    symbolPrimary = Color(0xFF111111),
    disabled = Color(0xFFB0B0B0),
)

val DarkTheme = VKColors(
    primary = primary,
    secondary = darkSecondary,
    tertiary = tertiary,
    correct = correct,
    correctBackground = correctBackground,
    uncorrect = uncorrect,
    uncorrectBackground = uncorrectBackground,
    symbolPrimary = Color(0xFF111111),
    disabled = Color(0xFFB0B0B0),
)

internal val DarkColors = darkColorScheme(
    primary = primary,
    onPrimary = onPrimary,
)
internal val LightColors = lightColorScheme(
    primary = primary,
    onPrimary = onPrimary,
)