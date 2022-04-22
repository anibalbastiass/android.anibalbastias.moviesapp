package com.anibalbastias.moviesapp.feature.ui.list.nowplaying.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.anibalbastias.moviesapp.databinding.FragmentMovieNowPlayingItemBinding
import com.anibalbastias.moviesapp.feature.presentation.model.UiMovieItem

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

    fun from(dataItem: UiMovieItem, itemClickListener: (Int) -> Unit) {
        binding.poster.load(dataItem.posterPath) {
            crossfade(true)
        }
        binding.root.setOnClickListener {
            itemClickListener.invoke(dataItem.id.toInt())
        }
    }
}
