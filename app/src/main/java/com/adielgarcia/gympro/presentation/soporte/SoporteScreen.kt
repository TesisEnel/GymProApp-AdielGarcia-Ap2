package com.adielgarcia.gympro.presentation.soporte

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.adielgarcia.gympro.APP_INFO
import com.adielgarcia.gympro.DIRECCION
import com.adielgarcia.gympro.MAIL
import com.adielgarcia.gympro.R
import com.adielgarcia.gympro.TELEFONO
import com.adielgarcia.gympro.ui.components.GymProContentCard
import com.adielgarcia.gympro.ui.theme.BlackOpsOne
import com.adielgarcia.gympro.ui.theme.PrimaryColor

@Composable
fun SoporteScreen() {
    val context = LocalContext.current

    var showDialog by remember { mutableStateOf(false) }

    if(showDialog){
        MailDialog(context, onDismiss = { showDialog = false })
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(30.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Image(
            painterResource(R.drawable.foreground),
            null,
            modifier = Modifier.size(100.dp).padding(bottom = 20.dp)
        )
        Text("GymPro", color = PrimaryColor, fontFamily = BlackOpsOne, fontSize = 30.sp)
        Text(
            "By Adiel Garcia y Erick Peña",
            color = PrimaryColor,
            fontFamily = BlackOpsOne,
            fontSize = 20.sp
        )

        GymProContentCard(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 30.dp)
        ) {
            Text(text = APP_INFO, modifier = Modifier.padding(10.dp), fontSize = 12.sp)
        }
        GymProContentCard(
            modifier = Modifier
                .fillMaxWidth()
                .padding(15.dp)
        ) {
            Column(modifier = Modifier.padding(10.dp)) {
                Text(text = "Correo: $MAIL", modifier = Modifier.padding(10.dp), fontSize = 12.sp)
                Text(
                    text = "Direccion: $DIRECCION",
                    modifier = Modifier.padding(10.dp),
                    fontSize = 12.sp
                )
                Text(
                    text = "Telefono: $TELEFONO",
                    modifier = Modifier.padding(10.dp),
                    fontSize = 12.sp
                )
            }
            Button(
                modifier = Modifier
                    .padding(bottom = 10.dp)
                    .align(Alignment.CenterHorizontally),
                onClick = { showDialog = true }
            ) {
                Text("Contactar")
            }
        }
    }
}