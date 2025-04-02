package com.adielgarcia.gympro.presentation.productos

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.adielgarcia.gympro.ui.components.GymProContentCard
import com.adielgarcia.gympro.ui.components.GymProSearchbar
import com.adielgarcia.gympro.ui.components.SearchbarType
import com.adielgarcia.gympro.ui.theme.BlackOpsOne
import com.adielgarcia.gympro.ui.theme.PrimaryColor

@Composable
fun ProductosScreen(
    viewModel: ProductosViewModel = hiltViewModel(),
    management: Boolean = false,
    launchNotification: (String) -> Unit
) {
    val uiState by viewModel.uiState.collectAsState()
    val onEvent = viewModel::onEvent

    var onCrear by remember { mutableStateOf(false) }

    if (uiState.selectedProducto != null || onCrear) {
        ProductoDialog(
            producto = uiState.selectedProducto,
            onDelete = {
                onEvent(ProductosEvents.OnDeleteProductoClick(it))
            },
            onDismiss = {
                onEvent(ProductosEvents.OnSelectProducto(null))
                onCrear = false
            },
            onCreate = { nombre, precio, categoria ->
                onEvent(ProductosEvents.OnAddProductoClick(nombre, precio, categoria))
            },
            onModify = {
                onEvent(ProductosEvents.OnEditProductoClick(it))
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
            modifier = Modifier.padding(15.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                if (management) "Administrando Productos" else "Productos",
                fontFamily = BlackOpsOne,
                fontSize = 24.sp,
                textAlign = TextAlign.Center
            )
            GymProSearchbar(
                search = uiState.search,
                type = SearchbarType.Text,
                onSearch = {
                    onEvent(ProductosEvents.OnSearchChange(it))
                },
                modifier = Modifier.padding(bottom = 10.dp)
            )
            LazyColumn(modifier = Modifier.fillMaxWidth()) {
                uiState.productos.forEach { producto ->
                    item {
                        GymProContentCard(
                            modifier = Modifier
                                .padding(vertical = 10.dp)
                                .clickable {
                                    if (management)
                                        onEvent(ProductosEvents.OnSelectProducto(producto))
                                    else
                                        launchNotification("Si quieres comprar '${producto.nombre}' debes cruzar por nuestro establecimiento fisico")
                                }
                        ) {
                            Column(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(6.dp)
                            ) {
                                Row(
                                    modifier = Modifier.fillMaxWidth(),
                                    horizontalArrangement = Arrangement.SpaceBetween
                                ) {
                                    Text(
                                        producto.nombre,
                                        color = PrimaryColor,
                                        fontWeight = FontWeight.SemiBold
                                    )
                                    Text(producto.categoria)
                                }
                                Text("$ " + producto.precio, fontWeight = FontWeight.Bold)
                            }
                        }
                    }
                }
            }
        }
    }

}