package com.example.movieapp

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.movieapp.dataJR.ResultsItemJR

class MovieJrAdapter(private val dataset: List<ResultsItemJR>, val eventHandling : (ResultsItemJR) -> Unit): RecyclerView.Adapter<MovieJRHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieJRHolder {
        return MovieJRHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.rv_jusrel, parent, false), eventHandling
        )
    }

    override fun onBindViewHolder(holder: MovieJRHolder, position: Int) {
        holder.onBindViewJR(dataset[position])
    }

    override fun getItemCount(): Int = 10

}