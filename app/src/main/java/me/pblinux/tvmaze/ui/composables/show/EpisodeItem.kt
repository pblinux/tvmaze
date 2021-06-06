package me.pblinux.tvmaze.ui.composables.show

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import me.pblinux.tvmaze.data.models.episode.Episode
import me.pblinux.tvmaze.ui.composables.common.Poster
import me.pblinux.tvmaze.ui.theme.RichBlack
import me.pblinux.tvmaze.ui.theme.White

@Composable
fun EpisodeItem(
    episode: Episode, modifier: Modifier = Modifier, posterSize: Dp = 136.dp,
    onClick: (Episode) -> Unit
) {
    Poster(
        image = episode.image?.original,
        posterSize = posterSize,
        contentScale = ContentScale.Crop,
        modifier = modifier,
        onClick = { onClick(episode) },
        content = {
            Box(
                Modifier
                    .fillMaxSize()
                    .background(Brush.verticalGradient(listOf(Color.Transparent, RichBlack)))
            ) {}

            Column(
                Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                verticalArrangement = Arrangement.Bottom
            ) {
                Text(
                    "#${episode.number}",
                    style = MaterialTheme.typography.body2.copy(color = White)
                )
                Text(episode.name, style = MaterialTheme.typography.h6.copy(color = White))
            }
        }

    )
}