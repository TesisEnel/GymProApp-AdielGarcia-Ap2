package com.adielgarcia.gympro.presentation.equipamientos

import androidx.compose.foundation.layout.Column
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
import com.adielgarcia.gympro.ui.components.GymProContentCard
import com.adielgarcia.gympro.ui.components.GymProSearchbar
import com.adielgarcia.gympro.ui.theme.BlackOpsOne
import com.adielgarcia.gympro.ui.theme.PrimaryColor

@Composable
fun EquipamientosScreen(
    viewModel: EquipamientosViewModel = hiltViewModel(),
    launchNotification: (String) -> Unit
) {
    val uiState by viewModel.uiState.collectAsState()
    val onEvent = viewModel::onEvent

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 10.dp, vertical = 4.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            "Equipamientos",
            fontFamily = BlackOpsOne,
            fontSize = 24.sp,
            textAlign = TextAlign.Center
        )
        GymProSearchbar(
            search = uiState.search,
            onSearch = { onEvent(EquipamientosEvents.OnSearchChange(it)) },
            modifier = Modifier.padding(vertical = 6.dp)
        )
        LazyColumn(modifier = Modifier.padding(4.dp)) {
            uiState.equipamientos.forEach { equipamiento ->
                item(key = equipamiento.equipoId) {
                    GymProContentCard(Modifier.padding(vertical = 4.dp)) {
                        Column(modifier= Modifier.padding(4.dp)) {
                            Text(
                                text = equipamiento.nombre,
                                fontSize = 20.sp,
                                color = PrimaryColor,
                                modifier = Modifier.padding(vertical = 4.dp)
                            )
                            Text(text = "Descripción: ${equipamiento.descripcion}")
                        }
                    }
                }
            }
        }
    }
}