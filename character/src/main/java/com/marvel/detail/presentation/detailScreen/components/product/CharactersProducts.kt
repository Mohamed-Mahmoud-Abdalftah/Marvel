package com.marvel.detail.presentation.detailScreen.components.product

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.marvel.detail.presentation.detailScreen.components.ImageGalleryDialog
import com.marvel.detail.presentation.detailScreen.state.DetailUIState
import com.marvel.domain.models.CharacterEntity
import com.marvel.home.R

@Composable
fun CharactersProducts(uiState: DetailUIState) {
    val sections = remember(uiState) {
        listOf(
            Triple(R.string.comics, uiState.characterComicsData, uiState.isComicsLoading),
            Triple(R.string.series, uiState.characterSeriesData, uiState.isSeriesLoading),
            Triple(R.string.stories, uiState.characterStoriesData, uiState.isStoriesLoading),
            Triple(R.string.events, uiState.characterEventsData, uiState.isEventsLoading)
        )
    }

    var showImagesDialog by remember { mutableStateOf(false) }
    var selectedCharacters by remember { mutableStateOf(emptyList<CharacterEntity>()) }

    Column {
        sections.forEach { (title, products, isLoading) ->
            if (products.isEmpty()) return@forEach

            CharacterProductsSection(
                title = title,
                products = products,
                isLoading = isLoading,
                onClick = { character ->
                    selectedCharacters = listOf(character)
                    showImagesDialog = true
                }
            )
        }
    }

    if (showImagesDialog) {
        ImageGalleryDialog(
            onClose = { showImagesDialog = false },
            onCloseClicked = { showImagesDialog = false },
            characterList = selectedCharacters
        )
    }
}

