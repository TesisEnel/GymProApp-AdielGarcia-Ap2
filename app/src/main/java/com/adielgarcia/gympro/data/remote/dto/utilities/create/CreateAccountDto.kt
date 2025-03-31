package com.adielgarcia.gympro.data.remote.dto.utilities.create

import com.adielgarcia.gympro.data.remote.Roles

data class CreateAccountDto(
    val username: String,
    val clave: String,
    val rol: String,

    val noIdentificacion: String? = null,
    val fechaNacimiento: String? = null,

    val peso: Float? = null,
    val altura: Float? = null,
    val genero: Char? = null,
    val suscripcionId: Int? = null,

    val rango: String? = null
)
