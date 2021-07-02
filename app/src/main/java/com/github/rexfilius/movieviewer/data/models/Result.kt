package com.github.rexfilius.movieviewer.data.models

import com.google.gson.annotations.SerializedName

data class Result(

    @SerializedName("id")
    val movieId: Int,

    @SerializedName("overview")
    val movieOverview: String,

    @SerializedName("poster_path")
    val moviePoster: String?,

    @SerializedName("release_date")
    val movieReleaseDate: String,

    @SerializedName("title")
    val movieTitle: String,
)
