package  com.adielgarcia.gympro.presentation.entrenadores

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.adielgarcia.gympro.data.remote.Resource
import com.adielgarcia.gympro.data.remote.dto.utilities.create.AddEntrenadorDto
import com.adielgarcia.gympro.data.remote.dto.utilities.edit.ChangeEntrenadorDto
import com.adielgarcia.gympro.data.remote.dto.utilities.edit.UpdateEntrenadorDto
import com.adielgarcia.gympro.data.remote.repository.EntrenadoresRepos
import com.adielgarcia.gympro.data.remote.repository.UsuariosRepos
import com.adielgarcia.gympro.utils.extensors.toCreateAccountDto
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EntrenadoresViewModel @Inject constructor(
    private val repos: EntrenadoresRepos,
    private val userRepos: UsuariosRepos
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
                _uiState.value = _uiState.value.copy(selectedEntrenador = event.entrenador)
            }

            is EntrenadoresEvents.OnDeleteEntrenador -> {
                viewModelScope.launch {
                    userRepos.deleteAccount(event.id).collect {
                        getEntrenadores()
                    }
                }
            }

            is EntrenadoresEvents.OnEditEntrenador -> {
                viewModelScope.launch {
                    repos.updateEntrenador(
                        UpdateEntrenadorDto(
                            event.entrenador.entrenadorId,
                            event.entrenador.rango
                        )
                    ).collect {
                        getEntrenadores()
                    }
                }
            }

            is EntrenadoresEvents.OnAddEntrenador -> {
                onAddEntrenador(event.username, event.rango)
            }

            is EntrenadoresEvents.OnUserChangeEntrenador -> {
                onChangeEntrenador(
                    event.userId,
                    event.oldEntrenadorId,
                    event.newEntrenadorId,
                    event.onLogout
                )
            }
        }
    }

    init {
        getEntrenadores()
    }

    private fun getEntrenadores() {
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

    private fun onChangeEntrenador(
        userId: Int,
        oldEntrenadorId: Int,
        newEntrenadorId: Int,
        onLogout: () -> Unit
    ) {
        viewModelScope.launch {
            userRepos.changeEntrenador(
                ChangeEntrenadorDto(
                    userId = userId,
                    newEntrenadorId = newEntrenadorId,
                    oldEntrenadorId = oldEntrenadorId
                )
            ).collect {
                onLogout()
            }
        }
    }

    private fun onAddEntrenador(username: String, rango: String) {
        viewModelScope.launch {
            userRepos.createAccount(
                AddEntrenadorDto(
                    username = username,
                    rango = rango
                ).toCreateAccountDto()
            ).collect {
                _uiState.value = EntrenadoresUiState(search = _uiState.value.search)
                getEntrenadores()
            }
        }
    }
}