package com.backbase.assignment.feature.ui.list.popular

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import com.backbase.assignment.databinding.FragmentMoviePopularBinding
import com.backbase.assignment.feature.data.model.list.MovieResult

class PopularMoviesView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {

    private var binding: FragmentMoviePopularBinding =
        FragmentMoviePopularBinding.inflate(LayoutInflater.from(context))

    init {
        addView(binding.root)
    }

    fun renderMovies(movies: List<MovieResult>) {

    }
}