package me.pblinux.tvmaze.ui.composables.show

import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.google.accompanist.flowlayout.FlowMainAxisAlignment
import com.google.accompanist.flowlayout.FlowRow
import me.pblinux.tvmaze.data.models.episode.Episode

@Composable
fun ShowEpisodes(
    result: Map<Int, List<Episode>>, season: Int,
    onEpisodeClick: (Episode) -> Unit
) {
    BoxWithConstraints {
        val posterSize = (this.maxWidth / 2) - 8.dp

        FlowRow(
            mainAxisAlignment = FlowMainAxisAlignment.Start,
            mainAxisSpacing = 16.dp,
            crossAxisSpacing = 16.dp,
            modifier = Modifier.fillMaxWidth()
        ) {
            result.values.toList()[season].forEach { episode ->
                EpisodeItem(episode = episode, posterSize = posterSize) { onEpisodeClick(it) }
            }
        }
    }
}