package com.abhiwisesa.moviecatalogue.core.domain.usecase

import com.abhiwisesa.moviecatalogue.core.domain.model.Movie
import com.abhiwisesa.moviecatalogue.core.domain.repository.IMovieRepository
import com.abhiwisesa.moviecatalogue.data.source.Resource
import kotlinx.coroutines.flow.Flow

class MovieInteractor(private val movieRepository: IMovieRepository) : MovieUseCase {
    override fun getListTvShow(): Flow<Resource<List<Movie>>> = movieRepository.getListTvShow()
    override fun getListMovie(): Flow<Resource<List<Movie>>> = movieRepository.getListMovie()
    override fun getFavoriteMovie(): Flow<List<Movie>> = movieRepository.getFavoriteMovie()
    override fun getFavoriteShow(): Flow<List<Movie>> = movieRepository.getFavoriteShow()
    override fun setFavoriteMovie(movie: Movie, state: Boolean) =
        movieRepository.setFavoriteMovie(movie, state)

//    override fun getFavoriteMoviePaging(): Flow<PagingData<Movie>> =
//        movieRepository.getFavoriteMoviePaging()
//
//    override fun getFavoriteShowPaging(): Flow<PagingData<Movie>> =
//        movieRepository.getFavoriteShowPaging()
}