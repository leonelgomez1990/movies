package com.lgomez.movies.data.repositories

import com.lgomez.movies.core.utils.MyResult
import com.lgomez.movies.domain.model.DetailsMovie
import com.lgomez.movies.domain.model.PopularMovies
import com.lgomez.movies.domain.repositories.MoviesRepository

class MoviesRepositoryImpl : MoviesRepository {
    override suspend fun getPopularMovies(): MyResult<ArrayList<PopularMovies>> {
        var movieList = arrayListOf<PopularMovies>()
        movieList.add(
            PopularMovies(
                634649,
                "Spider-Man: No Way Home",
                "https://image.tmdb.org/t/p/w500/iQFcwSGbZXMkeyKrxbPnwnRo5fl.jpg"
            )
        )
        movieList.add(
            PopularMovies(
                414906,
                "The Batman",
                "https://image.tmdb.org/t/p/w500/74xTEgt7R36Fpooo50r9T25onhq.jpg"
            )
        )
        return MyResult.Success(movieList)
    }

    override suspend fun getDetailsMovie(movieId: Int): MyResult<DetailsMovie> {
        return MyResult.Success(DetailsMovie(1, "Batman", "", listOf(), "", 0, ""))
    }
}