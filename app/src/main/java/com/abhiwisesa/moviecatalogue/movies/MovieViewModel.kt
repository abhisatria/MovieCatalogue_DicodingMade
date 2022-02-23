package com.abhiwisesa.moviecatalogue.movies

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.abhiwisesa.core.domain.usecase.MovieUseCase

class MovieViewModel(movieUseCase: MovieUseCase):ViewModel() {

    val getListMovie = movieUseCase.getListMovie().asLiveData()

}