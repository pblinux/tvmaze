package me.pblinux.tvmaze.data.models.common

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@Parcelize
data class Link(
    @SerialName("href")
    val link: String
) : Parcelable
