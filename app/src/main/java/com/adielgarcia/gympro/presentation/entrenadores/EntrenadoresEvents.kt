package  com.adielgarcia.gympro.presentation.entrenadores

import com.adielgarcia.gympro.data.remote.dto.entities.Entrenador
import com.adielgarcia.gympro.data.remote.dto.utilities.edit.UpdateEntrenadorDto

sealed interface EntrenadoresEvents {
    data class OnSearchChange(val query: String) : EntrenadoresEvents

    data class OnSelectEntrenador(val entrenador: Entrenador?) : EntrenadoresEvents
    data class OnDeleteEntrenador(val id: Int) : EntrenadoresEvents
    data class OnEditEntrenador(val entrenador: UpdateEntrenadorDto) : EntrenadoresEvents
    data class OnAddEntrenador(val username: String, val rango: String) : EntrenadoresEvents

    data class OnUserChangeEntrenador(val userId :Int, val oldEntrenadorId: Int, val newEntrenadorId: Int, val onLogout: () -> Unit) : EntrenadoresEvents
}