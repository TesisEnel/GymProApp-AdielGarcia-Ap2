package com.adielgarcia.gympro.presentation.productos

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.adielgarcia.gympro.data.remote.Resource
import com.adielgarcia.gympro.data.remote.repository.ProductosRepos
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductosViewModel @Inject constructor(
    private val productosRepos: ProductosRepos
) : ViewModel() {
    private val _uiState = MutableStateFlow(ProductosUiState())
    val uiState = _uiState.asStateFlow()

    fun onEvent(event: ProductosEvents) {
        when (event) {
            is ProductosEvents.OnSearchChange -> {
                filterProductos(event.query)
            }
        }
    }
    init {
        viewModelScope.launch {
            productosRepos.getProductos().collect{ result ->
                when(result){
                    is Resource.Success -> {
                        val data = result.data ?: emptyList();
                        _uiState.value = _uiState.value.copy(
                            productos = data , allProductos = data
                        )
                    }
                    else -> {}
                }
            }
        }
    }
    private fun filterProductos(query: String) {
        _uiState.value = _uiState.value.copy(search = query)

        if (query.isEmpty()) {
            _uiState.value = _uiState.value.copy(productos = _uiState.value.allProductos)
            return
        }
        val filtereds = _uiState.value.allProductos.filter {
            it.nombre.contains(query, ignoreCase = true)
        }
        _uiState.value = _uiState.value.copy(productos = filtereds)
    }
}