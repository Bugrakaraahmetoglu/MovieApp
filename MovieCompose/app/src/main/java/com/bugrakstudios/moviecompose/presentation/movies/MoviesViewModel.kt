package com.bugrakstudios.moviecompose.presentation.movies

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bugrakstudios.moviecompose.domain.use_case.get_movies.GetMovieUseCase
import com.bugrakstudios.moviecompose.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class MoviesViewModel @Inject constructor(private val getMovieUseCase: GetMovieUseCase) : ViewModel() {

    private val _state = mutableStateOf<MoviesState>(MoviesState())
    val state : State<MoviesState> = _state

    private var job : Job? = null

    init {
        getMovies(_state.value.searchQuery)
    }

    private fun getMovies(searchQuery: String){
        job?.cancel()

        getMovieUseCase.executeGetMovies(searchQuery).onEach {
            when(it){
                is Resource.Success -> {
                    _state.value = MoviesState(movies = it.data ?: emptyList())
                }
                is Resource.Error -> {
                    _state.value = MoviesState(error = it.message ?: "An unexpected error occurred")
                }
                is Resource.Loading -> {
                    _state.value = MoviesState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }

    fun onEvent(event: MoviesEvent){
        when(event){
            is MoviesEvent.Search -> {
                getMovies(event.searchQuery)
            }
        }
    }
}