package com.marvel.data.datasource


import com.marvel.core.utils.extensions.handleCall
import com.marvel.data.model.CharactersResponse
import com.marvel.data.remote.ApiService
import javax.inject.Inject

class CharacterByTypeDataSourceImpl @Inject constructor(
    private val api: ApiService
) : CharacterByTypeDataSource {

    override suspend fun getCharacterByType(characterId: Int, type: String): CharactersResponse {
        return handleCall {
            api.getCharacterByType(characterId, type)
        }
    }


}

