package com.adielgarcia.gympro.presentation.equipamientos

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.adielgarcia.gympro.data.remote.Resource
import com.adielgarcia.gympro.data.remote.dto.utilities.create.AddEquipamientoDto
import com.adielgarcia.gympro.data.remote.repository.EquipamientosRepos
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EquipamientosViewModel @Inject constructor(
    private val equipamientosRepos: EquipamientosRepos
) : ViewModel() {
    private val _uiState = MutableStateFlow(EquipamientosUiState())
    val uiState = _uiState.asStateFlow()

    fun onEvent(event: EquipamientosEvents) {
        when (event) {
            is EquipamientosEvents.OnSearchChange -> {
                _uiState.value = _uiState.value.copy(search = event.query)
                filterEquipamientos()
            }

            is EquipamientosEvents.OnSelectEquipamiento -> {
                _uiState.value = _uiState.value.copy(equipamientoSelected = event.equipamiento)
            }

            is EquipamientosEvents.OnDeleteEquipamiento -> {
                viewModelScope.launch {
                    equipamientosRepos.deleteEquipamiento(event.id).collect {
                        filterEquipamientos()
                    }
                }
            }

            is EquipamientosEvents.OnEditEquipamiento -> {
                viewModelScope.launch {
                    equipamientosRepos.updateEquipamiento(event.equipamiento).collect {
                        filterEquipamientos()
                    }
                }
            }

            is EquipamientosEvents.OnAddEquipamiento -> {
                viewModelScope.launch {
                    equipamientosRepos.addEquipamiento(
                        AddEquipamientoDto(
                            event.nombre,
                            event.descripcion
                        )
                    ).collect {
                        filterEquipamientos()
                    }
                }
            }
        }

    }

    init {
        viewModelScope.launch {
            equipamientosRepos.getEquipamientos().collect { result ->
                when (result) {
                    is Resource.Success -> {
                        val data = result.data ?: emptyList()
                        _uiState.value = _uiState.value.copy(
                            allEquipamientos = data, equipamientos = data
                        )
                    }

                    else -> {}
                }
            }
        }
    }

    private fun filterEquipamientos() {
        _uiState.value = _uiState.value.copy(
            equipamientos = _uiState.value.allEquipamientos.filter { equipamiento ->
                equipamiento.nombre.contains(
                    _uiState.value.search,
                    ignoreCase = true
                ) || equipamiento.descripcion.contains(_uiState.value.search, ignoreCase = true)
            }
        )
    }
}