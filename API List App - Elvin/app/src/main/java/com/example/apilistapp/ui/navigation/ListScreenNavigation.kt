package com.example.apilistapp.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.ui.NavDisplay
import com.example.apilistapp.ui.screens.list.ListScreen
import com.example.apilistapp.ui.screens.favorites.DetailScreen
import com.example.apilistapp.ui.screens.settings.SettingsViewModel

@Composable
fun ListScreenNavigation(settingsVM: SettingsViewModel) {
    val ListScreen2BackStack = rememberNavBackStack(ListScreenRoute.ListScreen1)
    NavDisplay(
        backStack = ListScreen2BackStack,
        onBack = { ListScreen2BackStack.removeLastOrNull() },
        entryProvider = entryProvider {
            entry<ListScreenRoute.ListScreen1> {
                ListScreen(settingsVM) { character -> ListScreen2BackStack.add(ListScreenRoute.DetailScreen1(character)) }
            }
            entry<ListScreenRoute.DetailScreen1> {key ->
                DetailScreen(key.character,) { ListScreen2BackStack.removeLastOrNull() }
            }
        })
}
