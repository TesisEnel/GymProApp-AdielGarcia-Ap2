package com.adielgarcia.gympro.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.BottomAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.adielgarcia.gympro.ui.theme.White
import com.adielgarcia.gympro.ui.theme.shadow
import com.adielgarcia.gympro.utils.BottomBarItem

@Composable
@Preview(showBackground = true, showSystemUi = true)
fun GymProBottomBar() {
    val items = listOf(
        BottomBarItem.EntrenadoresItem,
        BottomBarItem.SuscripcionItem,
        BottomBarItem.ProfileItem,
        BottomBarItem.ProductosItem,
        BottomBarItem.EquipamientosIcon
    )
    var currentItem by rememberSaveable {
        mutableStateOf(BottomBarItem.ProfileItem.label)
    }
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
                    isSelected = currentItem == item.label,
                    icon = item.icon,
                    label = item.label,
                    onClick = { currentItem = item.label }
                )
            }
        }
    }
}