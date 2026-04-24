package com.example.apilistapp.data.mapper

import com.example.apilistapp.data.local.entity.RMCharacterEntity
import com.example.apilistapp.data.remote.dto.CharacterModel
import com.example.apilistapp.domain.RMCharacter


fun CharacterModel.toDomain(): RMCharacter {
    return RMCharacter(
        id = id,
        name = name,
        status = status,
        species = species,
        created = created,
        episode = episode,
        gender = gender,
        image = image,
        location = location,
        origin = origin,
        type = type,
        url = url,
    )
}
fun RMCharacter.toDomain(): CharacterModel {
    return CharacterModel(
        id = id,
        name = name,
        status = status,
        species = species,
        created = created,
        episode = episode,
        gender = gender,
        image = image,
        location = location,
        origin = origin,
        type = type,
        url = url,
    )
}

fun RMCharacter.toEntity(): RMCharacterEntity {
    return RMCharacterEntity(
        id = id,
        name = name,
        status = status,
        species = species,
        type = type,
        gender = gender,
        image = image,
        url = url,
        created = created,
        origin = origin,
        location = location,
        episode = emptyList()
    )
}


fun RMCharacterEntity.toDomain(): RMCharacter {
    return RMCharacter(
        id = id,
        name = name,
        status = status,
        species = species,
        type = type,
        gender = gender,
        image = image,
        url = url,
        created = created,
        origin = origin,
        location = location,
        episode = emptyList()
    )
}