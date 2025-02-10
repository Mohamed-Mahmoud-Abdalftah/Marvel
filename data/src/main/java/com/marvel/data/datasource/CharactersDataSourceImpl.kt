package com.marvel.data.datasource

import com.marvel.core.utils.extensions.handleCall
import com.marvel.data.model.CharactersResponse
import com.marvel.data.remote.ApiService
import javax.inject.Inject

class CharactersDataSourceImpl @Inject constructor(
    private val api: ApiService
) : CharactersDataSource {

    override suspend fun getCharacters(offset: Int, limit: Int): CharactersResponse {
        return handleCall {
            api.getCharacters(offset, limit)
        }
    }


}

