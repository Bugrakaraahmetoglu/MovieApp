package com.bugrakstudios.moviecompose.data.remote

import com.bugrakstudios.moviecompose.data.remote.dto.MovieDetailDTO
import com.bugrakstudios.moviecompose.data.remote.dto.MoviesDTO
import com.bugrakstudios.moviecompose.util.Constants
import com.bugrakstudios.moviecompose.util.Constants.API_KEY
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieAPI {

    @GET(".")
    suspend fun getMovies(
        @Query("s") searchQuery: String,
        @Query("apikey") apiKey: String = API_KEY
    ) : MoviesDTO

    @GET(".")
    suspend fun getMoviDetail(
        @Query("i") imdbID: String,
        @Query("apikey") apiKey: String = API_KEY
    ) : MovieDetailDTO
}