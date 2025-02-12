package com.marvel.domain.models

import kotlinx.serialization.Serializable

@Serializable
data class CharacterEntity(
    val id: Int,
    val name: String,
    val title: String,
    val description: String,
    val thumbnail: String,
    val urls: List<UrlEntity>
): java.io.Serializable

@Serializable
data class UrlEntity(
    val type: String,
    val url: String
): java.io.Serializable
