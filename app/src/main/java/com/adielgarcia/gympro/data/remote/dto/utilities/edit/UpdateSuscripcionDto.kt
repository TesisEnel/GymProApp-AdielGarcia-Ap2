package com.adielgarcia.gympro.data.remote.dto.utilities.edit

data class UpdateSuscripcionDto(
    val suscripcionId: Int,
    val nombre: String,
    val descripcion: String,
    val precio: Float
)
