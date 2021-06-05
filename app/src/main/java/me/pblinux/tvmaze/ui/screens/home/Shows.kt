package me.pblinux.tvmaze.ui.screens.home

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyGridScope
import androidx.compose.foundation.lazy.LazyItemScope
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.google.accompanist.pager.ExperimentalPagerApi
import me.pblinux.tvmaze.data.models.show.Show
import me.pblinux.tvmaze.data.viewmodel.HomeViewModel
import me.pblinux.tvmaze.data.viewmodel.ShowViewModel
import me.pblinux.tvmaze.ui.composables.home.ShowItem
import me.pblinux.tvmaze.ui.composables.states.Loading
import me.pblinux.tvmaze.ui.composables.states.LoadingItem
import me.pblinux.tvmaze.ui.screens.LocalNavigation

@ExperimentalFoundationApi
@ExperimentalPagerApi
@Composable
fun Shows(
    homeViewModel: HomeViewModel = hiltViewModel(),
    showViewModel: ShowViewModel = hiltViewModel()
) {
    val shows: LazyPagingItems<Show> = homeViewModel.shows.collectAsLazyPagingItems()
    val navController = LocalNavigation.current

    when (shows.loadState.refresh) {
        is LoadState.Loading -> {
            Loading(Modifier.fillMaxSize())
        }
        else -> {
            BoxWithConstraints {
                val cardSize = (this.maxWidth / 2).minus(40.dp)
                LazyVerticalGrid(
                    cells = GridCells.Fixed(2),
                    contentPadding = PaddingValues(
                        start = 24.dp,
                        top = 0.dp,
                        bottom = 24.dp,
                        end = 24.dp
                    )
                ) {
                    itemsIndexed(shows, itemContent = { _, item ->
                        Box(Modifier.padding(bottom = 16.dp, start = 8.dp)) {
                            ShowItem(show = item!!, posterSize = cardSize) {
                                showViewModel.changeShow(item)
                                navController.navigate("showDetail")
                            }
                        }
                    })
                    shows.apply {
                        when (loadState.append) {
                            is LoadState.Loading -> {
                                item { LoadingItem(Modifier.fillMaxSize()) }
                            }
                            else -> {
                            }
                        }
                    }
                }
            }
        }
    }
//    LazyColumn(
//        contentPadding = PaddingValues(horizontal = 24.dp),
//        verticalArrangement = Arrangement.spacedBy(32.dp)
//
//    ) {
//        itemsIndexed(shows, itemContent = {index, item ->
//            if (index.mod(2) == 0) {
//                InvertedShowItem(show = item!!)
//            } else {
//                ShowItem(show = item!!)
//            }
//        })
//
//        shows.apply {
//            when {
//                loadState.refresh is LoadState.Loading -> {
//                    item { Loading(Modifier.fillParentMaxSize()) }
//                }
//                loadState.append is LoadState.Loading -> {
//                    item { LoadingItem(Modifier.fillParentMaxWidth()) }
//                }
//            }
//        }
//    }
}

@ExperimentalFoundationApi
private fun <T : Any> LazyGridScope.itemsIndexed(
    lazyPagingItems: LazyPagingItems<T>,
    itemContent: @Composable LazyItemScope.(index: Int, item: T?) -> Unit
) {
    items(lazyPagingItems.itemCount) { index ->
        itemContent(index, lazyPagingItems.getAsState(index).value)
    }
}