package com.adielgarcia.gympro.utils.extensors

import com.adielgarcia.gympro.data.remote.dto.utilities.create.AddClienteDto
import com.adielgarcia.gympro.data.remote.dto.utilities.create.AddEntrenadorDto
import com.adielgarcia.gympro.data.remote.dto.utilities.create.CreateAccountDto

fun AddEntrenadorDto.toCreateAccountDto(): CreateAccountDto {
    return CreateAccountDto(
        username = this.username,
        clave = this.clave,
        rol = this.rol,

        rango = this.rango
    );
}

fun AddClienteDto.toCreateAccountDto(): CreateAccountDto {
    return CreateAccountDto(
        username = this.username,
        clave = this.clave,
        rol = this.rol,

        noIdentificacion = this.noIdentificacion,
        fechaNacimiento = this.fechaNacimiento,
        peso = this.peso,
        altura = this.altura,
        genero = this.genero,
        suscripcionId = this.suscripcionId
    );
}