package com.adielgarcia.gympro.presentation.general

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.adielgarcia.gympro.data.remote.dto.utilities.fetching.GetUsuarioDto
import com.adielgarcia.gympro.presentation.mainlayout.MainLayout
import com.adielgarcia.gympro.presentation.login.LoginScreen
import com.adielgarcia.gympro.presentation.register.RegisterScreen
import com.adielgarcia.gympro.utils.DefaultScreens

@Composable
fun LoginWrapper() {
    val noLoggedNavController = rememberNavController()
    var adminMode by remember { mutableStateOf(false) }
    var userData by remember { mutableStateOf<GetUsuarioDto?>(null) }
    if (userData != null) {
        MainLayout(
            adminMode,
            userData ?: GetUsuarioDto(
                username = "",
                rol = "",
                clave= "",
                userId = 0
            ),
            onLogOut = {
                userData = null; adminMode = false
            }
        )
    } else {//No logged in
        NavHost(navController = noLoggedNavController, startDestination = DefaultScreens.Login) {
            composable<DefaultScreens.Login> {
                LoginScreen(
                    onRegister = {
                        noLoggedNavController.navigate(DefaultScreens.Register)
                    }
                ) { data, admin ->
                    userData = data
                    adminMode = admin
                }
            }
            composable<DefaultScreens.Register> {
                RegisterScreen(
                    navigateToLogin = {
                        noLoggedNavController.navigate(DefaultScreens.Login)
                    }
                ) { data ->
                    userData = data
                    noLoggedNavController.navigate(DefaultScreens.Login)
                }
            }
        }
    }
}