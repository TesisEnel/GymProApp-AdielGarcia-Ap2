package com.adielgarcia.gympro.presentation.perfil

import com.adielgarcia.gympro.data.remote.dto.entities.Entrenador
import com.adielgarcia.gympro.data.remote.dto.entities.Suscripcion

data class ProfileUiState(
    val entrenador: Entrenador? = null,
    val suscripcion: Suscripcion? = null,

    val edad: String= "",
    val peso: String = "",
    val altura: String = "",
    val password: String = "",
    val repeatedPassword: String = "",

    val isLoading: Boolean = false
)
