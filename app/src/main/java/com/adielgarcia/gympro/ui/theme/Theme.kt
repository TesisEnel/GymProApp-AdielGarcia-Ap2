package com.adielgarcia.gympro.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable


private val LightColorScheme = lightColorScheme(
    primary = PrimaryColor,
    secondary = SecondaryColor,
    tertiary = TertiaryColor,
    background = CelesteBG,

    /* Other default colors to override
    surface = Color(0xFFFFFBFE),
    onPrimary = Color.White,
    onSecondary = Color.White,
    onTertiary = Color.White,
    onBackground = Color(0xFF1C1B1F),
    onSurface = Color(0xFF1C1B1F),
    */
)

@Composable
fun GymProTheme(
    content: @Composable () -> Unit
) {
    val colorScheme = LightColorScheme


    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}