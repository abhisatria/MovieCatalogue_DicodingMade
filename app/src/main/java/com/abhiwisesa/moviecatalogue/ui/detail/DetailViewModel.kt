package com.abhiwisesa.moviecatalogue.ui.detail

import android.util.Log
import androidx.lifecycle.*
import com.abhiwisesa.moviecatalogue.core.domain.model.Movie
import com.abhiwisesa.moviecatalogue.core.domain.usecase.MovieUseCase

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

//    val getDetailMovie: LiveData<ApiResponse<DetailMovieResponse>> = Transformations.switchMap(_movieId){
//        movieRepository.getDetailMovie(it)
//    }
//    val getDetailShow: LiveData<ApiResponse<DetailTvShowResponse>> = Transformations.switchMap(_showId){
//        movieRepository.getDetailTvShow(it)
//    }

//    fun getDummyMovie() : LiveData<DetailMovieResponse> {
//        return movieRepository.getDummyMovie()
//    }
//
//    fun getDummyShow() : LiveData<DetailTvShowResponse>{
//        return movieRepository.getDummyShow()
//    }
//
//    fun isMovieSaved(id:Int):LiveData<Boolean> = movieRepository.isMovieSaved(id)
//
//    fun saveMovie(movieEntity: MovieEntity) {
//        viewModelScope.launch {
//            movieRepository.saveMovieToFavorite(movieEntity)
//        }
//    }
//
//    fun deleteMovie(id: Int) {
//        viewModelScope.launch {
//            movieRepository.deleteMovieFromFavorite(id)
//        }
//    }

}