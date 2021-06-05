package me.pblinux.tvmaze.ui.screens.home

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.padding
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import kotlinx.coroutines.launch
import me.pblinux.tvmaze.data.viewmodel.HomeViewModel
import me.pblinux.tvmaze.data.viewmodel.ShowViewModel
import me.pblinux.tvmaze.ui.composables.common.TVTabBar
import me.pblinux.tvmaze.ui.screens.LocalNavigation
import moe.tlaster.nestedscrollview.VerticalNestedScrollView
import moe.tlaster.nestedscrollview.rememberNestedScrollViewState

sealed class HomeTabs(val title: String) {
    object ShowsTab : HomeTabs("Shows")
    object MyListTab : HomeTabs("My List")
}

val Tabs = listOf(HomeTabs.ShowsTab, HomeTabs.MyListTab)

@ExperimentalFoundationApi
@ExperimentalMaterialApi
@ExperimentalPagerApi
@Composable
fun Home(showViewModel: ShowViewModel = hiltViewModel()) {
    val pagerState = rememberPagerState(pageCount = Tabs.size)
    val nestedState = rememberNestedScrollViewState()
    val coroutineScope = rememberCoroutineScope()
    val navController = LocalNavigation.current

    Scaffold {
        VerticalNestedScrollView(
            state = nestedState,
            header = {
                TVTabBar(
                    tabs = Tabs,
                    selectedTab = pagerState.currentPage,
                    onSearchPressed = { navController.navigate("search") },
                    onTabClicked = { coroutineScope.launch { pagerState.animateScrollToPage(it) } },
                    modifier = Modifier.padding(horizontal = 16.dp, vertical = 24.dp)
                )
            },
            content = {
                HorizontalPager(
                    state = pagerState
                ) { page ->
                    when (Tabs[page]) {
                        HomeTabs.ShowsTab -> {
                            Shows(showViewModel = showViewModel)
                        }
                        HomeTabs.MyListTab -> {
//                            MyList()
                        }
                    }

                }
            }
        )
    }
}