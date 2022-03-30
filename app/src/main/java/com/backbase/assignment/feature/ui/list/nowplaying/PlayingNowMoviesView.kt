package com.backbase.assignment.feature.ui.list.nowplaying

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.FrameLayout
import androidx.recyclerview.widget.LinearLayoutManager
import com.backbase.assignment.databinding.FragmentMovieNowPlayingBinding
import com.backbase.assignment.feature.data.model.list.MovieResult
import com.backbase.assignment.feature.ui.list.nowplaying.adapter.MoviesAdapter
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class PlayingNowMoviesView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {

    private var binding: FragmentMovieNowPlayingBinding =
        FragmentMovieNowPlayingBinding.inflate(LayoutInflater.from(context))

    private val nowPlayingAdapter = MoviesAdapter()

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

    fun renderMovies(list: List<MovieResult>) {
        nowPlayingAdapter.updateData(list)
        binding.llNowPlaying.visibility = View.VISIBLE
    }

    fun hideView() {
        binding.llNowPlaying.visibility = View.GONE
    }
}