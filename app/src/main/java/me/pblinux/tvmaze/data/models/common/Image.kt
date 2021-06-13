package me.pblinux.tvmaze.data.models.common

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.Serializable

@Parcelize
@Serializable
data class Image(
    val medium: String,
    val original: String,
) : Parcelable