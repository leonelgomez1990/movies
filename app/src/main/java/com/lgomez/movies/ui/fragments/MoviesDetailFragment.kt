package com.lgomez.movies.ui.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.google.android.material.snackbar.Snackbar
import com.lgomez.movies.R
import com.lgomez.movies.core.utils.snack
import com.lgomez.movies.databinding.FragmentMoviesDetailBinding
import com.lgomez.movies.ui.model.MovieUI
import com.lgomez.movies.ui.viewmodels.MoviesDetailViewModel
import com.lgomez.movies.ui.viewstates.BaseViewState
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MoviesDetailFragment : Fragment() {

    companion object {
        fun newInstance() = MoviesDetailFragment()
        const val TAG = "MoviesDetailFragment"
    }

    private var _binding: FragmentMoviesDetailBinding? = null
    private val binding get() = _binding!!

    private val viewModel: MoviesDetailViewModel by viewModels()
    private val args: MoviesDetailFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMoviesDetailBinding.inflate(layoutInflater)
        updateMovieDetailsUI(args.movie)
        setListeners()
        return binding.root
    }

    override fun onStart() {
        super.onStart()
        setObservers()
    }

    private fun setObservers() {
        viewModel.viewState.observe(viewLifecycleOwner, Observer { handleViewStates(it) })
    }

    private fun setListeners() {
        binding.swipeRefresh.setOnRefreshListener { viewModel.refreshUI() }
    }

    private fun handleViewStates(state: BaseViewState) {
        Log.d(TAG, "new viewState: $state")
        when (state) {
            is BaseViewState.Failure -> {
                handleExceptions(state.exception)
                viewModel.setReadyState()
            }
            is BaseViewState.Loading -> {
                enableUI(false)
            }
            is BaseViewState.Ready -> {
                updateUI()
                enableUI(true)
            }
        }
    }

    private fun enableUI(enable: Boolean) {
        if (enable) {
            binding.progressLoader.visibility = View.GONE
            binding.swipeRefresh.isRefreshing = false
        } else {
            binding.progressLoader.visibility = View.VISIBLE
        }
    }

    private fun handleExceptions(e: Exception) {
        Log.w(TAG, "$TAG: Exception thrown: ${e.message ?: "No message"}")
        showMessage(getString(R.string.msg_error_default))
    }

    private fun updateMovieDetailsUI(movie: MovieUI) {
        viewModel.setMovie(movie)
    }

    private fun updateUI() {
        with(binding) {
            txtTitle.text = viewModel.movie.title
            txtGenres.text = viewModel.movie.genres.joinToString()
            txtLanguage.text =
                resources.getString(
                    R.string.fragment_movies_detail_language,
                    viewModel.movie.originalLanguage
                )
            txtPopularity.text =
                resources.getString(
                    R.string.fragment_movies_detail_popularity,
                    viewModel.movie.popularity
                )
            Glide.with(root)
                .load(viewModel.movie.cover)
                .centerCrop()
                .into(imgCover)
        }
    }

    private fun showMessage(msg: String) {
        binding.root.snack(msg, Snackbar.LENGTH_LONG)
    }

}