package com.abhiwisesa.moviecatalogue.data.source

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.abhiwisesa.moviecatalogue.data.source.local.entity.MovieEntity
import com.abhiwisesa.moviecatalogue.data.source.local.room.MovieDao
import com.abhiwisesa.moviecatalogue.data.source.remote.RemoteDataSource
import com.abhiwisesa.moviecatalogue.data.source.remote.network.ApiService
import com.abhiwisesa.moviecatalogue.utils.DataDummy
import com.abhiwisesa.moviecatalogue.utils.PagedListUtil
import com.abhiwisesa.moviecatalogue.utils.SortUtils
import com.nhaarman.mockitokotlin2.verify
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock

class MovieRepositoryTest{

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private val apiService = mock(ApiService::class.java)
    private val remote = mock(RemoteDataSource::class.java)
    private val movieDao = mock(MovieDao::class.java)
    private val movieRepository = FakeMovieRepository(apiService,remote,movieDao)

    private val dummyListTvShow = DataDummy.generateDummyTvShow()
    private val dummyListMovie = DataDummy.generateDummyMovies()
    private val dummyTvShow = DataDummy.generateDummyDetailShow()
    private val dummyMovie = DataDummy.generateDummyDetailMovie()
    private val dummyMovieEntity = DataDummy.generateDummyMovieEntity()
    private val dummyListMovieEntity = DataDummy.generateListMovieEntity()

    private val liveDataBoolean = MutableLiveData<Boolean>()

    @Before
    fun init(){
        liveDataBoolean.postValue(true)
    }

    @Test
    fun getListTvShow() {
        `when`(movieRepository.getAllTvShow()).thenReturn(dummyListTvShow)
        val movieEntities = movieRepository.getAllTvShow()
        verify<RemoteDataSource>(remote).getListTvShow()
        assertNotNull(movieEntities)
        assertEquals(dummyListTvShow.size, movieEntities.size)
    }

    @Test
    fun getListMoive() {
        `when`(movieRepository.getAllMovie()).thenReturn(dummyListMovie)
        val movieEntities = movieRepository.getAllMovie()
        verify<RemoteDataSource>(remote).getListMovie()
        assertNotNull(movieEntities)
        assertEquals(dummyListTvShow.size, movieEntities.size)
    }

    @Test
    fun getTvShow() {
        `when`(movieRepository.getTvShow()).thenReturn(dummyTvShow)
        val movieEntities = movieRepository.getTvShow()
        verify<RemoteDataSource>(remote).getTvShow()
        assertNotNull(movieEntities)
        assertEquals(dummyTvShow.id, movieEntities?.id)
        assertEquals(dummyTvShow.originalLanguage, movieEntities?.originalLanguage)
        assertEquals(dummyTvShow.genres, movieEntities?.genres)
        assertEquals(dummyTvShow.overview, movieEntities?.overview)
        assertEquals(dummyTvShow.originalName, movieEntities?.originalName)
        assertEquals(dummyTvShow.posterPath, movieEntities?.posterPath)
        assertEquals(dummyTvShow.firstAirDate, movieEntities?.firstAirDate)
        movieEntities?.voteAverage?.let { assertEquals(dummyTvShow.voteAverage, it,0.1) }
    }
    @Test
    fun getMovie() {
        `when`(movieRepository.getMovie()).thenReturn(dummyMovie)
        val movieEntities = movieRepository.getMovie()
        verify<RemoteDataSource>(remote).getMovie()
        assertNotNull(movieEntities)
        assertEquals(dummyMovie.id, movieEntities?.id)
        assertEquals(dummyMovie.originalLanguage, movieEntities?.originalLanguage)
        assertEquals(dummyMovie.genres, movieEntities?.genres)
        assertEquals(dummyMovie.overview, movieEntities?.overview)
        assertEquals(dummyMovie.originalTitle, movieEntities?.title)
        assertEquals(dummyMovie.posterPath, movieEntities?.posterPath)
        assertEquals(dummyMovie.releaseDate, movieEntities?.releaseDate)
        assertEquals(dummyMovie.status, movieEntities?.status)
        movieEntities?.voteAverage?.let { assertEquals(dummyMovie.voteAverage, it,0.1) }

    }

    @Test
    fun getDetailFavoriteMovie(){
        val id = 1
        `when`(movieDao.getMovieById(id)).thenReturn(dummyMovieEntity)
        val movieEntities = movieRepository.getDetailFavoriteMovie(id)
        verify(movieDao).getMovieById(id)
        assertNotNull(movieEntities)
        assertEquals(dummyMovieEntity.id,movieEntities.id)
        assertEquals(dummyMovieEntity.posterPath,movieEntities.posterPath)
        assertEquals(dummyMovieEntity.dateAdded,movieEntities.dateAdded)
        assertEquals(dummyMovieEntity.description,movieEntities.description)
        assertEquals(dummyMovieEntity.genres,movieEntities.genres)
        assertEquals(dummyMovieEntity.title,movieEntities.title)
        assertEquals(dummyMovieEntity.dateAdded,movieEntities.dateAdded)
        assertEquals(dummyMovieEntity.releaseDate,movieEntities.releaseDate)
        assertEquals(dummyMovieEntity.voteAverage,movieEntities.voteAverage,0.1)
    }

    @Test
    fun isMovieSaved(){
        val id = 1
        `when`(movieDao.isMovieSaved(id)).thenReturn(liveDataBoolean)
        val isSaved = movieRepository.isMovieSaved(id)
        verify(movieDao).isMovieSaved(id)
        assertNotNull(isSaved)
        assertEquals(liveDataBoolean,isSaved)
    }

    @Test
    fun getFavoriteMovie(){
        val dataSourceFactory = mock(DataSource.Factory::class.java) as DataSource.Factory<Int, MovieEntity>
        `when`(movieDao.getFavoriteMovie()).thenReturn(dataSourceFactory)
        movieRepository.getFavoriteMovie(SortUtils.NEWEST,true)

        val movie = PagedListUtil.mockPagedList(DataDummy.generateListMovieEntity())
        verify(movieDao).getFavoriteMovie()

        assertNotNull(movie)
        assertEquals(dummyListMovieEntity.size.toLong(), movie.size.toLong())
    }

    @Test
    fun getFavoriteShow(){
        val dataSourceFactory = mock(DataSource.Factory::class.java) as DataSource.Factory<Int, MovieEntity>
        `when`(movieDao.getFavoriteShow()).thenReturn(dataSourceFactory)
        movieRepository.getFavoriteShow(SortUtils.NEWEST,false)

        val movie = PagedListUtil.mockPagedList(DataDummy.generateListMovieEntity())
        verify(movieDao).getFavoriteShow()

        assertNotNull(movie)
        assertEquals(dummyListMovieEntity.size.toLong(), movie.size.toLong())
    }


}