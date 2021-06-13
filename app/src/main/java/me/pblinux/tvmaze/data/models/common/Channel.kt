package me.pblinux.tvmaze.data.models.common

import android.os.Parcelable
import androidx.room.Embedded
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Parcelize
@Serializable
data class Channel(
    @SerialName("country") @Embedded val channelCountry: Country?,
    @SerialName("id") val channelId: Int,
    @SerialName("name") val channelName: String,
) : Parcelable