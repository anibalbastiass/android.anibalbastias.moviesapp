//package com.backbase.assignment.feature.ui.list.nowplaying.adapter
//
//import android.annotation.SuppressLint
//import androidx.recyclerview.widget.DiffUtil
//import com.backbase.assignment.feature.presentation.model.UiMovieItem
//
//class MoviesDiffCallback : DiffUtil.ItemCallback<UiMovieItem>() {
//
//    override fun areItemsTheSame(
//        oldItem: UiMovieItem,
//        newItem: UiMovieItem
//    ): Boolean {
//        return oldItem.id == newItem.id
//    }
//
//    @SuppressLint("DiffUtilEquals")
//    override fun areContentsTheSame(
//        oldItem: UiMovieItem,
//        newItem: UiMovieItem
//    ): Boolean {
//        return oldItem == newItem
//    }
//}