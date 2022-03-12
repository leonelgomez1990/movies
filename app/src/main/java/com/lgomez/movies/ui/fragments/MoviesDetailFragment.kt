package com.lgomez.movies.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.lgomez.movies.R
import com.lgomez.movies.databinding.FragmentMoviesDetailBinding
import com.lgomez.movies.ui.viewmodels.MoviesDetailViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MoviesDetailFragment : Fragment() {

    companion object {
        fun newInstance() = MoviesDetailFragment()
    }

    private var _binding: FragmentMoviesDetailBinding? = null
    private val binding get() = _binding!!
    private val viewModel: MoviesDetailViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMoviesDetailBinding.inflate(layoutInflater)
        return binding.root
    }

}