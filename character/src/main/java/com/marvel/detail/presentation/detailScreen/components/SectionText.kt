package com.marvel.detail.presentation.detailScreen.components

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
 fun SectionText(text: String) {
    Text(
        modifier = Modifier.padding(horizontal = 8.dp),
        text = text,
        style = MaterialTheme.typography.bodyLarge,
        color = Color.White
    )
}