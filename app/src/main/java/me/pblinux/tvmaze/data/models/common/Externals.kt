package me.pblinux.tvmaze.data.models.common

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Externals(
    @SerialName("tvrage") val tvRage: Int?,
    @SerialName("thetvdb") val theTvDb: Int?,
    val imdb: String?,
)