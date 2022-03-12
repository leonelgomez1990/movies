package com.lgomez.movies.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.lgomez.movies.R
import com.lgomez.movies.databinding.FragmentMoviesMasterBinding
import com.lgomez.movies.ui.viewmodels.MoviesMasterViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MoviesMasterFragment : Fragment() {

    companion object {
        fun newInstance() = MoviesMasterFragment()
    }

    private var _binding: FragmentMoviesMasterBinding? = null
    private val binding get() = _binding!!
    private val viewModel: MoviesMasterViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMoviesMasterBinding.inflate(layoutInflater)
        return binding.root
    }

}