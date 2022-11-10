package com.example.movieapp

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.movieapp.dataHor.API
import com.example.movieapp.dataHor.DataMovie
import com.example.movieapp.dataHor.ResultsItem
import com.example.movieapp.dataJR.APIJR
import com.example.movieapp.dataJR.DataJR
import com.example.movieapp.dataJR.ResultsItemJR
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class MainActivity : AppCompatActivity() {
    //INIS AUTH
    private lateinit var auth: FirebaseAuth
    //INIS AUTH

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        window.decorView.systemUiVisibility =
            View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
        supportActionBar?.hide()
        auth = Firebase.auth

        //horizontalstart
        val rvHorizontal = findViewById<RecyclerView>(R.id.rv_ho)
        rvHorizontal.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        rvHorizontal.setHasFixedSize(true)
        getMovieData { movies: List<ResultsItem> ->
            rvHorizontal.adapter = MovieAdapter(movies) {
                Intent(this, detailPage::class.java).apply {
                    putExtra("user", it)
                    startActivity(this)
                }
            }
        }
        //horizontalend

        //Just Released Start
        val rvJusrel = findViewById<RecyclerView>(R.id.rv_jusrel)
        rvJusrel.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        rvJusrel.setHasFixedSize(true)
        getMovieDataJR { moviesJr: List<ResultsItemJR> ->
            rvJusrel.adapter = MovieJrAdapter(moviesJr) {
                Intent(this, detailPageJR::class.java).apply {
                    putExtra("user", it)
                    startActivity(this)
                }
            }
        }
        //Just Released End

        //Now Playing Start
        val rvNP = findViewById<RecyclerView>(R.id.rv_NP)
        rvNP.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        rvNP.setHasFixedSize(true)
        getMovieDataNP { moviesJr: List<ResultsItemJR> ->
            rvNP.adapter = MovieJrAdapter(moviesJr) {
                Intent(this, detailPageJR::class.java).apply {
                    putExtra("user", it)
                    startActivity(this)
                }
            }
        }
        //Now Playing End

        //Button Logout
        findViewById<Button>(R.id.btn_logout).apply {
            setOnClickListener {
                logOut()
            }
        }
        //Button Logout
    }

    private fun getMovieData(callback: (List<ResultsItem>) -> Unit) {
        val apiService = retrofitService().create(API::class.java)
        apiService.getMovie().enqueue(object : Callback<DataMovie> {
            override fun onResponse(call: Call<DataMovie>, response: Response<DataMovie>) {
                return callback(response.body()!!.results!!)
            }

            override fun onFailure(call: Call<DataMovie>, t: Throwable) {

            }

        })

    }

    private fun getMovieDataJR(callback: (List<ResultsItemJR>) -> Unit) {
        val apiService = retrofitService().create(APIJR::class.java)
        apiService.getMovie().enqueue(object : Callback<DataJR> {
            override fun onResponse(call: Call<DataJR>, response: Response<DataJR>) {
                return callback(response.body()!!.results!!)
            }

            override fun onFailure(call: Call<DataJR>, t: Throwable) {

            }

        })

    }

    private fun getMovieDataNP(callback: (List<ResultsItemJR>) -> Unit) {
        val apiService = retrofitService().create(APIJR::class.java)
        apiService.getMovieNP().enqueue(object : Callback<DataJR> {
            override fun onResponse(call: Call<DataJR>, response: Response<DataJR>) {
                return callback(response.body()!!.results!!)
            }

            override fun onFailure(call: Call<DataJR>, t: Throwable) {

            }

        })

    }

    private fun logOut() {
        auth.signOut()
        startActivity(Intent(this, AuthPage::class.java))
        finish()
    }
}

private var retrofit: Retrofit? = null
fun retrofitService(): Retrofit {
    if (retrofit == null) {
        retrofit = Retrofit.Builder()
            .baseUrl("https://api.themoviedb.org/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
    return retrofit!!
}