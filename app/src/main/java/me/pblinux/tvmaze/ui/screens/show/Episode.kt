package me.pblinux.tvmaze.ui.screens.show

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import me.pblinux.tvmaze.data.viewmodel.ShowViewModel
import me.pblinux.tvmaze.ui.composables.show.EpisodeHeader
import me.pblinux.tvmaze.ui.composables.show.PosterBackground
import me.pblinux.tvmaze.utils.clean

@Composable
fun Episode(showViewModel: ShowViewModel = hiltViewModel()) {
    val episode by showViewModel.episode.collectAsState()
    episode?.let { ep ->
        Scaffold {
            PosterBackground(image = ep.image?.original)
            LazyColumn(
                contentPadding = PaddingValues(horizontal = 24.dp, vertical = 40.dp),
            ) {
                item {
                    EpisodeHeader(
                        image = ep.image?.original,
                        name = ep.name,
                        number = ep.number,
                        season = ep.season
                    )
                }

                item {
                    Spacer(Modifier.height(16.dp))
                }

                item {
                    Text(text = "Summary", style = MaterialTheme.typography.h6)
                }

                item {
                    Spacer(Modifier.height(8.dp))
                }

                item {
                    Text(
                        text = ep.summary?.clean() ?: "No info available",
                        style = MaterialTheme.typography.body1,
                        textAlign = TextAlign.Justify
                    )
                }
            }
        }
    }
}
