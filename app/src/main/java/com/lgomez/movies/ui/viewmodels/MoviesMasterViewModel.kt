package com.lgomez.movies.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.lgomez.movies.core.utils.SingleLiveEvent
import com.lgomez.movies.ui.model.MovieUI
import com.lgomez.movies.ui.navigatorstates.MoviesMasterNavigatorStates
import com.lgomez.movies.ui.viewstates.BaseViewState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MoviesMasterViewModel @Inject constructor() : ViewModel() {
    private val _viewState: MutableLiveData<BaseViewState> = MutableLiveData()
    val viewState: LiveData<BaseViewState> get() = _viewState

    private val _navigation = SingleLiveEvent<MoviesMasterNavigatorStates>()
    val navigation: LiveData<MoviesMasterNavigatorStates> get() = _navigation

    val movie = MovieUI("Spiderman")

    init {
        refreshUI()
    }

    fun goToMoviesDetail(movie: MovieUI) {
        _navigation.value = MoviesMasterNavigatorStates.ToMoviesDetail(movie)
    }

    fun refreshUI() {
        _viewState.value = BaseViewState.Ready
    }


}