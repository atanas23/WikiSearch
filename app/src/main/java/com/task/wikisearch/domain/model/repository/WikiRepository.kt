package com.task.wikisearch.domain.model.repository

import com.task.wikisearch.domain.model.internal.WikiItemInfo

interface WikiRepository {
    suspend fun getWikiList(query: String, limit: Int): List<WikiItemInfo>
}