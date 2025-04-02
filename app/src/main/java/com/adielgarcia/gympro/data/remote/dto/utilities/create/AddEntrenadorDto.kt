package com.adielgarcia.gympro.data.remote.dto.utilities.create

import com.adielgarcia.gympro.data.remote.Roles


data class AddEntrenadorDto(
    val username: String,
    val clave: String = "Gympro123",
    val rol: String = Roles.ENTRENADOR,

    val rango: String
)
