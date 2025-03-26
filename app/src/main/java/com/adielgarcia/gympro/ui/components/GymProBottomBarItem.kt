package com.adielgarcia.gympro.ui.components

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.adielgarcia.gympro.ui.theme.shadow

@Composable
fun GymProBottomBarItem(isSelected: Boolean, icon: Int, label: String, onClick: () -> Unit) {
    val iconSize by animateDpAsState(
        targetValue = if (isSelected) 30.dp else 25.dp,
        animationSpec = tween(durationMillis = 300),
        label = "Icon Size Animation"
    )

    val bottomPadding by animateDpAsState(
        targetValue = if (isSelected) 5.dp else 0.dp,
        animationSpec = tween(durationMillis = 300),
        label = "Bottom Padding Animation"
    )
    Column(modifier = Modifier.clickable { onClick() }) {
        Image(
            painter = painterResource(id = icon),
            contentDescription = label,
            modifier = Modifier
                .size(iconSize)
                .padding(bottom = bottomPadding)
                .shadow(color = Color(0x80000000), blurRadius = 10.dp)
        )
        if (isSelected) {
            Text(text = label)
        }
    }
}