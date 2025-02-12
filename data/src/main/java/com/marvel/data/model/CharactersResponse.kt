package com.marvel.data.model

import com.google.gson.annotations.SerializedName

data class CharactersResponse(
    @SerializedName("code") val code: Int? = null,
    @SerializedName("status") val status: String? = null,
    @SerializedName("copyright") val copyright: String? = null,
    @SerializedName("attributionText") val attributionText: String? = null,
    @SerializedName("attributionHTML") val attributionHTML: String? = null,
    @SerializedName("data") val data: Data? = null
)

data class Data(
    @SerializedName("offset") val offset: Int? = null,
    @SerializedName("limit") val limit: Int? = null,
    @SerializedName("total") val total: Int? = null,
    @SerializedName("count") val count: Int? = null,
    @SerializedName("results") val characters: List<CharacterResponse>? = null
)

data class CharacterResponse(
    @SerializedName("id") val id: Int? = null,
    @SerializedName("name") val name: String? = null,
    @SerializedName("title") val title: String? = null,
    @SerializedName("description") val description: String? = null,
    @SerializedName("modified") val modified: String? = null,
    @SerializedName("thumbnail") val thumbnail: Thumbnail? = null,
    @SerializedName("resourceURI") val resourceURI: String? = null,
    @SerializedName("urls") val urls: List<Url>? = null
)

data class Thumbnail(
    @SerializedName("path") val path: String? = null,
    @SerializedName("extension") val extension: String? = null
)

data class Url(
    @SerializedName("type") val type: String? = null,
    @SerializedName("url") val url: String? = null
)