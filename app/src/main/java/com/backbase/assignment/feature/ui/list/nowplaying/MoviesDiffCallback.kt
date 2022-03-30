package com.backbase.assignment.feature.ui.list.nowplaying

import android.annotation.SuppressLint
import androidx.recyclerview.widget.DiffUtil
import com.backbase.assignment.feature.data.model.list.MovieResult

class MoviesDiffCallback : DiffUtil.ItemCallback<MovieResult>() {

    override fun areItemsTheSame(
        oldItem: MovieResult,
        newItem: MovieResult
    ): Boolean {
        return oldItem.id == newItem.id
    }

    @SuppressLint("DiffUtilEquals")
    override fun areContentsTheSame(
        oldItem: MovieResult,
        newItem: MovieResult
    ): Boolean {
        return oldItem == newItem
    }
}