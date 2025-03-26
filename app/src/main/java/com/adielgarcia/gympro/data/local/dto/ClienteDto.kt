package com.adielgarcia.gympro.data.local.dto

data class ClienteDto(
    val clienteId : Int,
    val userId : Int,
    val suscripcionId : Int,
    val entrenadorId : Int,
    val correoElectronico : String,
    val noIdentificacion : String,
    val fechaNacimiento : String
)

data class AddCliente (
    val userId : Int,
    val suscripcionId : Int,
    val entrenadorId : Int,
    val correoElectronico : String,
    val noIdentificacion : String,
    val fechaNacimiento : String
)
