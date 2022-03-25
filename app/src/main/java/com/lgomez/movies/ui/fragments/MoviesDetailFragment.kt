package com.lgomez.movies.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
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
        when (state) {
            is BaseViewState.Failure -> {
                handleExceptions(state.exception)
                enableUI(true)
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

    }

    private fun updateMovieDetailsUI(movie: MovieUI) {
        viewModel.setMovie(movie)
    }

    private fun updateUI() {
        with(binding) {
            textView.text = viewModel.movie.title
        }
    }

}