package com.task.wikisearch.data.datasource.remote

import com.task.wikisearch.data.api.WikiSearchAPI
import com.task.wikisearch.data.mapper.WikiListRemoteAdapter
import com.task.wikisearch.domain.model.internal.WikiItemInfo

class WikiListRemoteDataSource(val wikiSearchAPI: WikiSearchAPI, val adapter: WikiListRemoteAdapter) {
    suspend fun getWikiList(query: String, limit: Int): List<WikiItemInfo> {
        val wikiList = wikiSearchAPI.getWikiList(query, limit)
        return adapter.adaptWikiListExternalToInternal(wikiList)
    }

}