package com.adielgarcia.gympro.utils

sealed class AdminNavItem(
    val label: String, val screen: Screens
) {
    data object EntrenadoresItem : AdminNavItem(
        label = "Manejar Entrenadores",
        screen = Screens.ManageEntrenadores
    )
    data object SuscripcionItem : AdminNavItem(
        label = "Manejar Suscripciones",
        screen = Screens.ManageSuscripciones
    )

    data object ProductosItem : AdminNavItem(
        label = "Manejar Productos",
        screen = Screens.ManageProductos
    )
    data object EquipamientosIcon : AdminNavItem(
        label = "Manejar Equipamientos",
        screen = Screens.ManageEquipamientos
    )

}