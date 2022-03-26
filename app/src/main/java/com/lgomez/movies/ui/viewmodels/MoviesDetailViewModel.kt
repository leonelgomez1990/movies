package com.lgomez.movies.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lgomez.movies.core.utils.MyResult
import com.lgomez.movies.domain.usecases.GetDetailsMovieUseCase
import com.lgomez.movies.ui.model.MovieUI
import com.lgomez.movies.ui.model.toMovieUI
import com.lgomez.movies.ui.viewstates.BaseViewState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MoviesDetailViewModel @Inject constructor(
    val getDetailsMovieUseCase: GetDetailsMovieUseCase
) : ViewModel() {
    private val _viewState: MutableLiveData<BaseViewState> = MutableLiveData()
    val viewState: LiveData<BaseViewState> get() = _viewState

    private var _movie: MovieUI = MovieUI()
    val movie: MovieUI get() = _movie

    init {
    }

    fun setReadyState() {
        _viewState.value = BaseViewState.Ready
    }

    fun refreshUI() {
        getDetailsMovie(movie.id)
    }

    fun setMovie(newMovie: MovieUI) {
        _movie = newMovie
        getDetailsMovie(newMovie.id)
    }

    private fun getDetailsMovie(movieId: Int) {
        viewModelScope.launch {
            _viewState.value = BaseViewState.Loading
            when (val result = getDetailsMovieUseCase(movieId)) {
                is MyResult.Failure -> {
                    _viewState.value = BaseViewState.Failure(result.exception)
                }
                is MyResult.Success -> {
                    _movie = result.data.toMovieUI()
                    _viewState.value = BaseViewState.Ready
                }
            }
        }
    }

}