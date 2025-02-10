package com.marvel.data.datasource

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.marvel.data.mapper.toCharacterEntity
import com.marvel.domain.models.CharacterEntity
import javax.inject.Inject

class CharacterPagingSource @Inject constructor(
    private val characterDataSource: CharactersDataSource,
) : PagingSource<Int, CharacterEntity>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, CharacterEntity> {
        val page = params.key ?: 1
        return try {
            val response = characterDataSource.getCharacters(page, params.loadSize)
            val characters = response.data?.characters.orEmpty().map { it.toCharacterEntity() }

            LoadResult.Page(
                data = characters,
                prevKey = if (page == 1) null else page - 1,
                nextKey = if (characters.isEmpty()) null else page + 1
            )

        } catch (exception: Exception) {

            LoadResult.Error(exception)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, CharacterEntity>): Int? {
        return state.anchorPosition
    }
}

