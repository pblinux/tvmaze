package me.pblinux.tvmaze.ui.composables.common

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun Chip(
    title: String,
    color: Color? = null
) {
    Surface(
        modifier = Modifier.padding(end = 8.dp, bottom = 8.dp),
        elevation = 8.dp,
        color = color ?: MaterialTheme.colors.secondary,
        shape = RoundedCornerShape(16.dp),
    ) {
        Text(title, modifier = Modifier.padding(horizontal = 16.dp, vertical = 4.dp))
    }
}