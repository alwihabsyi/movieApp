package com.example.movieapp

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.movieapp.dataJR.ResultsItemJR
import com.squareup.picasso.Picasso

class MovieJRHolder(view: View, val eventHandling: (ResultsItemJR) -> Unit ): RecyclerView.ViewHolder(view) {
    val titleJR = view.findViewById<TextView>(R.id.tv_jrtitle)
    val dateJR = view.findViewById<TextView>(R.id.tv_dateJR)
    val ratingJR = view.findViewById<TextView>(R.id.tv_rating)
    val imgJR = view.findViewById<ImageView>(R.id.iv_jusrel)
    val image = "https://image.tmdb.org/t/p/w500"

    fun onBindViewJR(movie: ResultsItemJR){
        titleJR.text = movie.title
        dateJR.text = movie.releaseDate
        ratingJR.text = movie.voteAverage.toString()

        Picasso
            .get()
            .load(image+movie.posterPath)
            .into(imgJR)

        itemView.setOnClickListener { eventHandling(movie) }
    }
}