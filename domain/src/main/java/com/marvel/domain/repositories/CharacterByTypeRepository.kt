package com.marvel.domain.repositories

import com.marvel.domain.models.CharacterEntity

interface CharacterByTypeRepository {

   suspend fun getCharacterByType(characterId: Int, type: String): List<CharacterEntity>

}
