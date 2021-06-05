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


//    Card(
//        modifier.size(width = posterSize, height = posterSize.times(1.5f)),
//        elevation = 4.dp
//    ) {
//        Image(
//            painter = rememberCoilPainter(
//                request = episode.image?.original,
//                fadeIn = true,
//                previewPlaceholder = R.drawable.ic_launcher_foreground,
//            ),
//            modifier = Modifier.clickable { onClick(episode) },
//            contentDescription = "",
//            contentScale = ContentScale.Crop
//        )
//
//        Box(
//            Modifier
//                .fillMaxSize()
//                .background(Brush.verticalGradient(listOf(Color.Transparent, RichBlack)))
//        ) {}
//
//        Column(
//            Modifier
//                .fillMaxSize()
//                .padding(16.dp),
//            verticalArrangement = Arrangement.Bottom
//        ) {
//            Text("#${episode.number}", style = MaterialTheme.typography.body2.copy(color = White))
//            Text(episode.name, style = MaterialTheme.typography.h6.copy(color = White))
//        }
//    }


//    Column(
//        modifier
//            .width(posterSize)
//            .clickable { onClick(episode) },
//        horizontalAlignment = Alignment.Start
//    ) {
//        Card(
//            Modifier.size(width = posterSize, height = posterSize.times(1.5f)),
//            elevation = 4.dp
//        ) {
//            Image(
//                painter = rememberCoilPainter(
//                    request = episode.image?.medium,
//                    fadeIn = true,
//                    previewPlaceholder = R.drawable.ic_launcher_foreground,
//                ),
//                modifier = Modifier.clickable { onClick(episode) },
//                contentDescription = "",
//                contentScale = ContentScale.Crop
//            )
//        }
//        Spacer(Modifier.height(8.dp))
//        Text(episode.name, style = MaterialTheme.typography.h6)
//        Text(episode.number.toString(), style = MaterialTheme.typography.body2)
//    }
}