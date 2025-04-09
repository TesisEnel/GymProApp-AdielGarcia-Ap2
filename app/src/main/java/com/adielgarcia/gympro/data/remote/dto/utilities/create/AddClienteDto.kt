package  com.adielgarcia.gympro.data.remote.dto.utilities.create

import com.adielgarcia.gympro.data.remote.Roles

data class AddClienteDto(
    val username: String,
    val clave: String,
    val rol: String = Roles.CLIENTE,

    val noIdentificacion: String,
    val fechaNacimiento: String,

    val peso: Float,
    val altura: Float,
    val genero: Char,
    val suscripcionId: Int
)
