package me.pblinux.tvmaze.data.models.episode

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import me.pblinux.tvmaze.data.models.common.Image

@Serializable
data class Episode(
    val id: Int,
    val url: String,
    val name: String,
    val season: Int,
    val number: Int,
    val type: String,
    @SerialName("airdate")
    val airDate: String,
    @SerialName("airtime")
    val airTime: String,
    @SerialName("airstamp")
    val airStamp: String,
    val runtime: Int,
    val image: Image?,
    val summary: String?,
)