package com.bugrakstudios.moviecompose.presentation.movies

import com.bugrakstudios.moviecompose.domain.model.Movie

data class MoviesState(
    val isLoading: Boolean = false,
    val movies: List<Movie> = emptyList(),
    val error: String = "",
    val searchQuery: String = "batman"
)
