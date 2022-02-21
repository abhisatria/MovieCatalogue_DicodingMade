package com.abhiwisesa.core.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class MovieResponse(

	@field:SerializedName("original_language")
	val originalLanguage: String,

	@field:SerializedName("id")
	val id: Int,

	@field:SerializedName("overview")
	val overview: String,

	@field:SerializedName("title")
	val title: String,

	@field:SerializedName("poster_path")
	val posterPath: String,

	@field:SerializedName("release_date")
	val releaseDate: String,

	@field:SerializedName("vote_average")
	val voteAverage: Double,

	)


//data class GenresItem(
//
//	@field:SerializedName("name")
//	val name: String,
//
//	@field:SerializedName("id")
//	val id: Int
//)
