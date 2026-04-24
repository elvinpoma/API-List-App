package com.example.apilistapp.ui.screens.favorites

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Female
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Male
import androidx.compose.material.icons.filled.Place
import androidx.compose.material.icons.filled.Public
import androidx.compose.material.icons.filled.Transgender
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.example.apilistapp.data.remote.dto.CharacterModel
import com.example.apilistapp.ui.screens.detail.DetailViewModel
import com.example.myapplication.ui.game.DetailViewModelFactory
import androidx.lifecycle.compose.collectAsStateWithLifecycle

@Composable
fun DetailScreen(
    character: CharacterModel,
    navigateBackList: () -> Unit,
) {

    val vm: DetailViewModel = viewModel(key = character.id.toString(), factory = DetailViewModelFactory(character))
    val estacaracter = vm.existe.collectAsStateWithLifecycle()
    LaunchedEffect(Unit) {
        vm.obtenerCharacterX(character)
    }
    val scrollState = rememberScrollState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {

        // --- 1. CABECERA PERSONALIZADA ---
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                // Botón de retroceso circular
                IconButton(
                    onClick = navigateBackList,
                    modifier = Modifier
                        .size(48.dp)
                        .background(MaterialTheme.colorScheme.surfaceVariant, CircleShape)
                ) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = "Volver atrás"
                    )
                }

                // Título centrado (con weight para equilibrar)
                Text(
                    text = "Detalle",
                    modifier = Modifier.weight(1f),
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center,
                    color = MaterialTheme.colorScheme.onBackground
                )

                if (!estacaracter.value) {
                    // Botón de Corazon circular - NO AÑADIDO
                    IconButton(
                        onClick = {
                            vm.addfav()
                        },
                        modifier = Modifier
                            .size(48.dp)
                            .background(MaterialTheme.colorScheme.surfaceVariant, CircleShape)
                    ) {
                        Icon(
                            imageVector = Icons.Filled.FavoriteBorder,
                            contentDescription = "Añadir a Favoritos"
                        )
                    }

                } else if (estacaracter.value) {
                    // Botón de Corazón circular - FAVORITO
                    IconButton(
                        onClick = {
                            vm.deletefav()
                        },
                        modifier = Modifier
                            .size(48.dp)
                            .background(MaterialTheme.colorScheme.surfaceVariant, CircleShape)
                    ) {
                        Icon(
                            imageVector = Icons.Filled.Favorite,
                            contentDescription = "Añadir a Favoritos",
                            tint = Color.Red // <--- Esto pinta el corazón de rojo
                        )
                    }
                }
            }

            // Espaciador invisible para centrado perfecto del título
            Spacer(modifier = Modifier.size(48.dp))
        }


        // --- 2. CONTENIDO PRINCIPAL DESPLAZABLE ---
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .verticalScroll(scrollState) // Habilita scroll
                .padding(bottom = 24.dp), // Espacio al final
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            // Imagen del Personaje con Coil
            AsyncImage(
                model = character.image,
                contentDescription = "Imagen de ${character.name}",
                modifier = Modifier
                    .padding(vertical = 24.dp)
                    .size(240.dp)
                    .clip(CircleShape) // Imagen circular
                    .background(MaterialTheme.colorScheme.surfaceVariant),
                contentScale = ContentScale.Crop,
            )

            // Nombre del Personaje
            Text(
                text = character.name,
                fontSize = 32.sp,
                fontWeight = FontWeight.ExtraBold,
                textAlign = TextAlign.Center,
                color = MaterialTheme.colorScheme.onBackground,
                modifier = Modifier.padding(horizontal = 16.dp)
            )

            Spacer(modifier = Modifier.height(32.dp))


            // --- SECCIÓN: INFORMACIÓN BÁSICA ---
            InfoSection(title = "Información Básica") {
                DetailItem(
                    icon = Icons.Default.Info,
                    label = "Estado",
                    value = character.status,
                    valueColor = when (character.status.lowercase()) {
                        "alive" -> Color(0xFF4CAF50) // Verde
                        "dead" -> Color(0xFFF44336) // Rojo
                        else -> MaterialTheme.colorScheme.onSurfaceVariant
                    }
                )

                HorizontalDivider()

                DetailItem(
                    icon = Icons.Default.Public, label = "Especie", value = character.species
                )

                // Género con icono dinámico
                val genderIcon = when (character.gender.lowercase()) {
                    "male" -> Icons.Default.Male
                    "female" -> Icons.Default.Female
                    else -> Icons.Default.Transgender
                }

                HorizontalDivider()

                DetailItem(
                    icon = genderIcon, label = "Género", value = character.gender
                )

                // Mostramos "Tipo" solo si existe
                if (character.type.isNotEmpty()) {
                    HorizontalDivider()
                    DetailItem(
                        icon = Icons.Default.Place, label = "Tipo", value = character.type
                    )
                }
            }


            // --- SECCIÓN: UBICACIÓN ---
            InfoSection(title = "Ubicación") {
                // Origen
                DetailItem(
                    icon = Icons.Default.LocationOn, label = "Origen", value = character.origin.name
                )

                HorizontalDivider()

                // Ubicación actual
                DetailItem(
                    icon = Icons.Default.Place,
                    label = "Ubicación actual",
                    value = character.location.name
                )
            }

            // --- SECCIÓN: EXTRAS ---
            InfoSection(title = "Otros Datos") {
                // Cantidad de episodios
                DetailItem(
                    icon = Icons.Default.Info,
                    label = "Apariciones",
                    value = "${character.episode.size} episodios"
                )
            }
        }
    }
}


// --- COMPONENTES AUXILIARES DE DISEÑO ---

@Composable
fun InfoSection(
    title: String, content: @Composable ColumnScope.() -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp)
    ) {
        // Título de la sección
        Text(
            text = title,
            fontSize = 14.sp,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.primary,
            modifier = Modifier.padding(start = 8.dp, bottom = 8.dp)
        )

        // Tarjeta contenedora de la información
        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(20.dp),
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.surfaceVariant
            )
        ) {
            Column(
                modifier = Modifier.padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                content()
            }
        }
    }
}

@Composable
fun DetailItem(
    icon: ImageVector,
    label: String,
    value: String,
    valueColor: Color = MaterialTheme.colorScheme.onSurfaceVariant
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            tint = MaterialTheme.colorScheme.secondary,
            modifier = Modifier.size(20.dp)
        )
        Text(
            text = label,
            fontWeight = FontWeight.SemiBold, // ¡AQUÍ ESTABA EL ERROR! (era fontWeight)
            fontSize = 16.sp,
            color = MaterialTheme.colorScheme.onSurface
        )
        Text(
            text = value,
            modifier = Modifier.weight(1f),
            fontSize = 16.sp,
            fontWeight = FontWeight.Medium,
            textAlign = TextAlign.End,
            color = valueColor
        )
    }
}
