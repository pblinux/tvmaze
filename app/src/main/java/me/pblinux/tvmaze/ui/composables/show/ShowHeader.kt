package me.pblinux.tvmaze.ui.composables.show

import androidx.compose.foundation.Image
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.google.accompanist.coil.rememberCoilPainter
import com.google.accompanist.flowlayout.FlowRow
import com.gowtham.ratingbar.RatingBar
import com.gowtham.ratingbar.RatingBarStyle
import me.pblinux.tvmaze.R
import me.pblinux.tvmaze.ui.composables.common.Chip
import me.pblinux.tvmaze.ui.theme.RichBlack
import me.pblinux.tvmaze.ui.theme.White


@Composable
fun ShowHeader(
    modifier: Modifier = Modifier,
    categories: List<String>? = listOf(),
    image: String?,
    name: String,
    network: String?,
    posterSize: Dp = 136.dp,
    premiered: String?,
    rating: Float? = 0f,
    favText: String,
    favIcon: ImageVector,
    onFavClicked: () -> Unit,
) {
    Row(modifier, verticalAlignment = Alignment.CenterVertically) {
        Card(
            Modifier
                .size(width = posterSize, height = posterSize.times(1.5f))
                .weight(0.4f)
        ) {
            Image(
                painter = rememberCoilPainter(
                    request = image,
                    fadeIn = true,
                    previewPlaceholder = R.drawable.ic_launcher_foreground,
                ),
                contentDescription = "",
                contentScale = ContentScale.FillBounds,
            )
        }
        Spacer(Modifier.width(16.dp))
        Column(Modifier.weight(0.6f)) {
            Text(name, style = MaterialTheme.typography.h6)
            if (network != null) {
                Text(network, style = MaterialTheme.typography.body2)
            }
            Spacer(Modifier.height(8.dp))
            Column {
                RatingBar(
                    size = 16.dp,
                    numStars = 10,
                    isIndicator = true,
                    ratingBarStyle = RatingBarStyle.HighLighted,
                    value = rating ?: 0f,
                ) {}
            }
            Spacer(Modifier.height(8.dp))
            FlowRow {
                categories?.forEach { category ->
                    Chip(category)
                }
            }
            premiered?.let {
                Spacer(Modifier.height(8.dp))
                Text("First aired: $it")
            }
            Spacer(Modifier.height(8.dp))
            Button(
                shape = RoundedCornerShape(16.dp),
                onClick = { onFavClicked() },
                contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = if (isSystemInDarkTheme()) White
                    else MaterialTheme.colors.secondary
                )
            ) {
                Icon(favIcon, contentDescription = "", tint = RichBlack)
                Spacer(Modifier.width(8.dp))
                Text(
                    text = favText,
                    style = MaterialTheme.typography.button.copy(color = RichBlack)
                )
            }
        }
    }
}