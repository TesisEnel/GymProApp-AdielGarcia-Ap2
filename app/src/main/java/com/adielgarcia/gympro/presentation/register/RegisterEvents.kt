package com.adielgarcia.gympro.presentation.register

import com.adielgarcia.gympro.data.remote.dto.utilities.fetching.GetUsuarioDto

sealed interface RegisterEvents {
    data class UsernameChanged(val username: String) : RegisterEvents
    data class PasswordChanged(val password: String) : RegisterEvents
    data class RepeatedPasswordChanged(val repeatedPassword: String) : RegisterEvents

    data class NoIdentificacionChanged(val noIdentificacion: String) : RegisterEvents
    data class FechaNacimientoChanged(val fechaNacimiento: String) : RegisterEvents
    data class PesoChanged(val peso: Float) : RegisterEvents
    data class AlturaChanged(val altura: Float) : RegisterEvents
    data class GeneroChanged(val genero: String) : RegisterEvents

    data class SuscripcionChanged(val suscripcionId: Int, val nombre: String) : RegisterEvents

    data class OnLogin(val navigateToLogin: () -> Unit) : RegisterEvents

    data class OnCreateAccount(val navigateToMain: (GetUsuarioDto) -> Unit, val showNotification: (String) -> Unit) : RegisterEvents

    data object OnNextStep : RegisterEvents
    data object OnPreviousStep : RegisterEvents
}
