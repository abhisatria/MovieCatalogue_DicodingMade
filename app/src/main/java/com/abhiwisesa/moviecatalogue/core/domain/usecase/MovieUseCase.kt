package com.abhiwisesa.moviecatalogue.core.domain.usecase

import com.abhiwisesa.moviecatalogue.core.domain.model.Movie
import com.abhiwisesa.moviecatalogue.data.source.Resource
import kotlinx.coroutines.flow.Flow

interface MovieUseCase {
    fun getListTvShow(): Flow<Resource<List<Movie>>>
    fun getListMovie(): Flow<Resource<List<Movie>>>

    fun getFavoriteMovie():Flow<List<Movie>>
    fun getFavoriteShow():Flow<List<Movie>>
    fun setFavoriteMovie(movie: Movie,state:Boolean)

//    fun getFavoriteMoviePaging():Flow<PagingData<Movie>>
//    fun getFavoriteShowPaging():Flow<PagingData<Movie>>
}