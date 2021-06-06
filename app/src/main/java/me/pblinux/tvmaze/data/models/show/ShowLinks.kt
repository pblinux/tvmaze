package me.pblinux.tvmaze.data.models.show

import androidx.room.Embedded
import kotlinx.serialization.Serializable
import me.pblinux.tvmaze.data.models.common.Link

@Serializable
data class ShowLinks(
    @Embedded val self : Link?,
)