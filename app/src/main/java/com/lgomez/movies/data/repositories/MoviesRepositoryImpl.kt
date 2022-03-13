package com.lgomez.movies.data.repositories

import com.lgomez.movies.core.utils.MyResult
import com.lgomez.movies.domain.model.DetailsMovie
import com.lgomez.movies.domain.model.PopularMovies
import com.lgomez.movies.domain.repositories.MoviesRepository

class MoviesRepositoryImpl : MoviesRepository {
    override suspend fun getPopularMovies(): MyResult<ArrayList<PopularMovies>> {
        return MyResult.Success(arrayListOf())
    }

    override suspend fun getDetailsMovie(movieId: Int): MyResult<DetailsMovie> {
        return MyResult.Success(DetailsMovie(1, "Batman", "", listOf(),"",0,""))
    }
}