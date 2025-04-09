package  com.adielgarcia.gympro.presentation.entrenadores

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Verified
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.adielgarcia.gympro.DEFAULT_COACH_PASSWORD
import com.adielgarcia.gympro.data.remote.dto.utilities.fetching.GetUsuarioDto
import com.adielgarcia.gympro.ui.components.ConfirmacionDialog
import com.adielgarcia.gympro.ui.components.GymProContentCard
import com.adielgarcia.gympro.ui.components.GymProSearchbar
import com.adielgarcia.gympro.ui.components.GymProTitleCard
import com.adielgarcia.gympro.ui.theme.BlackOpsOne
import com.adielgarcia.gympro.ui.theme.PrimaryColor

@Composable
fun EntrenadoresScreen(
    management: Boolean = false,
    userData: GetUsuarioDto,
    admin : Boolean,
    onLogout: (() -> Unit)? = null,
    viewmodel: EntrenadoresViewModel = hiltViewModel(),
    launchNotification: (String) -> Unit
) {
    val uiState by viewmodel.uiState.collectAsState()
    val onEvent = viewmodel::onEvent

    var onCrear by remember { mutableStateOf(false) }
    var entrenadorId by remember { mutableIntStateOf(0) }

    if (uiState.selectedEntrenador != null || onCrear) {
        EntrenadorDialog(
            entrenador = uiState.selectedEntrenador,
            onDelete = {
                onEvent(EntrenadoresEvents.OnDeleteEntrenador(it))
            },
            onDismiss = {
                onEvent(EntrenadoresEvents.OnSelectEntrenador(null))
                onCrear = false
            },
            onCreate = { nombre, categoria ->
                onEvent(EntrenadoresEvents.OnAddEntrenador(nombre, categoria))
                launchNotification("Entrenador creado exitosamente con la contraseña '$DEFAULT_COACH_PASSWORD'")
            },
            onModify = { ent ->
                onEvent(EntrenadoresEvents.OnEditEntrenador(ent))
            }
        )
    }
    if (entrenadorId != 0) {
        ConfirmacionDialog(
            title = "¿Estas seguro que quieres cambiar de entrenador?",
            message = "¡Esta operacion te cerrará la sesion actual!",
            onDismiss = { entrenadorId = 0},
            onConfirm = {
                onEvent(EntrenadoresEvents.OnUserChangeEntrenador(
                    userId = userData.userId,
                    oldEntrenadorId = userData.entrenadorId ?: 0,
                    newEntrenadorId = entrenadorId,
                    onLogout = onLogout ?: {}
                ))
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
                if (management) "Administrando Entrenadores" else "Entrenadores",
                fontFamily = BlackOpsOne,
                fontSize = 24.sp,
                textAlign = TextAlign.Center
            )
            GymProSearchbar(
                search = uiState.search,
                onSearch = { onEvent(EntrenadoresEvents.OnSearchChange(it)) },
                modifier = Modifier.padding(vertical = 6.dp)
            )
            if (uiState.entrenadores.isEmpty()) {
                Text(text = "No hay entrenadores")
            }
            LazyColumn(modifier = Modifier.padding(4.dp)) {
                uiState.entrenadores.forEach { entrenador ->
                    item(key = entrenador.entrenadorId) {
                        GymProContentCard(
                            Modifier
                                .clickable {
                                    if (management) {
                                        onEvent(
                                            EntrenadoresEvents.OnSelectEntrenador(
                                                entrenador
                                            )
                                        )
                                    } else {
                                        if (userData.suscripcionId == 1) {
                                            launchNotification("Tu suscripcion no incluye a un entrenador. Si quieres un entrenador debes cambiar de suscripcion")
                                            return@clickable;
                                        }
                                        if (entrenador.entrenadorId == userData.entrenadorId) {
                                            launchNotification("No puedes cambiar a tu mismo entrenador")
                                            return@clickable;
                                        }
                                        entrenadorId = entrenador.entrenadorId
                                    }
                                }
                                .padding(vertical = 4.dp)
                                .fillMaxWidth()) {
                            Column(
                                modifier = Modifier
                                    .padding(10.dp)
                                    .fillMaxWidth()
                            ) {
                                if (entrenador.entrenadorId == userData.entrenadorId) {
                                    GymProTitleCard("Mi Entrenador")
                                }
                                Row(
                                    horizontalArrangement = Arrangement.SpaceBetween,
                                    modifier = Modifier.fillMaxWidth()
                                ) {
                                    Row {
                                        Icon(
                                            imageVector = Icons.Filled.Person, null
                                        )
                                        Text(
                                            text = entrenador.username
                                                ?: "Sin Usuario asignado (#${entrenador.entrenadorId})",
                                            fontSize = 20.sp,
                                            color = PrimaryColor,
                                            modifier = Modifier.padding(vertical = 4.dp)
                                        )
                                    }
                                    Row {
                                        Icon(
                                            imageVector = Icons.Default.Verified, null
                                        )
                                        Text(
                                            text = entrenador.rango,
                                            modifier = Modifier.padding(vertical = 4.dp)
                                        )
                                    }
                                }
                                if(admin){
                                    Text(
                                        text = "${entrenador.clientesInscritos} clientes inscritos",
                                        modifier = Modifier.padding(vertical = 4.dp)
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}