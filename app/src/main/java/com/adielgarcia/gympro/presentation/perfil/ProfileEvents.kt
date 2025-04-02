package com.adielgarcia.gympro.presentation.perfil

sealed interface ProfileEvents {
    data class LoadEntrenadorData(val id: Int) : ProfileEvents
    data class LoadSuscripcionData(val id: Int) : ProfileEvents

    data class OnUpdatePeso(val peso: Float) : ProfileEvents
    data class OnUpdateAltura(val altura: Float) : ProfileEvents
    data class OnChangePassword(val password: String) : ProfileEvents
    data class OnChangeRepeatedPassword(val repeatedPassword: String) : ProfileEvents

    data class SavePeso(val id: Int, val showNotification: (String) -> Unit) : ProfileEvents
    data class SaveAltura(val id: Int, val showNotification: (String) -> Unit) : ProfileEvents

    data class SavePassword(val id: Int, val showNotification: (String) -> Unit) : ProfileEvents


}