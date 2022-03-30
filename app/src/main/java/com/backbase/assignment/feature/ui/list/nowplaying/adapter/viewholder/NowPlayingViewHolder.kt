package com.backbase.assignment.feature.ui.list.nowplaying.adapter.viewholder

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.backbase.assignment.databinding.FragmentMovieNowPlayingItemBinding
import com.backbase.assignment.feature.data.model.list.MovieResult

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
        binding.poster.load(dataItem.posterPath) {
            crossfade(true)
        }
    }
}
