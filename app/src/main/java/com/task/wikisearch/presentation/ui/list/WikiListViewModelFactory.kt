package com.task.wikisearch.presentation.ui.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.task.wikisearch.data.repository.ThumbnailDownloadManager
import com.task.wikisearch.domain.model.repository.WikiRepository

class WikiListViewModelFactory(private val repository: WikiRepository, private val manager: ThumbnailDownloadManager): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(WikiListViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return WikiListViewModel(repository, manager) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}