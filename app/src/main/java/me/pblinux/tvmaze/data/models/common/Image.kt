package me.pblinux.tvmaze.data.models.common

import kotlinx.serialization.Serializable

@Serializable
data class Image(
    val medium: String,
    val original: String,
)