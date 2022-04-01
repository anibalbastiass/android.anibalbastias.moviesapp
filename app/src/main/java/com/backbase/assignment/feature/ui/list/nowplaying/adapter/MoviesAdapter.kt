package com.backbase.assignment.feature.ui.list.nowplaying.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.backbase.assignment.feature.presentation.model.UiMovieItem
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Singleton

@Singleton
class MoviesAdapter(private val itemClickListener: (Int) -> Unit) :
    ListAdapter<UiMovieItem, NowPlayingViewHolder>(MoviesDiffCallback()) {

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

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NowPlayingViewHolder {
        return NowPlayingViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: NowPlayingViewHolder, position: Int) {
        holder.from(getItem(position) as UiMovieItem, itemClickListener)
    }
}