package  com.adielgarcia.gympro.data.remote

import com.adielgarcia.gympro.data.remote.dto.entities.Equipamiento
import com.adielgarcia.gympro.data.remote.dto.entities.Producto
import com.adielgarcia.gympro.data.remote.dto.entities.Suscripcion
import com.adielgarcia.gympro.data.remote.dto.utilities.create.AddEquipamientoDto
import com.adielgarcia.gympro.data.remote.dto.utilities.create.AddProductoDto
import com.adielgarcia.gympro.data.remote.dto.utilities.create.CreateAccountDto
import com.adielgarcia.gympro.data.remote.dto.utilities.edit.ChangeEntrenadorDto
import com.adielgarcia.gympro.data.remote.dto.utilities.edit.ChangePasswordDto
import com.adielgarcia.gympro.data.remote.dto.utilities.edit.ChangeSuscripcionDto
import com.adielgarcia.gympro.data.remote.dto.utilities.edit.UpdateAlturaDto
import com.adielgarcia.gympro.data.remote.dto.utilities.edit.UpdateEntrenadorDto
import com.adielgarcia.gympro.data.remote.dto.utilities.edit.UpdatePesoDto
import javax.inject.Inject

class RemoteDataSource @Inject constructor(
    private val api: GymApi
) {
    //#region Clientes
    suspend fun getClientes() = api.getClientes()
    suspend fun getClienteById(id: Int) = api.getClienteById(id)
    //#endregion

    //#region Entrenadores
    suspend fun getEntrenadores() = api.getEntrenadores()
    suspend fun getEntrenadorById(id: Int) = api.getEntrenadorById(id)
    suspend fun updateEntrenador(entrenador: UpdateEntrenadorDto) = api.updateEntrenador(entrenador)
    //#endregion

    //#region Equipamientos
    suspend fun getEquipamientos() = api.getEquipamientos()
    suspend fun getEquipamientoById(id: Int) = api.getEquipamientoById(id)
    suspend fun addEquipamiento(equipamiento: AddEquipamientoDto) =
        api.addEquipamiento(equipamiento)

    suspend fun updateEquipamiento(equipamiento: Equipamiento) =
        api.updateEquipamiento(equipamiento)

    suspend fun deleteEquipamiento(id: Int) = api.deleteEquipamiento(id)
    //#endregion

    //#region Productos
    suspend fun getProductos() = api.getProductos()
    suspend fun getProductoById(id: Int) = api.getProductoById(id)
    suspend fun addProducto(producto: AddProductoDto) = api.addProducto(producto)
    suspend fun updateProducto(producto: Producto) = api.updateProducto(producto)
    suspend fun deleteProducto(id: Int) = api.deleteProducto(id)
    //#endregion

    //#region Suscripciones
    suspend fun getSuscripciones() = api.getSuscripciones()
    suspend fun getSuscripcionById(id: Int) = api.getSuscripcionById(id)
    suspend fun updateSuscripcion(suscripcion: Suscripcion) = api.updateSuscripcion(suscripcion)
    suspend fun deleteSuscripcion(id: Int) = api.deleteSuscripcion(id)
    //#endregion

    //#region Usuarios
    suspend fun getUsuariosByEntrenador(id: Int) = api.getUsuariosByEntrenador(id)
    suspend fun getUsuarioById(id: Int) = api.getUsuarioById(id)
    suspend fun login(username: String, password: String) =
        api.login(username, password)

    suspend fun createAccount(account: CreateAccountDto) = api.createAccount(account)
    suspend fun updatePeso(dto: UpdatePesoDto) = api.updatePeso(dto)

    suspend fun updateAltura(dto: UpdateAlturaDto) = api.updateAltura(dto)
    suspend fun changePassword(dto: ChangePasswordDto) = api.changePassword(dto)
    suspend fun changeEntrenador(dto: ChangeEntrenadorDto) = api.changeEntrenador(dto)
    suspend fun updateSuscripcion(suscripcion: ChangeSuscripcionDto) =
        api.updateSuscripcion(suscripcion)

    suspend fun deleteAccount(id: Int) = api.deleteAccount(id)
    //#endregion
}