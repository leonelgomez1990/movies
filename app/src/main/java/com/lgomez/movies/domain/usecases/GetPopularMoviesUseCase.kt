package com.lgomez.movies.domain.usecases

import com.lgomez.movies.core.utils.MyResult
import com.lgomez.movies.domain.model.PopularMovies
import com.lgomez.movies.domain.repositories.MoviesRepository

class GetPopularMoviesUseCase(private val moviesRepository: MoviesRepository) {
    companion object {
        const val PAGE_SIZE = 20
    }
    suspend operator fun invoke(): MyResult<List<PopularMovies>> =
        moviesRepository.getPopularMovies()

    suspend fun checkNewPage(currentSize: Int) : MyResult<List<PopularMovies>> {
        val page = currentSize / PAGE_SIZE + 1
        return moviesRepository.getPopularMovies(page)
    }
}
