package me.pblinux.tvmaze.ui.composables.states

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview

@Preview
@Composable
fun UninitializedPreview() {
    Uninitialized("No loaded yet")
}

@Composable
fun Uninitialized(message: String) {
    Box(Modifier.fillMaxSize()) {
        Text(message, Modifier.align(Alignment.Center), textAlign = TextAlign.Center)
    }
}