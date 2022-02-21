package com.abhiwisesa.core.data.source.remote

import android.util.Log
import com.abhiwisesa.core.data.source.remote.network.ApiResponse
import com.abhiwisesa.core.data.source.remote.network.ApiService
import com.abhiwisesa.core.data.source.remote.response.MovieResponse
import com.abhiwisesa.core.data.source.remote.response.TvShowResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class RemoteDataSource(private val apiService: ApiService) {

//
//    fun getListTvShow(): ArrayList<ResultsItem> {
//        EspressoIdlingResource.increment()
//        val result = ArrayList<ResultsItem>()
//        handler.postDelayed(
//            {
//                result.addAll(DataDummy.generateDummyTvShow())
//                EspressoIdlingResource.decrement()
//            },
//            SERVICE_LATENCY_IN_MILLIS
//        )
//        return result
//    }


    suspend fun getListShow(): Flow<ApiResponse<List<TvShowResponse>>> {

        return flow {
            try {
                val response = apiService.getListTVShow()
                val dataArray = response.results
                if (dataArray.isNotEmpty()) {
                    emit(ApiResponse.Success(response.results))
                } else {
                    emit(ApiResponse.Empty)
                }
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.toString()))
                Log.e("RemoteDataSource", e.toString())
            }
        }.flowOn(Dispatchers.IO)
    }

    suspend fun getListMovie(): Flow<ApiResponse<List<MovieResponse>>> {

        return flow {
            try {
                val response = apiService.getListMovie()
                val dataArray = response.results
                if (dataArray.isNotEmpty()) {
                    emit(ApiResponse.Success(response.results))
                    Log.d("RemoteDataSource succes", "HAAAA")
                } else {
                    emit(ApiResponse.Empty)
                }
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.toString()))
                Log.e("RemoteDataSource", e.toString())
            }
        }.flowOn(Dispatchers.IO)
//        EspressoIdlingResource.increment()
//        val result = ArrayList<MovieInList>()
//        handler.postDelayed(
//            {
//                result.addAll(DataDummy.generateDummyMovies())
//                EspressoIdlingResource.decrement()
//            },
//            SERVICE_LATENCY_IN_MILLIS
//        )
//        return result
    }

//    fun getListMovie(): ArrayList<MovieInList> {
//        EspressoIdlingResource.increment()
//        val result = ArrayList<MovieInList>()
//        handler.postDelayed(
//            {
//                result.addAll(DataDummy.generateDummyMovies())
//                EspressoIdlingResource.decrement()
//            },
//            SERVICE_LATENCY_IN_MILLIS
//        )
//        return result
//    }
//
//    fun getTvShow(): DetailTvShowResponse?{
//        EspressoIdlingResource.increment()
//        var result : DetailTvShowResponse? = null
//        handler.postDelayed(
//            {
//                result = DataDummy.generateDummyDetailShow()
//                EspressoIdlingResource.decrement()
//            },
//            SERVICE_LATENCY_IN_MILLIS
//        )
//        return result
//    }
//    fun getMovie(): DetailMovieResponse?{
//        EspressoIdlingResource.increment()
//        var result : DetailMovieResponse? = null
//        handler.postDelayed(
//            {
//                result = DataDummy.generateDummyDetailMovie()
//                EspressoIdlingResource.decrement()
//            },
//            SERVICE_LATENCY_IN_MILLIS
//        )
//        return result
//    }

}