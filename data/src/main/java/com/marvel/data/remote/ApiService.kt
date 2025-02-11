package com.marvel.data.remote

import com.marvel.data.model.CharactersResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @GET("characters")
    suspend fun getCharacters(
        @Query("offset") offset: Int,
        @Query("limit") limit: Int
    ): Response<CharactersResponse>

    @GET("characters/{characterId}/{type}")
    suspend fun getCharacterDetails(
        @Path("characterId") characterId: Long,
        @Path("type") type: String,
    ): Response<CharactersResponse>

}
