package com.adielgarcia.gympro.utils

import com.adielgarcia.gympro.R

sealed class BottomBarItem(
    val label: String, val icon: Int, val route: String
) {
    data object ProfileItem : BottomBarItem(
        label = "Perfil",
        icon = R.drawable.profile_icon,
        route = "profile"
    )

    data object SuscripcionItem : BottomBarItem(
        label = "Suscripciones",
        icon = R.drawable.suscripciones_icon,
        route = "suscripciones"
    )

    data object EntrenadoresItem : BottomBarItem(
        label = "Entrenadores",
        icon = R.drawable.entrenador_icon,
        route = "entrenadores"
    )

    data object ProductosItem : BottomBarItem(
        label = "Productos",
        icon = R.drawable.productos_icon,
        route = "productos"
    )

    data object EquipamientosIcon : BottomBarItem(
        label = "Equipamientos",
        icon = R.drawable.equipamientos_icon,
        route = "equipamientos"
    )
}