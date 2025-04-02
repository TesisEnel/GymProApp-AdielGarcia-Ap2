package com.adielgarcia.gympro.utils.extensors

import com.adielgarcia.gympro.data.remote.dto.utilities.fetching.GetUsuarioDto
import com.adielgarcia.gympro.presentation.entitiesPresentation.ClientePresentation

fun GetUsuarioDto.extractClienteData(): ClientePresentation {
    return ClientePresentation(
        clienteId = this.clienteId ?: 0,
        userId = this.userId,
        username = this.username,
        clave = this.clave,
        rol = this.rol,
        suscripcionId = this.suscripcionId ?: 0,
        entrenadorId = this.entrenadorId ?: 0,
        noIdentificacion = this.noIdentificacion ?: "",
        fechaNacimiento = this.fechaNacimiento ?: "",
        peso = this.peso ?: 0f,
        altura = this.altura ?: 0f,
        genero = this.genero ?: 'M',
        edad = this.edad ?: 0,
        suscripcion = this.suscripcion ?: "",
        entrenador = this.entrenador ?: ""
    )
}