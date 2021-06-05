package me.pblinux.tvmaze.ui.composables.common

import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun Back(onClick: () -> Unit) {
    IconButton(
        onClick = onClick,
    ) {
        Icon(
            modifier = Modifier.size(80.dp),
            imageVector = Icons.Default.ArrowBack,
            contentDescription = "",
            tint = MaterialTheme.colors.secondary
        )
    }
}