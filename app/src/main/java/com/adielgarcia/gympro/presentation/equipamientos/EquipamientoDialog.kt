package  com.adielgarcia.gympro.presentation.equipamientos

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
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
import com.adielgarcia.gympro.data.remote.dto.entities.Equipamiento
import com.adielgarcia.gympro.ui.components.GymProContentCard
import com.adielgarcia.gympro.ui.theme.BlackOpsOne
import com.adielgarcia.gympro.ui.theme.PrimaryColor

@Composable
fun EquipamientoDialog(
    equipamiento: Equipamiento? = null,
    onDelete: (id: Int) -> Unit,
    onDismiss: () -> Unit,
    onCreate: (String, String) -> Unit,
    onModify: (Equipamiento) -> Unit
) {
    var nombre by remember { mutableStateOf(equipamiento?.nombre ?: "") }
    var descripcion by remember { mutableStateOf(equipamiento?.descripcion ?: "") }


    Dialog(
        onDismissRequest = onDismiss
    ) {
        GymProContentCard(
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(modifier = Modifier.padding(6.dp)) {
                Text(
                    text = if (equipamiento == null) "Crear" else "Modificar" + " Equipamiento",
                    fontFamily = BlackOpsOne,
                    modifier = Modifier.padding(bottom = 10.dp)
                )
                OutlinedTextField(
                    value = nombre,
                    onValueChange = { nombre = it },
                    label = { Text("Nombre") },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 10.dp)
                )
                OutlinedTextField(
                    value = descripcion,
                    onValueChange = { descripcion = it },
                    label = { Text("Decripcion") },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 10.dp)
                )

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    Text(
                        "Eliminar",
                        color = if (equipamiento == null) PrimaryColor.copy(alpha = 0.5f) else PrimaryColor,
                        modifier = Modifier.clickable {
                            if (equipamiento != null) onDelete(
                                equipamiento.equipoId
                            ); onDismiss()
                        }
                    )
                    Text(
                        "Guardar", color = PrimaryColor,
                        modifier = Modifier.clickable {
                            if (equipamiento == null) onCreate(
                                nombre,
                                descripcion
                            ) else onModify(
                                Equipamiento(
                                    equipamiento.equipoId,
                                    nombre,
                                    descripcion
                                )
                            ); onDismiss()
                        }
                    )
                }
            }
        }
    }
}