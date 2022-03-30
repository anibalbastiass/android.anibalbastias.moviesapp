package com.backbase.assignment

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MoviesActivity : AppCompatActivity() {

//    private val baseUrl = "https://api.themoviedb.org/3"
//    private val yourKey = ""

//    private lateinit var moviesAdapter: MoviesAdapter
//    private lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        recyclerView = findViewById(R.id.recyclerView)
//        recyclerView.layoutManager =
//            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
//
//        moviesAdapter = MoviesAdapter()
//        recyclerView.adapter = moviesAdapter
//
//        fetchMovies()
    }

//    private fun fetchMovies() {
//        val jsonString =
//            URL("$baseUrl/movie/now_playing?language=en-US&page=undefined&api_key=$yourKey").readText()
//        val jsonObject = JsonParser.parseString(jsonString).asJsonObject
//        moviesAdapter.items = jsonObject["results"] as JsonArray
//        moviesAdapter.notifyDataSetChanged()
//    }
}
