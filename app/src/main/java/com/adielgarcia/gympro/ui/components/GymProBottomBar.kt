package com.adielgarcia.gympro.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.BottomAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.adielgarcia.gympro.ui.theme.White
import com.adielgarcia.gympro.ui.theme.shadow
import com.adielgarcia.gympro.utils.BottomBarItem

@Composable
fun GymProBottomBar(currentScreenPath: String, onItemSelected: (String) -> Unit) {
    val items = listOf(
        BottomBarItem.EntrenadoresItem,
        BottomBarItem.SuscripcionItem,
        BottomBarItem.ProfileItem,
        BottomBarItem.ProductosItem,
        BottomBarItem.EquipamientosIcon
    )

    Box(modifier = Modifier) {
        BottomAppBar(
            containerColor = White,
            modifier = Modifier.shadow(
                color = Color(0x402B3CAB),
                offsetX = 0.dp,
                offsetY = (-4).dp,
                blurRadius = 20.dp
            )
        ) {}
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            items.forEach { item ->
                GymProBottomBarItem(
                    isSelected = currentScreenPath.endsWith(item.route),
                    icon = item.icon,
                    label = item.label,
                    onClick = {
                        onItemSelected(item.route)
                    }
                )
            }
        }
    }
}