package me.pblinux.tvmaze.data.models.common

import android.os.Parcelable
import androidx.room.Embedded
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Parcelize
@Serializable
data class Network(
    @SerialName("country") @Embedded val networkCountry: Country?,
    @SerialName("id") val networkId: Int,
    @SerialName("name") val networkName: String,
) : Parcelable