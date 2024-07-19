package com.bugrakstudios.moviecompose.domain.use_case.get_movies

import com.bugrakstudios.moviecompose.data.remote.dto.toMovieList
import com.bugrakstudios.moviecompose.domain.model.Movie
import com.bugrakstudios.moviecompose.domain.repository.MovieRepository
import com.bugrakstudios.moviecompose.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.IOError
import javax.inject.Inject

class GetMovieUseCase @Inject constructor(private val repository: MovieRepository) {

    fun executeGetMovies(SearchQuery: String) : Flow<Resource<List<Movie>>> = flow {
        try {
            emit(Resource.Loading())
            val movieList = repository.getMovies(SearchQuery)
            if (movieList.Response.equals("True")){
                emit(Resource.Success(movieList.toMovieList()))
            }else{
                emit(Resource.Error("No movie found!!!"))
            }
        }catch (e : IOError){
            emit(Resource.Error("No Internet Connection"))
        }
    }
}