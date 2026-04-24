package com.example.apilistapp.ui.screens.list

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.example.apilistapp.data.remote.dto.CharacterModel
import com.example.apilistapp.ui.screens.settings.SettingsViewModel
import com.example.apilistapp.ui.viewmodel.CharacterViewModel


@Composable
fun ListScreen(settingsVM: SettingsViewModel, navigateToDetail: (CharacterModel) -> Unit) {
    val vm: CharacterViewModel = viewModel()
    val isList by settingsVM.isList.collectAsStateWithLifecycle()
    val characters by vm.characters.collectAsState()

    Column(
        Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Lista de personajes",
            modifier = Modifier.padding(16.dp),
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center
        )

        if (characters.isEmpty()) {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator()
            }
        } else {
            when (isList) {
                true -> {
                    montarLista(characters, navigateToDetail) {
                        vm.obtenerCharactersAPI()
                    }
                }
                else -> {
                    montarGrid(characters, navigateToDetail) {
                        vm.obtenerCharactersAPI()
                    }
                }
            }
        }
    }
}

//Composable para mostrar en formato LISTA
@Composable
fun montarLista(
    characters: List<CharacterModel>,
    navigateToDetail: (CharacterModel) -> Unit,
    onLoadMore: () -> Unit
) {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(characters.size) { index ->
            val character = characters[index]
            if (index >= characters.size - 1) {
                onLoadMore()
            }
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { navigateToDetail(character) }
            ) {
                Row(
                    modifier = Modifier
                        .padding(8.dp)
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    AsyncImage(
                        model = character.image,
                        contentDescription = character.name,
                        modifier = Modifier
                            .size(80.dp)
                            .clip(RoundedCornerShape(8.dp)),
                        contentScale = ContentScale.Crop
                    )

                    Spacer(modifier = Modifier.width(16.dp))

                    Column {
                        Text(
                            text = character.name,
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.Bold
                        )
                        Text(
                            text = "Estado: ${character.status}",
                            style = MaterialTheme.typography.bodyMedium,
                            color = when (character.status.lowercase()) {
                                "alive" -> MaterialTheme.colorScheme.primary
                                "dead" -> MaterialTheme.colorScheme.error
                                else -> MaterialTheme.colorScheme.onSurfaceVariant
                            }
                        )
                    }
                }
            }
        }
    }
}

//Composable par mostrar en formato GRID
@Composable
fun montarGrid(
    characters: List<CharacterModel>,
    navigateToDetail: (CharacterModel) -> Unit,
    onLoadMore: () -> Unit
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(16.dp),
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        items(characters.size) { index ->
            val character = characters[index]
            if (index >= characters.size - 1) {
                onLoadMore()
            }
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { navigateToDetail(character) },
                shape = RoundedCornerShape(12.dp)
            ) {
                Column(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    AsyncImage(
                        model = character.image,
                        contentDescription = character.name,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(150.dp),
                        contentScale = ContentScale.Crop
                    )

                    Column(
                        modifier = Modifier.padding(12.dp)
                    ) {
                        Text(
                            text = character.name,
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.Bold,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis
                        )

                        Spacer(modifier = Modifier.height(4.dp))

                        Text(
                            text = character.status,
                            style = MaterialTheme.typography.bodySmall,
                            color = when (character.status.lowercase()) {
                                "alive" -> Color(0xFF4CAF50)
                                "dead" -> MaterialTheme.colorScheme.error
                                else -> MaterialTheme.colorScheme.onSurfaceVariant
                            }
                        )
                    }
                }
            }
        }
    }
}