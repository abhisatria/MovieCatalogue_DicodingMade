package com.abhiwisesa.core.domain.repository

import com.abhiwisesa.core.domain.model.Movie
import kotlinx.coroutines.flow.Flow

interface IMovieRepository {

    fun getListTvShow(): Flow<com.abhiwisesa.core.data.Resource<List<Movie>>>
    fun getListMovie(): Flow<com.abhiwisesa.core.data.Resource<List<Movie>>>

    fun getFavoriteMovie(): Flow<List<Movie>>
    fun getFavoriteShow(): Flow<List<Movie>>
//    fun getFavoriteMoviePaging(): Flow<PagingData<Movie>>
//    fun getFavoriteShowPaging(): Flow<PagingData<Movie>>

//    fun getAllTourism(): Flow<Resource<List<ResultsItem>>>

//    fun getFavoriteTourism(): Flow<List<Tourism>>
//
    fun setFavoriteMovie(movie: Movie, state: Boolean)

}