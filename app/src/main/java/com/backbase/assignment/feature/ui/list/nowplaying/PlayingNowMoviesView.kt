package com.backbase.assignment.feature.ui.list.nowplaying

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import androidx.recyclerview.widget.LinearLayoutManager
import com.backbase.assignment.databinding.FragmentMovieNowPlayingBinding
import com.backbase.assignment.feature.data.MovieDataState
import com.backbase.assignment.feature.data.model.list.MovieResult
import com.backbase.assignment.feature.data.state.APIState

class PlayingNowMoviesView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {

    private var binding: FragmentMovieNowPlayingBinding =
        FragmentMovieNowPlayingBinding.inflate(LayoutInflater.from(context))

    private val nowPlayingAdapter = NowPlayingMoviesAdapter()

    init {
        addView(binding.root)
        initAdapter()
    }

    private fun initAdapter() {
        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            adapter = nowPlayingAdapter
        }
    }

    fun renderMovies(state: MovieDataState) {
        when (state) {
//            is APIState.Empty -> TODO()
//            is APIState.Error -> TODO()
//            APIState.Loading -> TODO()
            is APIState.Success -> renderSuccess(state.data.results)
        }
    }

    private fun renderSuccess(results: List<MovieResult>) {
        nowPlayingAdapter.updateData(results)
    }
}