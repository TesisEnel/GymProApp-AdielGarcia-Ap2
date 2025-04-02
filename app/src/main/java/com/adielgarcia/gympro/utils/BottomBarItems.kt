package com.adielgarcia.gympro.utils

import com.adielgarcia.gympro.R
import com.adielgarcia.gympro.data.remote.Roles

sealed class BottomBarItem(
    val rol: String, val label: String, val icon: Int, val route: String
) {
    data object ProfileItem : BottomBarItem(
        rol = Roles.CLIENTE,
        label = "Perfil",
        icon = R.drawable.profile_icon,
        route = "profile"
    )

    data object SuscripcionItem : BottomBarItem(
        rol = Roles.CLIENTE,
        label = "Suscripciones",
        icon = R.drawable.suscripciones_icon,
        route = "suscripciones"
    )

    data object EntrenadoresItem : BottomBarItem(
        rol = Roles.CLIENTE,
        label = "Entrenadores",
        icon = R.drawable.entrenador_icon,
        route = "entrenadores"
    )

    data object ProductosItem : BottomBarItem(
        rol = Roles.CLIENTE,
        label = "Productos",
        icon = R.drawable.productos_icon,
        route = "productos"
    )

    data object EquipamientosIcon : BottomBarItem(
        rol = Roles.CLIENTE,
        label = "Equipamientos",
        icon = R.drawable.equipamientos_icon,
        route = "equipamientos"
    )

    data object SoporteIcon : BottomBarItem(
        rol = Roles.CLIENTE,
        label = "Soporte",
        icon = R.drawable.it_icon,
        route = "soporte"
    )
}