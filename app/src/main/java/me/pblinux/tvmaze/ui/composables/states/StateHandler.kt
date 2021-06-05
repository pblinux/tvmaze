package me.pblinux.tvmaze.ui.composables.states

import androidx.compose.runtime.Composable
import me.pblinux.tvmaze.data.models.State

@Suppress("UNCHECKED_CAST")
@Composable
fun <T> StateHandler(state: State, loadedContent: @Composable (T) -> Unit) = when (state) {
    State.Uninitialized -> Uninitialized(message = "")
    State.Loading -> Loading()
    State.Error -> Error {}
    is State.Loaded<*> -> {
        loadedContent(state.data as T)
    }
}