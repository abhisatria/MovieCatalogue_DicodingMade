package com.abhiwisesa.favorite.fragment

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.abhiwisesa.core.domain.usecase.MovieUseCase

class FavoriteViewModel(movieUseCase: MovieUseCase) : ViewModel() {

    val favoriteShow = movieUseCase.getFavoriteShow().asLiveData()

    val favoriteMovie = movieUseCase.getFavoriteMovie().asLiveData()

}