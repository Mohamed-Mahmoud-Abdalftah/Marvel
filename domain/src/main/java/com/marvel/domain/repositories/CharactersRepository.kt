package com.marvel.domain.repositories

import androidx.paging.PagingData
import com.marvel.domain.models.CharacterEntity

import kotlinx.coroutines.flow.Flow

interface CharactersRepository {

    fun getCharacters(): Flow<PagingData<CharacterEntity>>

}
