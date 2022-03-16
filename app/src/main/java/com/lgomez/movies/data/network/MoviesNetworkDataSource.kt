package com.lgomez.movies.data.network

import com.lgomez.movies.core.utils.MyResult
import com.lgomez.movies.data.network.model.toPopularMovie
import com.lgomez.movies.domain.model.PopularMovies

class MoviesNetworkDataSource(
    private val moviesProvider: MoviesProvider
) {
    private var movies: List<PopularMovies> = emptyList()

    suspend fun getPopularMovies(): MyResult<List<PopularMovies>> {
        return try {
            val apiResponse = moviesProvider.getPopularMovies()

            movies = apiResponse?.results.map { it.toPopularMovie() } ?: emptyList()

            MyResult.Success(movies)
        } catch (e: Exception) {
            MyResult.Failure(e)
        }
    }
}