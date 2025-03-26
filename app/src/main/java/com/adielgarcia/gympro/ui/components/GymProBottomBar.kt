package com.adielgarcia.gympro.ui.components

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.BottomAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.adielgarcia.gympro.ui.theme.White
import com.adielgarcia.gympro.ui.theme.shadow

@Composable
@Preview(showBackground = true, showSystemUi = true)
fun GymProBottomBar() {
    BottomAppBar(
        containerColor = White,
        modifier = Modifier.shadow(
            color = Color(0x402B3CAB),
            offsetX = 0.dp,
            offsetY = (-4).dp,
            blurRadius = 20.dp
        )
    ) {

    }
}