package com.abhiwisesa.moviecatalogue.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class ListTvShowResponse(


	@field:SerializedName("results")
	val results: List<TvShowResponse>
)

