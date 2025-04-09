package  com.adielgarcia.gympro.presentation.entrenadores

import com.adielgarcia.gympro.data.remote.dto.entities.Entrenador

data class EntrenadoresUiState(
    val search: String = "",

    val allEntrenadores: List<Entrenador> = emptyList(),
    val entrenadores: List<Entrenador> = emptyList(),
    val selectedEntrenador: Entrenador? = null
)
