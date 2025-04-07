package com.adielgarcia.gympro.presentation.perfil

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Fingerprint
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.adielgarcia.gympro.presentation.entitiesPresentation.ClientePresentation
import com.adielgarcia.gympro.ui.components.GymProContentCard
import com.adielgarcia.gympro.ui.components.GymProTitleCard
import com.adielgarcia.gympro.ui.components.SearchbarType
import com.adielgarcia.gympro.ui.components.SuscripcionCard
import com.adielgarcia.gympro.ui.theme.PrimaryColor
import com.adielgarcia.gympro.utils.extensors.maskCedula

@Composable
fun ProfileScreen(
    launchNotification: (String) -> Unit,
    goToEntrenadores: () -> Unit,
    viewModel: ProfileViewModel = hiltViewModel(),
    userData: ClientePresentation
) {

    val onEvent = viewModel::onEvent
    val uiState by viewModel.uiState.collectAsState()
    remember {
        onEvent(ProfileEvents.LoadEntrenadorData(userData.entrenadorId))
        0
    }
    remember {
        onEvent(ProfileEvents.LoadData(userData))
        0
    }
    remember {
        onEvent(ProfileEvents.LoadSuscripcionData(userData.suscripcionId))
        0
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

        GymProTitleCard("Usuario")
        GymProContentCard(modifier = Modifier.padding(vertical = 10.dp)) {
            Row(modifier = Modifier.fillMaxWidth()) {
                Column(modifier = Modifier.weight(1f), horizontalAlignment = Alignment.Start) {
                    Row(modifier = Modifier.padding(10.dp)) {
                        Icon(
                            imageVector = Icons.Default.Person,
                            null
                        )
                        Text(text = userData.username, modifier = Modifier.padding(start = 10.dp))
                    }
                    Row(modifier = Modifier.padding(10.dp)) {
                        Icon(
                            imageVector = Icons.Default.Fingerprint,
                            null
                        )
                        Text(
                            text = userData.noIdentificacion.maskCedula(),
                            modifier = Modifier.padding(start = 10.dp)
                        )
                    }
                }
                Column(modifier = Modifier.weight(1f), horizontalAlignment = Alignment.End) {
                    Row(modifier = Modifier.padding(10.dp)) {
                        Text(text = userData.suscripcion, color = PrimaryColor)
                    }
                    Row(modifier = Modifier.padding(10.dp)) {
                        Text(text = "Edad: " + userData.edad)
                    }
                }
            }
        }
        Column {
            GymProTitleCard("Datos Personales")
            GymProContentCard(modifier = Modifier.padding(vertical = 10.dp)) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp)
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(4.dp),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        TextField(
                            value = uiState.peso,
                            keyboardOptions = SearchbarType.Number,
                            onValueChange = { onEvent(ProfileEvents.OnUpdatePeso(it.toFloat())) },
                            label = { Text("Peso (Lbs)") },
                            modifier = Modifier
                                .weight(1f)
                                .padding(end = 10.dp)
                        )
                        TextButton(
                            enabled = uiState.peso != userData.peso.toString(),
                            modifier = Modifier.weight(1f),
                            onClick = {
                                onEvent(
                                    ProfileEvents.SavePeso(
                                        userData.userId,
                                        launchNotification
                                    )
                                )
                            }) {
                            Text("Actualizar peso")
                        }
                    }
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(4.dp),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        TextField(
                            value = uiState.altura,
                            modifier = Modifier
                                .weight(1f)
                                .padding(end = 10.dp),
                            keyboardOptions = SearchbarType.Number,
                            onValueChange = { onEvent(ProfileEvents.OnUpdateAltura(it.toFloat())) },
                            label = { Text("Altura (Mts)") }
                        )
                        TextButton(
                            enabled = uiState.altura != userData.altura.toString(),
                            modifier = Modifier.weight(1f),
                            onClick = {
                                onEvent(
                                    ProfileEvents.SaveAltura(
                                        userData.userId,
                                        launchNotification
                                    )
                                )
                            }) {
                            Text("Actualizar altura")
                        }
                    }
                }
            }
        }
        if (userData.entrenadorId != 0 && userData.suscripcionId > 1) {
            Column(
                modifier = Modifier.padding(10.dp)
            ) {
                GymProTitleCard("Entrenador")
                GymProContentCard(modifier = Modifier.padding(vertical = 10.dp)) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Column(
                            modifier = Modifier.weight(1f),
                            horizontalAlignment = Alignment.Start
                        ) {
                            Row(modifier = Modifier.padding(10.dp)) {
                                Icon(
                                    imageVector = Icons.Default.Person,
                                    null
                                )
                                Text(
                                    text = userData.entrenador,
                                    modifier = Modifier.padding(start = 10.dp)
                                )
                            }
                        }
                        Text(
                            uiState.entrenador?.rango ?: "",
                            modifier = Modifier
                                .weight(1f)
                                .padding(end = 10.dp),
                            textAlign = TextAlign.End
                        )
                    }
                }
            }
        } else if (userData.entrenadorId == 0 && userData.suscripcionId > 1) {
            launchNotification("No tienes un entrenador asignado")
            Button(
                onClick = { goToEntrenadores() },
                modifier = Modifier.align(Alignment.CenterHorizontally)
            ) {
                Text("Ir a Entrenadores")
            }
        }
        Column(
            modifier = Modifier.padding(10.dp)
        ) {
            uiState.suscripcion?.let { SuscripcionCard(readonly = true, suscripcion = it) }
        }
    }
}