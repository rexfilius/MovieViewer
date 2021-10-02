package com.github.rexfilius.movieviewer.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "movies")
data class Result(

    @PrimaryKey
    @SerializedName("id")
    val movieId: Int,

    @SerializedName("overview")
    val movieOverview: String,

    @SerializedName("poster_path")
    val moviePoster: String,

    @SerializedName("release_date")
    val movieReleaseDate: String,

    @SerializedName("title")
    val movieTitle: String,

    var isFavorite: Boolean = false
)
