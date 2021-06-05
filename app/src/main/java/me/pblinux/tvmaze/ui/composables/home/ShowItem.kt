package me.pblinux.tvmaze.ui.composables.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.google.accompanist.coil.rememberCoilPainter
import com.gowtham.ratingbar.RatingBar
import com.gowtham.ratingbar.RatingBarStyle
import me.pblinux.tvmaze.R
import me.pblinux.tvmaze.data.models.show.Show
import me.pblinux.tvmaze.data.models.show.showSample
import me.pblinux.tvmaze.ui.composables.common.Poster
import me.pblinux.tvmaze.utils.clean

@Preview
@Composable
fun RowShowItemPreview() {
    RowShowItem(showSample) {}
}

@Preview
@Composable
fun ShowItemPreview() {
    ShowItem(showSample) {}
}

@Composable
fun ShowItem(
    show: Show, modifier: Modifier = Modifier, posterSize: Dp = 136.dp,
    onClick: (Show) -> Unit
) {
    Column(
        modifier
            .width(posterSize)
            .clickable { onClick(show) },
        horizontalAlignment = Alignment.Start
    ) {
        Card(
            Modifier.size(width = posterSize, height = posterSize.times(1.5f)),
            elevation = 4.dp
        ) {
            Image(
                painter = rememberCoilPainter(
                    request = show.image?.medium,
                    fadeIn = true,
                    previewPlaceholder = R.drawable.ic_launcher_foreground,
                ),
                modifier = Modifier.clickable { onClick(show) },
                contentDescription = "",
                contentScale = ContentScale.FillBounds
            )
        }
        Spacer(Modifier.height(8.dp))
        Text(show.name, style = MaterialTheme.typography.h6)
        Text(show.network?.let { show.network.name } ?: show.genres.first(),
            style = MaterialTheme.typography.body2)
        Spacer(Modifier.height(8.dp))
        RatingBar(
            size = 10.dp,
            numStars = 10,
            padding = 1.dp,
            isIndicator = true,
            ratingBarStyle = RatingBarStyle.HighLighted,
            value = show.rating.average?.let { show.rating.average.toFloat() } ?: 0f,
            modifier = Modifier.background(MaterialTheme.colors.background)
        ) {}
    }
}

@Composable
fun RowShowItem(
    show: Show,
    modifier: Modifier = Modifier,
    posterSize: Dp = 136.dp,
    onClick: (Show) -> Unit,
) {
    Row(
        modifier
            .fillMaxWidth()
            .clickable { onClick(show) }, verticalAlignment = Alignment.CenterVertically
    ) {
        Poster(
            image = show.image?.medium,
            modifier = Modifier.weight(0.4f),
            posterSize = posterSize
        ) { onClick(show) }
//        Card(
//            Modifier
//                .size(width = posterSize, height = posterSize.times(1.5f))
//                .weight(0.4f)
//        ) {
//            Image(
//                painter = rememberCoilPainter(
//                    request = show.image?.medium,
//                    fadeIn = true,
//                    previewPlaceholder = R.drawable.ic_launcher_foreground,
//                ),
//                contentDescription = "",
//                contentScale = ContentScale.FillBounds,
//                modifier = Modifier.clickable { onClick(show) }
//            )
//        }
        Spacer(Modifier.width(16.dp))
        Column(Modifier.weight(0.6f)) {
            Text(show.name, style = MaterialTheme.typography.h6)
            if (show.network != null) {
                Text(show.network.name, style = MaterialTheme.typography.body2)
            }
            Spacer(Modifier.height(8.dp))
            Text(
                text = show.summary?.clean() ?: "No info available",
                style = MaterialTheme.typography.body2,
                maxLines = 6,
                overflow = TextOverflow.Ellipsis,
                textAlign = TextAlign.Justify,
            )
            Spacer(Modifier.height(8.dp))
            RatingBar(
                size = 16.dp,
                numStars = 10,
                isIndicator = true,
                ratingBarStyle = RatingBarStyle.HighLighted,
                value = show.rating.average?.let { show.rating.average.toFloat() } ?: 0f,
                modifier = Modifier.background(MaterialTheme.colors.background)
            ) {}
        }
    }
}