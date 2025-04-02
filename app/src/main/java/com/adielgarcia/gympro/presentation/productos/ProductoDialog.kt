package com.adielgarcia.gympro.presentation.productos

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
import com.adielgarcia.gympro.data.remote.Categorias
import com.adielgarcia.gympro.data.remote.dto.entities.Producto
import com.adielgarcia.gympro.ui.components.GymProContentCard
import com.adielgarcia.gympro.ui.components.SearchbarType
import com.adielgarcia.gympro.ui.theme.BlackOpsOne
import com.adielgarcia.gympro.ui.theme.PrimaryColor

@Composable
fun ProductoDialog(
    producto: Producto? = null,
    onDelete: (id: Int) -> Unit,
    onDismiss: () -> Unit,
    onCreate: (String, Float, String) -> Unit,
    onModify: (Producto) -> Unit
) {
    var nombre by remember { mutableStateOf(producto?.nombre ?: "") }
    var categoria by remember { mutableStateOf(producto?.categoria ?: "") }
    var precio by remember { mutableStateOf(producto?.precio ?: 0.0) }

    Dialog(
        onDismissRequest = onDismiss
    ) {
        GymProContentCard(
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(modifier = Modifier.padding(6.dp)) {
                Text(
                    text = if (producto == null) "Crear" else "Modificar" + " Producto",
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
                    value = precio.toString(),
                    onValueChange = { if (it.toDoubleOrNull() != null) precio = it.toDouble() },
                    label = { Text("Precio") },
                    keyboardOptions = SearchbarType.Number,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 10.dp)
                )
                var categoriasExpanded by remember { mutableStateOf(false) }

                OutlinedTextField(
                    value = categoria,
                    onValueChange = { },
                    readOnly = true,
                    enabled = false,
                    label = { Text("Categoria") },
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable { categoriasExpanded = true }
                        .padding(bottom = 10.dp)
                )
                DropdownMenu(
                    expanded = categoriasExpanded,
                    modifier = Modifier.fillMaxWidth(),
                    onDismissRequest = { categoriasExpanded = false }) {
                    Text(
                        text = Categorias.ACCESORIOS,
                        modifier = Modifier.clickable {
                            categoria = Categorias.ACCESORIOS; categoriasExpanded = false
                        })
                    Text(
                        text = Categorias.ROPA,
                        modifier = Modifier.clickable {
                            categoria = Categorias.ROPA; categoriasExpanded = false
                        })
                    Text(
                        text = Categorias.SUPLEMENTOS,
                        modifier = Modifier.clickable {
                            categoria = Categorias.SUPLEMENTOS; categoriasExpanded = false
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
                        color = if (producto == null) PrimaryColor.copy(alpha = 0.5f) else PrimaryColor,
                        modifier = Modifier.clickable { if (producto != null) onDelete(producto.productoId); onDismiss() })
                    Text(
                        "Guardar", color = PrimaryColor,
                        modifier = Modifier.clickable {
                            if (producto == null) onCreate(
                                nombre,
                                precio.toFloat(),
                                categoria
                            ) else onModify(
                                Producto(
                                    producto.productoId,
                                    nombre,
                                    categoria,
                                    precio.toFloat()
                                )
                            ); onDismiss()
                        })
                }
            }
        }
    }
}