package com.adielgarcia.gympro.presentation.soporte

import android.content.Context
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.adielgarcia.gympro.ui.components.GymProContentCard
import com.adielgarcia.gympro.utils.sendEmail

@Composable
fun MailDialog(context: Context, onDismiss: () -> Unit) {
    var body by remember { mutableStateOf("") }
    var subject by remember { mutableStateOf("") }
    Dialog(
        onDismissRequest = { onDismiss(); body = ""; subject = "" },
    ) {
        GymProContentCard {
            Column(modifier = Modifier.padding(8.dp)) {
                OutlinedTextField(
                    value = subject,
                    onValueChange = { subject = it },
                    label = { Text("Asunto") },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 10.dp)
                )
                OutlinedTextField(
                    value = body,
                    onValueChange = { body = it },
                    label = { Text("Cuerpo del correo") },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 10.dp)
                )
                Button(onClick = {
                    sendEmail(context, subject, body)
                    body = ""
                    subject = ""
                    onDismiss()
                }, modifier = Modifier.align(Alignment.CenterHorizontally)) {
                    Text("Enviar correo")
                }
            }
        }
    }
}
