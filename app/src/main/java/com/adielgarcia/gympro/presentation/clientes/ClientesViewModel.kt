package  com.adielgarcia.gympro.presentation.clientes

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.adielgarcia.gympro.data.remote.Resource
import com.adielgarcia.gympro.data.remote.repository.UsuariosRepos
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ClientesViewModel @Inject constructor(
    private val repos: UsuariosRepos
) : ViewModel() {
    private val _uiState = MutableStateFlow(ClientesUiState())
    val uiState = _uiState.asStateFlow()

    fun onEvent(event: ClientesEvents) {
        when (event) {
            is ClientesEvents.OnSearchChange -> {
                _uiState.value = _uiState.value.copy(search = event.search)
                _uiState.value = _uiState.value.copy(
                    clientes = _uiState.value.allClientes.filter {
                        it.username.contains(event.search, ignoreCase = true)
                    }
                )
            }

            is ClientesEvents.FindByEntrenador -> {
                loadClientes(event.entrenadorId)
            }
        }
    }

    private fun loadClientes(id: Int) {
        viewModelScope.launch {
            repos.getUsuariosByEntrenador(id).collect { result ->
                when (result) {
                    is Resource.Success -> {
                        val data = result.data ?: emptyList()
                        _uiState.value = _uiState.value.copy(
                            clientes = data, allClientes = data
                        )
                    }
                    else -> {}
                }
            }
        }
    }

}