package com.abhiwisesa.core.domain.usecase

import com.abhiwisesa.core.domain.model.Movie
import kotlinx.coroutines.flow.Flow

interface MovieUseCase {
    fun getListTvShow(): Flow<com.abhiwisesa.core.data.Resource<List<Movie>>>
    fun getListMovie(): Flow<com.abhiwisesa.core.data.Resource<List<Movie>>>

    fun getFavoriteMovie():Flow<List<Movie>>
    fun getFavoriteShow():Flow<List<Movie>>
    fun setFavoriteMovie(movie: Movie,state:Boolean)

//    fun getFavoriteMoviePaging():Flow<PagingData<Movie>>
//    fun getFavoriteShowPaging():Flow<PagingData<Movie>>
}