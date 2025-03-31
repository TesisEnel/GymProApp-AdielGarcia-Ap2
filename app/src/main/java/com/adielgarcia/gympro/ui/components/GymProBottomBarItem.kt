package com.adielgarcia.gympro.ui.components

import android.annotation.SuppressLint
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.absoluteOffset
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.adielgarcia.gympro.ui.theme.shadow

@SuppressLint("UseOfNonLambdaOffsetOverload")
@Composable
fun GymProBottomBarItem(isSelected: Boolean, icon: Int, label: String, onClick: () -> Unit) {
    val iconSize by animateDpAsState(
        targetValue = if (isSelected) 50.dp else 35.dp,
        animationSpec = tween(durationMillis = 300),
        label = "Icon Size Animation"
    )

    val offsetValue by animateDpAsState(
        targetValue = if (isSelected) (-20).dp else 10.dp,
        animationSpec = tween(durationMillis = 300),
        label = "Bottom Padding Animation"
    )
    Column(
        modifier = Modifier
            .clickable { onClick() },
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = icon),
            contentDescription = label,
            modifier = Modifier
                .size(iconSize)
                .absoluteOffset(y = offsetValue)
                .shadow(
                    color = if (isSelected) Color(0x80000000) else Color.Transparent,
                    blurRadius = 15.dp
                )
        )
        if (isSelected) {
            Text(text = label)
        }
    }
}
