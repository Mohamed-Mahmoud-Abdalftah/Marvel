package com.marvel.home.presentation.state

import androidx.compose.runtime.Immutable
import androidx.paging.PagingData
import com.marvel.domain.models.CharacterEntity


@Immutable
data class CharacterUIState( // can be sealed class
    val isLoading: Boolean = false,
    val charactersData: PagingData<CharacterEntity> = PagingData.empty(),
    val error: Throwable? = null,
    val selectedCharacterDataID: Int? = null
)