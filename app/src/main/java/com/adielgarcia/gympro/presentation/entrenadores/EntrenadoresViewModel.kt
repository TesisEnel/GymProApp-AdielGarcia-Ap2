package com.adielgarcia.gympro.presentation.entrenadores

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.adielgarcia.gympro.data.remote.Resource
import com.adielgarcia.gympro.data.remote.repository.EntrenadoresRepos
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EntrenadoresViewModel @Inject constructor(
    private val repos: EntrenadoresRepos
) : ViewModel() {
    private val _uiState = MutableStateFlow(EntrenadoresUiState())
    val uiState = _uiState.asStateFlow()

    fun onEvent(event: EntrenadoresEvents) {
        when (event) {
            is EntrenadoresEvents.OnSearchChange -> {
                _uiState.value = _uiState.value.copy(search = event.query)
                _uiState.value = _uiState.value.copy(
                    entrenadores = _uiState.value.allEntrenadores.filter {
                        it.username?.contains(event.query, ignoreCase = true) ?: false
                    }
                )
            }
            is EntrenadoresEvents.OnSelectEntrenador -> {

            }
        }
    }

    init {
        viewModelScope.launch {
            repos.getEntrenadores().collect { result ->
                when (result) {
                    is Resource.Success -> {
                        val data = result.data ?: emptyList()
                        _uiState.value = _uiState.value.copy(
                            allEntrenadores = data, entrenadores = data
                        )
                    }

                    else -> {}
                }
            }
        }
    }

}