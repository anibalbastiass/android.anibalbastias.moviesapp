package com.backbase.assignment.feature.ui.list.popular

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.FrameLayout
import androidx.recyclerview.widget.LinearLayoutManager
import com.backbase.assignment.databinding.FragmentMoviePopularBinding
import com.backbase.assignment.feature.data.model.list.MovieResult
import com.backbase.assignment.feature.ui.list.nowplaying.adapter.MoviesAdapter
import com.backbase.assignment.feature.ui.list.nowplaying.adapter.MoviesAdapter.Companion.POPULAR_VIEW_TYPE

class PopularMoviesView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {

    private var binding: FragmentMoviePopularBinding =
        FragmentMoviePopularBinding.inflate(LayoutInflater.from(context))

    private val nowPlayingAdapter = MoviesAdapter(POPULAR_VIEW_TYPE)

    init {
        addView(binding.root)
        initAdapter()
    }

    private fun initAdapter() {
        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            adapter = nowPlayingAdapter
        }
    }

    fun renderMovies(movies: List<MovieResult>) {
        binding.clPopularMovies.visibility = View.VISIBLE
    }

    fun hideView() {
        binding.clPopularMovies.visibility = View.GONE
    }
}