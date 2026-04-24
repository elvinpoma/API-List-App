package com.example.apilistapp.ui.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.ListAlt
import androidx.compose.material.icons.filled.Settings
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector

sealed class BottomBarItem(
    val Route: Routes,
    val label: String,
    val icon: ImageVector,
    val color: Color = Color.Unspecified
) {    data object Item1 : BottomBarItem(Routes.ListScreen,"Lista Personajes",Icons.Default.ListAlt, Color.Green)
    data object Item2 : BottomBarItem(Routes.FavoriteScreen,"Favoritos",Icons.Default.Favorite,Color.Red)
    data object Item3 : BottomBarItem(Routes.SettingsScreen,"Ajustes",Icons.Default.Settings, Color.Gray)
}
val bottomBarItems = listOf(BottomBarItem.Item1, BottomBarItem.Item2, BottomBarItem.Item3)
