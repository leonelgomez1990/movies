package com.lgomez.movies.ui.fragments

import android.app.AlertDialog
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import com.lgomez.movies.R
import com.lgomez.movies.core.utils.snack
import com.lgomez.movies.databinding.FragmentMoviesMasterBinding
import com.lgomez.movies.domain.model.PopularMovies
import com.lgomez.movies.ui.adapters.PopularMoviesItemAdapter
import com.lgomez.movies.ui.model.toMovieUI
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
    private val viewModel: MoviesMasterViewModel by activityViewModels()
    private val adapter = PopularMoviesItemAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMoviesMasterBinding.inflate(layoutInflater)
        setListeners()
        setupRecyclerView()
        setSearchView()
        return binding.root
    }

    override fun onStart() {
        super.onStart()
        setObservers()
    }

    private fun setObservers() {
        viewModel.navigation.observe(viewLifecycleOwner, Observer { handleNavigation(it) })
        viewModel.viewState.observe(viewLifecycleOwner, Observer { handleViewStates(it) })
        viewModel.movies.observe(
            viewLifecycleOwner,
            Observer { setupPopularMoviesRecyclerView(it) })
    }

    private fun setListeners() {
        binding.swipeRefresh.setOnRefreshListener {
            //When swiping, clear filter field and refresh recycler view
            binding.searchView.setQuery("", false);
            binding.searchView.clearFocus()
            viewModel.usingFilter = false
            viewModel.refreshUI()
        }
        binding.swipeRefresh.setOnChildScrollUpCallback { _, _ ->
            //Only swiping to refresh when recycler is on top of scrolling
            binding.rvMovies.canScrollVertically(-1);
        }
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

    private fun updateUI() {
        with(binding) {
        }
    }

    private fun setupRecyclerView() {
        adapter.setData(mutableListOf())
        adapter.onClickListener = { }
        val layoutManager = GridLayoutManager(context, 2)

        with(binding) {
            rvMovies.setHasFixedSize(true)
            rvMovies.layoutManager = layoutManager
            rvMovies.adapter = adapter

            rvMovies.addOnScrollListener(object : RecyclerView.OnScrollListener() {
                override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                    super.onScrolled(recyclerView, dx, dy)
                    if (layoutManager.itemCount > 0) {
                        viewModel.notifyLastSeen(
                            layoutManager.findLastVisibleItemPosition(),
                            layoutManager.itemCount
                        )
                    }
                }
            })
        }
    }

    private fun setupPopularMoviesRecyclerView(list: MutableList<PopularMovies>) {
        if (list.isEmpty() && viewModel.viewState.value is BaseViewState.Ready) {
            showDialog(
                resources.getString(R.string.msg_alert_empty_movies_title),
                resources.getString(R.string.msg_alert_empty_movies_body)
            )
        }
        adapter.setData(list)
        adapter.onClickListener = { viewModel.goToMoviesDetail(it.toMovieUI()) }
    }

    private fun showDialog(title: String, message: String) {
        AlertDialog.Builder(requireContext())
            .setTitle(title)
            .setMessage(message)
            .setPositiveButton(R.string.button_accept) { _, _ -> }
            .show()
    }

    private fun showMessage(msg: String) {
        binding.root.snack(msg, Snackbar.LENGTH_LONG)
    }

    private fun setSearchView() {
        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                binding.searchView.clearFocus()
                searchQuery(query)
                return false
            }

            override fun onQueryTextChange(query: String): Boolean {
                searchQuery(query)
                return false
            }
        })
    }

    private fun searchQuery(query: String) {
        adapter.filter(query)
        if (query.isNotBlank()) {
            viewModel.usingFilter = true
            (binding.rvMovies.adapter as PopularMoviesItemAdapter).filter(query)
        } else {
            viewModel.usingFilter = false
        }
    }

}