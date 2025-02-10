package com.marvel.data.datasource

import com.marvel.data.model.CharactersResponse


interface CharactersDataSource {

    suspend fun getCharacters(
        offset: Int, limit: Int
    ): CharactersResponse

}
