package com.marvel.detail.presentation.detailScreen.components.product

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.marvel.domain.models.CharacterEntity


@Composable
fun CharacterProductsSection(
    @StringRes title: Int,
    products: List<CharacterEntity>,
    isLoading: Boolean,
    onClick: (CharacterEntity) -> Unit
) {
    Spacer(modifier = Modifier.height(22.dp))

    Text(
        modifier = Modifier.padding(horizontal = 8.dp),
        text = stringResource(title).uppercase(),
        style = MaterialTheme.typography.labelSmall,
        fontWeight = FontWeight.Bold,
        color = Color.Red.copy(alpha = 0.7f)
    )

    Spacer(modifier = Modifier.height(10.dp))

    when {
        isLoading -> {
            Box(
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator(
                    modifier = Modifier.size(40.dp),
                    color = Color.White
                )
            }
        }

        products.isNotEmpty() -> {
            LazyRow(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 12.dp),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                item { Spacer(modifier = Modifier.width(8.dp)) }
                items(products, key = { it.id }) { product ->
                    CharacterProductItem(
                        item = product,
                        onClick = { onClick(product) }
                    )
                }

                item { Spacer(modifier = Modifier.width(8.dp)) }
            }
        }
    }
}
