package com.abhiwisesa.core.di

import androidx.room.Room
import com.abhiwisesa.core.BuildConfig


import com.abhiwisesa.core.data.source.local.room.MovieDatabase
import com.abhiwisesa.core.data.source.remote.RemoteDataSource
import com.abhiwisesa.core.data.source.remote.network.ApiService
import com.abhiwisesa.core.domain.repository.IMovieRepository
import com.abhiwisesa.core.utils.AppExecutors
import okhttp3.OkHttpClient
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val databaseModule = module {
    factory { get<MovieDatabase>().movieDao() }
    single {
        Room.databaseBuilder(
            androidContext(),
            MovieDatabase::class.java, "movies.db"
        ).fallbackToDestructiveMigration().build()
    }
}

val networkModule = module {
    single {
        OkHttpClient.Builder()
            .addInterceptor { chain ->
                val url = chain
                    .request()
                    .url
                    .newBuilder()
                    .addQueryParameter("api_key", BuildConfig.KEY)
                    .build()
                chain.proceed(chain.request().newBuilder().url(url).build())
            }
            .build()
    }
    single {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.themoviedb.org/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(get())
            .build()

        retrofit.create(ApiService::class.java)
    }
}

val repositoryModule = module {
    single { com.abhiwisesa.core.data.source.local.LocalDataSource(get()) }
    single { RemoteDataSource(get()) }
    factory { AppExecutors() }
    single<IMovieRepository> {
        com.abhiwisesa.core.data.MovieRepository(
            get(),
            get(),
            get()
        )
    }
}