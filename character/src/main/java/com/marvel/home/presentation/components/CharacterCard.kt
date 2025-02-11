package com.marvel.home.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.marvel.core.components.CoilImageComponent
import com.marvel.domain.models.CharacterEntity

@Composable
fun CharacterCard(characterItem: CharacterEntity, onItemClick: (Long) -> Unit) {
    Box(modifier = Modifier
        .fillMaxWidth()
        .height(180.dp)
        .clickable {
            onItemClick(characterItem.id)
        }) {
        CharacterImage(characterItem.thumbnail)
        TitleText(characterItem.name)
    }
}

@Composable
private fun CharacterImage(image: String) {
    CoilImageComponent(
        imageUrl = image,
        contentScale = ContentScale.FillBounds,
        contentDescription = "poster",
        modifier = Modifier.fillMaxWidth()
    )
}


@Composable
fun TitleText(title: String) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black.copy(alpha = 0.2f))
    ) {
        Text(
            modifier = Modifier
                .align(Alignment.BottomStart)
                .widthIn(min = 200.dp)
                .padding(horizontal = 20.dp, vertical = 30.dp)
                .drawCustomPath()
                .padding(horizontal = 20.dp, vertical = 4.dp),
            text = title.orEmpty(),
            color = Color.Black,
            style = MaterialTheme.typography.titleMedium,
            textAlign = TextAlign.Center
        )
    }
}

@Composable
private fun Modifier.drawCustomPath(): Modifier = this.drawBehind {
    val path = Path().apply {
        val width = size.width
        val height = size.height
        moveTo(width * 0.06f, 0f) // Top-left corner (shortened)
        lineTo(width - 20f, 0f) // Top-right corner
        lineTo(width * 0.9f, height) // Bottom-right corner (shortened)
        lineTo(0f, height) // Bottom-left corner
        close()
    }
    drawPath(path, color = Color.White)
}
