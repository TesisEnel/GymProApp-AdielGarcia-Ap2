package com.adielgarcia.gympro.utils.extensors

import com.adielgarcia.gympro.data.remote.dto.utilities.fetching.GetUsuarioDto
import com.adielgarcia.gympro.presentation.entitiesPresentation.EntrenadorPresentation

fun GetUsuarioDto.ExtractEntrenadorData(): EntrenadorPresentation{
    return EntrenadorPresentation(
        userId = this.userId,
        username = this.username,
        clave = this.clave,
        rol = this.rol,
        entrenadorId = this.entrenadorId ?: 0,
        rango = this.rango ?: "",
        clientesInscritos = this.clientesInscritos ?: 0
    )
}