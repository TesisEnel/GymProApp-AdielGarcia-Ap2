package  com.adielgarcia.gympro.presentation.login

import com.adielgarcia.gympro.data.remote.dto.utilities.fetching.GetUsuarioDto

sealed interface LoginEvents {
    data class UsernameChanged(val username: String) : LoginEvents
    data class PasswordChanged(val password: String) : LoginEvents

    data class OnRegister(val navigateToRegister: () -> Unit) : LoginEvents

    data class OnLogin(val navigateToMain: (GetUsuarioDto, Boolean) -> Unit, val showNotification: (String) -> Unit) : LoginEvents

    data object OnCleanErrorMessage : LoginEvents
    data object OnImageClicked: LoginEvents
}