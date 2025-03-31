package com.adielgarcia.gympro.data.remote.repository

import com.adielgarcia.gympro.data.remote.RemoteDataSource
import com.adielgarcia.gympro.data.remote.Resource
import com.adielgarcia.gympro.data.remote.dto.entities.Cliente
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class ClientesRepos @Inject constructor(
    private val dataSource: RemoteDataSource
) {
    fun getClientes(): Flow<Resource<List<Cliente>>> = flow {
        emit(Resource.Loading())
        try {
            val clientes = dataSource.getClientes()
            emit(Resource.Success(clientes))
        } catch (e: Exception) {
            emit(Resource.Error(e.message ?: "Error al obtener clientes"))
        }
    }

    fun getClienteById(id: Int): Flow<Resource<Cliente>> = flow {
        emit(Resource.Loading())
        try {
            val cliente = dataSource.getClienteById(id)
            emit(Resource.Success(cliente))
        } catch (e: Exception) {
            emit(Resource.Error(e.message ?: "Error al obtener cliente"))
        }
    }
}