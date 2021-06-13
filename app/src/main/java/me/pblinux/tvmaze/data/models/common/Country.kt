package me.pblinux.tvmaze.data.models.common

import android.os.Parcelable
import androidx.room.ColumnInfo
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Parcelize
@Serializable
data class Country(
    @ColumnInfo(name = "countryName") @SerialName("name") val countryName: String,
    @ColumnInfo(name = "countryCode") @SerialName("code") val countryCode: String,
    @ColumnInfo(name = "countryTimezone") @SerialName("timezone") val countryTimezone: String
) : Parcelable