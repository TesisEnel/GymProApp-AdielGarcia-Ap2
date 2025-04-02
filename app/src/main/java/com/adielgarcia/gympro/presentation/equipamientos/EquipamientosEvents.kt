package com.adielgarcia.gympro.presentation.equipamientos

import com.adielgarcia.gympro.data.remote.dto.entities.Equipamiento

sealed interface EquipamientosEvents {

    data class OnSearchChange(val query: String) : EquipamientosEvents
    data class OnSelectEquipamiento(val equipamiento: Equipamiento) : EquipamientosEvents
}