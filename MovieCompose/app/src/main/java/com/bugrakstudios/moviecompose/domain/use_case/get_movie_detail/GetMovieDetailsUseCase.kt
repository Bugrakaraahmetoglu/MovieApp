package com.bugrakstudios.moviecompose.domain.use_case.get_movie_detail

import com.bugrakstudios.moviecompose.data.remote.dto.toMovieDetail
import com.bugrakstudios.moviecompose.data.remote.dto.toMovieList
import com.bugrakstudios.moviecompose.domain.model.Movie
import com.bugrakstudios.moviecompose.domain.model.MovieDetail
import com.bugrakstudios.moviecompose.domain.repository.MovieRepository
import com.bugrakstudios.moviecompose.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.IOError
import javax.inject.Inject

class GetMovieDetailsUseCase @Inject constructor(private val repository: MovieRepository) {

    fun executeGetMovieDetails(imdbId: String) : Flow<Resource<MovieDetail>> = flow {
        try {
            emit(Resource.Loading())
            val movieList = repository.getMovieDetail(imdbId)
             emit(Resource.Success(movieList.toMovieDetail()))

        }  catch (e : IOError){
            emit(Resource.Error("No Internet Connection"))
        }
    }
}