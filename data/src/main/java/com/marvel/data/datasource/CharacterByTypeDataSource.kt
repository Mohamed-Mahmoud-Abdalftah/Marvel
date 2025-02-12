package com.marvel.data.datasource

import com.marvel.data.model.CharactersResponse


interface CharacterByTypeDataSource {
    suspend fun getCharacterByType(
         characterId: Int,
        type: String,
    ): CharactersResponse

}
