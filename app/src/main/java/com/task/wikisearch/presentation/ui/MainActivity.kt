package com.task.wikisearch.presentation.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
import com.task.wikisearch.data.api.RetrofitInstance
import com.task.wikisearch.presentation.ui.theme.WikiSearchTheme
import com.task.wikisearch.data.mapper.WikiListRemoteAdapter
import com.task.wikisearch.data.datasource.remote.WikiListRemoteDataSource
import com.task.wikisearch.data.repository.WikiListRepositoryImpl
import com.task.wikisearch.presentation.ui.list.WikiListViewModel
import com.task.wikisearch.presentation.navigation.Navigation
import com.task.wikisearch.presentation.ui.list.WikiListViewModelFactory

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val navController = rememberNavController()
            val api = RetrofitInstance.api

            val listAdapter = WikiListRemoteAdapter()
            val listDataSource = WikiListRemoteDataSource(api, listAdapter)
            val listRepository = WikiListRepositoryImpl(listDataSource)
            val listViewModel: WikiListViewModel = viewModel(
                factory = WikiListViewModelFactory(listRepository)
            )

            WikiSearchTheme {
                Navigation(navController = navController, viewModel = listViewModel)
            }
        }
    }
}