package com.bugrakstudios.moviecompose.data.repository

import com.bugrakstudios.moviecompose.data.remote.MovieAPI
import com.bugrakstudios.moviecompose.data.remote.dto.MovieDetailDTO
import com.bugrakstudios.moviecompose.data.remote.dto.MoviesDTO
import com.bugrakstudios.moviecompose.domain.repository.MovieRepository
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(private val api : MovieAPI): MovieRepository {

    override suspend fun getMovies(searchQuery: String): MoviesDTO {
        return api.getMovies(searchQuery)
    }

    override suspend fun getMovieDetail(imdbId: String): MovieDetailDTO {
        return api.getMoviDetail(imdbId)
    }
}