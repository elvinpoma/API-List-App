package com.example.apilistapp.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.ui.NavDisplay
import com.example.apilistapp.ui.screens.favorites.DetailScreen
import com.example.apilistapp.ui.screens.favorites.FavoriteScreen
import com.example.apilistapp.ui.screens.settings.SettingsViewModel

@Composable
fun FavoriteScreenNavigation(settingsVM: SettingsViewModel) {
    val favoriteScreen2BackStack = rememberNavBackStack(FavoriteScreenRoute.FavoriteScreen2)
    NavDisplay(
        backStack = favoriteScreen2BackStack,
        onBack = { favoriteScreen2BackStack.removeLastOrNull() },
        entryProvider = entryProvider {
            entry<FavoriteScreenRoute.FavoriteScreen2> {
                FavoriteScreen(settingsVM) { character -> favoriteScreen2BackStack.add(FavoriteScreenRoute.FavoriteScreen2_1(character)) }
            }
            entry<FavoriteScreenRoute.FavoriteScreen2_1> {key ->
                DetailScreen (key.character) { favoriteScreen2BackStack.removeLastOrNull() }
            }
        }
    )
}
