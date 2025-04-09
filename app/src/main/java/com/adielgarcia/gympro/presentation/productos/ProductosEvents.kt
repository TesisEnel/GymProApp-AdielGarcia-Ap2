package  com.adielgarcia.gympro.presentation.productos

import com.adielgarcia.gympro.data.remote.dto.entities.Producto

sealed interface ProductosEvents {
    data class OnSearchChange(val query: String) : ProductosEvents

    //Admins
    data class OnAddProductoClick(val nombre: String, val precio : Float, val categoria : String) : ProductosEvents
    data class OnDeleteProductoClick(val id: Int) : ProductosEvents
    data class OnEditProductoClick(val producto: Producto) : ProductosEvents
    data class OnSelectProducto(val producto: Producto?) : ProductosEvents
}