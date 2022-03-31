package com.backbase.assignment.feature.ui.list.popular.adapter

import android.annotation.SuppressLint
import androidx.recyclerview.widget.DiffUtil
import com.backbase.assignment.feature.data.local.model.EntityMovieItem

class MoviesPagedDiffCallback : DiffUtil.ItemCallback<EntityMovieItem>() {

    override fun areItemsTheSame(
        oldItem: EntityMovieItem,
        newItem: EntityMovieItem
    ): Boolean {
        return oldItem.movieId == newItem.movieId
    }

    @SuppressLint("DiffUtilEquals")
    override fun areContentsTheSame(
        oldItem: EntityMovieItem,
        newItem: EntityMovieItem
    ): Boolean {
        return oldItem == newItem
    }
}