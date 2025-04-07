package com.adielgarcia.gympro.presentation.suscripciones

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.adielgarcia.gympro.data.remote.dto.utilities.fetching.GetUsuarioDto
import com.adielgarcia.gympro.ui.components.ConfirmacionDialog
import com.adielgarcia.gympro.ui.components.SuscripcionCard
import com.adielgarcia.gympro.ui.theme.BlackOpsOne

@Composable
fun SuscripcionesScreen(
    management: Boolean = false,
    userData: GetUsuarioDto,
    onLogout: (() -> Unit)? = null,
    launchNotification: ((String) -> Unit)? = null,
    viewModel: SuscripcionesViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()
    val onEvent = viewModel::onEvent

    var seleccionada by remember { mutableIntStateOf(0) }

    if (seleccionada != 0) {
        val extraInformacion =
            if (seleccionada == 1) "\n¡Esta suscripcion no incluye entrenador, se eliminara tu entrenador actual!" else ""
        ConfirmacionDialog(
            title = "¿Estas seguro que quieres cambiar de suscripcion?",
            message = "¡Debes ir a nuestra sucursal física a realizar el pago correspondiente!\n¡Esta operacion te cerrará la sesion actual!$extraInformacion",
            onDismiss = { seleccionada = 0 },
            onConfirm = {
                onEvent(
                    SuscripcionesEvents.OnUserChangeSuscripcion(
                        userData.userId,
                        oldSuscripcionId = userData.suscripcionId ?: 0,
                        newSuscripcionId = seleccionada,
                        oldEntrenadorId = userData.entrenadorId ?: 0
                    )
                )
                if (onLogout != null) onLogout()
            }
        )
    }


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
        if (uiState.allSuscripciones.isEmpty()) {
            item {
                Text(text = "No hay suscripciones")
            }
        }
        uiState.allSuscripciones.forEach { suscripcion ->
            item(key = suscripcion.suscripcionId) {
                SuscripcionCard(
                    suscripcion = suscripcion,
                    readonly = true, selected = suscripcion.suscripcionId == userData.suscripcionId,
                    modifier = Modifier
                        .padding(8.dp)
                        .clickable {
                            if (management) {
                                onEvent(SuscripcionesEvents.OnSelectSuscripcion(suscripcion))
                            } else {
                                if (suscripcion.suscripcionId == userData.suscripcionId && launchNotification != null) {
                                    launchNotification("No puedes cambiar a la misma suscripcion")
                                    return@clickable
                                }

                                seleccionada = suscripcion.suscripcionId
                            }
                        }
                )
            }
        }
    }
}