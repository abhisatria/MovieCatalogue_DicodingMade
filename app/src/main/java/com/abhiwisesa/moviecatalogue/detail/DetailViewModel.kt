package com.abhiwisesa.moviecatalogue.detail

import android.util.Log
import androidx.lifecycle.*
import com.abhiwisesa.core.domain.model.Movie
import com.abhiwisesa.core.domain.usecase.MovieUseCase

class DetailViewModel(private val movieUseCase: MovieUseCase) : ViewModel() {
    private val _movieId = MutableLiveData<String>()
    private val _showId = MutableLiveData<String>()

    fun setSelectedMovie(movieId: String) {
        if (_movieId.value != movieId) {
            _movieId.value = movieId
        }
    }

    fun setSelectedShow(showId: String) {
        if (_showId.value != showId) {
            _showId.value = showId
        }
    }

    fun setFavoriteTourism(movie: Movie, newStatus: Boolean) {
        Log.d("DetailViewModel","TESSSS")
        return movieUseCase.setFavoriteMovie(movie, newStatus)
    }
}