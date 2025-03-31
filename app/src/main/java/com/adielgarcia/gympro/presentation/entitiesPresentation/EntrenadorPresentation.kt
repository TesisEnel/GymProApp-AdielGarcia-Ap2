package com.adielgarcia.gympro.presentation.entitiesPresentation

data class EntrenadorPresentation(
    val userId: Int,
    val username: String,
    val clave : String,
    val rol: String,
    val entrenadorId: Int,
    val rango : String,
    val clientesInscritos : Int
)
