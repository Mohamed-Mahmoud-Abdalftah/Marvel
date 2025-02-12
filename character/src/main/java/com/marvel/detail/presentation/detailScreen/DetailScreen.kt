package com.marvel.detail.presentation.detailScreen

import android.annotation.SuppressLint
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.rememberAsyncImagePainter
import com.marvel.core.utils.extensions.openUrlInBrowser
import com.marvel.detail.presentation.detailScreen.components.DescriptionSection
import com.marvel.detail.presentation.detailScreen.components.ImageDetail
import com.marvel.detail.presentation.detailScreen.components.LinksSection
import com.marvel.detail.presentation.detailScreen.components.NameSection
import com.marvel.detail.presentation.detailScreen.components.product.CharactersProducts
import com.marvel.detail.presentation.detailScreen.uievent.DetailUIEvent
import com.marvel.domain.models.CharacterEntity


@SuppressLint("StateFlowValueCalledInComposition")
@Composable
fun DetailScreen(character: CharacterEntity) {
    val viewModel: DetailViewModel = hiltViewModel()
    val context = LocalContext.current
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    val characterId by rememberUpdatedState(character.id)

    LaunchedEffect(characterId) {
        viewModel.onEvent(DetailUIEvent.LoadInitialSeries(characterId))
        viewModel.onEvent(DetailUIEvent.LoadInitialComics(characterId))
        viewModel.onEvent(DetailUIEvent.LoadInitialStories(characterId))
        viewModel.onEvent(DetailUIEvent.LoadInitialEvents(characterId))
    }

    val backgroundPainter = rememberAsyncImagePainter(character.thumbnail)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .paint(painter = backgroundPainter, contentScale = ContentScale.Crop)
            .background(Color.Black.copy(alpha = 0.9f))
            .padding(vertical = 20.dp)
            .animateContentSize()
    ) {
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            contentPadding = PaddingValues(bottom = 100.dp)
        ) {
            item(key = "image_detail") {
                ImageDetail(character.thumbnail) {
                    viewModel.onEvent(DetailUIEvent.Dismiss)
                }
            }

            item(key = "name_description") {
                NameSection(character.name)
                Spacer(modifier = Modifier.height(12.dp))
                DescriptionSection(character.description)
                Spacer(modifier = Modifier.height(12.dp))
            }

            item(key = "products") {
                val productsState = remember(uiState) { uiState }
                CharactersProducts(productsState)
            }

            item(key = "links_section") {
                LinksSection(character.urls) { url -> context.openUrlInBrowser(url) }
            }
        }
    }
}


