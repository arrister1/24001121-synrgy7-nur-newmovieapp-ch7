package com.example.movieapp.presentation.ui.main

import android.os.Build
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.movieapp.domain.model.Movie
import com.example.movieapp.presentation.databinding.ActivityDetailBinding

class DetailActivity : AppCompatActivity() {
    private lateinit var _activityDetailBinding: ActivityDetailBinding
    private val binding get() = _activityDetailBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _activityDetailBinding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val movies = if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU){
            intent.getParcelableExtra("EXTRA_MOVIE", Movie::class.java )
        } else {
            @Suppress("DEPRECATION")
            intent.getParcelableExtra<Movie>("EXTRA_MOVIE")

        }

        movies?.let {
            binding.tvDetailTitle.text = it.title
            binding.tvYearContent.text = it.releaseDate
            binding.tvDescContent.text = it.overview
            Glide.with(this).load("https://image.tmdb.org/t/p/w500/" + it.posterPath)
                .into(binding.imgDetail)
        }

        binding.btnBack.setOnClickListener{
            onBackPressedDispatcher.onBackPressed()
        }

    }
}