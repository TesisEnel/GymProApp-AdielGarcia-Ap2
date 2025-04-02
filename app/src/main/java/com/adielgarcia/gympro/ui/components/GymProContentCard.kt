package com.adielgarcia.gympro.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.adielgarcia.gympro.ui.theme.NormalCardColor1
import com.adielgarcia.gympro.ui.theme.NormalCardColor2
import com.adielgarcia.gympro.ui.theme.shadow

@Composable
fun GymProContentCard(modifier: Modifier = Modifier, content: @Composable () -> Unit) {
    Card(
        shape = RoundedCornerShape(4.dp),
        modifier = modifier
            .background(
                brush = diagonalGradient(
                    colors = listOf(
                        NormalCardColor1,
                        NormalCardColor2
                    )
                ),
                shape = RoundedCornerShape(4.dp)
            )
            .shadow(
                color = Color(0x4A0A0065),
                offsetX = 0.dp,
                offsetY = 0.dp,
                blurRadius = 7.dp
            )

    ) {
        content()
    }
}