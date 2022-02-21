package com.abhiwisesa.moviecatalogue.data.source

import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import com.abhiwisesa.moviecatalogue.data.source.local.entity.MovieEntity
import com.abhiwisesa.moviecatalogue.data.source.remote.network.ApiResponse
import com.abhiwisesa.moviecatalogue.data.source.remote.response.MovieResponse
import com.abhiwisesa.moviecatalogue.data.source.remote.response.TvShowResponse

interface MovieDataSource {
    fun getListTvShow(): LiveData<ApiResponse<List<TvShowResponse>>>

    fun getListMovie(): LiveData<ApiResponse<List<MovieResponse>>>

    fun getDummyAllMovie(): LiveData<List<MovieResponse>>

    fun getDummyAllTvShow():LiveData<List<TvShowResponse>>

    fun getDummyMovie() : LiveData<MovieResponse>

    fun getDummyShow() : LiveData<TvShowResponse>

    fun getDetailMovie(id :String) : LiveData<ApiResponse<MovieResponse>>

    fun getDetailTvShow(id:String) : LiveData<ApiResponse<TvShowResponse>>

    suspend fun saveMovieToFavorite(movie : MovieEntity)

    suspend fun deleteMovieFromFavorite(id: Int)

    fun getFavoriteMovie(sort:String,isMovie:Boolean) : LiveData<PagedList<MovieEntity>>

    fun getFavoriteShow(sort:String,isMovie:Boolean) : LiveData<PagedList<MovieEntity>>

    fun getDetailFavoriteMovie(id:Int) : MovieEntity

    fun isMovieSaved(id:Int) : LiveData<Boolean>
}