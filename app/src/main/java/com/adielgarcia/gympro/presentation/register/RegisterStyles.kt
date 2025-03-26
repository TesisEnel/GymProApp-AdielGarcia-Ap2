package com.adielgarcia.gympro.presentation.register

import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.TextFieldColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.adielgarcia.gympro.ui.theme.PrimaryColor
import com.adielgarcia.gympro.ui.theme.White

@Composable
fun outlinedTextFieldColors(
    focusedColor: Color = White,
    unfocusedColor: Color = Color(0xC8FFFFFF),
): TextFieldColors {
    return OutlinedTextFieldDefaults.colors(
        focusedBorderColor = focusedColor,
        unfocusedBorderColor = unfocusedColor,
        focusedLabelColor = focusedColor,
        unfocusedLabelColor = unfocusedColor,
        cursorColor = focusedColor,
        focusedLeadingIconColor = focusedColor,
        unfocusedLeadingIconColor = unfocusedColor,
    )
}

@Composable
fun containedButtonColors(
    contentColor: Color = PrimaryColor,
    containerColor: Color = White,
): ButtonColors {
    return ButtonDefaults.buttonColors(
        containerColor = containerColor,
        contentColor = contentColor,
    )
}
