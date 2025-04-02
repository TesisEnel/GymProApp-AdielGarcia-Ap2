package com.adielgarcia.gympro.presentation.perfil

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.adielgarcia.gympro.data.remote.Resource
import com.adielgarcia.gympro.data.remote.dto.utilities.edit.ChangePasswordDto
import com.adielgarcia.gympro.data.remote.dto.utilities.edit.UpdateAlturaDto
import com.adielgarcia.gympro.data.remote.dto.utilities.edit.UpdatePesoDto
import com.adielgarcia.gympro.data.remote.repository.EntrenadoresRepos
import com.adielgarcia.gympro.data.remote.repository.SuscripcionesRepos
import com.adielgarcia.gympro.data.remote.repository.UsuariosRepos
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val usuariosRepos: UsuariosRepos,
    private val entrenadoresRepos: EntrenadoresRepos,
    private val suscripcionesRepos: SuscripcionesRepos
) : ViewModel() {
    private val _uiState = MutableStateFlow(ProfileUiState())
    val uiState = _uiState.asStateFlow()

    fun onEvent(event: ProfileEvents) {
        when (event) {
            is ProfileEvents.LoadEntrenadorData -> loadEntrenadorData(event.id)
            is ProfileEvents.LoadSuscripcionData -> loadSuscripcionData(event.id)

            is ProfileEvents.OnUpdatePeso -> onUpdatePeso(event.peso.toString())
            is ProfileEvents.OnUpdateAltura -> onUpdateAltura(event.altura.toString())
            is ProfileEvents.OnChangePassword -> onChangePassword(event.password)
            is ProfileEvents.OnChangeRepeatedPassword -> onChangeRepeatedPassword(event.repeatedPassword)

            is ProfileEvents.SavePeso -> savePeso(event.id, event.showNotification)
            is ProfileEvents.SaveAltura -> saveAltura(event.id, event.showNotification)
            is ProfileEvents.SavePassword -> savePassword(event.id, event.showNotification)
        }
    }

    private fun loadEntrenadorData(id: Int) {
        viewModelScope.launch {
            entrenadoresRepos.getEntrenadorById(id).collect { result ->
                when (result) {
                    is Resource.Success -> {
                        _uiState.value = _uiState.value.copy(entrenador = result.data)
                    }

                    else -> {}
                }
            }
        }
    }

    private fun loadSuscripcionData(id: Int) {
        viewModelScope.launch {
            suscripcionesRepos.getSuscripcionById(id).collect { result ->
                when (result) {
                    is Resource.Success -> {
                        _uiState.value = _uiState.value.copy(suscripcion = result.data)
                    }

                    else -> {}
                }
            }
        }
    }

    private fun onUpdatePeso(peso: String) {
        if (peso.toFloatOrNull() == null) return

        _uiState.value = _uiState.value.copy(peso = peso)
    }

    private fun onUpdateAltura(altura: String) {
        if (altura.toFloatOrNull() == null) return
        _uiState.value = _uiState.value.copy(altura = altura)
    }

    private fun onChangeRepeatedPassword(repeatedPassword: String) {
        _uiState.value = _uiState.value.copy(repeatedPassword = repeatedPassword)
    }

    private fun onChangePassword(password: String) {
        _uiState.value = _uiState.value.copy(password = password)
    }

    private fun savePassword(id: Int, showNotificacion: (String) -> Unit) {
        if (_uiState.value.password != _uiState.value.repeatedPassword) {
            showNotificacion("Las contraseñas no coinciden")
            return
        }

        viewModelScope.launch {
            usuariosRepos.changePassword(ChangePasswordDto(id, _uiState.value.password))
                .collect { result ->
                    when (result) {
                        is Resource.Success -> {
                            showNotificacion("Contraseña actualizada")
                        }

                        else -> {}
                    }
                }
        }
    }

    private fun saveAltura(id: Int, showNotificacion: (String) -> Unit) {
        if (_uiState.value.altura.toFloatOrNull() == null) {
            showNotificacion("Altura invalida")
            return
        }
        viewModelScope.launch {
            usuariosRepos.updateAltura(UpdateAlturaDto(id, _uiState.value.altura.toFloat()))
                .collect { result ->
                    when (result) {
                        is Resource.Success -> {
                            showNotificacion("Altura actualizada")
                        }

                        else -> {}
                    }
                }
        }
    }

    private fun savePeso(id: Int, showNotificacion: (String) -> Unit) {
        if (_uiState.value.peso.toFloatOrNull() == null) {
            showNotificacion("Peso invalido")
            return
        }
        viewModelScope.launch {
            usuariosRepos.updatePeso(UpdatePesoDto(id, _uiState.value.peso.toFloat()))
                .collect { result ->
                    when (result) {
                        is Resource.Success -> {
                            showNotificacion("Peso actualizado")
                        }

                        else -> {}
                    }
                }
        }

    }
}
