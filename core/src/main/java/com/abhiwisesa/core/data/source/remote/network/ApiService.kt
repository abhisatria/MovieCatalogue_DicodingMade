package com.abhiwisesa.core.data.source.remote.network


import com.abhiwisesa.core.data.source.remote.response.ListMovieResponse
import com.abhiwisesa.core.data.source.remote.response.ListTvShowResponse
import com.abhiwisesa.core.data.source.remote.response.MovieResponse
import com.abhiwisesa.core.data.source.remote.response.TvShowResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {

    @GET("/3/movie/popular")
    suspend fun getListMovie(): ListMovieResponse

    @GET("/3/tv/popular")
    suspend fun getListTVShow(): ListTvShowResponse

    @GET("/3/movie/{id}")
    suspend fun getDetailMovie(@Path("id") id :String): MovieResponse

    @GET("/3/tv/{id}")
    suspend fun getDetailTvShow(@Path("id") id :String): TvShowResponse
}