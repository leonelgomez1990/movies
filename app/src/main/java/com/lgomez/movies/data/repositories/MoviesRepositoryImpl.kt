package com.lgomez.movies.data.repositories

import com.lgomez.movies.core.utils.MyResult
import com.lgomez.movies.data.network.MoviesNetworkDataSource
import com.lgomez.movies.domain.model.DetailsMovie
import com.lgomez.movies.domain.model.PopularMovies
import com.lgomez.movies.domain.repositories.MoviesRepository

class MoviesRepositoryImpl(
    private val moviesNetworkDataSource: MoviesNetworkDataSource
) : MoviesRepository {
    override suspend fun getPopularMovies(page: Int): MyResult<List<PopularMovies>> {
        return moviesNetworkDataSource.getPopularMovies(page)
    }

    override suspend fun getDetailsMovie(movieId: Int): MyResult<DetailsMovie> {
        return moviesNetworkDataSource.getDetailsMovie(movieId)
    }

    override suspend fun getAvailableLanguages(): MyResult<List<String>> {
        return moviesNetworkDataSource.getPrimaryTranslations()
    }
}