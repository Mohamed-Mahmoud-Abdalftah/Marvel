package com.marvel.detail.presentation.detailScreen.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.core.text.HtmlCompat
import com.marvel.home.R


@Composable
 fun DescriptionSection(description: String?) {
    description?.takeIf { it.isNotBlank() }?.let {
        SectionHeader(stringResource(R.string.description))
        SectionText(HtmlCompat.fromHtml(it, HtmlCompat.FROM_HTML_MODE_COMPACT).toString())
    }
}