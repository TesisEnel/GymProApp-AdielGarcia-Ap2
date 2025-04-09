package  com.adielgarcia.gympro.presentation.productos

import com.adielgarcia.gympro.data.remote.dto.entities.Producto

data class ProductosUiState(
    val search : String = "",
    val allProductos : List<Producto> = emptyList(),
    val productos : List<Producto> = emptyList(),

    val selectedProducto : Producto? = null
)
