package me.pblinux.tvmaze.ui.screens.search

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import me.pblinux.tvmaze.data.models.State
import me.pblinux.tvmaze.data.models.search.SearchResult
import me.pblinux.tvmaze.data.viewmodel.HomeViewModel
import me.pblinux.tvmaze.data.viewmodel.ShowViewModel
import me.pblinux.tvmaze.ui.composables.common.Back
import me.pblinux.tvmaze.ui.composables.home.RowShowItem
import me.pblinux.tvmaze.ui.composables.search.SuggestedShows
import me.pblinux.tvmaze.ui.composables.states.Loading
import me.pblinux.tvmaze.ui.screens.LocalNavigation

@Composable
fun Search(
    homeViewModel: HomeViewModel = hiltViewModel(),
    showViewModel: ShowViewModel = hiltViewModel()
) {
    val state by homeViewModel.results.collectAsState()
    val query by homeViewModel.query.collectAsState()
    val suggested by homeViewModel.suggested.collectAsState()

    val navController = LocalNavigation.current
    val focusManager = LocalFocusManager.current

    Scaffold {
        LazyColumn(
            contentPadding = PaddingValues(24.dp),
            verticalArrangement = Arrangement.spacedBy(24.dp),
        ) {
            item {
                Back {
                    navController.popBackStack()
                }
            }

            item {
                Text(
                    text = "Search your favourites",
                    style = MaterialTheme.typography.h3.copy(MaterialTheme.colors.secondary)
                )
            }
            item {
                OutlinedTextField(
                    query, onValueChange = { homeViewModel.updateQuery(it) },
                    label = { Text("Your search") },
                    placeholder = { Text("Twin Peaks, WestWorld, Breaking Bad...") },
                    keyboardActions = KeyboardActions(
                        onSearch = {
                            homeViewModel.search()
                            focusManager.clearFocus()
                        }
                    ),
                    keyboardOptions = KeyboardOptions(
                        imeAction = ImeAction.Search
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 16.dp)
                )
            }

            when (state) {
                State.Uninitialized -> {
                    item {
                        SuggestedShows(state = suggested) {
                            showViewModel.changeShow(it)
                            navController.navigate("showDetail")
                        }
                    }
                }
                State.Loading -> {
                    item { Loading() }
                }
                is State.Loaded<*> -> {
                    val results = (state as State.Loaded<*>).data as List<*>
                    items(results, itemContent = { show ->
                        RowShowItem(show = (show as SearchResult).show) {
                            showViewModel.changeShow(show.show)
                            navController.navigate("showDetail")
                        }
                    })
                }
                else -> {

                }
            }
        }
    }
}