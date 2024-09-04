package com.task.wikisearch.presentation.ui.list

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.task.wikisearch.data.repository.WikiListRepositoryImpl
import com.task.wikisearch.domain.model.internal.WikiItemInfo
import com.task.wikisearch.domain.model.repository.WikiRepository
import kotlinx.coroutines.launch

class WikiListViewModel(val repository: WikiRepository) : ViewModel() {
    val wikiList = mutableStateOf<List<WikiItemInfo>>(emptyList())
    var searchLimit = mutableStateOf(20f)
    var thumbnailLimit = mutableStateOf(20f)
    var searchQuery = mutableStateOf("")

    fun getWikiList() {
        viewModelScope.launch {

            if (!searchQuery.value.isEmpty()) {
                try {
                    val cellData =
                        repository.getWikiList(searchQuery.value, searchLimit.value.toInt())
                    wikiList.value = cellData
                } catch (e: Exception) {
                    Log.e("WikiListViewModel", "Error fetching Wikipedia data", e)
                    error(message = "Error: ${e.message}")
                }
            }
        }
    }

    fun updateSearchLimit(value: Float) {
        searchLimit.value = value
        if (thumbnailLimit.value > value) {
            thumbnailLimit.value = value
        }
    }

    fun updateThumbnailLimit(value: Float) {
        thumbnailLimit.value = value
    }

    fun updateSearchQuery(value: String) {
        searchQuery.value = value
    }
}