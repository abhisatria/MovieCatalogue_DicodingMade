package com.abhiwisesa.moviecatalogue.core.domain.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import java.util.*

@Parcelize
data class Show(
    val movieId: Int,
    val title: String,
    val language: String,
    val genres: String,
    var releaseDate: String,
    val description: String,
    val posterPath: String,
    val voteAverage: Double,
    val isMovie: Boolean = false,
    val dateAdded: Date
) : Parcelable