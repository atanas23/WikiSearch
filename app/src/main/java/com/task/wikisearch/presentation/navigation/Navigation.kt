package com.task.wikisearch.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.task.wikisearch.presentation.ui.details.WikiDetailsScreen
import com.task.wikisearch.presentation.ui.list.WikiListScreen
import com.task.wikisearch.presentation.ui.list.WikiListViewModel

@Composable
fun Navigation(navController: NavHostController, viewModel: WikiListViewModel) {
    NavHost(navController = navController, startDestination = "wiki_list") {
        composable("wiki_list") {
            WikiListScreen(
                listViewModel = viewModel,
                navController = navController
            )
        }

        composable("wiki_details/{key}") { backStackEntry ->
            val wikiKey = backStackEntry.arguments?.getString("key")

            wikiKey?.let { WikiDetailsScreen(it) }
        }
    }
}