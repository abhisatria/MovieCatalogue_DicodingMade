package com.abhiwisesa.favorite.di

import com.abhiwisesa.favorite.fragment.FavoriteViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val favoriteModule = module {
    viewModel { FavoriteViewModel(get()) }
}