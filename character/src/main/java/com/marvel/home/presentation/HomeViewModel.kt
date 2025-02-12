package com.marvel.home.presentation

import android.util.Log
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.marvel.core.navigation.NavigationService
import com.marvel.core.presentation.StateAndEventViewModel
import com.marvel.domain.models.CharacterEntity
import com.marvel.domain.useCase.CharactersUseCase
import com.marvel.home.presentation.state.CharacterUIState
import com.marvel.home.presentation.uievent.CharacterUIEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import java.net.URLEncoder
import java.nio.charset.StandardCharsets

import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val charactersUseCase: CharactersUseCase, private val navigator: NavigationService
) : StateAndEventViewModel<CharacterUIState, CharacterUIEvent>(CharacterUIState()) {
    private val _charactersData = MutableStateFlow<PagingData<CharacterEntity>>(PagingData.empty())
    val charactersData: StateFlow<PagingData<CharacterEntity>> = _charactersData


    public override suspend fun handleEvent(event: CharacterUIEvent) {
        when (event) {
            CharacterUIEvent.LoadInitialHome -> {
                getCharacters()  // Load default tab data
            }

            is CharacterUIEvent.OnCharacterClicked -> {
                onMovieClicked(event.characterEntity)
            }

            is CharacterUIEvent.Dismiss -> {
                handleBack()
            }
        }
    }

    private fun getCharacters() {
        viewModelScope.launch {
            charactersUseCase.execute()
                .cachedIn(viewModelScope) // Cache PagingData in the ViewModel scope to retain during configuration changes
                .onStart {
                    updateUiState { copy(isLoading = true) } // Show loading before data is fetched
                }.catch { error ->
                    Log.e("TAG", "getPlayingMovies: error $error")
                    updateUiState {
                        copy(
                            isLoading = false, error = error
                        )
                    } // Show error if any occurs
                }.collectLatest { pagingData ->
                    _charactersData.emit(pagingData) // Use emit to ensure state flow updates correctly
                    updateUiState {
                        copy(
                            charactersData = pagingData, isLoading = false, error = null
                        )
                    }
                }
        }
    }

    private fun onMovieClicked(character: CharacterEntity) {
        val json = Json.encodeToString(character)
        val encodedJson = URLEncoder.encode(json, StandardCharsets.UTF_8.toString())

        navigator.navigateTo("detail/$encodedJson") {
            launchSingleTop = true
            restoreState = true
        }
    }



    private fun handleBack() {
        navigator.goBack()
    }

}