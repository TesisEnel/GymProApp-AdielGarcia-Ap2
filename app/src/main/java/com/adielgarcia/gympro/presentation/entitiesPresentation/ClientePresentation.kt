package     com.adielgarcia.gympro.presentation.entitiesPresentation

data class ClientePresentation(
    val userId: Int,
    val username: String,
    val clave : String,
    val rol: String,

    val clienteId: Int,
    val noIdentificacion: String,
    val fechaNacimiento : String,
    val peso: Float,
    val altura: Float,
    val genero: Char,
    val edad: Int,


    val entrenadorId: Int,
    val entrenador : String,

    val suscripcionId: Int,
    val suscripcion : String
)
