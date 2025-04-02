package com.adielgarcia.gympro.ui.components


import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color

fun diagonalGradient(colors: List<Color>): Brush {
    return Brush.linearGradient(
        colors = colors,
        start = Offset.Zero,         // Top-left corner (0f, 0f)
        end = Offset.Infinite,       // Bottom-right corner (positive infinity on both axes)
    )
}