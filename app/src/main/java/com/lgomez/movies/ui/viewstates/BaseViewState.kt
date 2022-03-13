package com.lgomez.movies.ui.viewstates

sealed class BaseViewState {
    object Ready : BaseViewState()
    object Loading : BaseViewState()
    data class Failure(val exception: Exception) : BaseViewState()
}

