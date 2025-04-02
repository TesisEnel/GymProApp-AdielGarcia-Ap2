package com.adielgarcia.gympro.presentation.login

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.adielgarcia.gympro.data.remote.Resource
import com.adielgarcia.gympro.data.remote.dto.utilities.fetching.GetUsuarioDto
import com.adielgarcia.gympro.data.remote.repository.UsuariosRepos
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val usuariosRepos: UsuariosRepos
) : ViewModel() {

    private val _uiState = MutableStateFlow(LoginUiState())
    val uiState = _uiState.asStateFlow()

    fun onEvent(event: LoginEvents) {
        when (event) {
            is LoginEvents.UsernameChanged -> {
                usernameChange(event.username)
            }

            is LoginEvents.PasswordChanged -> {
                passwordChange(event.password)
            }

            is LoginEvents.OnRegister -> {
                event.navigateToRegister()
            }

            is LoginEvents.OnLogin -> {
                onLogin(event.navigateToMain, event.showNotification)
            }

            is LoginEvents.OnCleanErrorMessage -> {
                onCleanErrorMessage()
            }

            is LoginEvents.OnImageClicked -> {
                onImageClicked()
            }
        }
    }

    private fun usernameChange(username: String) {
        _uiState.value = _uiState.value.copy(
            username = username
        )
    }

    private fun passwordChange(password: String) {
        _uiState.value = _uiState.value.copy(
            password = password
        )
    }

    private fun onCleanErrorMessage() {
        _uiState.value = _uiState.value.copy(
            errorMessage = null
        )
    }

    private fun onLogin(
        navigateToMain: (GetUsuarioDto, Boolean) -> Unit,
        showNotification: (String) -> Unit
    ) {
        if (!fieldsAreValid()) return;

        viewModelScope.launch {
            usuariosRepos.login(
                username = _uiState.value.username,
                password = _uiState.value.password
            ).collect { result ->
                when (result) {
                    is Resource.Success -> {
                        _uiState.value = _uiState.value.copy(
                            errorMessage = null,
                            isLoading = false
                        )
                        if (result.data == null) {
                            showNotification("No se encontro un usuario con esas credenciales")
                        } else {
                            showNotification("Bienvenido ${result.data.username}")
                            navigateToMain(result.data, _uiState.value.adminMode)
                        }
                    }

                    is Resource.Error -> {
                        _uiState.value = _uiState.value.copy(
                            isLoading = false
                        )
                        showNotification(result.message ?: "Error desconocido")
                    }

                    is Resource.Loading -> {
                        _uiState.value = _uiState.value.copy(
                            errorMessage = null,
                            isLoading = true
                        )
                    }
                }
            }
        }
    }

    private fun fieldsAreValid(): Boolean {
        var stillValid = true;
        if (_uiState.value.username.isBlank()) {
            _uiState.value = _uiState.value.copy(
                usernameError = "El username no puede estar vacío"
            )
            stillValid = false;
        } else {
            _uiState.value = _uiState.value.copy(
                usernameError = null
            )
        }
        if (_uiState.value.password.isBlank()) {
            _uiState.value = _uiState.value.copy(
                passwordError = "La clave no puede estar vacía"
            )
            stillValid = false;
        } else {
            _uiState.value = _uiState.value.copy(
                passwordError = null
            )
        }
        return stillValid;
    }

    private fun onImageClicked() {
        if (uiState.value.clickCount == 4) return;

        _uiState.value = _uiState.value.copy(
            clickCount = _uiState.value.clickCount + 1
        )
        if (_uiState.value.clickCount == 4) {
            _uiState.value = _uiState.value.copy(
                adminMode = true
            )
        }
    }
}
