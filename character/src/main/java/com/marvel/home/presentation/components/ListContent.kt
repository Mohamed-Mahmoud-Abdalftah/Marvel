package com.marvel.home.presentation.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import com.marvel.domain.models.CharacterEntity


@Composable
fun ListContent(charactersPagingItems: LazyPagingItems<CharacterEntity>, onItemClick: (Long) -> Unit) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(1),
    ) {
        items(charactersPagingItems.itemCount) { index ->
            charactersPagingItems[index]?.let { CharacterCard(it, onItemClick) }
        }

        charactersPagingItems.apply {
            when {
                loadState.refresh is LoadState.Loading -> {
                    item { PageLoader(modifier = Modifier.fillMaxWidth()) }
                }

                loadState.refresh is LoadState.Error -> {
                    val error = charactersPagingItems.loadState.refresh as LoadState.Error
                    item {
                        ErrorMessage(modifier = Modifier.fillMaxWidth(),
                            message = error.error.localizedMessage!!,
                            onClickRetry = { retry() })
                    }
                }

                loadState.append is LoadState.Loading -> {
                    item { LoadingNextPageItem(modifier = Modifier) }
                }

                loadState.append is LoadState.Error -> {
                    val error = charactersPagingItems.loadState.append as LoadState.Error
                    item {
                        ErrorMessage(modifier = Modifier,
                            message = error.error.localizedMessage!!,
                            onClickRetry = { retry() })
                    }
                }
            }
        }
    }
}