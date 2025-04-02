package com.adielgarcia.gympro.data.remote.repository

import com.adielgarcia.gympro.data.remote.RemoteDataSource
import com.adielgarcia.gympro.data.remote.Resource
import com.adielgarcia.gympro.data.remote.dto.entities.Suscripcion
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import javax.inject.Inject

class SuscripcionesRepos @Inject constructor(
    private val dataSource: RemoteDataSource
) {
    fun getSuscripcion(): Flow<Resource<List<Suscripcion>>> = flow {
        emit(Resource.Loading())
        try {
            val suscripciones = dataSource.getSuscripciones()
            emit(Resource.Success(suscripciones))
        } catch (e: Exception) {
            emit(Resource.Error(e.message ?: "Error al obtener suscripciones"))
        }
    }

    fun getSuscripcionById(id: Int): Flow<Resource<Suscripcion>> = flow {
        emit(Resource.Loading())
        try {
            val suscripcion = dataSource.getSuscripcionById(id)
            emit(Resource.Success(suscripcion))
        } catch (e: Exception) {
            emit(Resource.Error(e.message ?: "Error al obtener suscripcion"))
        }
    }

    fun updateSuscripcion(suscripcion: Suscripcion): Flow<Resource<Boolean>> = flow {
        emit(Resource.Loading())
        try {
            val updatedSuscripcion = dataSource.updateSuscripcion(suscripcion)
            emit(Resource.Success(true))
        } catch (e: HttpException) {
            if (e.code() == 204) {
                emit(Resource.Success(true))
            } else {
                emit(Resource.Error(e.message ?: "Error al actualizar producto"))
            }
        } catch (e: Exception) {
            emit(Resource.Error(e.message ?: "Error al actualizar suscripcion"))

        }
    }

    fun deleteSuscripcion(id: Int): Flow<Resource<Boolean>> = flow {
        emit(Resource.Loading())
        try {
            val deletedSuscripcion = dataSource.deleteSuscripcion(id)
            emit(Resource.Success(true))
        } catch (e: HttpException) {
            if (e.code() == 204) {
                emit(Resource.Success(true))
            } else {
                emit(Resource.Error(e.message ?: "Error al actualizar producto"))
            }
        } catch (e: Exception) {
            emit(Resource.Error(e.message ?: "Error al eliminar suscripcion"))
        }
    }
}