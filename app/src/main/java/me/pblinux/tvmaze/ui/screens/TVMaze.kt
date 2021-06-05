package me.pblinux.tvmaze.ui.screens

import androidx.compose.animation.*
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.pager.ExperimentalPagerApi
import me.pblinux.tvmaze.data.viewmodel.ShowViewModel
import me.pblinux.tvmaze.ui.screens.home.Home
import me.pblinux.tvmaze.ui.screens.search.Search
import me.pblinux.tvmaze.ui.screens.show.Episode
import me.pblinux.tvmaze.ui.screens.show.Show

sealed class TVScreen(val route: String) {
    object Home : TVScreen("home")
    object Search : TVScreen("search")
    object Show : TVScreen("showDetail")
    object Episode : TVScreen("episodeDetail")
}

val LocalNavigation = compositionLocalOf<NavHostController> { error("Not implemented yet!") }

@ExperimentalAnimationApi
@ExperimentalPagerApi
@ExperimentalMaterialApi
@ExperimentalFoundationApi
@Composable
fun TVMazeApp() {
    val navController = rememberNavController()

    CompositionLocalProvider(LocalNavigation provides navController) {
        val showViewModel: ShowViewModel = hiltViewModel()

        Box(Modifier.fillMaxSize()) {
            NavHost(navController = navController, startDestination = TVScreen.Home.route) {
                composable(TVScreen.Home.route) { SwitchAnimation { Home(showViewModel) } }
                composable(TVScreen.Search.route) { SwitchAnimation { Search(showViewModel = showViewModel) } }
                composable(TVScreen.Show.route) { SwitchAnimation { Show(showViewModel) } }
                composable(TVScreen.Episode.route) { SwitchAnimation { Episode(showViewModel) } }
            }
        }
    }
}

@ExperimentalAnimationApi
@Composable
fun SwitchAnimation(content: @Composable (AnimatedVisibilityScope.() -> Unit)) {
    AnimatedVisibility(
        visible = true,
        enter = slideInVertically(
            initialOffsetY = { -40 }
        ) + expandVertically(
            expandFrom = Alignment.Top
        ) + fadeIn(
            initialAlpha = 0.3f
        ),
        exit = slideOutVertically() + shrinkVertically() + fadeOut(),
        content = content,
    )
}