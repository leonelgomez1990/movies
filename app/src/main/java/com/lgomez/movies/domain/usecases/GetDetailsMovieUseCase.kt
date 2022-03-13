package com.lgomez.movies.domain.usecases

import com.lgomez.movies.core.utils.MyResult
import com.lgomez.movies.domain.model.DetailsMovie
import com.lgomez.movies.domain.repositories.MoviesRepository

class GetDetailsMovieUseCase(private val moviesRepository: MoviesRepository) {
    suspend operator fun invoke(movieId: Int): MyResult<DetailsMovie> =
        moviesRepository.getDetailsMovie(movieId)
}
