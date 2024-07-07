package com.example.movieapp.presentation.viewmodel


import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.example.movieapp.common.Resource
import com.example.movieapp.domain.model.Movie
import com.example.movieapp.domain.usecase.MovieUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.verify
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class MovieViewModelTest {

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var movieUseCase: MovieUseCase
    private lateinit var movieViewModel: MovieViewModel
    private val dummyMovies = DataDummy.generateDummyMovies()

    @Before
    fun setUp() {
        Dispatchers.setMain(Dispatchers.Unconfined)
        movieViewModel = MovieViewModel(movieUseCase)
    }

    @After

    fun tearDown() {
        Dispatchers.resetMain()
    }
    @Test
    fun `when Get All Movies Should Not Null and Return Success`() = runTest {
        val observer = Observer<Resource<List<Movie>>> {}
        try {
            val expectedMovies = Resource.Success(dummyMovies)
            `when`(movieUseCase.getAllMovie()).thenReturn(flowOf(expectedMovies))

            movieViewModel.movie.observeForever(observer)
            movieViewModel.getAllMovie()

            verify(movieUseCase).getAllMovie()
            assert(movieViewModel.movie.value != null)
            assert(movieViewModel.movie.value == expectedMovies)
        } finally {
            movieViewModel.movie.removeObserver(observer)
        }
    }

    @Test
    fun`when Get All Movies failed`() = runTest {
        val observer = Observer<Resource<List<Movie>>>{}
        try{
            val failedMovies = Resource.Error("Error to get data",dummyMovies)
            `when`(movieUseCase.getAllMovie()).thenReturn(flowOf(failedMovies))

            movieViewModel.movie.observeForever(observer)
            movieViewModel.getAllMovie()

            verify(movieUseCase).getAllMovie()
            assert(movieViewModel.movie.value != null)

            assert(movieViewModel.movie.value == failedMovies)
        } finally {
            movieViewModel.movie.removeObserver(observer)
        }

        }
    }
