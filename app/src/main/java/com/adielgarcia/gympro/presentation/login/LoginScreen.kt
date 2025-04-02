package com.adielgarcia.gympro.presentation.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.AccountCircle
import androidx.compose.material.icons.outlined.Lock
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.adielgarcia.gympro.R
import com.adielgarcia.gympro.data.remote.dto.utilities.fetching.GetUsuarioDto
import com.adielgarcia.gympro.ui.theme.BlackOpsOne
import kotlinx.coroutines.launch


@Composable
fun LoginScreen(
    viewModel: LoginViewModel = hiltViewModel(),
    onRegister: () -> Unit,
    onLogin: (GetUsuarioDto, Boolean) -> Unit
) {
    val uiState = viewModel.uiState.collectAsState().value

    val onEvent = viewModel::onEvent

    val scope = rememberCoroutineScope()
    val snackbarHostState = remember { SnackbarHostState() }
    Scaffold(
        snackbarHost = { SnackbarHost(snackbarHostState) }
    )
    { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Image(
                painter = painterResource(id = R.drawable.foreground),
                contentDescription = "Logo",
                modifier = Modifier
                    .padding(16.dp)
                    .clickable {
                        if (uiState.clickCount < 4) {
                            onEvent(LoginEvents.OnImageClicked)
                        }
                        if (uiState.clickCount == 3) {
                            scope.launch {
                                snackbarHostState.showSnackbar("¡Ahora eres administrador!")
                            }
                        }
                    }
            )

            Text(text = "GymPro", fontFamily = BlackOpsOne, fontSize = 28.sp)
            Text(text = "By Adiel Garcia y Erick Peña", modifier = Modifier.padding(bottom = 20.dp))

            OutlinedTextField(
                leadingIcon = {
                    Icon(
                        Icons.Outlined.AccountCircle,
                        null
                    )
                },
                value = uiState.username,
                onValueChange = { onEvent(LoginEvents.UsernameChanged(it)) },
                label = { Text(text = "Nombre de Usuario") },
                maxLines = 1,
                singleLine = true,
                isError = uiState.usernameError != null,
                modifier = Modifier
                    .fillMaxWidth(0.7f)
                    .padding(top = 16.dp)
            )
            if (uiState.usernameError != null) {
                Text(text = uiState.usernameError, color = Color.Red)
            }
            OutlinedTextField(
                leadingIcon = {
                    Icon(
                        Icons.Outlined.Lock,
                        null
                    )
                },
                value = uiState.password,
                onValueChange = { onEvent(LoginEvents.PasswordChanged(it)) },
                label = { Text(text = "Contraseña") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                visualTransformation = PasswordVisualTransformation(),
                maxLines = 1,
                isError = uiState.passwordError != null,
                singleLine = true,
                modifier = Modifier
                    .fillMaxWidth(0.7f)
                    .padding(top = 16.dp)
            )
            if (uiState.passwordError != null) {
                Text(text = uiState.passwordError, color = Color.Red)
            }

            Button(
                enabled = !uiState.isLoading,
                onClick = {
                    onEvent(LoginEvents.OnLogin(onLogin, showNotification = { mensaje ->
                        scope.launch {
                            snackbarHostState.showSnackbar(mensaje)
                        }
                    }))
                },
                modifier = Modifier
                    .fillMaxWidth(0.7f)
                    .padding(top = 36.dp)
            ) {
                Text(text = "Iniciar sesión")
            }

            Text("¿No tienes una cuenta?", modifier = Modifier.padding(top = 18.dp))
            OutlinedButton(onClick = onRegister, modifier = Modifier.fillMaxWidth(0.4f)) {
                Text(text = "Registrarse")
            }
        }
    }
}
