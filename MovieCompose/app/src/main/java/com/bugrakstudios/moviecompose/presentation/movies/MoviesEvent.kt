package com.bugrakstudios.moviecompose.presentation.movies

sealed class MoviesEvent {
    data class Search(val searchQuery: String) : MoviesEvent()
}