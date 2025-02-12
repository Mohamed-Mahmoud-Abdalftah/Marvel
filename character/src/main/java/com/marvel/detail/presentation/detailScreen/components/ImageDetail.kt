package com.marvel.detail.presentation.detailScreen.components


import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.marvel.core.components.CoilImageComponent
import com.marvel.home.R

@Composable
fun ImageDetail(imageUrl: String, backClick: () -> Unit) {
    val screenHeight = LocalConfiguration.current.screenHeightDp.dp
    val imageHeight = screenHeight * 0.4f

    Box(modifier = Modifier.fillMaxWidth()) {
        CoilImageComponent(
            imageUrl = imageUrl,
            modifier = Modifier
                .fillMaxWidth()
                .height(imageHeight),
            contentDescription = "",
            contentScale = ContentScale.FillBounds
        )

        IconButton(
            onClick = backClick,
            modifier = Modifier
                .size(48.dp)
                .padding(2.dp)
                .align(Alignment.TopStart)
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_back),
                contentDescription = "Back",
                tint = Color.White
            )
        }
    }
}
