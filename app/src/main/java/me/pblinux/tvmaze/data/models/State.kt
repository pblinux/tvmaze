package me.pblinux.tvmaze.data.models

sealed class State {
    data class Loaded<T>(val data: T): State()
    object Error: State()
    object Loading: State()
    object Uninitialized: State()
}