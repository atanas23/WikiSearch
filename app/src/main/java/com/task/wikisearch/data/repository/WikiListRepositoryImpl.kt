package com.task.wikisearch.data.repository

import com.task.wikisearch.data.datasource.remote.WikiListRemoteDataSource
import com.task.wikisearch.domain.model.internal.WikiItemInfo
import com.task.wikisearch.domain.model.repository.WikiRepository

class WikiListRepositoryImpl(val dataSource: WikiListRemoteDataSource): WikiRepository {
    override suspend fun getWikiList(query: String, limit: Int): List<WikiItemInfo> {
        return dataSource.getWikiList(query, limit)
    }
}