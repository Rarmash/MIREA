package com.rarmash.prac9.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color


private val ColorScheme = lightColorScheme(
    primary = Color.Blue,
    secondary = Color.Black,
    tertiary = Color.Cyan,

    background = PBlue,
    surface = PBlue,
    onPrimary = Color.White,
    onSecondary = Color.White,
    onTertiary = Color.White
)

@Composable
fun Prac9Theme(
    content: @Composable () -> Unit
) {
    val colorScheme = ColorScheme

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}