package com.lgomez.movies.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.lgomez.movies.databinding.FragmentMoviesMasterBinding
import com.lgomez.movies.ui.navigatorstates.MoviesMasterNavigatorStates
import com.lgomez.movies.ui.viewmodels.MoviesMasterViewModel
import com.lgomez.movies.ui.viewstates.BaseViewState
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MoviesMasterFragment : Fragment() {

    companion object {
        fun newInstance() = MoviesMasterFragment()
        const val TAG = "MoviesMasterFragment"
    }

    private var _binding: FragmentMoviesMasterBinding? = null
    private val binding get() = _binding!!
    private val viewModel: MoviesMasterViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMoviesMasterBinding.inflate(layoutInflater)
        setListeners()
        return binding.root
    }

    override fun onStart() {
        super.onStart()
        setObservers()
    }

    private fun setObservers() {
        viewModel.navigation.observe(viewLifecycleOwner, Observer { handleNavigation(it) })
        viewModel.viewState.observe(viewLifecycleOwner, Observer { handleViewStates(it) })
    }

    private fun setListeners() {
        binding.swipeRefresh.setOnRefreshListener { viewModel.refreshUI() }
        binding.textView.setOnClickListener { viewModel.goToMoviesDetail(viewModel.movie) }
    }

    private fun handleNavigation(navigation: MoviesMasterNavigatorStates) {
        when (navigation) {
            is MoviesMasterNavigatorStates.ToMoviesDetail -> {
                val action =
                    MoviesMasterFragmentDirections.actionMoviesMasterFragmentToMoviesDetailFragment(
                        navigation.movie
                    )
                findNavController().navigate(action)
            }
        }
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


}