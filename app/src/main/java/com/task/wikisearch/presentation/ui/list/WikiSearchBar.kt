package com.task.wikisearch.presentation.ui.list

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay

@Composable
fun WikiSearchBar(
    searchQuery: String,
    onSearch: (String) -> Unit,
    modifier: Modifier = Modifier,
    wikiListViewModel: WikiListViewModel
) {
    val coroutineScope = rememberCoroutineScope()

    LaunchedEffect(searchQuery) {
        delay(500)
        if (searchQuery.isNotEmpty()) {
            onSearch(searchQuery)
        }
    }

    OutlinedTextField(
        value = searchQuery,
        onValueChange = { wikiListViewModel.updateSearchQuery(it)
        },
        label = { Text("Search") },
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp)
    )
}