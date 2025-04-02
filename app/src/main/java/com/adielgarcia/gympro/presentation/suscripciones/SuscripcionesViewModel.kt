package com.adielgarcia.gympro.presentation.suscripciones

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.adielgarcia.gympro.data.remote.Resource
import com.adielgarcia.gympro.data.remote.dto.utilities.edit.ChangeSuscripcionDto
import com.adielgarcia.gympro.data.remote.repository.SuscripcionesRepos
import com.adielgarcia.gympro.data.remote.repository.UsuariosRepos
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SuscripcionesViewModel @Inject constructor(
    private val suscripcionesRepos: SuscripcionesRepos,
    private val userRepos: UsuariosRepos
) : ViewModel() {
    private val _uiState = MutableStateFlow(SuscripcionesUiState())
    val uiState = _uiState.asStateFlow()

    init {
        loadSuscripciones()
    }

    fun onEvent(event: SuscripcionesEvents) {
        when (event) {
            is SuscripcionesEvents.OnUserChangeSuscripcion -> {
                viewModelScope.launch {
                    userRepos.updateSuscripcion(
                        ChangeSuscripcionDto(
                            event.userId,
                            event.newSuscripcionId,
                            event.oldSuscripcionId
                        )
                    ).collect {
                        loadSuscripciones()
                    }
                }
            }

            else -> {}
        }
    }

    private fun loadSuscripciones() {
        viewModelScope.launch {
            suscripcionesRepos.getSuscripcion().collect { result ->
                when (result) {
                    is Resource.Success -> {
                        _uiState.value = _uiState.value.copy(
                            allSuscripciones = result.data ?: emptyList()
                        )
                    }

                    else -> {}
                }
            }
        }
    }
}