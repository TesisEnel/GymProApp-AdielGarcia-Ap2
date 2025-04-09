package  com.adielgarcia.gympro.data.remote.repository

import com.adielgarcia.gympro.data.remote.RemoteDataSource
import com.adielgarcia.gympro.data.remote.Resource
import com.adielgarcia.gympro.data.remote.dto.entities.Entrenador
import com.adielgarcia.gympro.data.remote.dto.utilities.edit.UpdateEntrenadorDto
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import okhttp3.Response
import retrofit2.HttpException
import javax.inject.Inject

class EntrenadoresRepos @Inject constructor(
    private val dataSource: RemoteDataSource
) {
    fun getEntrenadores(): Flow<Resource<List<Entrenador>>> = flow {
        emit(Resource.Loading())
        try {
            val entrenadores = dataSource.getEntrenadores()
            emit(Resource.Success(entrenadores))
        } catch (e: Exception) {
            emit(Resource.Error(e.message ?: "Error al obtener entrenadores"))
        }
    }
    fun getEntrenadorById(id: Int): Flow<Resource<Entrenador>> = flow {
        emit(Resource.Loading())
        try {
            val entrenador = dataSource.getEntrenadorById(id)
            emit(Resource.Success(entrenador))
        } catch (e: Exception) {
            emit(Resource.Error(e.message ?: "Error al obtener entrenador"))
        }
    }
    fun updateEntrenador(entrenador: UpdateEntrenadorDto): Flow<Resource<Boolean>> = flow {
        emit(Resource.Loading())
        try {
            val response = dataSource.updateEntrenador(entrenador)
            emit(Resource.Success(true))
        }catch(e: HttpException){
            if(e.code() == 204) {
                emit (Resource.Success(true))
            }else {
                emit(Resource.Error(e.message ?: "Error al actualizar producto"))
            }
        } catch (e: Exception) {
            emit(Resource.Error(e.message ?: "Error al actualizar entrenador"))
        }
    }
}