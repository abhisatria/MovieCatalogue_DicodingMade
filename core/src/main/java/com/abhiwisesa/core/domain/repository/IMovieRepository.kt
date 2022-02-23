package com.abhiwisesa.core.domain.repository

import com.abhiwisesa.core.domain.model.Movie
import kotlinx.coroutines.flow.Flow

interface IMovieRepository {

    fun getListTvShow(): Flow<com.abhiwisesa.core.data.Resource<List<Movie>>>
    fun getListMovie(): Flow<com.abhiwisesa.core.data.Resource<List<Movie>>>

    fun getFavoriteMovie(): Flow<List<Movie>>
    fun getFavoriteShow(): Flow<List<Movie>>
    fun setFavoriteMovie(movie: Movie, state: Boolean)

}