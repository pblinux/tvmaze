package me.pblinux.tvmaze.ui.screens.home

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Text
import androidx.compose.runtime.Composable

@Composable
fun MyList() {
    LazyColumn {
        items(100, itemContent = {
            Text("Hi: $it")
        })
    }
}