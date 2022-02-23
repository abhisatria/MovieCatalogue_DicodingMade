package com.abhiwisesa.core.utils

import com.abhiwisesa.core.domain.model.Movie


object DataDummy {

    fun generateDummyMovies(): List<Movie> {
        val movies = ArrayList<Movie>()
        movies.add(
            Movie(634649,
                "Spider-Man: No Way Home",
                "en",
                "2021-12-15",
                "ini description",
                "/1g0dhYtq4irTY1GPXvft6k4YLjm.jpg",
                8.0,
                false,
                true,
                null
            )
        )
        return movies
    }

    fun generateDummyTvShow(): List<Movie> {
        val movies = ArrayList<Movie>()
        movies.add(
            Movie(634649,
                "Spider-Man: No Way Home",
                "en",
                "2021-12-15",
                "ini description",
                "/1g0dhYtq4irTY1GPXvft6k4YLjm.jpg",
                8.0,
                false,
                true,
                null
            )
        )
        return movies
    }}