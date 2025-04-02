package com.adielgarcia.gympro.presentation.entrenadores

import com.adielgarcia.gympro.data.remote.dto.entities.Entrenador

sealed interface EntrenadoresEvents {
    data class OnSearchChange(val query: String) : EntrenadoresEvents
    data class OnSelectEntrenador(val entrenador: Entrenador) : EntrenadoresEvents


}