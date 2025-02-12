package com.marvel.detail.presentation.detailScreen

import androidx.lifecycle.viewModelScope
import com.marvel.core.navigation.NavigationService
import com.marvel.core.presentation.StateAndEventViewModel
import com.marvel.detail.presentation.detailScreen.state.DetailUIState
import com.marvel.detail.presentation.detailScreen.uievent.DetailUIEvent
import com.marvel.domain.useCase.CharacterByTypeUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val characterByTypeUseCase: CharacterByTypeUseCase,
    private val navigator: NavigationService
) : StateAndEventViewModel<DetailUIState, DetailUIEvent>(DetailUIState()) {


    public override suspend fun handleEvent(event: DetailUIEvent) {
        when (event) {

            is DetailUIEvent.Dismiss -> {
                handleBack()
            }

            is DetailUIEvent.LoadInitialComics -> {
                loadCharacterComics(event.id)
            }
            is DetailUIEvent.LoadInitialSeries -> {
                loadCharacterSeries(event.id)
            }
            is DetailUIEvent.LoadInitialStories -> {
                loadCharacterStories(event.id)
            }
            is DetailUIEvent.LoadInitialEvents -> {
                loadCharacterEvents(event.id)
            }
        }
    }


    private fun loadCharacterComics(id: Int) {
        updateUiState { copy(isComicsLoading = true, error = null) }
        viewModelScope.launch {
            try {
                val characterData = characterByTypeUseCase.execute(id,"comics")
              updateUiState { copy(isComicsLoading = false, characterComicsData = characterData) }
            } catch (e: Exception) {
                updateUiState { copy(isComicsLoading = false, error = e) }
            }
        }
    }

    private fun loadCharacterSeries(id: Int) {
        updateUiState { copy(isSeriesLoading = true, error = null) }
        viewModelScope.launch {
            try {
                val characterData = characterByTypeUseCase.execute(id,"series")
                updateUiState { copy(isSeriesLoading = false, characterSeriesData = characterData) }
            } catch (e: Exception) {
                updateUiState { copy(isSeriesLoading = false, error = e) }
            }
        }
    }

    private fun loadCharacterStories(id: Int) {
        updateUiState { copy(isStoriesLoading = true, error = null) }
        viewModelScope.launch {
            try {
                val characterData = characterByTypeUseCase.execute(id,"stories")
                updateUiState { copy(isStoriesLoading = false, characterStoriesData = characterData) }
            } catch (e: Exception) {
                updateUiState { copy(isStoriesLoading = false, error = e) }
            }
        }
    }

    private fun loadCharacterEvents(id: Int) {
        updateUiState { copy(isEventsLoading = true, error = null) }
        viewModelScope.launch {
            try {
                val characterData = characterByTypeUseCase.execute(id,"events")
                updateUiState { copy(isEventsLoading = false, characterEventsData = characterData) }
            } catch (e: Exception) {
                updateUiState { copy(isEventsLoading = false, error = e) }
            }
        }
    }

    private fun handleBack() {
        navigator.goBack()
    }

}