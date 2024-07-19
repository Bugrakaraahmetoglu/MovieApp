package com.bugrakstudios.moviecompose.viewModel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.bugrakstudios.moviecompose.domain.use_case.get_movies.GetMovieUseCase
import com.bugrakstudios.moviecompose.presentation.movies.MoviesEvent
import com.bugrakstudios.moviecompose.presentation.movies.MoviesViewModel
import com.bugrakstudios.moviecompose.repo.FakeRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test



@ExperimentalCoroutinesApi
class MoviesViewModelTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private val testDispatcher = StandardTestDispatcher()
    private lateinit var fakeRepository: FakeRepository
    private lateinit var getMovieUseCase: GetMovieUseCase
    private lateinit var viewModel: MoviesViewModel

    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
        fakeRepository = FakeRepository()
        getMovieUseCase = GetMovieUseCase(fakeRepository)
        viewModel = MoviesViewModel(getMovieUseCase)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `getMovies should emit movies list when repository returns success`() = runTest {
        // Act
        viewModel.onEvent(MoviesEvent.Search("query"))

        // Assert
        assert(viewModel.state.value.movies.isNotEmpty())
        assert(!viewModel.state.value.isLoading)
    }

    @Test
    fun `getMovies should emit error when repository throws exception`() = runTest {
        // Arrange
        fakeRepository.setShouldReturnNetworkError(true)

        // Act
        viewModel.onEvent(MoviesEvent.Search("query"))

        // Assert
        assert(viewModel.state.value.error == "No Internet Connection")
        assert(!viewModel.state.value.isLoading)
    }
}