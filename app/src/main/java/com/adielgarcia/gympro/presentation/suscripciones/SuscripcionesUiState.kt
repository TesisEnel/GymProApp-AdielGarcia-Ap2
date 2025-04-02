package com.adielgarcia.gympro.presentation.suscripciones

import com.adielgarcia.gympro.data.remote.dto.entities.Suscripcion

data class SuscripcionesUiState(
    val allSuscripciones : List<Suscripcion> = emptyList()
)
