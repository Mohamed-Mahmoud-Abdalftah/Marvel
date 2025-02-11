package com.marvel.home.presentation

import android.util.Log
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.paging.compose.collectAsLazyPagingItems
import com.marvel.core.components.ErrorComponent
import com.marvel.core.components.LoadingComponent
import com.marvel.home.presentation.components.ListContent
import com.marvel.home.presentation.components.Toolbar
import com.marvel.home.presentation.uievent.CharacterUIEvent


@Composable
fun HomeScreen() {
    val viewModel: HomeViewModel = hiltViewModel()
    val state by viewModel.uiState.collectAsStateWithLifecycle()
    val pagingItems = viewModel.charactersData.collectAsLazyPagingItems()

    LaunchedEffect(Unit) {
        viewModel.onEvent(CharacterUIEvent.LoadInitialHome)
    }

    Column {
        Toolbar()
        when {
            state.isLoading -> {
                LoadingComponent()
            }

            state.error != null -> {
                Log.d("TAG", "HomeScreen: state.error = ${state.error}")
                ErrorComponent(error = state.error)
            }


            else -> {
                ListContent(pagingItems, onItemClick = { itemId ->
                    viewModel.onEvent(CharacterUIEvent.OnCharacterClicked(itemId))
                })
            }
        }
    }

    BackHandler(enabled = true) {
        viewModel.onEvent(CharacterUIEvent.Dismiss)
    }
}
