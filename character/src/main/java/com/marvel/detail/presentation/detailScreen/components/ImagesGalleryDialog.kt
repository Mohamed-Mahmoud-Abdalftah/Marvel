package com.marvel.detail.presentation.detailScreen.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import coil.compose.AsyncImage
import com.marvel.domain.models.CharacterEntity
import com.marvel.home.R


@Composable
fun ImageGalleryDialog(
    onClose: (() -> Unit)? = null,
    onCloseClicked: (() -> Unit)? = null,
    characterList: List<CharacterEntity>
) {
    if (characterList.isEmpty()) return // Prevent rendering if the list is empty

    val pagerState = rememberPagerState(
        initialPage = 0, // Ensure it starts at the first page
        pageCount = { characterList.size }
    )

    val currentPageText by remember(pagerState) {
        derivedStateOf { "${pagerState.currentPage + 1}/${characterList.size}" }
    }

    Dialog(
        onDismissRequest = { onClose?.invoke() },
        properties = DialogProperties(
            dismissOnClickOutside = false,
            usePlatformDefaultWidth = false
        )
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.DarkGray.copy(alpha = 0.8f))
        ) {
            // Close Button
            IconButton(
                modifier = Modifier
                    .align(Alignment.End)
                    .padding(10.dp),
                onClick = { onCloseClicked?.invoke() }
            ) {
                Icon(
                    imageVector = Icons.Default.Close,
                    contentDescription = "Close Gallery",
                    tint = Color.White,
                    modifier = Modifier.size(22.dp)
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Image Pager
            HorizontalPager(
                modifier = Modifier.fillMaxWidth(),
                state = pagerState,
                key = { characterList[it].id },
                pageSpacing = 20.dp,
                contentPadding = PaddingValues(horizontal = 32.dp),
                verticalAlignment = Alignment.Top
            ) { page ->
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    AsyncImage(
                        model = characterList[page].thumbnail,
                        error = painterResource(R.drawable.ic_page_error),
                        contentScale = ContentScale.Crop,
                        contentDescription = "Character Image",
                        modifier = Modifier
                            .fillMaxWidth()
                            .fillMaxHeight(0.8f)
                    )

                    Spacer(modifier = Modifier.height(12.dp))

                    Text(
                        text = characterList[page].title,
                        style = MaterialTheme.typography.labelLarge,
                        color = Color.White,
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 12.dp)
                    )
                }
            }

            // Page Indicator
            Text(
                text = currentPageText,
                style = MaterialTheme.typography.labelSmall,
                color = Color.LightGray.copy(alpha = 0.8f),
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )
        }
    }
}
