package com.adielgarcia.gympro.presentation.entrenadores

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.adielgarcia.gympro.data.remote.Rangos
import com.adielgarcia.gympro.data.remote.dto.entities.Entrenador
import com.adielgarcia.gympro.data.remote.dto.utilities.edit.UpdateEntrenadorDto
import com.adielgarcia.gympro.ui.components.GymProContentCard
import com.adielgarcia.gympro.ui.theme.BlackOpsOne
import com.adielgarcia.gympro.ui.theme.PrimaryColor
import com.adielgarcia.gympro.ui.theme.disabledTextfieldColors

@Composable
fun EntrenadorDialog(
    entrenador: Entrenador? = null,
    onDelete: (id: Int) -> Unit,
    onDismiss: () -> Unit,
    onCreate: (String, String) -> Unit,
    onModify: (UpdateEntrenadorDto) -> Unit
) {
    var nombre by remember { mutableStateOf(entrenador?.username ?: "") }
    var rango by remember { mutableStateOf(entrenador?.rango ?: "") }

    Dialog(
        onDismissRequest = onDismiss
    ) {
        GymProContentCard(
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(modifier = Modifier.padding(6.dp)) {
                Text(
                    text = if (entrenador == null) "Crear" else "Modificar" + " Entrenador",
                    fontFamily = BlackOpsOne,
                    modifier = Modifier.padding(bottom = 10.dp)
                )
                OutlinedTextField(
                    value = nombre,
                    readOnly = entrenador != null,
                    enabled = entrenador == null,
                    onValueChange = { nombre = it },
                    label = { Text("Nombre") },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 10.dp)
                )


                var rangosExpanded by remember { mutableStateOf(false) }

                OutlinedTextField(
                    value = rango,
                    onValueChange = { },
                    readOnly = true,
                    colors = disabledTextfieldColors(),
                    enabled = false,
                    label = { Text("Rango") },
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable { rangosExpanded = true }
                        .padding(bottom = 10.dp)
                )
                DropdownMenu(
                    expanded = rangosExpanded,
                    modifier = Modifier.fillMaxWidth(),
                    onDismissRequest = { rangosExpanded = false }) {
                    Text(
                        text = Rangos.PRINCIPIANTE,
                        modifier = Modifier.clickable {
                            rango = Rangos.PRINCIPIANTE; rangosExpanded = false
                        })
                    Text(
                        text = Rangos.INTERMEDIO,
                        modifier = Modifier.clickable {
                            rango = Rangos.INTERMEDIO; rangosExpanded = false
                        })
                    Text(
                        text = Rangos.PROFESIONAL,
                        modifier = Modifier.clickable {
                            rango = Rangos.PROFESIONAL; rangosExpanded = false
                        })
                    Text(
                        text = Rangos.MASTER,
                        modifier = Modifier.clickable {
                            rango = Rangos.MASTER; rangosExpanded = false
                        })
                }

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    Text(
                        "Eliminar",
                        color = if (entrenador == null) PrimaryColor.copy(alpha = 0.5f) else PrimaryColor,
                        modifier = Modifier.clickable { if (entrenador != null) onDelete(entrenador.userId); onDismiss() })
                    Text(
                        "Guardar", color = PrimaryColor,
                        modifier = Modifier.clickable {
                            if (entrenador == null) onCreate(
                                nombre,
                                rango
                            ) else onModify(
                                UpdateEntrenadorDto(
                                    entrenador.entrenadorId,
                                    rango
                                )
                            ); onDismiss()
                        })
                }
            }
        }
    }
}