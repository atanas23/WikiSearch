package com.task.wikisearch.presentation.ui.list

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsScreen(
    onDismissRequest: () -> Unit,
    wikiListSettingsViewModel: WikiListViewModel
) {
    var searchLimit by remember { mutableStateOf(wikiListSettingsViewModel.searchLimit.value) }
    var thumbnailLimit by remember { mutableStateOf(wikiListSettingsViewModel.thumbnailLimit.value) }

    AlertDialog(
        onDismissRequest = onDismissRequest,
        title = { Text("Settings") },
        text = {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Text("Search Results Limit: ${searchLimit.toInt()}")
                Slider(
                    value = searchLimit,
                    onValueChange = { searchLimit = it },
                    valueRange = 1f..100f,
                    steps = 99,
                    modifier = Modifier.fillMaxWidth()
                )

                Text("Thumbnail Downloads Limit: ${thumbnailLimit.toInt()}")
                Slider(
                    value = thumbnailLimit,
                    onValueChange = { thumbnailLimit = it },
                    valueRange = 1f..searchLimit,
                    steps = (searchLimit - 1).toInt(),
                    modifier = Modifier.fillMaxWidth()
                )
            }
        },
        confirmButton = {
            TextButton(onClick = {
                onDismissRequest()
                wikiListSettingsViewModel.updateLimits(searchLimit, thumbnailLimit)
            }) {
                Text("OK")
            }
        }
    )
}