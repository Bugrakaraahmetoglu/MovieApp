package com.bugrakstudios.moviecompose.repo

import androidx.lifecycle.MutableLiveData
import com.bugrakstudios.moviecompose.data.remote.dto.MovieDetailDTO
import com.bugrakstudios.moviecompose.data.remote.dto.MoviesDTO
import com.bugrakstudios.moviecompose.data.remote.dto.Search
import com.bugrakstudios.moviecompose.domain.model.Movie
import com.bugrakstudios.moviecompose.domain.repository.MovieRepository
import java.io.IOException

class FakeRepository : MovieRepository {

    private var shouldReturnNetworkError = false
    private val movies = listOf(
        Search(imdbID = "1", Title = "Test Movie 1", Year = "2021", Type = "movie", Poster = ""),
        Search(imdbID = "2", Title = "Test Movie 2", Year = "2022", Type = "movie", Poster = "")
    )

    private val movieDetails = mapOf(
        "1" to MovieDetailDTO(
            Actors = "Actor 1, Actor 2",
            Awards = "Some Award",
            BoxOffice = "",
            Country = "USA",
            DVD = "",
            Director = "Director 1",
            Genre = "Action",
            Language = "English",
            Metascore = "80",
            Plot = "Some plot",
            Poster = "",
            Production = "",
            Rated = "PG-13",
            Ratings = listOf(),
            Released = "2021",
            Response = "True",
            Runtime = "120 min",
            Title = "Test Movie 1",
            Type = "movie",
            Website = "",
            Writer = "Writer 1",
            Year = "2021",
            imdbID = "1",
            imdbRating = "7.5",
            imdbVotes = "1000"
        )
        // Diğer film detaylarını buraya ekleyebilirsiniz.
    )

    fun setShouldReturnNetworkError(value: Boolean) {
        shouldReturnNetworkError = value
    }

    override suspend fun getMovies(searchQuery: String): MoviesDTO {
        return if (shouldReturnNetworkError) {
            throw IOException("No Internet Connection")
        } else {
            MoviesDTO(Response = "True", Search = movies, totalResults = "2")
        }
    }

    override suspend fun getMovieDetail(imdbId: String): MovieDetailDTO {
        return movieDetails[imdbId] ?: throw Exception("Movie not found")
    }
}
