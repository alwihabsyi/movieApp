package com.example.movieapp

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.movieapp.dataHor.ResultsItem

class MovieAdapter(private val dataset: List<ResultsItem>, val eventHandling: (ResultsItem) -> Unit):RecyclerView.Adapter<MovieViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        return MovieViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.rv_horizontal,parent,false), eventHandling
        )
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.onBindView(dataset[position])
    }

    override fun getItemCount(): Int = 10
}