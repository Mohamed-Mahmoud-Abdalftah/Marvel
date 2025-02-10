package com.marvel.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.marvel.core.utils.Constants
import com.marvel.core.utils.IODispatcher
import com.marvel.data.datasource.CharactersDataSource
import com.marvel.data.datasource.CharacterPagingSource
import com.marvel.domain.models.CharacterEntity
import com.marvel.domain.repositories.CharactersRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

class CharactersRepositoryImpl @Inject constructor(
    private val dataSource: CharactersDataSource,
    @IODispatcher private val dispatcher: CoroutineContext
) : CharactersRepository {

    override fun getCharacters(): Flow<PagingData<CharacterEntity>> {
        return Pager(
            config = PagingConfig(
                pageSize = Constants.MAX_PAGE_SIZE,
                enablePlaceholders = false
            ),
            pagingSourceFactory = { CharacterPagingSource(dataSource, ) }
        ).flow
            .flowOn(dispatcher) // Ensure dispatcher is used
    }



}
