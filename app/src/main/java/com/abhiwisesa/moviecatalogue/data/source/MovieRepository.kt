package com.abhiwisesa.moviecatalogue.data.source

import com.abhiwisesa.moviecatalogue.core.domain.model.Movie
import com.abhiwisesa.moviecatalogue.core.domain.repository.IMovieRepository
import com.abhiwisesa.moviecatalogue.data.source.local.LocalDataSource
import com.abhiwisesa.moviecatalogue.data.source.remote.RemoteDataSource
import com.abhiwisesa.moviecatalogue.data.source.remote.network.ApiResponse
import com.abhiwisesa.moviecatalogue.data.source.remote.response.MovieResponse
import com.abhiwisesa.moviecatalogue.data.source.remote.response.TvShowResponse
import com.abhiwisesa.moviecatalogue.utils.AppExecutors
import com.abhiwisesa.moviecatalogue.utils.DataMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class MovieRepository(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val appExecutors: AppExecutors
) : IMovieRepository {

    override fun getListTvShow(): Flow<Resource<List<Movie>>> =
        object : NetworkBoundResource<List<Movie>, List<TvShowResponse>>() {
            override fun loadFromDB(): Flow<List<Movie>> {
                return localDataSource.getALlShow().map {
                    DataMapper.mapEntitiesToDomain(it)
                }
            }

            override fun shouldFetch(data: List<Movie>?): Boolean =
                data == null || data.isEmpty()

            override suspend fun createCall(): Flow<ApiResponse<List<TvShowResponse>>> =
                remoteDataSource.getListShow()


            override suspend fun saveCallResult(data: List<TvShowResponse>) {
                val movieList = DataMapper.mapResponsesToEntities(data)
                localDataSource.insertMovie(movieList)
            }

        }.asFlow()


    override fun getListMovie(): Flow<Resource<List<Movie>>> =
        object : NetworkBoundResource<List<Movie>, List<MovieResponse>>() {
            override fun loadFromDB(): Flow<List<Movie>> {
                return localDataSource.getAllMovie().map {
                    DataMapper.mapEntitiesToDomain(it)
                }
            }

            override fun shouldFetch(data: List<Movie>?): Boolean =
                data == null || data.isEmpty()

            override suspend fun createCall(): Flow<ApiResponse<List<MovieResponse>>> {
                return remoteDataSource.getListMovie()
            }

            override suspend fun saveCallResult(data: List<MovieResponse>) {
                val movieList = DataMapper.mapResponsesToEntities(data)
                localDataSource.insertMovie(movieList)
            }
        }.asFlow()

    override fun getFavoriteMovie(): Flow<List<Movie>> {
        return localDataSource.getFavoriteMovie().map {
            DataMapper.mapEntitiesToDomain(it)
        }
    }

    override fun getFavoriteShow(): Flow<List<Movie>> {
        return localDataSource.getFavoriteShow().map {
            DataMapper.mapEntitiesToDomain(it)
        }
    }

//    override fun getFavoriteMoviePaging(): Flow<PagingData<Movie>> {
//        return localDataSource.getFavoriteMoviePaging().map {
//            DataMapper.mapEntitiesToDomain(it)
//        }
//    }
//
//    override fun getFavoriteShowPaging(): Flow<PagingData<Movie>> {
//        return localDataSource.getFavoriteShowPaging().map {
//            DataMapper.mapEntitiesToDomain(it)
//        }
//    }

    override fun setFavoriteMovie(movie: Movie, state: Boolean) {
        val movieEntity = DataMapper.mapDomainToEntity(movie)
        appExecutors.diskIO().execute { localDataSource.setFavoriteMovie(movieEntity, state) }
    }


//
//    override fun getListMovie(): LiveData<ApiResponse<List<ResultsItemMovie>>> = liveData {
//        emit(ApiResponse.Loading)
//        EspressoIdlingResource.increment()
//        try {
//            val response = apiService.getListMovie()
//            emit(ApiResponse.Success(response.results))
//            EspressoIdlingResource.decrement()
//        } catch (e: Exception) {
//            Log.d(TAG, "getMovies: ${e.message.toString()} ")
//            emit(ApiResponse.Error(e.message.toString()))
//            EspressoIdlingResource.decrement()
//        }
//    }
//
//    override fun getDummyAllMovie(): LiveData<List<ResultsItemMovie>> {
//        val listMovie = MutableLiveData<List<ResultsItemMovie>>()
//        val movies = DataDummy.generateDummyMovies()
//
//        listMovie.postValue(movies)
//
//        return listMovie
//    }
//
//    override fun getDummyAllTvShow(): LiveData<List<ResultsItem>> {
//        val listTvShow = MutableLiveData<List<ResultsItem>>()
//        val shows = DataDummy.generateDummyTvShow()
//
//        listTvShow.postValue(shows)
//
//        return listTvShow
//    }
//
//    override fun getDummyMovie(): LiveData<DetailMovieResponse> {
//        val result = MutableLiveData<DetailMovieResponse>()
//        val genres: List<GenresItem> = emptyList()
//        var genre = GenresItem("Action", 28)
//        genres.toMutableList().add(genre)
//
//        genre = GenresItem("Adventure", 12)
//        genres.toMutableList().add(genre)
//
//        genre = GenresItem("Science Fiction", 878)
//        genres.toMutableList().add(genre)
//        result.postValue(
//            DetailMovieResponse(
//                "en",
//                genres,
//                531241,
//                "Peter Parker is unmasked and no longer able to separate his normal life from the high-stakes of being a super-hero. When he asks for help from Doctor Strange the stakes become even more dangerous, forcing him to discover what it truly means to be Spider-Man.",
//                "Spider-Man: No Way Home",
//                "/1g0dhYtq4irTY1GPXvft6k4YLjm.jpg",
//                "2021-12-15",
//                8.4,
//                "Released"
//            )
//        )
//
//        return result
//    }
//
//    override fun getDummyShow(): LiveData<DetailTvShowResponse> {
//        val result = MutableLiveData<DetailTvShowResponse>()
//        val genres: List<GenresItem> = emptyList()
//        val genre = GenresItem("Drama", 18)
//        genres.toMutableList().add(genre)
//
//        result.postValue(
//            DetailTvShowResponse(
//                "en",
//                genres,
//                85552,
//                "2019-06-16",
//                "At Maddy’s birthday party, relationships are celebrated and questioned. Meanwhile, Jules turns to Elliot for advice and Cal takes a trip down memory lane.",
//                "/jtnfNzqZwN4E32FGGxx1YZaBWWf.jpg",
//                "Euphoria",
//                8.4
//            )
//        )
//        return result
//    }
//
//    override fun getDetailMovie(id: String): LiveData<ApiResponse<DetailMovieResponse>> = liveData {
//        emit(ApiResponse.Loading)
//        EspressoIdlingResource.increment()
//        try {
//            val response = apiService.getDetailMovie(id)
//            emit(ApiResponse.Success(response))
//            EspressoIdlingResource.decrement()
//        } catch (e: Exception) {
//            Log.d(TAG, "getDetailMovie: ${e.message.toString()} ")
//            emit(ApiResponse.Error(e.message.toString()))
//            EspressoIdlingResource.decrement()
//        }
//    }
//
//    override fun getDetailTvShow(id: String): LiveData<ApiResponse<DetailTvShowResponse>> = liveData {
//        emit(ApiResponse.Loading)
//        EspressoIdlingResource.increment()
//        try {
//            val response = apiService.getDetailTvShow(id)
//            emit(ApiResponse.Success(response))
//            EspressoIdlingResource.decrement()
//        } catch (e: Exception) {
//            Log.d(TAG, "getDetailTvShow: ${e.message.toString()} ")
//            emit(ApiResponse.Error(e.message.toString()))
//            EspressoIdlingResource.decrement()
//        }
//    }
//
//    override suspend fun saveMovieToFavorite(movie: MovieEntity) = mMovieDao.insertMovie(movie)
//
//    override suspend fun deleteMovieFromFavorite(id: Int) = mMovieDao.deleteMovie(id)
//
//    override fun getFavoriteMovie(sort : String,isMovie : Boolean): LiveData<PagedList<MovieEntity>> {
//        val query = SortUtils.getSortedQuery(sort,isMovie)
//        val config = PagedList.Config.Builder()
//            .setEnablePlaceholders(false)
//            .setInitialLoadSizeHint(4)
//            .setPageSize(4)
//            .build()
//        return LivePagedListBuilder(mMovieDao.getMovies(query), config).build()
//    }
//
//    override fun getFavoriteShow(sort: String, isMovie: Boolean): LiveData<PagedList<MovieEntity>> {
//        val query = SortUtils.getSortedQuery(sort,isMovie)
//        val config = PagedList.Config.Builder()
//            .setEnablePlaceholders(false)
//            .setInitialLoadSizeHint(4)
//            .setPageSize(4)
//            .build()
//        return LivePagedListBuilder(mMovieDao.getShow(query), config).build()
//    }
//
//    override fun getDetailFavoriteMovie(id: Int): MovieEntity {
//        return mMovieDao.getMovieById(id)
//    }
//
//    override fun isMovieSaved(id: Int): LiveData<Boolean> {
//        return mMovieDao.isMovieSaved(id)
//    }

}