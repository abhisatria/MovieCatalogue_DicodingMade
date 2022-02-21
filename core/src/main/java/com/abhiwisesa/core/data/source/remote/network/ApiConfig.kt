package com.abhiwisesa.core.data.source.remote.network

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

//class ApiConfig {
//    companion object{
//        fun getApiService(): ApiService {
//
//            val client = OkHttpClient.Builder()
//                .addInterceptor { chain ->
//                    val url = chain
//                            .request()
//                            .url
//                        .newBuilder()
//                        .addQueryParameter("api_key", BuildConfig.KEY)
//                        .build()
//                    chain.proceed(chain.request().newBuilder().url(url).build())
//                }
//                .build()
//            val retrofit = Retrofit.Builder()
//                    .baseUrl("https://api.themoviedb.org/")
//                    .addConverterFactory(GsonConverterFactory.create())
//                    .client(client)
//                    .build()
//            return retrofit.create(ApiService::class.java)
//        }
//    }
//}