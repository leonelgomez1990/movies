package com.lgomez.movies.ui.fragments

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.lgomez.movies.R
import com.lgomez.movies.ui.viewmodels.MoviesMasterViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MoviesMasterFragment : Fragment() {

    companion object {
        fun newInstance() = MoviesMasterFragment()
    }

    private lateinit var viewModel: MoviesMasterViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_movies_master, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(MoviesMasterViewModel::class.java)
        // TODO: Use the ViewModel
    }

}