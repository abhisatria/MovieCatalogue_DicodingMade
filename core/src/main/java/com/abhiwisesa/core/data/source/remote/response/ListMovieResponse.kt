package com.abhiwisesa.core.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class ListMovieResponse(

	@field:SerializedName("results")
	val results: List<MovieResponse>
)

//data class MovieInList(
//
//	@field:SerializedName("release_date")
//	val releaseDate: String,
//
//	@field:SerializedName("original_title")
//	val originalTitle: String,
//
//	@field:SerializedName("poster_path")
//	val posterPath: String,
//
//	@field:SerializedName("id")
//	val id: Int
//)
