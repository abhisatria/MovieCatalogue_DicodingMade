package com.abhiwisesa.moviecatalogue.ui.movies

//import androidx.arch.core.executor.testing.InstantTaskExecutorRule
//import androidx.lifecycle.MutableLiveData
//import androidx.lifecycle.Observer
//import com.abhiwisesa.core.data.MovieRepository
//import com.abhiwisesa.moviecatalogue.data.source.remote.response.MovieInList
//import com.abhiwisesa.moviecatalogue.core.utils.DataDummy
//import com.abhiwisesa.moviecatalogue.movies.MovieViewModel
//import com.nhaarman.mockitokotlin2.verify
//import org.junit.Assert.*
//import org.junit.Before
//import org.junit.Rule
//import org.junit.Test
//
//import org.junit.runner.RunWith
//import org.mockito.Mock
//import org.mockito.Mockito.`when`
//import org.mockito.junit.MockitoJUnitRunner

//@RunWith(MockitoJUnitRunner::class)
class MovieViewModelTest {
//
//    private lateinit var viewModel: MovieViewModel
//
//    @get:Rule
//    var instantTaskExecutorRule = InstantTaskExecutorRule()
//
//    @Mock
//    private lateinit var movieRepository: com.abhiwisesa.core.data.MovieRepository
//
//    @Mock
//    private lateinit var observer: Observer<List<MovieInList>>
//
//
//    @Before
//    fun setUp() {
//        viewModel = MovieViewModel(movieRepository)
//    }
//
//    @Test
//    fun getListMovies(){
//        val dummyMovies = DataDummy.generateDummyMovies()
//        val listMovies = MutableLiveData<List<MovieInList>>()
//        listMovies.value = dummyMovies
//
//        `when`(movieRepository.getDummyAllMovie()).thenReturn(listMovies)
//        val movieEntities = viewModel.getMovies()
//
//        verify(movieRepository).getDummyAllMovie()
//
//        assertNotNull(movieEntities)
//        assertEquals(20,movieEntities.value?.size)
//
//        viewModel.getMovies().observeForever(observer)
//        verify(observer).onChanged(dummyMovies)
//    }

}