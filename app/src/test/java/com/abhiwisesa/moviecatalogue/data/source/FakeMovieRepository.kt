package com.abhiwisesa.moviecatalogue.data.source

import android.content.ContentValues.TAG
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.liveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.abhiwisesa.moviecatalogue.data.source.local.entity.MovieEntity
import com.abhiwisesa.moviecatalogue.data.source.local.room.MovieDao
import com.abhiwisesa.moviecatalogue.data.source.remote.*
import com.abhiwisesa.moviecatalogue.data.source.remote.network.ApiResponse
import com.abhiwisesa.moviecatalogue.data.source.remote.network.ApiService
import com.abhiwisesa.moviecatalogue.data.source.remote.response.*
import com.abhiwisesa.moviecatalogue.utils.DataDummy
import com.abhiwisesa.moviecatalogue.utils.EspressoIdlingResource

class FakeMovieRepository (private val apiService: ApiService,private val remoteDataSource: RemoteDataSource,private val mMovieDao : MovieDao ) :
    MovieDataSource {

    override fun getListTvShow(): LiveData<ApiResponse<List<ResultsItem>>> = liveData{
        emit(ApiResponse.Loading)
        EspressoIdlingResource.increment()
        try {
            val response = apiService.getListTVShow()
            emit(ApiResponse.Success(response.results))
            EspressoIdlingResource.decrement()
        } catch (e: Exception) {
            Log.d(TAG, "getListTvShow: ${e.message.toString()} ")
            emit(ApiResponse.Error(e.message.toString()))
            EspressoIdlingResource.decrement()
        }
    }


    override fun getListMovie(): LiveData<ApiResponse<List<MovieInList>>> = liveData {
        emit(ApiResponse.Loading)
        EspressoIdlingResource.increment()
        Log.d("Movie Repo","tess")
        try {
            val response = apiService.getListMovie()
            emit(ApiResponse.Success(response.results))
            EspressoIdlingResource.decrement()
        } catch (e: Exception) {
            Log.d(TAG, "getMovies: ${e.message.toString()} ")
            emit(ApiResponse.Error(e.message.toString()))
            EspressoIdlingResource.decrement()
        }
    }

    override fun getDummyAllMovie(): LiveData<List<MovieInList>> {
        val listMovie = MutableLiveData<List<MovieInList>>()
        val movies = DataDummy.generateDummyMovies()

        listMovie.postValue(movies)

        return listMovie
    }

    override fun getDummyAllTvShow(): LiveData<List<ResultsItem>> {
        val listTvShow = MutableLiveData<List<ResultsItem>>()
        val shows = DataDummy.generateDummyTvShow()

        listTvShow.postValue(shows)

        return listTvShow
    }

    fun getAllTvShow():List<ResultsItem>{
        return remoteDataSource.getListTvShow()
    }

    fun getAllMovie():List<MovieInList>{
        return remoteDataSource.getListMovie()
    }

    fun getTvShow(): TvShowResponse?{
        return remoteDataSource.getTvShow()
    }

    fun getMovie(): MovieResponse?{
        return remoteDataSource.getMovie()
    }


    override fun getDummyMovie(): LiveData<MovieResponse> {
        val result = MutableLiveData<MovieResponse>()
        val genres: List<GenresItem> = emptyList()
        var genre = GenresItem("Action", 28)
        genres.toMutableList().add(genre)

        genre = GenresItem("Adventure", 12)
        genres.toMutableList().add(genre)

        genre = GenresItem("Science Fiction", 878)
        genres.toMutableList().add(genre)
        result.postValue(
            MovieResponse(
                "en",
                genres,
                531241,
                "Peter Parker is unmasked and no longer able to separate his normal life from the high-stakes of being a super-hero. When he asks for help from Doctor Strange the stakes become even more dangerous, forcing him to discover what it truly means to be Spider-Man.",
                "Spider-Man: No Way Home",
                "/1g0dhYtq4irTY1GPXvft6k4YLjm.jpg",
                "2021-12-15",
                8.4,
                "Released"
            )
        )

        return result
    }

    override fun getDummyShow(): LiveData<TvShowResponse> {
        val result = MutableLiveData<TvShowResponse>()
        val genres: List<GenresItem> = emptyList()
        val genre = GenresItem("Drama", 18)
        genres.toMutableList().add(genre)

        result.postValue(
            TvShowResponse(
                "en",
                genres,
                85552,
                "2019-06-16",
                "At Maddy’s birthday party, relationships are celebrated and questioned. Meanwhile, Jules turns to Elliot for advice and Cal takes a trip down memory lane.",
                "/jtnfNzqZwN4E32FGGxx1YZaBWWf.jpg",
                "Euphoria",
                8.4
            )
        )
        return result
    }

    override fun getDetailMovie(id: String): LiveData<ApiResponse<MovieResponse>> = liveData {
        emit(ApiResponse.Loading)
        EspressoIdlingResource.increment()
        try {
            val response = apiService.getDetailMovie(id)
            emit(ApiResponse.Success(response))
            EspressoIdlingResource.decrement()
        } catch (e: Exception) {
            Log.d(TAG, "getDetailMovie: ${e.message.toString()} ")
            emit(ApiResponse.Error(e.message.toString()))
            EspressoIdlingResource.decrement()
        }
    }

    override fun getDetailTvShow(id: String): LiveData<ApiResponse<TvShowResponse>> = liveData {
        emit(ApiResponse.Loading)
        EspressoIdlingResource.increment()
        try {
            val response = apiService.getDetailTvShow(id)
            emit(ApiResponse.Success(response))
            EspressoIdlingResource.decrement()
        } catch (e: Exception) {
            Log.d(TAG, "getDetailTvShow: ${e.message.toString()} ")
            emit(ApiResponse.Error(e.message.toString()))
            EspressoIdlingResource.decrement()
        }
    }

    override suspend fun saveMovieToFavorite(movie: MovieEntity) = mMovieDao.insertMovie(movie)

    override suspend fun deleteMovieFromFavorite(id: Int) = mMovieDao.deleteMovie(id)

    override fun getFavoriteMovie(sort : String,isMovie : Boolean): LiveData<PagedList<MovieEntity>> {
        val config = PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setInitialLoadSizeHint(4)
            .setPageSize(4)
            .build()
        return LivePagedListBuilder(mMovieDao.getFavoriteMovie(), config).build()
    }

    override fun getFavoriteShow(sort: String, isMovie: Boolean): LiveData<PagedList<MovieEntity>> {
        val config = PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setInitialLoadSizeHint(4)
            .setPageSize(4)
            .build()
        return LivePagedListBuilder(mMovieDao.getFavoriteShow(), config).build()
    }

    override fun getDetailFavoriteMovie(id: Int): MovieEntity {
        return mMovieDao.getMovieById(id)
    }

    override fun isMovieSaved(id: Int): LiveData<Boolean> {
        return mMovieDao.isMovieSaved(id)
    }

}