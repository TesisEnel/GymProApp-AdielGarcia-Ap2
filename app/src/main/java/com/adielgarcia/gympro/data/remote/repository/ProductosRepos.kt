package  com.adielgarcia.gympro.data.remote.repository

import com.adielgarcia.gympro.data.remote.RemoteDataSource
import com.adielgarcia.gympro.data.remote.Resource
import com.adielgarcia.gympro.data.remote.dto.entities.Producto
import com.adielgarcia.gympro.data.remote.dto.utilities.create.AddProductoDto
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import okhttp3.Response
import retrofit2.HttpException
import javax.inject.Inject

class ProductosRepos @Inject constructor(
    private val dataSource: RemoteDataSource
) {
    fun getProductos(): Flow<Resource<List<Producto>>> = flow {
        emit(Resource.Loading())
        try {
            val productos = dataSource.getProductos()
            emit(Resource.Success(productos))
        } catch (e: Exception) {
            emit(Resource.Error(e.message ?: "Error al obtener productos"))
        }
    }

    fun getProductoById(id: Int): Flow<Resource<Producto>> = flow {
        emit(Resource.Loading())
        try {
            val producto = dataSource.getProductoById(id)
            emit(Resource.Success(producto))
        } catch (e: Exception) {
            emit(Resource.Error(e.message ?: "Error al obtener producto"))
        }
    }
    fun addProducto(producto: AddProductoDto): Flow<Resource<Producto>> = flow {
        emit(Resource.Loading())
        try {
            val newProducto = dataSource.addProducto(producto)
            emit(Resource.Success(newProducto))
        } catch (e: Exception) {
            emit(Resource.Error(e.message ?: "Error al agregar producto"))
        }
    }
    fun updateProducto(producto: Producto): Flow<Resource<Boolean>> = flow {
        emit(Resource.Loading())
        try {
            dataSource.updateProducto(producto)
            emit(Resource.Success(true))
        }catch(e: HttpException){
            if(e.code() == 204) {
                emit (Resource.Success(true))
            }else {
                emit(Resource.Error(e.message ?: "Error al actualizar producto"))
            }
        } catch (e: Exception) {
            emit(Resource.Error(e.message ?: "Error al actualizar producto"))
        }
    }
    fun deleteProducto(id: Int): Flow<Resource<Boolean>> = flow {
        emit(Resource.Loading())
        try {
            dataSource.deleteProducto(id)
            emit(Resource.Success(true))
        }catch(e: HttpException){
            if(e.code() == 204) {
                emit (Resource.Success(true))
            }else {
                emit(Resource.Error(e.message ?: "Error al eliminar producto"))
            }
        } catch (e: Exception) {

            emit(Resource.Error(e.message ?: "Error al eliminar producto"))
        }
    }
}