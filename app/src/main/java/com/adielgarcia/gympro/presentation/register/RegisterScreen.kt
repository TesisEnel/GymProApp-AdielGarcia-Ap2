package com.adielgarcia.gympro.presentation.register

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Mail
import androidx.compose.material.icons.outlined.AccountCircle
import androidx.compose.material.icons.outlined.Lock
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.adielgarcia.gympro.R
import com.adielgarcia.gympro.ui.theme.BlackOpsOne
import com.adielgarcia.gympro.ui.theme.PrimaryColor
import com.adielgarcia.gympro.ui.theme.SecondaryColor
import com.adielgarcia.gympro.ui.theme.White

@Composable
fun RegisterScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(SecondaryColor),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            painter = painterResource(id = R.drawable.foreground),
            contentDescription = "Logo",
            modifier = Modifier.padding(16.dp)
        )

        Text(text = "GymPro", fontFamily = BlackOpsOne, fontSize = 28.sp, color = White)
        Text(text = "By Adiel Garcia", modifier = Modifier.padding(bottom = 30.dp), color = White)
        Row(
            modifier = Modifier
                .fillMaxWidth(.7f)

                .padding(vertical = 10.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            OutlinedTextField(
                leadingIcon = {
                    Icon(
                        Icons.Outlined.AccountCircle,
                        null
                    )
                },
                value = "",
                onValueChange = {},
                colors = outlinedTextFieldColors(),
                label = { Text(text = "Nombre Completo") },

                modifier = Modifier.weight(8f).padding(end = 10.dp)

            )
            // ABRIR DIALOGO DE FECHA PARA SELECCIONAR LA FECHA DE NACIMIENTO
            OutlinedTextField(
                value = "",
                onValueChange = {},
                colors = outlinedTextFieldColors(),
                label = { Text(text = "Edad") },
                modifier = Modifier.weight(2.5f)
            )
        }
        OutlinedTextField(
            leadingIcon = {
                Icon(
                    Icons.Filled.Mail,
                    null
                )
            },
            value = "",
            onValueChange = {},
            colors = outlinedTextFieldColors(),
            label = { Text(text = "Correo Electronico") },
            modifier = Modifier
                .fillMaxWidth(0.7f)
                .padding(bottom = 10.dp)
        )

        OutlinedTextField(
            leadingIcon = {
                Icon(
                    Icons.Outlined.Lock,
                    null
                )
            },
            value = "",
            onValueChange = {},
            colors = outlinedTextFieldColors(),
            label = { Text(text = "Contraseña") },
            modifier = Modifier
                .fillMaxWidth(0.7f)
                .padding(bottom = 10.dp)
        )
        OutlinedTextField(
            leadingIcon = {
                Icon(
                    Icons.Outlined.Lock,
                    null
                )
            },
            value = "",
            onValueChange = {},
            colors = outlinedTextFieldColors(),
            label = { Text(text = "Confirmar Contraseña") },
            modifier = Modifier
                .fillMaxWidth(0.7f)
                .padding(bottom = 10.dp)
        )

        Button(
            onClick = { /*TODO*/ },
            colors = containedButtonColors(),
            modifier = Modifier
                .fillMaxWidth(0.7f)
                .padding(vertical = 25.dp)
        ) {
            Text(text = "CREAR CUENTA")
        }
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Text("¿Ya tienes una cuenta?", color = White)
            TextButton(onClick = { /*TODO*/ }) {
                Text(
                    text = "Inicia sesión",
                    color = PrimaryColor,
                    textDecoration = TextDecoration.Underline
                )
            }
        }
    }
}