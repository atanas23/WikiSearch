package com.task.wikisearch.data.mapper

import com.task.wikisearch.domain.model.external.WikiListExternal
import com.task.wikisearch.domain.model.internal.WikiItemInfo
import com.task.wikisearch.domain.model.internal.WikiThumbnailInfo

class WikiListRemoteAdapter {
    //for when there is no thumbnail
    private val defaultThumbnailUrl = ""
    private val defaultThumbnail = WikiThumbnailInfo(url = defaultThumbnailUrl, width = 64, height = 64)

    fun adaptWikiListExternalToInternal(wikiListExternal: WikiListExternal): List<WikiItemInfo> {
        return wikiListExternal.pages.map { externalCell ->
            val internalThumbnail = if (externalCell.thumbnail != null) {
                WikiThumbnailInfo(
                    url = "https:" + externalCell.thumbnail.url,
                    width = externalCell.thumbnail.width,
                    height = externalCell.thumbnail.height
                )
            } else {
                defaultThumbnail
            }

            WikiItemInfo(
                thumbnail = internalThumbnail,
                title = externalCell.title,
                description = externalCell.description ?: "",
                key = externalCell.key
            )
        }
    }
}