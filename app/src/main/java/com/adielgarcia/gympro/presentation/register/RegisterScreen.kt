package com.adielgarcia.gympro.presentation.register

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.CalendarToday
import androidx.compose.material.icons.outlined.AccountCircle
import androidx.compose.material.icons.outlined.CoPresent
import androidx.compose.material.icons.outlined.DateRange
import androidx.compose.material.icons.outlined.Lock
import androidx.compose.material.icons.outlined.LockReset
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.adielgarcia.gympro.R
import com.adielgarcia.gympro.data.remote.Generos
import com.adielgarcia.gympro.data.remote.dto.utilities.fetching.GetUsuarioDto
import com.adielgarcia.gympro.ui.components.ShowDatePickerDialog
import com.adielgarcia.gympro.ui.theme.BlackOpsOne
import com.adielgarcia.gympro.ui.theme.SecondaryColor
import com.adielgarcia.gympro.ui.theme.Suscripcion1Color1
import com.adielgarcia.gympro.ui.theme.White
import kotlinx.coroutines.launch

@Composable
fun RegisterScreen(
    viewModel: RegisterViewModel = hiltViewModel(),
    navigateToLogin: () -> Unit,
    onRegister: (GetUsuarioDto) -> Unit
) {
    val uiState = viewModel.uiState.collectAsState().value
    val onEvent = viewModel::onEvent

    val scope = rememberCoroutineScope()
    val snackbarHostState = remember { SnackbarHostState() }

    val context = LocalContext.current
    val showDatePicker = remember { mutableStateOf(false) }

    if (showDatePicker.value) {
        ShowDatePickerDialog(
            context = context,
            onDateSelected = { selectedDate ->
                onEvent(RegisterEvents.FechaNacimientoChanged(selectedDate))
                showDatePicker.value = false
            }
        )
    }

    Scaffold(
        snackbarHost = { SnackbarHost(snackbarHostState) }
    )
    { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(SecondaryColor)
                .padding(innerPadding)
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Image(
                painter = painterResource(id = R.drawable.foreground),
                contentDescription = "Logo",
                modifier = Modifier.padding(16.dp)
            )

            Text(text = "GymPro", fontFamily = BlackOpsOne, fontSize = 28.sp, color = White)
            Text(
                text = "By Adiel Garcia y Erick Peña",
                modifier = Modifier.padding(bottom = 30.dp),
                color = White
            )
            Spacer(modifier = Modifier.height(20.dp))
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 10.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(text = "Crea una cuenta", color = White)
                when (uiState.currentStep) {
                    1 -> {
                        OutlinedTextField(
                            leadingIcon = {
                                Icon(
                                    Icons.Outlined.AccountCircle,
                                    null
                                )
                            },
                            value = uiState.username,
                            onValueChange = { onEvent(RegisterEvents.UsernameChanged(it)) },
                            colors = outlinedTextFieldColors(),
                            label = { Text(text = "Nombre de usuario") },

                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 10.dp)

                        )
                        OutlinedTextField(
                            leadingIcon = {
                                Icon(
                                    Icons.Outlined.Lock,
                                    null
                                )
                            },
                            value = uiState.password,
                            onValueChange = { onEvent(RegisterEvents.PasswordChanged(it)) },
                            colors = outlinedTextFieldColors(),
                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                            visualTransformation = PasswordVisualTransformation(),
                            label = { Text(text = "Contraseña") },
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 10.dp)
                        )

                        OutlinedTextField(
                            leadingIcon = {
                                Icon(
                                    Icons.Outlined.LockReset,
                                    null
                                )
                            },
                            value = uiState.repeatedPassword,
                            onValueChange = { onEvent(RegisterEvents.RepeatedPasswordChanged(it)) },
                            colors = outlinedTextFieldColors(),
                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                            visualTransformation = PasswordVisualTransformation(),
                            label = { Text(text = "Confirmar contraseña") },
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 10.dp)
                        )

                    }

                    2 -> {
                        OutlinedTextField(
                            leadingIcon = {
                                Icon(
                                    Icons.Outlined.CoPresent,
                                    null
                                )
                            },
                            value = uiState.noIdentificacion,
                            onValueChange = { onEvent(RegisterEvents.NoIdentificacionChanged(it)) },

                            colors = outlinedTextFieldColors(),
                            label = { Text(text = "Número de identificación") },
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 10.dp)
                        )
                        OutlinedTextField(
                            leadingIcon = {
                                Icon(
                                    Icons.Outlined.DateRange,
                                    null
                                )
                            },
                            trailingIcon = {
                                IconButton(onClick = { showDatePicker.value = true }) {
                                    Icon(
                                        imageVector = Icons.Default.CalendarToday,
                                        contentDescription = "Seleccionar Fecha"
                                    )
                                }
                            },

                            readOnly = true,
                            value = uiState.fechaNacimiento,
                            onValueChange = { onEvent(RegisterEvents.FechaNacimientoChanged(it)) },
                            colors = outlinedTextFieldColors(),
                            label = { Text(text = "Fecha de nacimiento") },
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 10.dp)
                        )
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 10.dp)
                        ) {
                            OutlinedTextField(
                                leadingIcon = {
                                    Icon(
                                        Icons.Outlined.AccountCircle,
                                        null
                                    )
                                },
                                value = uiState.peso.toString(),
                                onValueChange = { onEvent(RegisterEvents.PesoChanged(it.toFloat())) },
                                colors = outlinedTextFieldColors(),
                                keyboardOptions = KeyboardOptions(
                                    keyboardType = KeyboardType.Number
                                ),
                                label = { Text(text = "Peso (Lbs)") },
                                modifier = Modifier
                                    .padding(end = 5.dp)
                                    .weight(1f)
                            )
                            OutlinedTextField(
                                leadingIcon = {
                                    Icon(
                                        Icons.Outlined.AccountCircle,
                                        null
                                    )

                                },
                                value = uiState.altura.toString(),
                                onValueChange = { onEvent(RegisterEvents.AlturaChanged(it.toFloat())) },
                                keyboardOptions = KeyboardOptions(
                                    keyboardType = KeyboardType.Number
                                ),
                                colors = outlinedTextFieldColors(),
                                label = { Text(text = "Altura (Mts)") },
                                modifier = Modifier
                                    .padding(start = 5.dp)
                                    .weight(1f)
                            )

                        }
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceEvenly
                        ) {
                            Column {
                                RadioButton(
                                    selected = uiState.genero == Generos.MASCULINO,
                                    onClick = { onEvent(RegisterEvents.GeneroChanged(Generos.MASCULINO)) }
                                )
                                Text(text = Generos.MASCULINO, color = White)

                            }
                            Column {

                                RadioButton(
                                    selected = uiState.genero == Generos.FEMENINO,
                                    onClick = { onEvent(RegisterEvents.GeneroChanged(Generos.FEMENINO)) }
                                )
                                Text(text = Generos.FEMENINO, color = White)
                            }

                        }

                    }

                    3 -> {
                        val expanded = remember { mutableStateOf(false) }


                        Column(
                            modifier = Modifier.fillMaxWidth(0.7f)
                        ) {
                            OutlinedTextField(
                                value = uiState.suscripcion,
                                onValueChange = {},
                                enabled = false,
                                readOnly = true,
                                label = { Text("Selecciona la suscripción") },
                                colors = outlinedTextFieldColors(),
                                trailingIcon = {
                                    Icon(
                                        imageVector = Icons.Filled.ArrowDropDown,
                                        contentDescription = null,
                                        modifier = Modifier.clickable {
                                            expanded.value = !expanded.value
                                        }
                                    )
                                },
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(bottom = 10.dp)
                            )

                            DropdownMenu(
                                expanded = expanded.value,
                                onDismissRequest = { expanded.value = false },
                                modifier = Modifier
                                    .fillMaxWidth(0.7f)
                                    .background(White)
                                    .padding(bottom = 50.dp)
                            ) {
                                uiState.suscripciones.forEach { subscription ->
                                    DropdownMenuItem(
                                        text = {
                                            Text(text = subscription.nombre)
                                        },
                                        onClick = {
                                            onEvent(
                                                RegisterEvents.SuscripcionChanged(
                                                    subscription.suscripcionId,
                                                    subscription.nombre
                                                )
                                            )
                                            expanded.value = false
                                        }
                                    )

                                }
                            }
                        }
                    }

                }
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                TextButton(
                    enabled = uiState.currentStep != 1,
                    onClick = {
                        onEvent(RegisterEvents.OnPreviousStep)
                    }
                ) {
                    Text(
                        text = "Atrás",
                        color = Suscripcion1Color1.copy(alpha = if (uiState.currentStep != 1) 1f else 0.4f)
                    )
                }

                Spacer(modifier = Modifier.weight(1f))
                if (uiState.currentStep == 3) {
                    Button(
                        onClick = {
                            onEvent(
                                RegisterEvents.OnCreateAccount(
                                    onRegister,
                                    showNotification = { mensaje ->
                                        scope.launch {
                                            snackbarHostState.showSnackbar(mensaje)
                                        }
                                    }
                                )
                            )
                        },
                        colors = containedButtonColors()
                    ) {
                        Text(text = "CREAR CUENTA")
                    }
                } else {
                    TextButton(
                        onClick = {
                            onEvent(RegisterEvents.OnNextStep)
                        }
                    ) {
                        Text(
                            text = "Siguiente",
                            color = Suscripcion1Color1
                        )
                    }
                }
            }

            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Text("¿Ya tienes una cuenta?", color = White)
                TextButton(onClick = navigateToLogin) {
                    Text(
                        text = "Inicia sesión",
                        color = Suscripcion1Color1,
                        textDecoration = TextDecoration.Underline
                    )
                }
            }
        }
    }
}

