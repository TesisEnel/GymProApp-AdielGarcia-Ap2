package com.adielgarcia.gympro.presentation.productos

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.adielgarcia.gympro.data.remote.Resource
import com.adielgarcia.gympro.data.remote.dto.entities.Producto
import com.adielgarcia.gympro.data.remote.dto.utilities.create.AddProductoDto
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

            is ProductosEvents.OnSelectProducto -> {
                _uiState.value = _uiState.value.copy(selectedProducto = event.producto)
            }

            is ProductosEvents.OnAddProductoClick -> {
                createProducto(event.nombre, event.precio, event.categoria)
            }

            is ProductosEvents.OnDeleteProductoClick -> {
                deleteProducto(event.id)
            }

            is ProductosEvents.OnEditProductoClick -> {
                modifyProducto(event.producto)
            }
        }
    }

    init {
        getProductos()
    }

    private fun getProductos() {
        viewModelScope.launch {
            productosRepos.getProductos().collect { result ->
                when (result) {
                    is Resource.Success -> {
                        val data = result.data ?: emptyList();
                        _uiState.value = _uiState.value.copy(
                            productos = data, allProductos = data
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

    private fun createProducto(nombre: String, precio: Float, categoria: String) {
        viewModelScope.launch {
            productosRepos.addProducto(AddProductoDto(nombre, categoria, precio))
                .collect { result ->
                    when (result) {
                        is Resource.Success -> {
                            _uiState.value = ProductosUiState(search = _uiState.value.search)
                            getProductos()
                        }

                        else -> {}
                    }
                }
        }
    }

    private fun modifyProducto(producto: Producto) {
        viewModelScope.launch {
            productosRepos.updateProducto(producto).collect { _ ->
                _uiState.value = ProductosUiState(search = _uiState.value.search)
                getProductos()
            }
        }
    }

    private fun deleteProducto(id: Int) {
        viewModelScope.launch {
            productosRepos.deleteProducto(id).collect { _ ->
                _uiState.value = ProductosUiState(search = _uiState.value.search)
                getProductos()
            }
        }
    }
}