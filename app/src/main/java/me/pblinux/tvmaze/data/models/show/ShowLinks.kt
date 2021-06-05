package me.pblinux.tvmaze.data.models.show

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.Serializable
import me.pblinux.tvmaze.data.models.common.Link

@Serializable
@Parcelize
data class ShowLinks(
    val self : Link?,
) : Parcelable