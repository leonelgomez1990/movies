package com.lgomez.movies.domain.repositories

import com.lgomez.movies.core.utils.MyResult
import com.lgomez.movies.domain.model.DetailsMovie
import com.lgomez.movies.domain.model.PopularMovies

interface MoviesRepository {
    suspend fun getPopularMovies(): MyResult<ArrayList<PopularMovies>>
    suspend fun getDetailsMovie(movieId: Int): MyResult<DetailsMovie>
}
