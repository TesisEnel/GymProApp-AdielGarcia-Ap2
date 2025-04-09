package  com.adielgarcia.gympro.data.remote.dto.utilities.create

import com.adielgarcia.gympro.DEFAULT_COACH_PASSWORD
import com.adielgarcia.gympro.data.remote.Roles


data class AddEntrenadorDto(
    val username: String,
    val clave: String = DEFAULT_COACH_PASSWORD,
    val rol: String = Roles.ENTRENADOR,

    val rango: String
)
