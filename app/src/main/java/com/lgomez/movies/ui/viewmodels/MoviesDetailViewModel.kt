package com.lgomez.movies.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.lgomez.movies.ui.viewstates.BaseViewState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MoviesDetailViewModel @Inject constructor() : ViewModel() {
    private val _viewState: MutableLiveData<BaseViewState> = MutableLiveData()
    val viewState: LiveData<BaseViewState> get() = _viewState

    init {
        refreshUI()
    }

    fun refreshUI() {
        _viewState.value = BaseViewState.Ready
    }


}