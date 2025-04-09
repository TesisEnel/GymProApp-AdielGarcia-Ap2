package  com.adielgarcia.gympro.presentation.register

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.adielgarcia.gympro.data.remote.Generos
import com.adielgarcia.gympro.data.remote.Resource
import com.adielgarcia.gympro.data.remote.dto.utilities.create.AddClienteDto
import com.adielgarcia.gympro.data.remote.dto.utilities.fetching.GetUsuarioDto
import com.adielgarcia.gympro.data.remote.repository.SuscripcionesRepos
import com.adielgarcia.gympro.data.remote.repository.UsuariosRepos
import com.adielgarcia.gympro.utils.extensors.toCreateAccountDto
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val usuariosRepos: UsuariosRepos,
    private val suscripcionesRepos: SuscripcionesRepos
) : ViewModel() {
    private val _uiState = MutableStateFlow(RegisterUiState())
    val uiState = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            suscripcionesRepos.getSuscripcion().collect { result ->
                when (result) {
                    is Resource.Success -> {
                        _uiState.value = _uiState.value.copy(
                            suscripciones = result.data ?: emptyList(),
                            isLoading = false,
                            errorMessage = null
                        )
                    }
                    is Resource.Error -> {
                        _uiState.value = _uiState.value.copy(
                            isLoading = false,
                            errorMessage = result.message
                        )

                    }
                    is Resource.Loading -> {
                        _uiState.value = _uiState.value.copy(
                            isLoading = true,
                            errorMessage = null
                        )
                    }
                }
            }
        }
    }

    fun onEvent(event: RegisterEvents) {
        when (event) {
            is RegisterEvents.UsernameChanged -> {
                usernameChange(event.username)
            }

            is RegisterEvents.PasswordChanged -> {
                passwordChange(event.password)
            }

            is RegisterEvents.RepeatedPasswordChanged -> {
                repeatedPasswordChange(event.repeatedPassword)
            }

            is RegisterEvents.NoIdentificacionChanged -> {
                noIdentificacionChange(event.noIdentificacion)
            }

            is RegisterEvents.FechaNacimientoChanged -> {
                fechaNacimientoChange(event.fechaNacimiento)
            }

            is RegisterEvents.PesoChanged -> {
                pesoChange(event.peso)
            }

            is RegisterEvents.AlturaChanged -> {
                alturaChange(event.altura)
            }

            is RegisterEvents.GeneroChanged -> {
                generoChange(event.genero)
            }

            is RegisterEvents.SuscripcionChanged -> {
                suscripcionIdChange(event.suscripcionId, event.nombre)
            }

            is RegisterEvents.OnLogin -> {
                event.navigateToLogin()
            }

            is RegisterEvents.OnNextStep -> {
                onNextStep()
            }

            is RegisterEvents.OnPreviousStep -> {
                onPreviousStep()
            }

            is RegisterEvents.OnCreateAccount -> {
                onCreateAccount(event.navigateToMain, event.showNotification)
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

    private fun repeatedPasswordChange(repeatedPassword: String) {
        _uiState.value = _uiState.value.copy(
            repeatedPassword = repeatedPassword
        )
    }

    private fun noIdentificacionChange(noIdentificacion: String) {
        _uiState.value = _uiState.value.copy(
            noIdentificacion = noIdentificacion
        )
    }

    private fun fechaNacimientoChange(fechaNacimiento: String) {
        _uiState.value = _uiState.value.copy(
            fechaNacimiento = fechaNacimiento
        )
    }

    private fun pesoChange(peso: Float) {
        _uiState.value = _uiState.value.copy(
            peso = peso
        )
    }

    private fun alturaChange(altura: Float) {
        _uiState.value = _uiState.value.copy(
            altura = altura
        )
    }

    private fun generoChange(genero: String) {
        _uiState.value = _uiState.value.copy(
            genero = genero
        )
    }

    private fun suscripcionIdChange(suscripcionId: Int, nombre: String ) {
        _uiState.value = _uiState.value.copy(
            suscripcionId = suscripcionId,
            suscripcion = nombre
        )
    }

    private fun onCreateAccount(
        navigateToMain: (GetUsuarioDto) -> Unit,
        showNotification: (String) -> Unit
    ) {
        if (!fieldsAreValid()) return;
        val newAccount = AddClienteDto(
            username = _uiState.value.username,
            clave = _uiState.value.password,
            noIdentificacion = _uiState.value.noIdentificacion,
            fechaNacimiento = _uiState.value.fechaNacimiento,
            peso = _uiState.value.peso,
            altura = _uiState.value.altura,
            genero = if (_uiState.value.genero == Generos.MASCULINO) 'M' else 'F',
            suscripcionId = _uiState.value.suscripcionId
        )
        viewModelScope.launch {
            usuariosRepos.createAccount(newAccount.toCreateAccountDto()).collect { result ->
                when (result) {
                    is Resource.Success -> {
                        _uiState.value = _uiState.value.copy(
                            isLoading = false,
                            errorMessage = null
                        )
                        if (result.data != null) {
                            navigateToMain(result.data)
                        } else {
                            showNotification("No se pudo crear la cuenta")
                        }
                    }

                    is Resource.Error -> {
                        _uiState.value = _uiState.value.copy(
                            isLoading = false,
                            errorMessage = result.message
                        )
                        showNotification(result.message ?: "Error al crear la cuenta")
                    }

                    is Resource.Loading -> {
                        _uiState.value = _uiState.value.copy(
                            isLoading = true,
                            errorMessage = null
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
        if (_uiState.value.username.length > 35) {
            _uiState.value = _uiState.value.copy(
                usernameError = "El username no puede pasar de 35 caracteres"
            )
            stillValid = false;
        } else {
            _uiState.value = _uiState.value.copy(
                usernameError = null
            )
        }
        if (_uiState.value.password.isBlank()) {
            _uiState.value = _uiState.value.copy(
                passwordError = "La contraseña no puede estar vacía"
            )
            stillValid = false;
        } else {
            _uiState.value = _uiState.value.copy(
                passwordError = null
            )
        }
        if (_uiState.value.password.length > 35) {
            _uiState.value = _uiState.value.copy(
                passwordError = "La contraseña no puede pasar de 35 caracteres"
            )
            stillValid = false;
        } else {
            _uiState.value = _uiState.value.copy(
                passwordError = null
            )
        }
        if (_uiState.value.repeatedPassword.isBlank()) {
            _uiState.value = _uiState.value.copy(
                repeatedPasswordError = "La contraseña no puede estar vacía"
            )
            stillValid = false;
        } else {
            _uiState.value = _uiState.value.copy(
                repeatedPasswordError = null
            )
        }

        if (_uiState.value.password != _uiState.value.repeatedPassword) {
            _uiState.value = _uiState.value.copy(
                repeatedPasswordError = "Las contraseñas no coinciden"
            )
            stillValid = false;
        } else {
            _uiState.value = _uiState.value.copy(
                repeatedPasswordError = null
            )
        }


        if (_uiState.value.noIdentificacion.isBlank()) {
            _uiState.value = _uiState.value.copy(
                noIdentificacionError = "El número de identificación no puede estar vacío"
            )
            stillValid = false;
        } else {
            _uiState.value = _uiState.value.copy(
                noIdentificacionError = null
            )

        }
        if (_uiState.value.fechaNacimiento.isBlank()) {
            _uiState.value = _uiState.value.copy(
                fechaNacimientoError = "La fecha de nacimiento no puede estar vacía"
            )
            stillValid = false;
        } else {
            _uiState.value = _uiState.value.copy(
                fechaNacimientoError = null
            )
        }
        if (_uiState.value.peso == 0f) {
            _uiState.value = _uiState.value.copy(
                pesoError = "El peso no puede estar vacío"
            )
            stillValid = false;
        } else {
            _uiState.value = _uiState.value.copy(
                pesoError = null
            )

        }
        if (_uiState.value.altura == 0f) {
            _uiState.value = _uiState.value.copy(
                alturaError = "La altura no puede estar vacía"
            )
            stillValid = false;
        } else {
            _uiState.value = _uiState.value.copy(
                alturaError = null
            )
        }
        if (_uiState.value.genero.isBlank()) {
            _uiState.value = _uiState.value.copy(
                generoError = "El género no puede estar vacío"
            )
            stillValid = false;
        } else {
            _uiState.value = _uiState.value.copy(
                generoError = null
            )
        }
        if (_uiState.value.suscripcionId == 0) {
            _uiState.value = _uiState.value.copy(
                suscripcionIdError = "La suscripción no puede estar vacía"
            )
            stillValid = false;
        } else {
            _uiState.value = _uiState.value.copy(
                suscripcionIdError = null
            )
        }
        return stillValid;
    }

    private fun onNextStep() {
        _uiState.value = _uiState.value.copy(
            currentStep = _uiState.value.currentStep + 1
        )
    }

    private fun onPreviousStep() {
        _uiState.value = _uiState.value.copy(
            currentStep = _uiState.value.currentStep - 1
        )
    }
}