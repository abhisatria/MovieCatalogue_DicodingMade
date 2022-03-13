//package com.abhiwisesa.moviecatalogue.ui.detail
//
//import androidx.arch.core.executor.testing.InstantTaskExecutorRule
//import androidx.lifecycle.MutableLiveData
//import androidx.lifecycle.Observer
//import com.abhiwisesa.core.data.MovieRepository
//import com.abhiwisesa.moviecatalogue.core.data.source.remote.response.MovieResponse
//import com.abhiwisesa.moviecatalogue.core.data.source.remote.response.TvShowResponse
//import com.abhiwisesa.moviecatalogue.core.utils.DataDummy
//import com.abhiwisesa.moviecatalogue.detail.DetailViewModel
//import com.nhaarman.mockitokotlin2.verify
//import org.junit.Assert.*
//import org.junit.Before
//import org.junit.Rule
//
//import org.junit.Test
//import org.junit.runner.RunWith
//import org.mockito.Mock
//import org.mockito.Mockito.`when`
//import org.mockito.junit.MockitoJUnitRunner
//
//@RunWith(MockitoJUnitRunner::class)
class DetailViewModelTest {
//    private lateinit var viewModel: DetailViewModel
//
//    @get:Rule
//    var instantTaskExecutorRule = InstantTaskExecutorRule()
//
//    @Mock
//    private lateinit var movieRepository: com.abhiwisesa.core.data.MovieRepository
//
//    @Mock
//    private lateinit var movieObserver: Observer<MovieResponse>
//
//    @Mock
//    private lateinit var showObserver: Observer<TvShowResponse>
//
//    private val dummyDetail = DataDummy.generateDummyDetailMovie()
//    private val dummyDetailShow = DataDummy.generateDummyDetailShow()
//    private val movieId = dummyDetail.id.toString()
//    private val showId = dummyDetailShow.id.toString()
//
//
//    @Before
//    fun setUp() {
//        viewModel = DetailViewModel(movieRepository)
//        viewModel.setSelectedMovie(movieId)
//        viewModel.setSelectedShow(showId)
//    }
//
//    @Test
//    fun getMovie() {
//        val movie = MutableLiveData<MovieResponse>()
//        movie.value = dummyDetail
//
//        `when`(movieRepository.getDummyMovie()).thenReturn(movie)
//
//        val movieEntity = viewModel.getDummyMovie().value as MovieResponse
//        verify(movieRepository).getDummyMovie()
//
//        assertNotNull(movieEntity)
//        assertEquals(dummyDetail.id, movieEntity.id)
//        assertEquals(dummyDetail.originalLanguage, movieEntity.originalLanguage)
//        assertEquals(dummyDetail.genres, movieEntity.genres)
//        assertEquals(dummyDetail.overview, movieEntity.overview)
//        assertEquals(dummyDetail.originalTitle, movieEntity.title)
//        assertEquals(dummyDetail.posterPath, movieEntity.posterPath)
//        assertEquals(dummyDetail.releaseDate, movieEntity.releaseDate)
//        assertEquals(dummyDetail.voteAverage, movieEntity.voteAverage, 0.1)
//        assertEquals(dummyDetail.status, movieEntity.status)
//
//        viewModel.getDummyMovie().observeForever(movieObserver)
//        verify(movieObserver).onChanged(dummyDetail)
//    }
//
//    @Test
//    fun getShow() {
//        val show = MutableLiveData<TvShowResponse>()
//        show.value = dummyDetailShow
//
//        `when`(movieRepository.getDummyShow()).thenReturn(show)
//
//        viewModel.setSelectedShow(dummyDetailShow.id.toString())
//        val movieEntity = viewModel.getDummyShow().value as TvShowResponse
//        verify(movieRepository).getDummyShow()
//        assertNotNull(movieEntity)
//        assertEquals(dummyDetailShow.id, movieEntity.id)
//        assertEquals(dummyDetailShow.originalLanguage, movieEntity.originalLanguage)
//        assertEquals(dummyDetailShow.genres, movieEntity.genres)
//        assertEquals(dummyDetailShow.overview, movieEntity.overview)
//        assertEquals(dummyDetailShow.originalName, movieEntity.originalName)
//        assertEquals(dummyDetailShow.posterPath, movieEntity.posterPath)
//        assertEquals(dummyDetailShow.firstAirDate, movieEntity.firstAirDate)
//        assertEquals(dummyDetailShow.voteAverage, movieEntity.voteAverage, 0.1)
//
//        viewModel.getDummyShow().observeForever(showObserver)
//        verify(showObserver).onChanged(dummyDetailShow)
//    }
//
}