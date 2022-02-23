package com.abhiwisesa.moviecatalogue.di

import com.abhiwisesa.core.domain.usecase.MovieInteractor
import com.abhiwisesa.core.domain.usecase.MovieUseCase
import com.abhiwisesa.moviecatalogue.detail.DetailViewModel
import com.abhiwisesa.moviecatalogue.movies.MovieViewModel
import com.abhiwisesa.moviecatalogue.tv_show.TvShowViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val useCaseModule = module {
    factory<MovieUseCase> { MovieInteractor(get()) }
}

val viewModelModule = module {
    viewModel { MovieViewModel(get()) }
    viewModel { TvShowViewModel(get()) }
    viewModel { DetailViewModel(get()) }
}