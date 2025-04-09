package  com.adielgarcia.gympro.presentation.equipamientos

import com.adielgarcia.gympro.data.remote.dto.entities.Equipamiento

sealed interface EquipamientosEvents {
    data class OnAddEquipamiento(val nombre: String, val descripcion: String) : EquipamientosEvents
    data class OnDeleteEquipamiento(val id: Int) : EquipamientosEvents
    data class OnEditEquipamiento(val equipamiento: Equipamiento) : EquipamientosEvents


    data class OnSearchChange(val query: String) : EquipamientosEvents
    data class OnSelectEquipamiento(val equipamiento: Equipamiento?) : EquipamientosEvents
}