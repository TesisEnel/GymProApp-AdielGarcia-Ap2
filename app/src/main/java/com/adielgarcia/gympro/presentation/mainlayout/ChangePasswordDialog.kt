package  com.adielgarcia.gympro.presentation.mainlayout

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.adielgarcia.gympro.ui.components.GymProContentCard
import com.adielgarcia.gympro.ui.theme.PrimaryColor

@Composable
fun ChangePasswordDialog(
    onDismiss: () -> Unit,
    onConfirm: () -> Unit,
    oldPassword: String, onOldPasswordChange: (String) -> Unit,
    newPassword: String, onNewPasswordChange: (String) -> Unit,
    confirmPassword: String, onConfirmPasswordChange: (String) -> Unit
) {
    val focusManager = LocalFocusManager.current
    Dialog(
        onDismissRequest = onDismiss
    ) {
        GymProContentCard(modifier = Modifier.fillMaxWidth()) {
            Column(modifier = Modifier
                .fillMaxWidth()
                .padding(6.dp)) {
                Text(
                    text = "Cambiar Contraseña", fontSize = 20.sp,
                    modifier = Modifier.padding(bottom = 20.dp)
                )
                Text(
                    text = "¡Al cambiar la contraseña se cerrará la sesión actual!", color = Color.Red,
                    modifier = Modifier.padding(bottom = 10.dp)
                )
                OutlinedTextField(
                    value = oldPassword,
                    onValueChange = onOldPasswordChange,
                    maxLines = 1,
                    singleLine = true,
                    visualTransformation = PasswordVisualTransformation(),
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Password,
                        imeAction = ImeAction.Next
                    ),
                    keyboardActions = KeyboardActions(
                        onNext = { focusManager.moveFocus(focusDirection = FocusDirection.Down) }
                    ),
                    label = { Text("Contraseña Actual") },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 10.dp)
                )

                OutlinedTextField(
                    value = newPassword,
                    maxLines = 1,
                    singleLine = true,
                    visualTransformation = PasswordVisualTransformation(),
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Password,
                        imeAction = ImeAction.Next
                    ),
                    keyboardActions = KeyboardActions(
                        onNext = { focusManager.moveFocus(focusDirection = FocusDirection.Down) }
                    ),
                    onValueChange = onNewPasswordChange,
                    label = { Text("Nueva Contraseña") },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 10.dp)
                )

                OutlinedTextField(
                    value = confirmPassword,
                    maxLines = 1,
                    singleLine = true,
                    visualTransformation = PasswordVisualTransformation(),
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Password,
                        imeAction = ImeAction.Send,
                    ),
                    keyboardActions = KeyboardActions(
                        onSend = { focusManager.clearFocus(); onConfirm(); onDismiss(); }
                    ),
                    onValueChange = onConfirmPasswordChange,
                    label = { Text("Confirmar Contraseña") },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 10.dp)
                )


                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    Text(
                        "Cancelar",
                        color = PrimaryColor,
                        modifier = Modifier.clickable {
                            onDismiss();
                        }
                    )
                    Text(
                        "Guardar", color = PrimaryColor,
                        modifier = Modifier.clickable {
                            onConfirm();
                            onDismiss();
                        }
                    )
                }
            }
        }
    }
}