package com.backbase.assignment.feature.ui.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.backbase.assignment.R
import com.backbase.assignment.databinding.FragmentMovieListBinding
import com.backbase.assignment.feature.presentation.MoviesViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MovieListFragment : Fragment() {

    private lateinit var binding: FragmentMovieListBinding
    private val viewModel: MoviesViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMovieListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initSwipeRefresh()
        observeMovies()
    }

    private fun observeMovies() {
        viewModel.nowPlayingMovies.value?.let { dataState ->
            binding.nowPlayingMoviesView.renderMovies(dataState)
        } ?: run {
            viewModel.getNowPlayingMovies()
        }

        viewModel.nowPlayingMovies.observe(viewLifecycleOwner) { dataState ->
            binding.nowPlayingMoviesView.renderMovies(dataState)
        }
    }

    private fun initSwipeRefresh() {
        binding.srlList.apply {
            setColorSchemeColors(ContextCompat.getColor(requireContext(), R.color.colorPrimaryDark))
            setOnRefreshListener {
                viewModel.getNowPlayingMovies()
            }
        }
    }
}