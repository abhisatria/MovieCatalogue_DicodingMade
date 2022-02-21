package com.abhiwisesa.moviecatalogue.data.source.local

import com.abhiwisesa.moviecatalogue.data.source.local.entity.MovieEntity
import com.abhiwisesa.moviecatalogue.data.source.local.room.MovieDao
import kotlinx.coroutines.flow.Flow

class LocalDataSource(private val mMovieDao: MovieDao) {
    fun getAllMovie(): Flow<List<MovieEntity>> = mMovieDao.getAllMovie()

    fun getALlShow(): Flow<List<MovieEntity>> = mMovieDao.getAllShow()

    fun getFavoriteMovie(): Flow<List<MovieEntity>> = mMovieDao.getFlowFavoriteMovie()
//    fun getFavoriteMoviePaging(): Flow<PagingData<MovieEntity>> {
//        val pager = Pager(
//            PagingConfig(
//                pageSize = 10,
//                enablePlaceholders = true,
//                maxSize = 200
//            )
//        ) {
//            mMovieDao.getFavoriteMovie()
//        }.flow
//        return pager
//    }

//    fun getFavoriteShowPaging(): Flow<PagingData<MovieEntity>> {
//        val pager = Pager(
//            PagingConfig(
//                pageSize = 10,
//                enablePlaceholders = true,
//                maxSize = 200
//            )
//        ) {
//            mMovieDao.getFavoriteShow()
//        }.flow
//        return pager
//    }

    fun getFavoriteShow(): Flow<List<MovieEntity>> = mMovieDao.getFlowFavoriteShow()

    suspend fun insertMovie(movieList: List<MovieEntity>) = mMovieDao.insertListMovie(movieList)

    fun setFavoriteMovie(movie: MovieEntity, newState: Boolean) {
        movie.isFavorite = newState
        mMovieDao.updateFavoriteMovie(movie)
    }
}