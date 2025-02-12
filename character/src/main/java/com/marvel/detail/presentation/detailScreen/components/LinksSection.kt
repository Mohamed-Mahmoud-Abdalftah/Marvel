package com.marvel.detail.presentation.detailScreen.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.ripple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.marvel.domain.models.UrlEntity
import com.marvel.home.R
import java.util.Locale

@Composable
 fun LinksSection(urls: List<UrlEntity>, urlCallback: (String) -> Unit) {
    if (urls.isNotEmpty()) {
        SectionHeader(stringResource(R.string.related_links))
        urls.forEach { url ->
            if (url.type.isNotBlank()) {
                LinkItem(url, urlCallback)
            }
        }
    }
}

@Composable
 fun LinkItem(url: UrlEntity, urlCallback: (String) -> Unit) {
    Row(modifier = Modifier
        .fillMaxWidth()
        .clickable(
            interactionSource = remember { MutableInteractionSource() },
            indication = ripple(color = Color.White)
        ) { urlCallback(url.url) }
        .padding(horizontal = 8.dp, vertical = 12.dp),
        verticalAlignment = Alignment.CenterVertically) {
        Text(
            modifier = Modifier.weight(1f),
            text = url.type.replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.ROOT) else it.toString() },
            style = MaterialTheme.typography.bodyLarge,
            color = Color.White
        )
        Icon(
            imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight,
            tint = Color.White,
            contentDescription = null
        )
    }
}
