package com.adielgarcia.gympro.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.Logout
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.ArrowDropUp
import androidx.compose.material.icons.outlined.AccountCircle
import androidx.compose.material.icons.outlined.SettingsSuggest
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.adielgarcia.gympro.data.remote.Roles
import com.adielgarcia.gympro.data.remote.dto.utilities.fetching.GetUsuarioDto
import com.adielgarcia.gympro.ui.theme.SecondaryColor
import com.adielgarcia.gympro.ui.theme.TitleCardColor1
import com.adielgarcia.gympro.ui.theme.TitleCardColor2
import com.adielgarcia.gympro.ui.theme.White
import com.adielgarcia.gympro.utils.AdminNavItem
import com.adielgarcia.gympro.utils.BottomBarItem

@Composable
fun GymProDrawer(
    adminMode: Boolean,
    user: GetUsuarioDto,
    onLogout: () -> Unit,
    onNavigateTo: (String) -> Unit,
    content: @Composable () -> Unit
) {

    ModalNavigationDrawer(
        drawerContent = {
            ModalDrawerSheet {
                Column(modifier = Modifier.fillMaxSize()) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(
                                brush = Brush.horizontalGradient(
                                    colors = listOf(
                                        TitleCardColor2,
                                        TitleCardColor1,
                                    ),
                                )
                            )
                            .border(1.dp, Color.Black.copy(alpha = 0.2f))
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .weight(1f),
                            horizontalArrangement = Arrangement.SpaceEvenly,
                            verticalAlignment = Alignment.CenterVertically
                        ) {

                            Icon(
                                imageVector = Icons.Outlined.AccountCircle,
                                contentDescription = "Usuario",
                                tint = White,
                                modifier = Modifier.size(60.dp)
                            )
                            Column(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(start = 10.dp),
                                verticalArrangement = Arrangement.SpaceEvenly
                            ) {
                                Text(text = user.username, color = White, fontSize = 20.sp)
                                Text(text = user.rol, color = White, fontSize = 14.sp)
                                if (adminMode) {
                                    Text(
                                        text = "Administrador",
                                        color = White,
                                        fontSize = 14.sp
                                    )
                                }
                            }
                        }
                    }
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(8.5f)
                    ) {

                        when (user.rol) {
                            Roles.CLIENTE -> {
                                val items = listOf(
                                    BottomBarItem.EntrenadoresItem,
                                    BottomBarItem.SuscripcionItem,
                                    BottomBarItem.ProfileItem,
                                    BottomBarItem.ProductosItem,
                                    BottomBarItem.EquipamientosIcon,
                                    BottomBarItem.SoporteIcon
                                )
                                items.forEach { item ->
                                    Row(
                                        modifier = Modifier
                                            .padding(horizontal = 10.dp)
                                            .fillMaxWidth()
                                            .clickable { onNavigateTo(item.route) }) {
                                        Image(
                                            painter = painterResource(id = item.icon),
                                            contentDescription = item.label,
                                            modifier = Modifier
                                                .size(30.dp)
                                                .padding(end = 10.dp)
                                        )
                                        Text(text = item.label)
                                    }
                                }
                            }
                            //Vacio por el momento
                            Roles.ENTRENADOR -> {

                            }
                        }
                        if (adminMode) {
                            var adminExpanded by remember { mutableStateOf(false) }
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(horizontal = 10.dp)
                                    .clickable { adminExpanded = !adminExpanded },
                            ) {
                                Icon(
                                    imageVector = Icons.Outlined.SettingsSuggest,
                                    contentDescription = "Administracion",
                                    tint = SecondaryColor
                                )
                                Text(
                                    text = "Administración",
                                    modifier = Modifier.padding(horizontal = 10.dp)
                                )
                                Icon(
                                    imageVector = if (adminExpanded) Icons.Default.ArrowDropUp else Icons.Default.ArrowDropDown,
                                    contentDescription = "Administración",
                                )
                            }
                            if (adminExpanded) {
                                val items = listOf(
                                    AdminNavItem.EntrenadoresItem,
                                    AdminNavItem.EquipamientosIcon,
                                    AdminNavItem.ProductosItem,
                                    AdminNavItem.SuscripcionItem
                                )
                                items.forEach { item ->
                                    Row(
                                        modifier = Modifier
                                            .padding(horizontal = 10.dp)
                                            .padding(start = 10.dp)
                                            .fillMaxWidth()
                                            .clickable { onNavigateTo(item.screen.route) }
                                    ) {

                                        Text(text = "- " + item.label)
                                    }
                                }
                                HorizontalDivider(modifier = Modifier.padding(vertical = 10.dp))
                            }

                        }
                    }

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable {
                                onLogout()
                            }
                            .weight(.5f),
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Outlined.Logout,
                            contentDescription = "Logout",
                        )
                        Text(
                            text = "Cerrar sesión",
                            color = SecondaryColor,
                            fontSize = 20.sp
                        )
                    }
                }

            }
        },
        gesturesEnabled = true,
        content = content
    )
}