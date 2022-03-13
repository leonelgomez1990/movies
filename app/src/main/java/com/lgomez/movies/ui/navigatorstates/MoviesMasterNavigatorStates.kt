package com.lgomez.movies.ui.navigatorstates

import com.lgomez.movies.ui.model.MovieUI

sealed class MoviesMasterNavigatorStates {
    data class ToMoviesDetail(val movie: MovieUI) : MoviesMasterNavigatorStates()
}
