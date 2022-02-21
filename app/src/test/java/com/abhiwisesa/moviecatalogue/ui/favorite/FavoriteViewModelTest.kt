package com.abhiwisesa.moviecatalogue.ui.favorite

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import com.abhiwisesa.moviecatalogue.data.source.MovieRepository
import com.abhiwisesa.moviecatalogue.data.source.local.entity.MovieEntity
import com.abhiwisesa.moviecatalogue.utils.SortUtils
import com.nhaarman.mockitokotlin2.verify
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class FavoriteViewModelTest{
    private lateinit var viewModel: FavoriteViewModel

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var movieRepository: MovieRepository

    @Mock
    private lateinit var pagedList: PagedList<MovieEntity>

    @Mock
    private lateinit var movieObserver: Observer<PagedList<MovieEntity>>


    @Before
    fun setUp() {
        viewModel = FavoriteViewModel(movieRepository)
    }

    @Test
    fun getFavoriteShow() {
        val dummyShow = pagedList
        `when`(dummyShow.size).thenReturn(5)
        val show = MutableLiveData<PagedList<MovieEntity>>()
        show.value = dummyShow

        `when`(movieRepository.getFavoriteShow(SortUtils.NEWEST,false)).thenReturn(show)

        val movieEntity = viewModel.getFavoriteShow(SortUtils.NEWEST).value
        verify(movieRepository).getFavoriteShow(SortUtils.NEWEST,false)
        assertNotNull(movieEntity)
        assertEquals(5,movieEntity?.size)


        viewModel.getFavoriteShow(SortUtils.NEWEST).observeForever(movieObserver)
        verify(movieObserver).onChanged(dummyShow)
    }

    @Test
    fun getFavoriteMovie() {
        val dummyMovie = pagedList
        `when`(dummyMovie.size).thenReturn(5)
        val show = MutableLiveData<PagedList<MovieEntity>>()
        show.value = dummyMovie

        `when`(movieRepository.getFavoriteMovie(SortUtils.NEWEST,true)).thenReturn(show)

        val movieEntity = viewModel.getFavoriteMovie(SortUtils.NEWEST).value
        verify(movieRepository).getFavoriteMovie(SortUtils.NEWEST,true)
        assertNotNull(movieEntity)
        assertEquals(5,movieEntity?.size)


        viewModel.getFavoriteMovie(SortUtils.NEWEST).observeForever(movieObserver)
        verify(movieObserver).onChanged(dummyMovie)
    }

}