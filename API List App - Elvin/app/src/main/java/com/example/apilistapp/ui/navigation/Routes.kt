package com.example.apilistapp.ui.navigation

import androidx.navigation3.runtime.NavKey
import com.example.apilistapp.data.remote.dto.CharacterModel
import com.example.apilistapp.domain.RMCharacter
import kotlinx.serialization.Serializable
import kotlinx.serialization.Serializer
@Serializable
sealed class Routes : NavKey {
    @Serializable data object ListScreen : Routes()
    @Serializable data object FavoriteScreen : Routes()
    @Serializable data object SettingsScreen : Routes()
    @Serializable data object DetailScreen : Routes()

}

sealed class FavoriteScreenRoute : NavKey {
    @Serializable data object FavoriteScreen2 : FavoriteScreenRoute()
    @Serializable data class FavoriteScreen2_1(val character: CharacterModel) : FavoriteScreenRoute()
}
sealed class ListScreenRoute : NavKey {
    @Serializable data object ListScreen1 : ListScreenRoute()
    @Serializable data class DetailScreen1(val character: CharacterModel) : ListScreenRoute()
}

