package com.marvel.data.mapper

import com.marvel.data.model.CharacterResponse
import com.marvel.data.model.CharactersResponse
import com.marvel.data.model.Thumbnail
import com.marvel.data.model.Url
import com.marvel.domain.models.CharacterEntity
import com.marvel.domain.models.UrlEntity

fun CharactersResponse.toCharacterList(): List<CharacterEntity> {
    return data?.characters.orEmpty().map { it.toCharacterEntity() }
}

fun CharacterResponse.toCharacterEntity(): CharacterEntity = CharacterEntity(
    id = id ?: 0,
    name = name.orEmpty(),
    title = title.orEmpty(),
    description = description.orEmpty(),
    thumbnail = generateSecureThumbnailUrl(thumbnail),
    urls = urls.orEmpty().map { it.toUrlEntity() }
)

private fun generateSecureThumbnailUrl(thumbnail: Thumbnail?): String = with(thumbnail) {
    "${this?.path.orEmpty().replace("http:", "https:")}/detail.${this?.extension.orEmpty()}"
}

fun Url.toUrlEntity(): UrlEntity = UrlEntity(
    type = type.orEmpty(),
    url = url.orEmpty()
)

