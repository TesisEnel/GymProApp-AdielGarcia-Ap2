package com.adielgarcia.gympro.data.remote

import com.adielgarcia.gympro.data.remote.dto.entities.Cliente
import com.adielgarcia.gympro.data.remote.dto.entities.Entrenador
import com.adielgarcia.gympro.data.remote.dto.entities.Equipamiento
import com.adielgarcia.gympro.data.remote.dto.entities.Producto
import com.adielgarcia.gympro.data.remote.dto.entities.Suscripcion
import com.adielgarcia.gympro.data.remote.dto.entities.Usuario
import com.adielgarcia.gympro.data.remote.dto.utilities.create.AddEquipamientoDto
import com.adielgarcia.gympro.data.remote.dto.utilities.create.AddProductoDto
import com.adielgarcia.gympro.data.remote.dto.utilities.create.CreateAccountDto
import com.adielgarcia.gympro.data.remote.dto.utilities.edit.ChangeEntrenadorDto
import com.adielgarcia.gympro.data.remote.dto.utilities.edit.ChangePasswordDto
import com.adielgarcia.gympro.data.remote.dto.utilities.edit.ChangeSuscripcionDto
import com.adielgarcia.gympro.data.remote.dto.utilities.edit.UpdateAlturaDto
import com.adielgarcia.gympro.data.remote.dto.utilities.edit.UpdateEntrenadorDto
import com.adielgarcia.gympro.data.remote.dto.utilities.edit.UpdatePesoDto
import com.adielgarcia.gympro.data.remote.dto.utilities.fetching.GetUsuarioDto
import okhttp3.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface GymApi {
    //#region CLIENTES
    @GET("/Cliente")
    suspend fun getClientes(): List<Cliente>

    @GET("/GetClienteById/{id}")
    suspend fun getClienteById(@Path("id") id: Int): Cliente
    //#endregion

    //#region ENTRENADORES

    @GET("/GetEntrenadores")
    suspend fun getEntrenadores(): List<Entrenador>

    @GET("/GetEntrenadorById/{id}")
    suspend fun getEntrenadorById(@Path("id") id: Int): Entrenador

    @PUT("/UpdateEntrenador")
    suspend fun updateEntrenador(@Body entrenador: UpdateEntrenadorDto)
    //#endregion

    //#region EQUIPAMIENTOS
    @GET("/GetEquipamientos")
    suspend fun getEquipamientos(): List<Equipamiento>

    @GET("/GetEquipamientoById/{id}")
    suspend fun getEquipamientoById(@Path("id") id: Int): Equipamiento

    @POST("/AddEquipamiento")
    suspend fun addEquipamiento(@Body equipamiento: AddEquipamientoDto): Equipamiento

    @PUT("/UpdateEquipamiento")
    suspend fun updateEquipamiento(@Body equipamiento: Equipamiento)

    @DELETE("/DeleteEquipamiento/{id}")
    suspend fun deleteEquipamiento(@Path("id") id: Int)
    //#endregion

    //#region PRODUCTOS
    @GET("/GetProductos")
    suspend fun getProductos(): List<Producto>

    @GET("/GetProductoById/{id}")
    suspend fun getProductoById(@Path("id") id: Int): Producto

    @POST("/AddProducto")
    suspend fun addProducto(@Body producto: AddProductoDto): Producto

    @PUT("/UpdateProducto")
    suspend fun updateProducto(@Body producto: Producto)

    @DELETE("/DeleteProducto/{id}")
    suspend fun deleteProducto(@Path("id") id: Int)
    //#endregion

    //#region SUSCRIPCIONES
    @GET("/GetSuscripciones")
    suspend fun getSuscripciones(): List<Suscripcion>

    @GET("/GetSuscripcionById/{id}")
    suspend fun getSuscripcionById(@Path("id") id: Int): Suscripcion

    @PUT("/UpdateSuscripcion")
    suspend fun updateSuscripcion(@Body suscripcion: Suscripcion)

    @DELETE("/DeleteSuscripcion/{id}")
    suspend fun deleteSuscripcion(@Path("id") id: Int)
    //#endregion

    //#region USUARIOS

    @GET("/GetClientesByEntrenador/{id}")
    suspend fun getUsuariosByEntrenador(@Path("id") id: Int): List<GetUsuarioDto>

    @GET("/GetUsuarioById/{id}")
    suspend fun getUsuarioById(@Path("id") id: Int): GetUsuarioDto

    @GET("/Login/{username}/{password}")
    suspend fun login(
        @Path("username") username: String,
        @Path("password") password: String
    ): GetUsuarioDto

    @POST("/CreateAccount")
    suspend fun createAccount(@Body account: CreateAccountDto): Usuario

    @PUT("/UpdatePeso")
    suspend fun updatePeso(@Body dto: UpdatePesoDto): Boolean

    @PUT("/UpdateAltura")
    suspend fun updateAltura(@Body dto: UpdateAlturaDto): Boolean

    @PUT("/ChangePassword")
    suspend fun changePassword(@Body dto: ChangePasswordDto): Boolean

    @PUT("/ChangeEntrenador")
    suspend fun changeEntrenador(@Body dto: ChangeEntrenadorDto): Boolean

    @PUT("/ChangeSuscripcion")
    suspend fun updateSuscripcion(@Body suscripcion: ChangeSuscripcionDto): Boolean

    @DELETE("/DeleteAccount/{id}")
    suspend fun deleteAccount(@Path("id") id: Int): Boolean
    //#endregion
}