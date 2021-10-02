package com.github.rexfilius.movieviewer.models

import com.google.gson.annotations.SerializedName

data class MovieDetail(

    @SerializedName("id")
    val movieId: Int,

    @SerializedName("overview")
    val movieOverview: String,

    @SerializedName("release_date")
    val movieReleaseDate: String,

    @SerializedName("title")
    val movieTitle: String,
)

