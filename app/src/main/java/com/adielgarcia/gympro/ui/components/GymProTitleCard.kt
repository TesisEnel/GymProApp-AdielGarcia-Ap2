package com.adielgarcia.gympro.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.adielgarcia.gympro.ui.theme.TitleCardColor1
import com.adielgarcia.gympro.ui.theme.TitleCardColor2
import com.adielgarcia.gympro.ui.theme.White
import com.adielgarcia.gympro.ui.theme.shadow

@Composable
fun GymProTitleCard(title: String, modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .shadow(
                color = Color(0x752B3CAB),
                offsetX = 2.dp,
                offsetY = 2.dp,
                blurRadius = 8.dp
            )
            .background(
                brush = Brush.horizontalGradient(
                    colors = listOf(
                        TitleCardColor1, TitleCardColor2
                    )
                )
            ),

        contentAlignment = Alignment.Center
    ) {
        Text(text = title, color = White, fontSize = 16.sp, modifier = Modifier.padding(16.dp))
    }
}