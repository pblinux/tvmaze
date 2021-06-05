package me.pblinux.tvmaze.ui.composables.show

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.layout.ContentScale
import com.google.accompanist.coil.rememberCoilPainter
import me.pblinux.tvmaze.R

@Composable
fun PosterBackground(image: String?) {
    Box(Modifier.fillMaxSize()) {
        Image(
            painter = rememberCoilPainter(
                request = image,
                fadeIn = true,
                previewPlaceholder = R.drawable.ic_launcher_foreground
            ),
            contentDescription = "",
            contentScale = ContentScale.FillHeight,
            modifier = Modifier.fillMaxSize()
        )
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    Brush.verticalGradient(
                        listOf(
                            MaterialTheme.colors.background.copy(alpha = 0.9f),
                            MaterialTheme.colors.background
                        ),
                        startY = 150f,
                    )
                )
        )
    }
}