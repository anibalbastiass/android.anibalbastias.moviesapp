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
import com.backbase.assignment.feature.data.remote.state.APIState
import com.backbase.assignment.feature.domain.UiMovieDataState
import com.backbase.assignment.feature.presentation.MoviesPagedViewModel
import com.backbase.assignment.feature.presentation.MoviesViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MovieListFragment : Fragment() {

    private lateinit var binding: FragmentMovieListBinding
    private val viewModel: MoviesViewModel by viewModels()
    private val pagedViewModel: MoviesPagedViewModel by viewModels()

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
        observeNowPlayingMovies()
        observePopularMovies()
    }

    private fun observePopularMovies() {
        pagedViewModel.movies.observe(viewLifecycleOwner) { pagedList ->
            binding.popularMoviesView.renderMovies(pagedList) { movieId ->
                // TODO: Navigate to Details by moveId
            }
        }
    }

    private fun observeNowPlayingMovies() {
        viewModel.nowPlayingMovies.value?.let { dataState ->
            renderState(dataState)
        } ?: run {
            viewModel.getNowPlayingMovies()
        }

        viewModel.nowPlayingMovies.observe(viewLifecycleOwner) { dataState ->
            renderState(dataState)
        }
    }

    private fun renderState(state: UiMovieDataState) {
        when (state) {
            is APIState.Empty -> {
                hideProgress()
            }
            is APIState.Error -> {
                hideProgress()
            }
            APIState.Loading -> {
                binding.nowPlayingMoviesView.hideView()
            }
            is APIState.Success -> {
                hideProgress()
                binding.nowPlayingMoviesView.renderMovies(state.data)
            }
        }
    }

    private fun hideProgress() {
        binding.srlList.isRefreshing = false
        binding.pbLoading.visibility = View.GONE
    }

    private fun initSwipeRefresh() {
        binding.srlList.apply {
            setColorSchemeColors(ContextCompat.getColor(requireContext(), R.color.colorPrimaryDark))
            setOnRefreshListener {
                viewModel.getNowPlayingMovies()
                pagedViewModel.refreshMovies()
            }
        }
    }
}