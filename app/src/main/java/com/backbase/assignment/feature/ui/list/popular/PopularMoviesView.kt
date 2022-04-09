//package com.backbase.assignment.feature.ui.list.popular
//
//import android.content.Context
//import android.util.AttributeSet
//import android.view.LayoutInflater
//import android.view.View
//import android.widget.FrameLayout
//import androidx.paging.PagedList
//import androidx.recyclerview.widget.LinearLayoutManager
//import com.backbase.assignment.databinding.FragmentMoviePopularBinding
//import com.backbase.assignment.feature.data.local.model.EntityMovieItem
//import com.backbase.assignment.feature.ui.list.popular.adapter.MoviesPagedAdapter
//
//class PopularMoviesView @JvmOverloads constructor(
//    context: Context,
//    attrs: AttributeSet? = null,
//    defStyleAttr: Int = 0
//) : FrameLayout(context, attrs, defStyleAttr) {
//
//    private var binding: FragmentMoviePopularBinding =
//        FragmentMoviePopularBinding.inflate(LayoutInflater.from(context))
//
//    init {
//        addView(binding.root)
//    }
//
//    private fun initAdapter(nowPlayingAdapter: MoviesPagedAdapter) {
//        binding.recyclerView.apply {
//            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
//            adapter = nowPlayingAdapter
//        }
//    }
//
//    fun renderMovies(
//        movies: PagedList<EntityMovieItem>,
//        itemClickListener: (Int) -> Unit
//    ) {
//
//        val nowPlayingAdapter = MoviesPagedAdapter(itemClickListener)
//        initAdapter(nowPlayingAdapter)
//
//        nowPlayingAdapter.submitList(movies)
//        binding.clPopularMovies.visibility = View.VISIBLE
//    }
//}