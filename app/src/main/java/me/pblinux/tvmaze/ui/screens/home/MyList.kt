package me.pblinux.tvmaze.ui.screens.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import me.pblinux.tvmaze.data.viewmodel.HomeViewModel
import me.pblinux.tvmaze.data.viewmodel.ShowViewModel
import me.pblinux.tvmaze.ui.composables.home.RowShowItem
import me.pblinux.tvmaze.ui.screens.LocalNavigation

@Composable
fun MyList(
    homeViewModel: HomeViewModel = hiltViewModel(),
    showViewModel: ShowViewModel = hiltViewModel()
) {
    val favourites by homeViewModel.favourites.collectAsState()
    val navController = LocalNavigation.current

    if (favourites.isNotEmpty()) {
        LazyColumn(
            contentPadding = PaddingValues(
                start = 24.dp,
                top = 0.dp,
                bottom = 24.dp,
                end = 24.dp
            ),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            modifier = Modifier.fillMaxSize()
        ) {
            items(favourites, itemContent = { show ->
                RowShowItem(show = show) {
                    showViewModel.changeShow(it)
                    navController.navigate("showDetail")
                }
            })
        }
    } else {
        Box(Modifier.fillMaxSize()) {
            Text(
                "There are no elements",
                Modifier.align(Alignment.Center),
                textAlign = TextAlign.Center
            )
        }
    }
}