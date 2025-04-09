package  com.adielgarcia.gympro.presentation.equipamientos

import com.adielgarcia.gympro.data.remote.dto.entities.Equipamiento

data class EquipamientosUiState(
    val search: String = "",
    val allEquipamientos: List<Equipamiento> = emptyList(),
    val equipamientos: List<Equipamiento> = emptyList(),

    val equipamientoSelected: Equipamiento? = null
)
