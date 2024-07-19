package com.bugrakstudios.moviecompose.domain.repository

import com.bugrakstudios.moviecompose.data.remote.dto.MovieDetailDTO
import com.bugrakstudios.moviecompose.data.remote.dto.MoviesDTO

interface MovieRepository {

    suspend fun getMovies(searchQuery: String): MoviesDTO
    suspend fun getMovieDetail(imdbId: String): MovieDetailDTO
}