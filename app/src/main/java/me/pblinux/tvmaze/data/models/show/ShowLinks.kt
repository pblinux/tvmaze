package me.pblinux.tvmaze.data.models.show

import android.os.Parcelable
import androidx.room.Embedded
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.Serializable
import me.pblinux.tvmaze.data.models.common.Link

@Parcelize
@Serializable
data class ShowLinks(
    @Embedded val self : Link?,
) : Parcelable