package com.adielgarcia.gympro.data.remote.dto.utilities.fetching

data class GetUsuarioDto(
    val userId: Int,
    val username: String,
    val clave : String,
    val rol: String,

    val clienteId: Int? = null,
    val noIdentificacion: String? = null,
    val fechaNacimiento : String? = null,
    val peso: Float? = null,
    val altura: Float? = null,
    val genero: Char? = null,
    val edad: Int? = null,

    val entrenadorId: Int? = null,
    val entrenador : String? = null,

    val suscripcionId: Int? = null,
    val suscripcion : String? = null,

    val rango : String? = null,
    val clientesInscritos : Int? = null
)
