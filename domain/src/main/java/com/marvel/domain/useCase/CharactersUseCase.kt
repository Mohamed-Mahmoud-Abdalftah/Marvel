package com.marvel.domain.useCase


import androidx.paging.PagingData
import com.marvel.domain.models.CharacterEntity
import com.marvel.domain.repositories.CharactersRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


class CharactersUseCase @Inject constructor(
    private val charactersRepository: CharactersRepository,
) {
    // Fetch characters from API
    fun execute(): Flow<PagingData<CharacterEntity>> {
        return charactersRepository.getCharacters()
    }

}
