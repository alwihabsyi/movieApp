package com.example.movieapp.dataJR

import retrofit2.Call
import retrofit2.http.GET

interface APIJR {
    @GET("/3/movie/top_rated?api_key=e8b079f75d74c9114f1c9d5456a8bc5b&page=1")
    fun getMovie(): Call<DataJR>

    @GET("/3/movie/now_playing?api_key=e8b079f75d74c9114f1c9d5456a8bc5b&page=1")
    fun getMovieNP(): Call<DataJR>

    @GET("/3/movie/upcoming?api_key=e8b079f75d74c9114f1c9d5456a8bc5b&page=1")
    fun getMovieUP(): Call<DataJR>
}