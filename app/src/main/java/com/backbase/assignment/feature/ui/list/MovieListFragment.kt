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
import com.backbase.assignment.feature.presentation.MoviesViewModel
import dagger.hilt.android.AndroidEntryPoint
import java.lang.IllegalStateException

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
                binding.popularMoviesView.hideView()
            }
            is APIState.Success -> {
                hideProgress()

                binding.nowPlayingMoviesView.renderMovies(state.data)
                binding.popularMoviesView.renderMovies(state.data)
            }
            else -> throw IllegalStateException("State not known")
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
            }
        }
    }
}