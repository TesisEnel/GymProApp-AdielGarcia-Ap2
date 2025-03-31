package com.adielgarcia.gympro.data.remote.dto.entities

data class Entrenador(
    val entrenadorId: Int,
    val userId: Int,
    val rango : String,
    val clientesInscritos: Int,
    val username: String? = null,
)
