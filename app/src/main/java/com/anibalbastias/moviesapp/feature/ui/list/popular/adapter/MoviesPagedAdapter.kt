package com.anibalbastias.moviesapp.feature.ui.list.popular.adapter

import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import com.anibalbastias.moviesapp.feature.data.local.model.EntityMovieItem
import javax.inject.Singleton

@Singleton
class MoviesPagedAdapter(
    private val itemClickListener: (Int) -> Unit
) : PagedListAdapter<EntityMovieItem, PopularViewHolder>(MoviesPagedDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PopularViewHolder {
        return PopularViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: PopularViewHolder, position: Int) {
        getItem(position)?.let { item ->
            holder.from(item, itemClickListener)
        }
    }
}