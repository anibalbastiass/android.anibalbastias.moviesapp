//package com.backbase.assignment.feature.ui.movie
//
//import android.net.Uri
//import android.view.LayoutInflater
//import android.view.View
//import android.view.ViewGroup
//import android.widget.ImageView
//import android.widget.TextView
//import androidx.recyclerview.widget.RecyclerView
//import com.backbase.assignment.R
//import com.backbase.assignment.feature.ui.list.popular.widget.RatingView
//import com.google.gson.JsonArray
//import com.google.gson.JsonObject
//
//class MoviesAdapter(var items: JsonArray = JsonArray()) :
//    RecyclerView.Adapter<MoviesAdapter.ViewHolder>() {
//
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
//        return ViewHolder(
//            LayoutInflater.from(parent.context).inflate(
//                R.layout.fragment_movie_item,
//                parent,
//                false
//            )
//        )
//    }
//
//    override fun onBindViewHolder(holder: ViewHolder, position: Int) =
//        holder.bind(items[position] as JsonObject)
//
//    override fun getItemCount() = items.size()
//
//    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
//        lateinit var poster: ImageView
//        lateinit var title: TextView
//        lateinit var releaseDate: TextView
//        lateinit var rating: RatingView
//
//        fun bind(item: JsonObject) = with(itemView) {
//            poster = itemView.findViewById(R.id.poster)
//            poster.setImageURI(Uri.parse("https://image.tmdb.org/t/p/original/${item["poster_path"].asString}"))
//
//            title = itemView.findViewById(R.id.title)
//            title.text = item["title"].asString
//
//            releaseDate = itemView.findViewById(R.id.releaseDate)
//            releaseDate.text = item["release_date"].asString
//        }
//    }
//}