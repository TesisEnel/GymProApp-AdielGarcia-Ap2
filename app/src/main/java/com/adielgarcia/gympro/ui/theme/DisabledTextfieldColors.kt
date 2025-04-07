package com.adielgarcia.gympro.ui.theme

import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

@Composable
fun disabledTextfieldColors(): TextFieldColors {
    return TextFieldDefaults.colors(
        disabledTextColor = SecondaryColor,
        disabledLeadingIconColor = SecondaryColor,
        disabledTrailingIconColor = SecondaryColor,
        disabledPlaceholderColor = SecondaryColor,
        disabledLabelColor = SecondaryColor,
        disabledIndicatorColor = SecondaryColor,
        errorIndicatorColor = SecondaryColor,
        errorLeadingIconColor = SecondaryColor,

        errorTrailingIconColor = SecondaryColor,
        errorLabelColor = SecondaryColor,
        errorCursorColor = SecondaryColor,
        focusedIndicatorColor = Color.Transparent,
        unfocusedIndicatorColor = Color.Transparent,
        unfocusedLeadingIconColor = SecondaryColor,
        unfocusedTrailingIconColor = SecondaryColor,
        unfocusedLabelColor = SecondaryColor,
        unfocusedPlaceholderColor = SecondaryColor,
        disabledContainerColor = Color.Transparent
    )
}