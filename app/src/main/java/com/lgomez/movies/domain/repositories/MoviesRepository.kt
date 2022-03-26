package com.lgomez.movies.domain.repositories

import com.lgomez.movies.core.utils.MyResult
import com.lgomez.movies.domain.model.DetailsMovie
import com.lgomez.movies.domain.model.PopularMovies

interface MoviesRepository {
    suspend fun getPopularMovies(page: Int = 1): MyResult<List<PopularMovies>>
    suspend fun getDetailsMovie(movieId: Int): MyResult<DetailsMovie>
    suspend fun getAvailableLanguages(): MyResult<List<String>>
}
