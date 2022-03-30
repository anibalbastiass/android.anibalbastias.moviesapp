package com.backbase.assignment.feature.ui.list.nowplaying.adapter.viewholder

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.backbase.assignment.R
import com.backbase.assignment.databinding.FragmentMovieNowPlayingItemBinding
import com.backbase.assignment.databinding.FragmentMoviePopularItemBinding
import com.backbase.assignment.feature.data.model.list.MovieResult

class PopularViewHolder(private val binding: FragmentMoviePopularItemBinding) :
    RecyclerView.ViewHolder(binding.root) {

    companion object {
        fun from(parent: ViewGroup): PopularViewHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            val binding =
                FragmentMoviePopularItemBinding.inflate(layoutInflater, parent, false)
            return PopularViewHolder(binding)
        }
    }

    fun from(dataItem: MovieResult) {
        binding.poster.load(dataItem.posterPath) {
            crossfade(true)
            placeholder(R.drawable.movies_logo)
        }
    }
}
