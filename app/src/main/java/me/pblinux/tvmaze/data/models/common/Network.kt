package me.pblinux.tvmaze.data.models.common

import androidx.room.Embedded
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Network(
    @SerialName("country") @Embedded val networkCountry: Country?,
    @SerialName("id") val networkId: Int,
    @SerialName("name") val networkName: String,
)