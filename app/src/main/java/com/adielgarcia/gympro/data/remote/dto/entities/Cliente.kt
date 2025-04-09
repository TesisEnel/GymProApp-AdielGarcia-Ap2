package  com.adielgarcia.gympro.data.remote.dto.entities

data class Cliente(
    val clienteId : Int,
    val userId : Int,

    val suscripcionId : Int,
    val entrenadorId : Int,

    val noIdentificacion: String,
    val fechaNacimiento : String,

    val peso: Float,
    val altura: Float,
    val genero: Char,
    val edad: Int
)
