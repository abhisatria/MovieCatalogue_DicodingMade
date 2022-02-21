package com.abhiwisesa.moviecatalogue.ui.tv_show

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.abhiwisesa.moviecatalogue.data.source.MovieRepository
import com.abhiwisesa.moviecatalogue.data.source.remote.response.ResultsItem
import com.abhiwisesa.moviecatalogue.utils.DataDummy
import com.nhaarman.mockitokotlin2.verify
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule

import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class TvShowViewModelTest {
    private lateinit var viewModel: TvShowViewModel

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var movieRepository: MovieRepository

    @Mock
    private lateinit var observer: Observer<List<ResultsItem>>

    @Before
    fun setUp() {
        viewModel = TvShowViewModel(movieRepository)
    }


    @Test
    fun getListTvShow(){
        val dummyShows = DataDummy.generateDummyTvShow()
        val listShows = MutableLiveData<List<ResultsItem>>()
        listShows.value = dummyShows

        Mockito.`when`(movieRepository.getDummyAllTvShow()).thenReturn(listShows)
        val showEntities = viewModel.getDummyTvShow()

        verify(movieRepository).getDummyAllTvShow()

        assertNotNull(showEntities)
        assertEquals(20,showEntities.value?.size)

        viewModel.getDummyTvShow().observeForever(observer)
        verify(observer).onChanged(dummyShows)
    }
}