package  com.adielgarcia.gympro.data.remote.dto.entities

data class Usuario(
    val userId: Int,
    val username: String,
    val clave: String,
    val rol: String,
    val fechaCreacion: String
)
