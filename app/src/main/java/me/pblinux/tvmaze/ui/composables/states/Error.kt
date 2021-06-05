package me.pblinux.tvmaze.ui.composables.states

import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Preview
@Composable
fun ErrorPreview() {
    Error {
        //TO-DO
    }
}

@Composable
fun Error(
    onRetry: () -> Unit
) {
    Box(Modifier.fillMaxSize()) {
        Card(Modifier.align(Alignment.Center)) {
            Column(Modifier.padding(32.dp), horizontalAlignment = Alignment.CenterHorizontally) {
                Text(text = "Something happens :(")
                Spacer(modifier = Modifier.height(24.dp))
                Button(onClick = onRetry) {
                    Text("Try again")
                }
            }
        }
    }
}