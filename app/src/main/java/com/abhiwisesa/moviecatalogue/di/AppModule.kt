package com.abhiwisesa.moviecatalogue.di

import com.abhiwisesa.moviecatalogue.core.domain.usecase.MovieInteractor
import com.abhiwisesa.moviecatalogue.core.domain.usecase.MovieUseCase
import com.abhiwisesa.moviecatalogue.ui.detail.DetailViewModel
import com.abhiwisesa.moviecatalogue.ui.favorite.FavoriteViewModel
import com.abhiwisesa.moviecatalogue.ui.movies.MovieViewModel
import com.abhiwisesa.moviecatalogue.ui.tv_show.TvShowViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val useCaseModule = module {
    factory<MovieUseCase> { MovieInteractor(get()) }
}

val viewModelModule = module {
    viewModel { MovieViewModel(get()) }
    viewModel { TvShowViewModel(get()) }
    viewModel { DetailViewModel(get()) }
    viewModel { FavoriteViewModel(get()) }
}