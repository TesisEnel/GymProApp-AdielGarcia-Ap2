package com.adielgarcia.gympro.presentation.suscripciones

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.adielgarcia.gympro.data.remote.dto.utilities.fetching.GetUsuarioDto
import com.adielgarcia.gympro.ui.components.SuscripcionCard
import com.adielgarcia.gympro.ui.theme.BlackOpsOne

@Composable
fun SuscripcionesScreen(
    management: Boolean = false,
    userData: GetUsuarioDto,
    viewModel: SuscripcionesViewModel = hiltViewModel(),
    launchNotification: (String) -> Unit
) {
    val uiState by viewModel.uiState.collectAsState()
    val onEvent = viewModel::onEvent

    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        item {
            Text(
                if (management) "Administrando Suscripciones" else "Suscripciones",
                fontFamily = BlackOpsOne,
                fontSize = 24.sp,
                textAlign = TextAlign.Center
            )
        }
        uiState.allSuscripciones.forEach { suscripcion ->
            item(key = suscripcion.suscripcionId) {
                SuscripcionCard(
                    suscripcion = suscripcion,
                    readonly = true,
                    modifier = Modifier
                        .padding(8.dp)
                        .clickable {
                            if (management) {
                                onEvent(SuscripcionesEvents.OnSelectSuscripcion(suscripcion))
                            } else {
                                onEvent(SuscripcionesEvents.OnUserChangeSuscripcion(userData.userId, userData.suscripcionId ?: 0, suscripcion.suscripcionId))
                                launchNotification("Suscripción seleccionada: ${suscripcion.nombre}, Los cambios se aplicaran al reiniciar")
                            }
                        }
                )
            }
        }
    }
}