package com.task.wikisearch.domain.model.internal

data class WikiThumbnailInfo(
    val url: String?,
    val width: Int?,
    val height: Int?
)

data class WikiItemInfo(
    val thumbnail: WikiThumbnailInfo,
    val title: String,
    val description: String,
    val key: String,
)
