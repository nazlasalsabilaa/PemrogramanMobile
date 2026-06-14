package com.nazlasalsabila.global_icons

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Entity(tableName = "movie_explorer")
@Serializable
@Parcelize
data class GlobalIcon(
    @PrimaryKey @SerialName("id") val id: Int,
    @SerialName("title") val name: String,
    @SerialName("overview") val overview: String,
    @SerialName("poster_path") val photoUrl: String?,
    @SerialName("release_date") val releaseDate: String,
    @SerialName("vote_average") val voteAverage: Double,
    @SerialName("original_language") val originalLanguage: String
) : Parcelable

@Serializable
data class MovieResponse(
    @SerialName("results") val results: List<GlobalIcon>
)