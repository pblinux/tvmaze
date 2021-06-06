package me.pblinux.tvmaze.data.models.common

import androidx.room.ColumnInfo
import androidx.room.Embedded
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Channel(
    @SerialName("country") @Embedded val channelCountry: Country?,
    @SerialName("id") val channelId: Int,
    @SerialName("name") val channelName: String,
)