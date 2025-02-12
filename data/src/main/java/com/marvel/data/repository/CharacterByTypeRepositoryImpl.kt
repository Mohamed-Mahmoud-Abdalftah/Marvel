package com.marvel.data.repository

import com.marvel.data.datasource.CharacterByTypeDataSource
import com.marvel.data.mapper.toCharacterList
import com.marvel.domain.models.CharacterEntity
import com.marvel.domain.repositories.CharacterByTypeRepository
import javax.inject.Inject

class CharacterByTypeRepositoryImpl @Inject constructor(
    private val dataSource: CharacterByTypeDataSource,
) : CharacterByTypeRepository {

    override suspend fun getCharacterByType(
        characterId: Int,
        type: String
    ): List<CharacterEntity> {
        return try {
            dataSource.getCharacterByType(characterId,type).toCharacterList()
        } catch (exception: Exception) {
            throw Exception("Failed to load movie details", exception)
        }
    }
}

