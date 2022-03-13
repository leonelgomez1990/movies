package com.lgomez.movies.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lgomez.movies.core.utils.MyResult
import com.lgomez.movies.core.utils.SingleLiveEvent
import com.lgomez.movies.domain.usecases.GetPopularMoviesUseCase
import com.lgomez.movies.ui.model.MovieUI
import com.lgomez.movies.ui.navigatorstates.MoviesMasterNavigatorStates
import com.lgomez.movies.ui.viewstates.BaseViewState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MoviesMasterViewModel @Inject constructor(
    val getPopularMoviesUseCase: GetPopularMoviesUseCase
) : ViewModel() {
    private val _viewState: MutableLiveData<BaseViewState> = MutableLiveData()
    val viewState: LiveData<BaseViewState> get() = _viewState

    private val _navigation = SingleLiveEvent<MoviesMasterNavigatorStates>()
    val navigation: LiveData<MoviesMasterNavigatorStates> get() = _navigation

    val movie = MovieUI(id = 1)

    init {
        refreshUI()
    }

    fun goToMoviesDetail(movie: MovieUI) {
        _navigation.value = MoviesMasterNavigatorStates.ToMoviesDetail(movie)
    }

    fun refreshUI() {
        getMovies()
    }

    private fun getMovies() {
        viewModelScope.launch {
            _viewState.value = BaseViewState.Loading
            when (val result = getPopularMoviesUseCase()) {
                is MyResult.Failure -> {
                    _viewState.value = BaseViewState.Failure(result.exception)
                }
                is MyResult.Success -> {
                    movie.title = "Spiderman"
                    _viewState.value = BaseViewState.Ready
                }
            }
        }
    }


}