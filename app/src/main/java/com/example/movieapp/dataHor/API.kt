package com.example.movieapp.dataHor

import retrofit2.Call
import retrofit2.http.GET

interface API {
    @GET("/3/movie/popular?api_key=e8b079f75d74c9114f1c9d5456a8bc5b")
    fun getMovie(): Call<DataMovie>
}