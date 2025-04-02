package com.adielgarcia.gympro.data.remote.repository

import com.adielgarcia.gympro.data.remote.RemoteDataSource
import com.adielgarcia.gympro.data.remote.Resource
import com.adielgarcia.gympro.data.remote.dto.utilities.create.CreateAccountDto
import com.adielgarcia.gympro.data.remote.dto.utilities.edit.ChangeEntrenadorDto
import com.adielgarcia.gympro.data.remote.dto.utilities.edit.ChangePasswordDto
import com.adielgarcia.gympro.data.remote.dto.utilities.edit.ChangeSuscripcionDto
import com.adielgarcia.gympro.data.remote.dto.utilities.edit.UpdateAlturaDto
import com.adielgarcia.gympro.data.remote.dto.utilities.edit.UpdatePesoDto
import com.adielgarcia.gympro.data.remote.dto.utilities.fetching.GetUsuarioDto
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import javax.inject.Inject

class UsuariosRepos @Inject constructor(
    private val dataSource: RemoteDataSource
) {
    fun getUsuariosByEntrenador(id: Int): Flow<Resource<List<GetUsuarioDto>>> = flow {
        emit(Resource.Loading())
        try {
            val usuarios = dataSource.getUsuariosByEntrenador(id)

            emit(Resource.Success(usuarios))
        } catch (e: Exception) {
            emit(Resource.Error(e.message ?: "Error al obtener usuarios"))
        }
    }

    fun getUsuarioById(id: Int): Flow<Resource<GetUsuarioDto>> = flow {
        emit(Resource.Loading())
        try {
            val usuario = dataSource.getUsuarioById(id)
            emit(Resource.Success(usuario))
        } catch (e: Exception) {
            emit(Resource.Error(e.message ?: "Error al obtener usuario"))
        }
    }

    fun login(username: String, password: String): Flow<Resource<GetUsuarioDto>> = flow {
        emit(Resource.Loading())
        try {
            val usuario = dataSource.login(username, password)
            emit(Resource.Success(usuario))
        } catch (e: HttpException) {
            val message =
                if (e.code() == 404) "No se encontro un usuario con esas credenciales" else e.message();
            emit(Resource.Error(message))
        } catch (e: Exception) {
            emit(Resource.Error(e.message ?: "Error al iniciar sesión"))
        }
    }

    fun createAccount(account: CreateAccountDto): Flow<Resource<GetUsuarioDto>> = flow {
        emit(Resource.Loading())
        try {
            val newUsuario = dataSource.createAccount(account)
            val usuario = dataSource.login(newUsuario.username, newUsuario.clave)
            emit(Resource.Success(usuario))
        } catch (e: HttpException) {
            val message =
                if (e.code() == 400) "El nombre de usuario '${account.username}' ya existe" else e.message();
            emit(Resource.Error(message))
        } catch (e: Exception) {
            emit(Resource.Error(e.message ?: "Error al crear cuenta"))
        }
    }

    fun updatePeso(dto: UpdatePesoDto): Flow<Resource<Boolean>> = flow {
        emit(Resource.Loading())
        try {
            val success = dataSource.updatePeso(dto)
            emit(Resource.Success(success))
        } catch (e: Exception) {
            emit(Resource.Error(e.message ?: "Error al actualizar peso"))
        }
    }

    fun updateAltura(dto: UpdateAlturaDto): Flow<Resource<Boolean>> = flow {
        emit(Resource.Loading())
        try {
            val success = dataSource.updateAltura(dto)
            emit(Resource.Success(success))
        } catch (e: Exception) {
            emit(Resource.Error(e.message ?: "Error al actualizar altura"))
        }
    }

    fun changePassword(dto: ChangePasswordDto): Flow<Resource<Boolean>> = flow {
        emit(Resource.Loading())

        try {
            val success = dataSource.changePassword(dto)
            emit(Resource.Success(success))
        } catch (e: Exception) {
            emit(Resource.Error(e.message ?: "Error al cambiar contraseña"))
        }
    }

    fun deleteAccount(id: Int): Flow<Resource<Boolean>> = flow {
        emit(Resource.Loading())
        try {
            val success = dataSource.deleteAccount(id)
            emit(Resource.Success(success))
        } catch (e: Exception) {
            emit(Resource.Error(e.message ?: "Error al eliminar cuenta"))
        }
    }

    fun changeEntrenador(dto: ChangeEntrenadorDto): Flow<Resource<Boolean>> = flow {

        emit(Resource.Loading())
        try {
            val success = dataSource.changeEntrenador(dto)
            emit(Resource.Success(success))
        } catch (e: Exception) {
            emit(Resource.Error(e.message ?: "Error al cambiar entrenador"))
        }
    }

    fun updateSuscripcion(suscripcion: ChangeSuscripcionDto): Flow<Resource<Boolean>> = flow {
        emit(Resource.Loading())
        try {
            val success = dataSource.updateSuscripcion(suscripcion)
            emit(Resource.Success(success))
        } catch (e: Exception) {
            emit(Resource.Error(e.message ?: "Error al actualizar suscripción"))
        }
    }
}