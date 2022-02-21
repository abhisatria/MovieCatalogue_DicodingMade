package com.abhiwisesa.moviecatalogue.ui.movies

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.abhiwisesa.moviecatalogue.core.domain.usecase.MovieUseCase

class MovieViewModel(movieUseCase: MovieUseCase):ViewModel() {

//    fun getMovies(): LiveData<List<MovieInList>> = movieRepository.getDummyAllMovie()
    val getListMovie = movieUseCase.getListMovie().asLiveData()
//    val getListMovie: LiveData<ApiResponse<List<MovieInList>>> =
//        movieRepository.getListMovie()

}