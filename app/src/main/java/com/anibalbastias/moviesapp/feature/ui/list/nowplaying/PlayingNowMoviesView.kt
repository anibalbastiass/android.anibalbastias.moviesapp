package com.anibalbastias.moviesapp.feature.ui.list.nowplaying

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.FrameLayout
import androidx.recyclerview.widget.LinearLayoutManager
import com.anibalbastias.moviesapp.databinding.FragmentMovieNowPlayingBinding
import com.anibalbastias.moviesapp.feature.presentation.model.UiMovieItem
import com.anibalbastias.moviesapp.feature.ui.list.nowplaying.adapter.MoviesAdapter

class PlayingNowMoviesView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {

    private var binding: FragmentMovieNowPlayingBinding =
        FragmentMovieNowPlayingBinding.inflate(LayoutInflater.from(context))

    init {
        addView(binding.root)
    }

    private fun initAdapter(nowPlayingAdapter: MoviesAdapter) {
        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            adapter = nowPlayingAdapter
        }
    }

    fun renderMovies(list: List<UiMovieItem>, itemClickListener: (Int) -> Unit) {
        val nowPlayingAdapter = MoviesAdapter(itemClickListener)
        initAdapter(nowPlayingAdapter)

        nowPlayingAdapter.updateData(list)

        binding.llNowPlaying.visibility = View.VISIBLE
    }

    fun hideView() {
        binding.llNowPlaying.visibility = View.GONE
    }
}