package com.anibalbastias.moviesapp.feature.ui.list.popular.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.anibalbastias.moviesapp.databinding.FragmentMoviePopularItemBinding
import com.anibalbastias.moviesapp.feature.data.local.model.EntityMovieItem

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

    fun from(
        dataItem: EntityMovieItem,
        itemClickListener: (Int) -> Unit
    ) {
        binding.title.text = dataItem.originalTitle
        binding.releaseDate.text = dataItem.releaseDate
        binding.poster.load(dataItem.posterPath)
        binding.rating.calculate(dataItem.voteAverage)

        binding.root.setOnClickListener {
            itemClickListener.invoke(dataItem.movieId.toInt())
        }
    }
}
