package me.pblinux.tvmaze.data.models.common

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Link(
    @SerialName("href") val link: String
)
