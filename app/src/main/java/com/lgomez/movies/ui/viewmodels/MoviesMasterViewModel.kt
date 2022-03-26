package com.lgomez.movies.ui.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lgomez.movies.core.utils.MyResult
import com.lgomez.movies.core.utils.SingleLiveEvent
import com.lgomez.movies.domain.model.PopularMovies
import com.lgomez.movies.domain.usecases.ConfigureLanguage
import com.lgomez.movies.domain.usecases.GetPopularMoviesUseCase
import com.lgomez.movies.ui.model.MovieUI
import com.lgomez.movies.ui.navigatorstates.MoviesMasterNavigatorStates
import com.lgomez.movies.ui.viewstates.BaseViewState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MoviesMasterViewModel @Inject constructor(
    val getPopularMoviesUseCase: GetPopularMoviesUseCase,
    val configureLanguage: ConfigureLanguage
) : ViewModel() {

    companion object {
        const val TAG = "MoviesMasterViewModel"
        const val PAGE_THRESHOLD = 2
    }
    private val _viewState: MutableLiveData<BaseViewState> = MutableLiveData()
    val viewState: LiveData<BaseViewState> get() = _viewState

    private val _navigation = SingleLiveEvent<MoviesMasterNavigatorStates>()
    val navigation: LiveData<MoviesMasterNavigatorStates> get() = _navigation

    private val _movies: MutableLiveData<MutableList<PopularMovies>> = MutableLiveData()
    val movies: LiveData<MutableList<PopularMovies>> get() = _movies

    init {
        setLanguage()
        refreshUI()
    }

    fun setReadyState() {
        _viewState.value = BaseViewState.Ready
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
                    _movies.value = result.data.toMutableList()
                    _viewState.value = BaseViewState.Ready
                }
            }
        }
    }

    private fun setLanguage() {
        viewModelScope.launch {
            configureLanguage()
        }
    }

    fun notifyLastSeen(last: Int, total: Int) {
        if ((last + PAGE_THRESHOLD >= total) && (viewState.value != BaseViewState.Loading)) {
            Log.d(TAG, "Load new page. last=$last, total=$total")
            viewModelScope.launch {
                _viewState.value = BaseViewState.Loading
                when (val result = getPopularMoviesUseCase.checkNewPage(total)) {
                    is MyResult.Failure -> {
                        _viewState.value = BaseViewState.Failure(result.exception)
                    }
                    is MyResult.Success -> {
                        _movies.value?.addAll(result.data.toMutableList())
                        _movies.value = movies.value
                        _viewState.value = BaseViewState.Ready
                    }
                }
            }
        }
    }
}