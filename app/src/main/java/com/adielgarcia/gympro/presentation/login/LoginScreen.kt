package com.adielgarcia.gympro.presentation.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.AccountCircle
import androidx.compose.material.icons.outlined.Lock
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.adielgarcia.gympro.R
import com.adielgarcia.gympro.ui.theme.BlackOpsOne

@Composable
fun LoginScreen() {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            painter = painterResource(id = R.drawable.foreground),
            contentDescription = "Logo",
            modifier = Modifier.padding(16.dp)
        )

        Text(text = "GymPro", fontFamily = BlackOpsOne, fontSize = 28.sp)
        Text(text = "By Adiel Garcia", modifier = Modifier.padding(bottom = 20.dp))

        OutlinedTextField(
            leadingIcon = {
                Icon(
                    Icons.Outlined.AccountCircle,
                    null
                )
            },
            value = "",
            onValueChange = {},
            label = { Text(text = "Usuario o Correo ") },
            modifier = Modifier
                .fillMaxWidth(0.7f)
                .padding(vertical = 16.dp)
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
            label = { Text(text = "Contraseña") },
            modifier = Modifier
                .fillMaxWidth(0.7f)
                .padding(vertical = 16.dp)
        )

        Button(
            onClick = { /*TODO*/ },
            modifier = Modifier
                .fillMaxWidth(0.7f)
                .padding(top = 20.dp)
        ) {
            Text(text = "Iniciar sesión")
        }

        Text("¿No tienes una cuenta?", modifier = Modifier.padding(top = 18.dp))
        OutlinedButton(onClick = { /*TODO*/ }, modifier = Modifier.fillMaxWidth(0.4f)) {
            Text(text = "Registrarse")
        }
    }
}