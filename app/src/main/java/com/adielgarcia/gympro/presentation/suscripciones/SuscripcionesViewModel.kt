package com.adielgarcia.gympro.presentation.suscripciones

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.adielgarcia.gympro.data.remote.Resource
import com.adielgarcia.gympro.data.remote.repository.SuscripcionesRepos
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SuscripcionesViewModel @Inject constructor(
    private val suscripcionesRepos: SuscripcionesRepos
) : ViewModel() {
    private val _uiState = MutableStateFlow(SuscripcionesUiState())
    val uiState = _uiState.asStateFlow()

    init {
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