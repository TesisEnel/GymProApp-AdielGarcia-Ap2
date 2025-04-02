package com.adielgarcia.gympro.presentation.suscripciones

import com.adielgarcia.gympro.data.remote.dto.entities.Suscripcion

interface SuscripcionesEvents {
    data class OnSelectSuscripcion(val suscripcion: Suscripcion) : SuscripcionesEvents

    data class OnManageSuscripcion(val suscripcion: Suscripcion) : SuscripcionesEvents

    object OnCreateSuscripcion : SuscripcionesEvents

    object OnDeleteSuscripcion : SuscripcionesEvents

}