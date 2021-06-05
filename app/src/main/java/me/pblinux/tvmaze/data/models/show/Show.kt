package me.pblinux.tvmaze.data.models.show

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import me.pblinux.tvmaze.data.models.common.*

@Serializable
@Parcelize
data class Show(
    @SerialName("_links") val links: ShowLinks,
    val averageRuntime: Int?,
    val externals: Externals,
    val genres: List<String>,
    val id: Int,
    val image: Image?,
    val language: String?,
    val name: String,
    val network: Network?,
    val officialSite: String?,
    val premiered: String?,
    val rating: Rating,
    val runtime: Int?,
    val schedule: Schedule,
    val status: String,
    val summary: String?,
    val type: String,
    val url: String,
    val webChannel: Channel?,
    val weight: Int,
) : Parcelable