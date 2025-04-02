package com.adielgarcia.gympro.presentation

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.adielgarcia.gympro.data.remote.Roles
import com.adielgarcia.gympro.data.remote.dto.utilities.fetching.GetUsuarioDto
import com.adielgarcia.gympro.presentation.clientes.ClientesScreen
import com.adielgarcia.gympro.presentation.entrenadores.EntrenadoresScreen
import com.adielgarcia.gympro.presentation.equipamientos.EquipamientosScreen
import com.adielgarcia.gympro.presentation.perfil.ProfileScreen
import com.adielgarcia.gympro.presentation.productos.ProductosScreen
import com.adielgarcia.gympro.presentation.soporte.SoporteScreen
import com.adielgarcia.gympro.presentation.suscripciones.SuscripcionesScreen
import com.adielgarcia.gympro.ui.components.GymProBottomBar
import com.adielgarcia.gympro.ui.components.GymProDrawer
import com.adielgarcia.gympro.utils.BottomBarItem
import com.adielgarcia.gympro.utils.Screens
import com.adielgarcia.gympro.utils.extensors.extractClienteData
import com.adielgarcia.gympro.utils.extensors.extractEntrenadorData
import kotlinx.coroutines.launch

@Composable
fun MainLayout(
    adminMode: Boolean,
    loggedUser: GetUsuarioDto,
    onLogOut: () -> Unit
) {
    val navHost = rememberNavController()


    val scope = rememberCoroutineScope()
    val snackbarHostState = remember { SnackbarHostState() }

    val userIsCliente = loggedUser.rol == Roles.CLIENTE

    val clienteLogged = loggedUser.extractClienteData()
    val entrenadorLogged = loggedUser.extractEntrenadorData()

    var currentPath by remember { mutableStateOf(if (userIsCliente) BottomBarItem.ProfileItem.route else Screens.Clientes.route) }

    GymProDrawer(
        onNavigateTo = { screen ->
            currentPath = screen
            navHost.navigate(screen)
        },
        adminMode = adminMode,
        user = loggedUser,
        onLogout = onLogOut
    ) {
        Scaffold(
            snackbarHost = { SnackbarHost(snackbarHostState) },
            bottomBar = {
                if (userIsCliente) {
                    GymProBottomBar(
                        currentScreenPath = currentPath,
                        onItemSelected = { screen ->
                            currentPath = screen
                            navHost.navigate(screen)
                        }
                    )
                }
            }
        ) { innerPadding ->
            NavHost(
                navController = navHost,
                startDestination = if (userIsCliente) BottomBarItem.ProfileItem.route else Screens.Clientes.route,
                modifier = Modifier.padding(innerPadding)
            ) {
                //#region ADMIN SCREENS

                //#endregion

                //#region CLIENTES SCREENS
                composable(BottomBarItem.ProfileItem.route) {
                    ProfileScreen(
                        userData = clienteLogged,
                        launchNotification = {
                            scope.launch {
                                snackbarHostState.showSnackbar(it)
                            }
                        }
                    )
                }
                composable(BottomBarItem.ProductosItem.route) {
                    ProductosScreen(
                        launchNotification = {
                            scope.launch {
                                snackbarHostState.showSnackbar(it)
                            }
                        }
                    )
                }
                composable(BottomBarItem.EquipamientosIcon.route) {
                    EquipamientosScreen(
                        launchNotification = {
                            scope.launch {
                                snackbarHostState.showSnackbar(it)
                            }
                        }
                    )
                }
                composable(BottomBarItem.SuscripcionItem.route) {
                    SuscripcionesScreen(
                        launchNotification = {
                            scope.launch {
                                snackbarHostState.showSnackbar(it)
                            }
                        }
                    )
                }
                composable(BottomBarItem.EntrenadoresItem.route) {
                    EntrenadoresScreen(
                        launchNotification = {
                            scope.launch {
                                snackbarHostState.showSnackbar(it)
                            }
                        }
                    )
                }
                composable(BottomBarItem.SoporteIcon.route) {
                    SoporteScreen()
                }
                //#endregion

                //#region ENTRENADORES SCREEN
                composable(Screens.Clientes.route) {
                    ClientesScreen(
                        entrenador = entrenadorLogged,
                        launchNotification = {
                            scope.launch {
                                snackbarHostState.showSnackbar(it)
                            }
                        }
                    )
                }
                //#endregion
            }
        }
    }
}