package me.pblinux.tvmaze.data.models.show

import android.os.Parcelable
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import me.pblinux.tvmaze.data.models.common.*

@Entity
@Parcelize
@Serializable
data class Show(
    @Embedded @SerialName("_links") val links: ShowLinks,
    @Embedded val externals: Externals,
    @Embedded val image: Image?,
    @Embedded(prefix = "network_") val network: Network?,
    @Embedded val rating: Rating,
    @Embedded val schedule: Schedule,
    @Embedded(prefix = "channel_") val webChannel: Channel?,
    @PrimaryKey val id: Int,
    val averageRuntime: Int?,
    val genres: List<String>,
    val language: String?,
    val name: String,
    val officialSite: String?,
    val premiered: String?,
    val runtime: Int?,
    val status: String,
    val summary: String?,
    val type: String,
    val url: String,
    val weight: Int,
) : Parcelable