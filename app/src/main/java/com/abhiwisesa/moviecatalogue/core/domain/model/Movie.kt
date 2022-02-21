package com.abhiwisesa.moviecatalogue.core.domain.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import java.util.*

@Parcelize
data class Movie(
    val movieId: Int,
    val title: String,
    val language: String,
    var releaseDate: String,
    val description: String,
    val posterPath: String,
    val voteAverage: Double,
    val isFavorite : Boolean = false,
    val isMovie: Boolean,
    val dateAdded: Date? = null
) : Parcelable