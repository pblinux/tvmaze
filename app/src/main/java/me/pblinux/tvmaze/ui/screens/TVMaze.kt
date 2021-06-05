package me.pblinux.tvmaze.ui.screens

import androidx.compose.animation.*
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.compositionLocalOf
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

        NavHost(navController = navController, startDestination = "home") {
            composable("home") { Transition { Home(showViewModel) } }
            composable("search") { Transition { Search(showViewModel = showViewModel) } }
            composable("showDetail") { Transition { Show(showViewModel) } }
            composable("episodeDetail") { Transition { Episode(showViewModel) } }
        }
    }
}

@ExperimentalAnimationApi
@Composable
fun Transition(content: @Composable (AnimatedVisibilityScope.() -> Unit)) {
    AnimatedVisibility(
        visible = true,
        content = content,
        enter = slideInHorizontally() + expandHorizontally() + fadeIn(),
        exit = shrinkOut() + fadeOut()
    )
}