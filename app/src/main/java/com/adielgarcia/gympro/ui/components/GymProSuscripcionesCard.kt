package com.adielgarcia.gympro.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.adielgarcia.gympro.data.remote.dto.entities.Suscripcion
import com.adielgarcia.gympro.ui.theme.PrimaryColor
import com.adielgarcia.gympro.ui.theme.SecondaryColor
import com.adielgarcia.gympro.ui.theme.Suscripcion1Color1
import com.adielgarcia.gympro.ui.theme.Suscripcion1Color2
import com.adielgarcia.gympro.ui.theme.Suscripcion2Color1
import com.adielgarcia.gympro.ui.theme.Suscripcion2Color2
import com.adielgarcia.gympro.ui.theme.Suscripcion3Color1
import com.adielgarcia.gympro.ui.theme.Suscripcion3Color2

@Composable
fun SuscripcionCard(
    suscripcion: Suscripcion,
    modifier: Modifier = Modifier,
    readonly: Boolean = false,
    selected: Boolean = false,
    onItemClick: ((Suscripcion) -> Unit)? = null
) {
    val colors = when (suscripcion.suscripcionId) {
        1 -> listOf(Suscripcion1Color1, Suscripcion1Color2)
        2 -> listOf(Suscripcion2Color1, Suscripcion2Color2)
        3 -> listOf(Suscripcion3Color1, Suscripcion3Color2)
        else -> listOf(PrimaryColor, SecondaryColor)
    }
    val cardModifier = if (readonly) modifier else modifier.clickable {
        if (onItemClick != null) {
            onItemClick(suscripcion)
        }
    }

    Column(
        modifier = cardModifier
            .fillMaxWidth()
            .background(brush = diagonalGradient(colors = colors), shape = RoundedCornerShape(4.dp))
            .border(1.dp, Color.Black.copy(alpha = 0.2f))
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                suscripcion.nombre,
                fontSize = 18.sp,
                color = Color.Black.copy(.45f),
                fontWeight = if (selected) FontWeight.Bold else FontWeight.Normal
            )
            Text(
                "$" + suscripcion.precio,
                fontSize = 18.sp,
                color = Color.Black.copy(.45f),
                fontWeight = if (selected) FontWeight.Bold else FontWeight.Normal
            )
        }
        Text(
            suscripcion.descripcion,
            color = Color.Black.copy(.45f),
            fontWeight = if (selected) FontWeight.Bold else FontWeight.Normal,
            modifier = Modifier.padding(start = 20.dp, end = 20.dp, bottom = 20.dp)
        )
    }

}
