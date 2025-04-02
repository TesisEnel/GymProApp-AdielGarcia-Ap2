package com.adielgarcia.gympro.presentation.productos

sealed interface ProductosEvents {
    data class OnSearchChange(val query: String) : ProductosEvents

}