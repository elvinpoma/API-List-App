package com.example.apilistapp.ui.navigation


import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.ui.NavDisplay
import com.example.apilistapp.ui.screens.settings.SettingsScreen
import com.example.apilistapp.ui.screens.settings.SettingsViewModel

@Composable
fun NavigationWrapper(settingsVM: SettingsViewModel) {
    val backStack = rememberNavBackStack(   Routes.ListScreen)
    val currentRoute = backStack.lastOrNull()
    Scaffold(
        bottomBar = {
            NavigationBar {
                bottomBarItems.forEach { item ->
                    val isSelected = currentRoute == item.Route

                    NavigationBarItem(
                        selected = isSelected,
                        onClick = {
                            if (currentRoute != item.Route) {
                                backStack.removeAll { true }
                                backStack.add(item.Route)
                            }
                        },
                        icon = { Icon(item.icon, contentDescription = item.label) },
                        label = { Text(item.label) },
                        colors = NavigationBarItemDefaults.colors(
                            selectedIconColor = if (item.color != Color.Unspecified) item.color else MaterialTheme.colorScheme.primary
                        )
                    )
                }
            }

        }
    ) { innerPadding ->
        NavDisplay(
            backStack = backStack,
            modifier = Modifier.padding(innerPadding),
            onBack = { backStack.removeLastOrNull() },
            entryProvider = entryProvider {
                entry<Routes.ListScreen> { ListScreenNavigation(settingsVM) }
                entry<Routes.FavoriteScreen> { FavoriteScreenNavigation(settingsVM) }
                entry<Routes.SettingsScreen> { SettingsScreen(settingsVM) }
            }
        )

    }
}