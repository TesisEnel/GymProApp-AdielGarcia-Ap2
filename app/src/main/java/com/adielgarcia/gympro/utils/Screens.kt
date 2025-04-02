package com.adielgarcia.gympro.utils

import kotlinx.serialization.Serializable

sealed class DefaultScreens{
    @Serializable
    data object Login: DefaultScreens()

    @Serializable
    data object Register: DefaultScreens()
}

sealed class Screens(val route: String){
    //#region ADMINISTRADOR
    data object ManageEntrenadores: Screens("manageEntrenadores ")

    data object ManageSuscripciones: Screens("manageSuscripciones")

    data object ManageProductos: Screens("manageProductos")

    data object ManageEquipamientos: Screens("manageEquipamientos")
    //#endregion

    //#region ENTRENADOR

    data object Clientes : Screens("Clientes")
    //#endregion
}
