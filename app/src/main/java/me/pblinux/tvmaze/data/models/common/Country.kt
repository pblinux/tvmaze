package me.pblinux.tvmaze.data.models.common

import androidx.room.ColumnInfo
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Country(
    @ColumnInfo(name = "countryName") @SerialName("name") val countryName: String,
    @ColumnInfo(name = "countryCode") @SerialName("code") val countryCode: String,
    @ColumnInfo(name = "countryTimezone") @SerialName("timezone") val countryTimezone: String
)