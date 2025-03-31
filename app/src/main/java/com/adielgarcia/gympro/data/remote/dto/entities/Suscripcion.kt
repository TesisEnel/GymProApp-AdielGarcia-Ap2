package com.adielgarcia.gympro.data.remote.dto.entities

data class Suscripcion(
    val suscripcionId: Int,
    val nombre: String,
    val descripcion: String,
    val precio: Float,
    val clientesSuscritos: Int
)
