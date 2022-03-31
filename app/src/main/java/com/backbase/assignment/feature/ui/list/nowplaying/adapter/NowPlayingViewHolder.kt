package com.backbase.assignment.feature.ui.list.nowplaying.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.backbase.assignment.databinding.FragmentMovieNowPlayingItemBinding
import com.backbase.assignment.feature.presentation.model.UiMovieItem

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

    fun from(dataItem: UiMovieItem) {
        binding.poster.load(dataItem.posterPath) {
            crossfade(true)
        }
    }
}
