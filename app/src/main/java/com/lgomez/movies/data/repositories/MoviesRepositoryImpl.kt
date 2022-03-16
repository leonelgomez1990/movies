package com.lgomez.movies.data.repositories

import com.lgomez.movies.core.utils.MyResult
import com.lgomez.movies.data.network.MoviesNetworkDataSource
import com.lgomez.movies.domain.model.DetailsMovie
import com.lgomez.movies.domain.model.PopularMovies
import com.lgomez.movies.domain.repositories.MoviesRepository

class MoviesRepositoryImpl(
    private val moviesNetworkDataSource: MoviesNetworkDataSource
) : MoviesRepository {
    override suspend fun getPopularMovies(): MyResult<List<PopularMovies>> {
        return moviesNetworkDataSource.getPopularMovies()
    }

    override suspend fun getDetailsMovie(movieId: Int): MyResult<DetailsMovie> {
        return MyResult.Success(DetailsMovie(1, "Batman", "", listOf(), "", 0, ""))
    }
}