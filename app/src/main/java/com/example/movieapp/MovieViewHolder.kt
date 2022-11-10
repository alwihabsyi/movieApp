package com.example.movieapp

import android.annotation.SuppressLint
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.movieapp.dataHor.ResultsItem
import com.squareup.picasso.Picasso

class MovieViewHolder(view: View, val eventHandling: (ResultsItem) -> Unit ):RecyclerView.ViewHolder(view) {
    val title = view.findViewById<TextView>(R.id.tv_rv)
    val cover = view.findViewById<ImageView>(R.id.img_rv)
    val views = view.findViewById<TextView>(R.id.tv_views)
    val image = "https://image.tmdb.org/t/p/w500"

    @SuppressLint("ResourceType")
    fun onBindView(movie: ResultsItem){
        title.text = movie.title
        views.text = movie.voteCount.toString()+" x viewed"
        Picasso
            .get()
            .load(image+movie.backdropPath)
            .into(cover)

        itemView.setOnClickListener { eventHandling(movie) }
    }
}