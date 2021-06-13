package me.pblinux.tvmaze.data.models.search

import kotlinx.serialization.Serializable
import me.pblinux.tvmaze.data.models.show.Show

@Serializable
data class SearchResult(
    val score: Double,
    val show: Show,
)