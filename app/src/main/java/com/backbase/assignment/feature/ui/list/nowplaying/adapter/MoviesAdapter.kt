package com.backbase.assignment.feature.ui.list.nowplaying.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.backbase.assignment.feature.presentation.model.UiMovieItem
import com.backbase.assignment.feature.ui.list.nowplaying.MoviesDiffCallback
import com.backbase.assignment.feature.ui.list.nowplaying.adapter.viewholder.NowPlayingViewHolder
import com.backbase.assignment.feature.ui.list.nowplaying.adapter.viewholder.PopularViewHolder
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Singleton

@Singleton
class MoviesAdapter(private val movieType: Int = NOW_PLAYING_VIEW_TYPE) :
    ListAdapter<UiMovieItem, RecyclerView.ViewHolder>(MoviesDiffCallback()) {

    companion object {
        const val NOW_PLAYING_VIEW_TYPE = 1
        const val POPULAR_VIEW_TYPE = 2
    }

    private val adapterScope = CoroutineScope(Dispatchers.Default)

    fun updateData(list: List<UiMovieItem>?) {
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
                holder.from(getItem(position) as UiMovieItem)
            }

            is PopularViewHolder -> {
                holder.from(getItem(position) as UiMovieItem)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            NOW_PLAYING_VIEW_TYPE -> NowPlayingViewHolder.from(parent)
            POPULAR_VIEW_TYPE -> PopularViewHolder.from(parent)
            else -> throw ClassCastException("Unknown viewType $viewType")
        }
    }

    override fun getItemViewType(position: Int): Int {
        return movieType
    }
}