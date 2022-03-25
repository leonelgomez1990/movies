package com.lgomez.movies.data.network

import android.util.Log
import com.lgomez.movies.core.utils.MyResult
import com.lgomez.movies.data.network.model.toDetailsMovie
import com.lgomez.movies.data.network.model.toPopularMovie
import com.lgomez.movies.domain.model.AppLanguage
import com.lgomez.movies.domain.model.DetailsMovie
import com.lgomez.movies.domain.model.PopularMovies

private const val API_KEY = "0d4a85fde806c8c22ed4b13ac766d3ff"

class MoviesNetworkDataSource(
    private val moviesProvider: MoviesProvider
) {

    companion object {
        const val TAG = "MoviesNetworkDataSource"
    }

    private var movies: List<PopularMovies> = emptyList()

    suspend fun getPopularMovies(): MyResult<List<PopularMovies>> {
        return try {
            Log.d(TAG, "getPopularMovies:try to get data")
            val apiResponse = moviesProvider.getPopularMovies(API_KEY, AppLanguage.code, 1)

            Log.d(TAG, "getPopularMovies:try to convert object")
            movies = apiResponse?.results.map { it.toPopularMovie() } ?: emptyList()

            Log.d(TAG, "getPopularMovies:success")
            MyResult.Success(movies)
        } catch (e: Exception) {
            Log.e(TAG, "getPopularMovies:failure, Exception thrown: ${e.message}")
            MyResult.Failure(e)
        }
    }

    suspend fun getDetailsMovie(movieId: Int): MyResult<DetailsMovie> {
        return try {
            Log.d(TAG, "getDetailsMovie($movieId):try to get data")
            val apiResponse = moviesProvider.getDetailsMovie(movieId, API_KEY, AppLanguage.code)

            Log.d(TAG, "getDetailsMovie($movieId):try to convert object")
            val movie = apiResponse?.toDetailsMovie()

            Log.d(TAG, "getDetailsMovie($movieId):success")
            MyResult.Success(movie)
        } catch (e: Exception) {
            Log.e(TAG, "getDetailsMovie($movieId):failure, Exception thrown: ${e.message}")
            MyResult.Failure(e)
        }
    }
}