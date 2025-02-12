package com.marvel.detail.presentation.detailScreen.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.marvel.home.R

@Composable
 fun NameSection(name: String?) {
    name?.takeIf { it.isNotBlank() }?.let {
        SectionHeader(stringResource(R.string.name))
        SectionText(it)
    }
}