package com.marvel.domain.useCase


import com.marvel.domain.models.CharacterEntity
import com.marvel.domain.repositories.CharacterByTypeRepository
import javax.inject.Inject


class CharacterByTypeUseCase @Inject constructor(
    private val characterByTypeRepository: CharacterByTypeRepository,
) {
    // Fetch characters  from API
    suspend fun execute(characterId: Int, type: String): List<CharacterEntity> {
        return characterByTypeRepository.getCharacterByType(characterId,type)
    }

}
