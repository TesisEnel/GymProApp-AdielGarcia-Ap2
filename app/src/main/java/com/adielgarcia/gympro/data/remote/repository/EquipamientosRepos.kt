package com.adielgarcia.gympro.data.remote.repository

import com.adielgarcia.gympro.data.remote.RemoteDataSource
import com.adielgarcia.gympro.data.remote.Resource
import com.adielgarcia.gympro.data.remote.dto.entities.Equipamiento
import com.adielgarcia.gympro.data.remote.dto.utilities.create.AddEquipamientoDto
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import okhttp3.Response
import javax.inject.Inject

class EquipamientosRepos @Inject constructor(
    private val dataSource: RemoteDataSource
) {
    fun getEquipamientos(): Flow<Resource<List<Equipamiento>>> = flow {
        emit(Resource.Loading())
        try {
            val equipamientos = dataSource.getEquipamientos()
            emit(Resource.Success(equipamientos))
        } catch (e: Exception) {
            emit(Resource.Error(e.message ?: "Error al obtener equipamientos"))
        }
    }

    fun getEquipamientoById(id: Int): Flow<Resource<Equipamiento>> = flow {
        emit(Resource.Loading())
        try {
            val equipamiento = dataSource.getEquipamientoById(id)
            emit(Resource.Success(equipamiento))
        } catch (e: Exception) {
            emit(Resource.Error(e.message ?: "Error al obtener equipamiento"))
        }
    }

    fun addEquipamiento(equipamiento: AddEquipamientoDto): Flow<Resource<Equipamiento>> = flow {
        emit(Resource.Loading())
        try {
            val newEquipamiento = dataSource.addEquipamiento(equipamiento)
            emit(Resource.Success(newEquipamiento))
        } catch (e: Exception) {
            emit(Resource.Error(e.message ?: "Error al agregar equipamiento"))
        }
    }

    fun updateEquipamiento(equipamiento: Equipamiento): Flow<Resource<Response>> = flow {
        emit(Resource.Loading())
        try {
            val response = dataSource.updateEquipamiento(equipamiento)
            emit(Resource.Success(response))
        } catch (e: Exception) {
            emit(Resource.Error(e.message ?: "Error al actualizar equipamiento"))
        }
    }

    fun deleteEquipamiento(id: Int): Flow<Resource<Response>> = flow {
        emit(Resource.Loading())
        try {
            val response = dataSource.deleteEquipamiento(id)
            emit(Resource.Success(response))
        } catch (e: Exception) {
            emit(Resource.Error(e.message ?: "Error al eliminar equipamiento"))
        }
    }
}