package com.adielgarcia.gympro.presentation.equipamientos

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.adielgarcia.gympro.R
import com.adielgarcia.gympro.ui.components.GymProContentCard
import com.adielgarcia.gympro.ui.components.GymProSearchbar
import com.adielgarcia.gympro.ui.theme.BlackOpsOne
import com.adielgarcia.gympro.ui.theme.PrimaryColor

@Composable
fun EquipamientosScreen(
    management: Boolean = false,
    viewModel: EquipamientosViewModel = hiltViewModel(),
    launchNotification: (String) -> Unit
) {
    val uiState by viewModel.uiState.collectAsState()
    val onEvent = viewModel::onEvent

    var onCrear by remember { mutableStateOf(false) }
    if (uiState.equipamientoSelected != null || onCrear) {
        EquipamientoDialog(
            equipamiento = uiState.equipamientoSelected,
            onDelete = {
                onEvent(EquipamientosEvents.OnDeleteEquipamiento(it))
            },
            onDismiss = {
                onEvent(EquipamientosEvents.OnSelectEquipamiento(null))
                onCrear = false
            },
            onCreate = { nombre, descripcion ->
                onEvent(EquipamientosEvents.OnAddEquipamiento(nombre, descripcion))
            },
            onModify = {
                onEvent(EquipamientosEvents.OnEditEquipamiento(it))
            }
        )
    }

    Scaffold(
        floatingActionButton = {
            if (management) {
                FloatingActionButton(onClick = { onCrear = true }) {
                    Text("+")
                }
            }
        }
    ) { _ ->
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 10.dp, vertical = 4.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                if (management) "Administrando Equipamientos" else "Equipamientos",
                fontFamily = BlackOpsOne,
                fontSize = 24.sp,
                textAlign = TextAlign.Center
            )
            GymProSearchbar(
                search = uiState.search,
                onSearch = { onEvent(EquipamientosEvents.OnSearchChange(it)) },
                modifier = Modifier.padding(vertical = 6.dp)
            )
            if (uiState.equipamientos.isEmpty()) {
                Text(text = "No hay equipamientos")
            }
            LazyColumn(modifier = Modifier.padding(4.dp)) {
                uiState.equipamientos.forEach { equipamiento ->
                    item(key = equipamiento.equipoId) {
                        GymProContentCard(
                            Modifier
                                .padding(vertical = 4.dp)
                                .fillMaxWidth()
                                .clickable {
                                    if (management)
                                        onEvent(
                                            EquipamientosEvents.OnSelectEquipamiento(
                                                equipamiento
                                            )
                                        )
                                }
                        ) {
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(4.dp)
                            ) {
                                Image(
                                    painter = painterResource(R.drawable.gym_equipment),
                                    null,
                                    modifier = Modifier
                                        .size(70.dp)
                                        .padding(8.dp)
                                )
                                Column(
                                    modifier = Modifier
                                        .padding(4.dp)
                                        .fillMaxWidth()
                                ) {
                                    Text(
                                        text = equipamiento.nombre,
                                        fontSize = 20.sp,
                                        color = PrimaryColor,
                                        modifier = Modifier.padding(vertical = 4.dp)
                                    )
                                    Text(text = equipamiento.descripcion)
                                }
                            }

                        }
                    }
                }
            }
        }
    }
}