package com.abhiwisesa.core.data.source.local

import com.abhiwisesa.core.data.source.local.entity.MovieEntity
import com.abhiwisesa.core.data.source.local.room.MovieDao
import kotlinx.coroutines.flow.Flow

class LocalDataSource(private val mMovieDao: MovieDao) {
    fun getAllMovie(): Flow<List<MovieEntity>> = mMovieDao.getAllMovie()

    fun getALlShow(): Flow<List<MovieEntity>> = mMovieDao.getAllShow()

    fun getFavoriteMovie(): Flow<List<MovieEntity>> = mMovieDao.getFlowFavoriteMovie()

    fun getFavoriteShow(): Flow<List<MovieEntity>> = mMovieDao.getFlowFavoriteShow()

    suspend fun insertMovie(movieList: List<MovieEntity>) = mMovieDao.insertListMovie(movieList)

    fun setFavoriteMovie(movie: MovieEntity, newState: Boolean) {
        movie.isFavorite = newState
        mMovieDao.updateFavoriteMovie(movie)
    }
}