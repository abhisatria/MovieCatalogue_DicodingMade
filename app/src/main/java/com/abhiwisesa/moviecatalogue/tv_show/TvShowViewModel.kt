package com.abhiwisesa.moviecatalogue.tv_show

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.abhiwisesa.core.domain.usecase.MovieUseCase

class TvShowViewModel(movieUseCase: MovieUseCase): ViewModel() {

    val getListTvShow = movieUseCase.getListTvShow().asLiveData()
}