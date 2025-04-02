package com.adielgarcia.gympro.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.adielgarcia.gympro.ui.theme.shadow

@Composable
fun GymProSearchbar(
    modifier: Modifier = Modifier,
    search: String,
    type: KeyboardOptions = SearchbarType.Text,
    onSearch: (String) -> Unit,
) {
    TextField(
        colors =  TextFieldDefaults.colors(unfocusedIndicatorColor = Color.Transparent, focusedIndicatorColor = Color.Transparent),
        value = search,
        leadingIcon = {
            Icon(
                imageVector = Icons.Default.Search,
                contentDescription = null
            )
        },
        onValueChange = onSearch,
        placeholder = { Text("Busqueda") },
        singleLine = true,
        shape = RoundedCornerShape(percent = 100),
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(percent = 100))
            .clipToBounds()
            .background(Color.White)
            .shadow(
                color = Color.Black.copy(alpha = .25f),
                offsetX = 4.dp,
                offsetY = 4.dp,
                blurRadius = 7.dp,
                modifier = Modifier.clipToBounds()
            ),
        keyboardOptions = type
    )
}


sealed class SearchbarType {
    companion object {
        val Number = KeyboardOptions(
            autoCorrectEnabled = true,
            keyboardType = KeyboardType.Number,
            imeAction = ImeAction.Search
        )

        val Text = KeyboardOptions(
            autoCorrectEnabled = true,
            keyboardType = KeyboardType.Text,
            capitalization = KeyboardCapitalization.Words,
            imeAction = ImeAction.Search
        )
    }
}