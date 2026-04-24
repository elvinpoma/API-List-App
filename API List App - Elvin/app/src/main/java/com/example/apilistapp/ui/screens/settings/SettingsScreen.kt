package com.example.apilistapp.ui.screens.settings

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Card
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle




@Composable
fun SettingsScreen(settingsVM: SettingsViewModel) {
    SettingsDialog(settingsVM)

    Column(
        Modifier
            .fillMaxSize()
            .fillMaxHeight(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center

    ) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .padding(8.dp)
        ) {
            Spacer(Modifier.padding(10.dp))
            Card {
                Column(
                    modifier = Modifier
                        .padding(16.dp)
                        .fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "CONFIGURACIONES",
                        fontSize = 25.sp,
                        fontWeight = FontWeight.ExtraBold,
                        textAlign = TextAlign.Center
                    )
                }
            }
            Spacer(Modifier.padding(30.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text("Modo Oscuro")
                BotonModoOscuro(settingsVM)
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text("Modo de Vista")
                ModoDeVista(settingsVM)

            }
            Spacer(Modifier.padding(50.dp))
            OutlinedButton(
                onClick = {
                    settingsVM.borrarFavs()
                }, Modifier
                    .fillMaxWidth()
                    .padding(20.dp, 0.dp, 20.dp, 10.dp)
            ) { Text("Borrar Favs") }
            Spacer(Modifier.padding(20.dp))
        }


    }
}

@Composable
fun SettingsDialog(settingsVM: SettingsViewModel) {
    val showDialog by settingsVM.borrarDatos.collectAsStateWithLifecycle()

    if (showDialog) {
        BorrarDatosAlert(
            onDismissRequest = { settingsVM.reiniciarBorrador() },
            onConfirmation = {
                settingsVM.borrarFavoritos()
            },
            dialogTitle = "Borrar Favoritos",
            dialogText = "¿Estás seguro de que deseas borrar todos tus personajes favoritos? Esta acción no se puede deshacer.",
            icon = Icons.Default.Info
        )
    }
}

@Composable
fun BorrarDatosAlert(
    onDismissRequest: () -> Unit,
    onConfirmation: () -> Unit,
    dialogTitle: String,
    dialogText: String,
    icon: ImageVector
) {
    AlertDialog(
        icon = {
            Icon(icon, contentDescription = "Icono de información")
        },
        title = {
            Text(text = dialogTitle)
        },
        text = {
            Text(text = dialogText)
        },
        onDismissRequest = {
            onDismissRequest()
        },
        confirmButton = {
            TextButton(
                onClick = {
                    onConfirmation()
                }
            ) {
                Text("Confirmar")
            }
        },
        dismissButton = {
            TextButton(
                onClick = {
                    onDismissRequest()
                }
            ) {
                Text("Cancelar")
            }
        }
    )
}


@Composable
fun BotonModoOscuro(settingsVM: SettingsViewModel) {
    val darkModeActual by settingsVM.isDarkMode.collectAsStateWithLifecycle()
    Switch(
        checked = darkModeActual,
        onCheckedChange = {
            settingsVM.onThemeChanged(it)
        }
    )
}

@Composable
fun ModoDeVista(settingsVM: SettingsViewModel) {
    var expanded by remember { mutableStateOf(false) }
    val isListMode by settingsVM.isList.collectAsStateWithLifecycle()
    val selectedOption = if (isListMode) "LIST" else "GRID"

    Box {
        OutlinedButton(onClick = { expanded = true }) {
            Text(selectedOption)
        }

        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            DropdownMenuItem(
                text = { Text("GRID") },
                onClick = {
                    settingsVM.onListChanged(false)
                    expanded = false
                }
            )
            DropdownMenuItem(
                text = { Text("LIST") },
                onClick = {
                    settingsVM.onListChanged(true)
                    expanded = false
                }
            )
        }
    }

}