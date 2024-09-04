package com.task.wikisearch.domain.model.external

data class WikiThumbnailInfoExternal(
    val url: String?,
    val width: Int?,
    val height: Int?
)

data class WikiItemInfoExternal(
    val thumbnail: WikiThumbnailInfoExternal?,
    val title: String,
    val description: String?,
    val key: String,
)

data class WikiListExternal(
    val pages: List<WikiItemInfoExternal>
)
