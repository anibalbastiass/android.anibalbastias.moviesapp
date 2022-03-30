package com.backbase.assignment.feature.ui.list.nowplaying

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.backbase.assignment.databinding.FragmentMovieNowPlayingItemBinding
import com.backbase.assignment.feature.data.model.list.MovieResult
import com.backbase.assignment.feature.ui.list.popular.PopularMoviesView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Singleton

@Singleton
class NowPlayingMoviesAdapter :
    ListAdapter<MovieResult, RecyclerView.ViewHolder>(MoviesDiffCallback()) {

    companion object {
        private const val NOW_PLAYING_VIEW_TYPE_ITEM = 1
        private const val POPULAR_VIEW_TYPE_ITEM = 2
    }

    private val adapterScope = CoroutineScope(Dispatchers.Default)

    fun updateData(list: List<MovieResult>?) {
        adapterScope.launch {
            val items = when (list) {
                null -> listOf()
                else -> list
            }

            withContext(Dispatchers.Main) {
                submitList(items)
            }
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is NowPlayingViewHolder -> {
                holder.from(getItem(position) as MovieResult)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            NOW_PLAYING_VIEW_TYPE_ITEM -> NowPlayingViewHolder.from(parent)
            else -> throw ClassCastException("Unknown viewType $viewType")
        }
    }

    override fun getItemViewType(position: Int): Int {
        return NOW_PLAYING_VIEW_TYPE_ITEM
    }

    class NowPlayingViewHolder(private val binding: FragmentMovieNowPlayingItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        companion object {
            fun from(parent: ViewGroup): NowPlayingViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding =
                    FragmentMovieNowPlayingItemBinding.inflate(layoutInflater, parent, false)
                return NowPlayingViewHolder(binding)
            }
        }

        fun from(dataItem: MovieResult) {
            binding.poster.load(dataItem.posterPath)
        }
    }
}