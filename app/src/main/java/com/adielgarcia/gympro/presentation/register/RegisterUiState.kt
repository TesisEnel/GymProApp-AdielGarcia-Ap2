package com.adielgarcia.gympro.presentation.register

import com.adielgarcia.gympro.data.remote.dto.entities.Suscripcion

data class RegisterUiState(
    //Fields for the first step
    val username: String = "",
    val usernameError: String? = null,
    val password: String = "",
    val passwordError: String? = null,
    val repeatedPassword: String = "",
    val repeatedPasswordError: String? = null,

    //Fields for the second step
    val noIdentificacion: String = "",
    val noIdentificacionError: String? = null,
    val fechaNacimiento: String = "",
    val fechaNacimientoError: String? = null,
    val peso: Float = 0f,
    val pesoError: String? = null,
    val altura: Float = 0f,
    val alturaError: String? = null,
    val genero: String = "",
    val generoError: String? = null,

    //Fields for the third step
    val suscripcionId: Int = 0,
    val suscripcion: String = "",
    val suscripcionIdError: String? = null,

    val isLoading: Boolean = false,
    val errorMessage: String? = null,

    val currentStep: Int = 1,

    val suscripciones: List<Suscripcion> = emptyList()
)
