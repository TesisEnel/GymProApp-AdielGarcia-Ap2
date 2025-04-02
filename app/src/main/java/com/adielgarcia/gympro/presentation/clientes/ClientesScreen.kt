package com.adielgarcia.gympro.presentation.clientes

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.adielgarcia.gympro.presentation.entitiesPresentation.EntrenadorPresentation
import com.adielgarcia.gympro.ui.components.GymProContentCard
import com.adielgarcia.gympro.ui.components.GymProSearchbar
import com.adielgarcia.gympro.ui.theme.BlackOpsOne
import com.adielgarcia.gympro.ui.theme.PrimaryColor

@Composable
fun ClientesScreen(
    viewModel: ClientesViewModel = hiltViewModel(),
    entrenador: EntrenadorPresentation,
    launchNotification: (String) -> Unit
) {
    val uiState by viewModel.uiState.collectAsState()
    val onEvent = viewModel::onEvent
    remember {
        onEvent(ClientesEvents.FindByEntrenador(entrenador.entrenadorId))
        0
    }
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 10.dp, vertical = 4.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            "Clientes",
            fontFamily = BlackOpsOne,
            fontSize = 24.sp,
            textAlign = TextAlign.Center
        )
        GymProSearchbar(
            search = uiState.search,
            onSearch = { onEvent(ClientesEvents.OnSearchChange(it)) },
            modifier = Modifier.padding(vertical = 6.dp)
        )

        LazyColumn(modifier = Modifier.padding(4.dp)) {
            uiState.clientes.forEach { cliente ->
                item(key = cliente.clienteId) {
                    GymProContentCard(
                        Modifier
                            .padding(vertical = 4.dp)
                            .fillMaxWidth()
                    ) {
                        Column(
                            modifier = Modifier
                                .padding(10.dp)
                                .fillMaxWidth()
                        ) {
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(vertical = 4.dp),
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                Text(
                                    text = cliente.username,
                                    fontSize = 20.sp,
                                    color = PrimaryColor,
                                )
                                Text(
                                    text = cliente.suscripcion ?: "Sin Suscripcion",
                                )
                            }
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(vertical = 4.dp),
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                Text(
                                    text = cliente.peso.toString() + " lbs",
                                )
                                Text(
                                    text = cliente.altura.toString() + " mts",
                                )
                            }
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(vertical = 4.dp),
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                Text(
                                    text = "${cliente.edad} años",
                                )
                                Text(
                                    text = if (cliente.genero == 'M') "Masculino" else "Femenino"
                                )

                            }
                        }
                    }
                }
            }
        }
    }
}