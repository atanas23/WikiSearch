package com.task.wikisearch.presentation.ui.list

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.task.wikisearch.data.repository.ThumbnailDownloadManager
import com.task.wikisearch.domain.model.internal.WikiItemInfo
import com.task.wikisearch.domain.model.repository.WikiRepository
import kotlinx.coroutines.launch

class WikiListViewModel(private val repository: WikiRepository, private val manager: ThumbnailDownloadManager) : ViewModel() {
    val wikiList = mutableStateOf<List<WikiItemInfo>>(emptyList())
    var searchLimit = mutableStateOf(20f)
    var thumbnailLimit = mutableStateOf(20f)
    var searchQuery = mutableStateOf("")

    val downloadedImages = manager.downloadedThumbnails

    fun getWikiList() {
        viewModelScope.launch {

            if (!searchQuery.value.isEmpty()) {
                try {
                    val cellData =
                        repository.getWikiList(searchQuery.value, searchLimit.value.toInt())
                    wikiList.value = cellData
                    startImageDownload()
                } catch (e: Exception) {
                    Log.e("WikiListViewModel", "Error fetching Wikipedia data", e)
                    error(message = "Error: ${e.message}")
                }
            }
        }
    }

    fun updateLimits(value: Float, newThumbnailLimit: Float) {
        searchLimit.value = value
        if (thumbnailLimit.value > value) {
            thumbnailLimit.value = value
        } else {
            thumbnailLimit.value = newThumbnailLimit
        }

    }

    fun updateSearchQuery(value: String) {
        searchQuery.value = value
    }

    private fun startImageDownload() {
        viewModelScope.launch {
            manager.downloadThumbnails(wikiList.value, thumbnailLimit.value.toInt())
        }
    }
}