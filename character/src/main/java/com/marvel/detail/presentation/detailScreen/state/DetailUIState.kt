package com.marvel.detail.presentation.detailScreen.state

import androidx.compose.runtime.Immutable
import com.marvel.domain.models.CharacterEntity


@Immutable
data class DetailUIState(
    val isComicsLoading: Boolean = false,
    val isSeriesLoading: Boolean = false,
    val isStoriesLoading: Boolean = false,
    val isEventsLoading: Boolean = false,
    val characterComicsData: List<CharacterEntity> = emptyList<CharacterEntity>(),
    val characterSeriesData: List<CharacterEntity> = emptyList<CharacterEntity>(),
    val characterStoriesData: List<CharacterEntity> = emptyList<CharacterEntity>(),
    val characterEventsData: List<CharacterEntity> = emptyList<CharacterEntity>(),
    val error: Throwable? = null
)