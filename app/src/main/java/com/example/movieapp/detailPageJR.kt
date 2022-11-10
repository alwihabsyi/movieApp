package com.example.movieapp

import android.annotation.SuppressLint
import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.view.WindowManager
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.movieapp.dataHor.ResultsItem
import com.example.movieapp.dataJR.APIJR
import com.example.movieapp.dataJR.DataJR
import com.example.movieapp.dataJR.ResultsItemJR
import com.squareup.picasso.Picasso
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class detailPageJR : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_page_jr)

        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.KITKAT) {
            getWindow().setFlags(
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
            )
        }

        val user = intent.getParcelableExtra<ResultsItemJR>("user")
        val image = "https://image.tmdb.org/t/p/w500"

        //INISIALISASI START
        val imgcover = findViewById<ImageView>(R.id.img_movie)
        val title = findViewById<TextView>(R.id.tv_tdetail)
        val rating = findViewById<TextView>(R.id.tv_ratedt)
        val date = findViewById<TextView>(R.id.tv_datedt)
        val views = findViewById<TextView>(R.id.tv_viewdt)
        val synopsis = findViewById<TextView>(R.id.tv_synopsis)
        val imgDetail1 = findViewById<ImageView>(R.id.img_detpage1)
        val imgDetail2 = findViewById<ImageView>(R.id.img_detpage2)
        val btn_play = findViewById<Button>(R.id.btn_play)
        val btn_share = findViewById<Button>(R.id.btn_share)
        //INISIALISASI END

        //PINDAH DATA START
        Picasso
            .get()
            .load(image + user?.backdropPath)
            .into(imgcover)
        title.text = user?.title
        rating.text = user?.voteAverage.toString()
        date.text = user?.releaseDate
        views.text = user?.voteCount.toString()
        synopsis.text = user?.overview
        Picasso
            .get()
            .load(image + user?.posterPath)
            .into(imgDetail1)
        Picasso
            .get()
            .load(image + user?.backdropPath)
            .into(imgDetail2)
        //PINDAH DATA END

        //IMPLICIT INTENT
        btn_play.setOnClickListener {
            val Intent = Intent(
                Intent.ACTION_VIEW,
                Uri.parse("https://www.youtube.com/results?search_query=" + user?.title + " Trailer")
            )
            startActivity(Intent)
        }

        btn_share.setOnClickListener {
            val Intent = Intent(
                Intent.ACTION_VIEW,
                Uri.parse("https://api.whatsapp.com/send?text=Let's+Watch+" + user?.title)
            )
            startActivity(Intent)
        }
        //IMPLICIT INTENT

        //RV START
        val rvOYML = findViewById<RecyclerView>(R.id.rv_yml1)
        rvOYML.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        rvOYML.setHasFixedSize(true)
        getMovieDataNP { movies: List<ResultsItemJR> ->
            rvOYML.adapter = MovieJrAdapter(movies) {
                Intent(this, detailPageJR::class.java).apply {
                    putExtra("user", it)
                    startActivity(this)
                }
            }
        }
        //RV END
    }

    private fun getMovieDataNP(callback: (List<ResultsItemJR>) -> Unit) {
        val apiService = retrofitService().create(APIJR::class.java)
        apiService.getMovieUP().enqueue(object : Callback<DataJR> {
            override fun onResponse(call: Call<DataJR>, response: Response<DataJR>) {
                return callback(response.body()!!.results!!)
            }

            override fun onFailure(call: Call<DataJR>, t: Throwable) {

            }

        })

    }
}