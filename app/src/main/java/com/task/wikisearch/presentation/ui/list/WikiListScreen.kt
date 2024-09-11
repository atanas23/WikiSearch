package com.task.wikisearch.presentation.ui.list

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun WikiListScreen(
    listViewModel: WikiListViewModel,
    navController: NavController
) {
    var showDialog by remember {
        mutableStateOf(false)
    }
    val wikiList by listViewModel.wikiList
    val thumbnailList by listViewModel.downloadedImages

    listViewModel.getWikiList()

    Column(
        modifier = Modifier
            .padding(top = 48.dp, start = 8.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically

        ) {
            Button(onClick = { showDialog = true }) {
                Text("Open Settings")
            }
            WikiSearchBar(
                listViewModel.searchQuery.value,
                onSearch = {
                    listViewModel.getWikiList()
                },
                Modifier,
                listViewModel
            )
        }

        if (showDialog) {
            SettingsScreen(
                onDismissRequest = { showDialog = false },
                listViewModel
            )
        }

        val combinedState by remember {
            derivedStateOf { wikiList to thumbnailList }
        }

        LazyColumn(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            items(
                items = combinedState.first,
                key = { it.key }
            ) { cell ->
                val thumbnailUrl by rememberUpdatedState(combinedState.second[cell.key])

                WikiCell(
                    thumbnailUrl = thumbnailUrl ?: "",
                    title = cell.title,
                    description = cell.description,
                    onClickAction = { navController.navigate("wiki_details/${cell.key}") }
                )
            }
        }
    }
}