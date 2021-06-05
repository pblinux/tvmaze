package me.pblinux.tvmaze.ui.screens.show

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import me.pblinux.tvmaze.data.models.State
import me.pblinux.tvmaze.data.models.episode.Episode
import me.pblinux.tvmaze.data.viewmodel.ShowViewModel
import me.pblinux.tvmaze.ui.composables.common.Back
import me.pblinux.tvmaze.ui.composables.common.SeasonsTabBar
import me.pblinux.tvmaze.ui.composables.show.PosterBackground
import me.pblinux.tvmaze.ui.composables.show.ShowEpisodes
import me.pblinux.tvmaze.ui.composables.show.ShowHeader
import me.pblinux.tvmaze.ui.composables.states.Loading
import me.pblinux.tvmaze.ui.screens.LocalNavigation
import me.pblinux.tvmaze.utils.clean

@Suppress("UNCHECKED_CAST")
@Composable
fun Show(showViewModel: ShowViewModel = hiltViewModel()) {
    val navController = LocalNavigation.current
    val show by showViewModel.show.collectAsState()
    val episodes by showViewModel.episodes.collectAsState()
    var selectedSeason by remember { mutableStateOf(0) }

    show?.let { current ->
        Scaffold {
            PosterBackground(image = current.image?.original)
            LazyColumn(
                contentPadding = PaddingValues(horizontal = 24.dp, vertical = 40.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                item {
                    Back {
                        navController.popBackStack()
                    }
                }

                item {
                    ShowHeader(
                        image = current.image?.medium,
                        name = current.name,
                        network = current.network?.name,
                        rating = current.rating.average?.toFloat(),
                        categories = current.genres,
                        premiered = current.premiered,
                    )
                }

                item {
                    Column {
                        Text("Schedule", style = MaterialTheme.typography.h6)
                        current.schedule.days.forEach { day ->
                            Text("$day at ${current.schedule.time}")
                        }
                    }
                }

                item {
                    Text("Summary:", style = MaterialTheme.typography.h6)
                }

                item {
                    Text(
                        current.summary?.clean() ?: "No info available",
                        textAlign = TextAlign.Justify
                    )
                }

                item {
                    Text("Seasons:", style = MaterialTheme.typography.h6)
                }

                when (episodes) {
                    State.Uninitialized -> {
                    }
                    State.Loading -> {
                        item { Loading() }
                    }
                    State.Error -> {
                    }
                    is State.Loaded<*> -> {
                        val result = ((episodes as State.Loaded<*>).data as Map<Int, List<Episode>>)

                        item {
                            SeasonsTabBar(episodeInfo = result, selectedTab = selectedSeason) {
                                selectedSeason = it
                            }
                        }

                        item {
                            ShowEpisodes(result = result, season = selectedSeason) {
                                showViewModel.changeEpisode(it)
                                navController.navigate("episodeDetail")
                            }
                        }
                    }
                }
            }
        }
    }

}