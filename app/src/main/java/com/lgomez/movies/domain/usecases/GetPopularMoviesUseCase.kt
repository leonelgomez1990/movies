package com.lgomez.movies.domain.usecases

import com.lgomez.movies.core.utils.MyResult
import com.lgomez.movies.domain.model.PopularMovies
import com.lgomez.movies.domain.repositories.MoviesRepository

class GetPopularMoviesUseCase(private val moviesRepository: MoviesRepository) {
    suspend operator fun invoke(): MyResult<List<PopularMovies>> =
        moviesRepository.getPopularMovies()
}
