package com.abhiwisesa.core.utils

import com.abhiwisesa.core.data.source.local.entity.MovieEntity
import com.abhiwisesa.core.data.source.remote.response.MovieResponse
import com.abhiwisesa.core.data.source.remote.response.TvShowResponse
import com.abhiwisesa.core.domain.model.Movie

object DataMapper {
    fun mapResponsesToEntities(input: List<MovieResponse>): List<MovieEntity> {
        val movieList = ArrayList<MovieEntity>()
        input.map {
            val movie = MovieEntity(
                id = it.id,
                title = it.title,
                language = it.originalLanguage,
                releaseDate = it.releaseDate,
                description = it.overview,
                posterPath = it.posterPath,
                voteAverage = it.voteAverage,
                isMovie = true,
                isFavorite = false,
                dateAdded = DateHelper.getCurrentDate()
            )
            movieList.add(movie)
        }
        return movieList
    }

    @JvmName("mapResponsesToEntities1")
    fun mapResponsesToEntities(input: List<TvShowResponse>): List<MovieEntity> {
        val movieList = ArrayList<MovieEntity>()
        input.map {
            val movie = MovieEntity(
                id = it.id,
                title = it.originalName,
                language = it.originalLanguage,
                releaseDate = it.firstAirDate,
                description = it.overview,
                posterPath = it.posterPath,
                voteAverage = it.voteAverage,
                isMovie = false,
                isFavorite = false,
                dateAdded = DateHelper.getCurrentDate()
            )
            movieList.add(movie)
        }
        return movieList
    }

    fun mapEntitiesToDomain(input: List<MovieEntity>): List<Movie> =
        input.map {
            Movie(
                description = it.description,
                isFavorite =  it.isFavorite,
                movieId = it.id,
                title = it.title,
                language = it.language,
                releaseDate = it.releaseDate,
                posterPath = it.posterPath,
                voteAverage = it.voteAverage,
                isMovie = it.isMovie,
                dateAdded = it.dateAdded
            )
        }
//    fun mapEntitiesToDomain(input: PagingData<MovieEntity>): PagingData<Movie> =
//        input.map {
//            Movie(
//                description = it.description,
//                isFavorite =  it.isFavorite,
//                movieId = it.id,
//                title = it.title,
//                language = it.language,
//                releaseDate = it.releaseDate,
//                posterPath = it.posterPath,
//                voteAverage = it.voteAverage,
//                isMovie = it.isMovie,
//                dateAdded = it.dateAdded
//            )
//        }

    fun mapDomainToEntity(input: Movie) = MovieEntity(
        description = input.description,
        isFavorite =  input.isFavorite,
        id = input.movieId,
        title = input.title,
        language = input.language,
        releaseDate = input.releaseDate,
        posterPath = input.posterPath,
        voteAverage = input.voteAverage,
        isMovie = input.isMovie,
        dateAdded = input.dateAdded
    )
}