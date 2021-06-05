package me.pblinux.tvmaze.ui.composables.common

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.size
import androidx.compose.material.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.google.accompanist.coil.rememberCoilPainter
import me.pblinux.tvmaze.R

@Composable
fun Poster(
    image: String?,
    modifier: Modifier = Modifier,
    posterSize: Dp = 136.dp,
    contentScale: ContentScale = ContentScale.FillBounds,
    content: (@Composable () -> Unit)? = null,
    onClick: (() -> Unit)? = null,
) {
    Card(
        modifier.size(width = posterSize, height = posterSize.times(1.5f))
    ) {
        Image(
            painter = rememberCoilPainter(
                request = image,
                fadeIn = true,
                previewPlaceholder = R.drawable.ic_launcher_foreground,
            ),
            contentDescription = "",
            contentScale = contentScale,
            modifier = Modifier.clickable(enabled = onClick != null, onClick = {
                if (onClick != null) {
                    onClick()
                }
            })
        )
        if (content != null) {
            content()
        }
    }
}