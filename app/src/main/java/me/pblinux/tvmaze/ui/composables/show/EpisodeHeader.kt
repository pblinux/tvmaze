package me.pblinux.tvmaze.ui.composables.show

import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import me.pblinux.tvmaze.ui.composables.common.Poster

@Composable
fun EpisodeHeader(
    image: String?,
    name: String,
    number: Int,
    season: Int,
) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(Modifier.weight(0.5f)) {
            Text(text = "#$number", style = MaterialTheme.typography.h2)
            Text(text = name, style = MaterialTheme.typography.h4)
            Text(text = "Season $season", style = MaterialTheme.typography.h5)
        }
        Spacer(Modifier.width(24.dp))
        Poster(
            image = image,
            contentScale = ContentScale.Crop,
            posterSize = 200.dp,
            modifier = Modifier.weight(0.5f)
        )
    }
}