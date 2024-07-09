package com.example.movieapp.presentation.ui.main

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.movieapp.common.Resource
import com.example.movieapp.datas.local.DataStore
import com.example.movieapp.domain.model.Movie
import com.example.movieapp.presentation.databinding.ActivityMainBinding
import com.example.movieapp.presentation.ui.user.ProfileActivity
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel


class MainActivity : AppCompatActivity(), com.example.movieapp.presentation.adapter.MovieAdapter.OnItemClickListener {
    private lateinit var _activityMainBinding: ActivityMainBinding
    private val binding get() = _activityMainBinding

    private val dataStore: DataStore by inject()
    private val viewModels: com.example.movieapp.presentation.viewmodel.MovieViewModel by viewModel()

    private lateinit var adapter: com.example.movieapp.presentation.adapter.MovieAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.btnProfile.setOnClickListener {
            startActivity(Intent(this@MainActivity, ProfileActivity::class.java ))
            finish()
        }

        //dataStore = DataStore(this)

        setupUsername()
        setupRecyclerView()
        setupObservers()

        // Panggil ViewModel untuk mengambil data film
        viewModels.getAllMovie()
    }



    private fun setupUsername() {
        lifecycleScope.launch {
            dataStore.username.collect{username ->
                binding.tvUname.text = username ?: "Guest"
            }
        }
    }

    private fun setupRecyclerView() {
        adapter = com.example.movieapp.presentation.adapter.MovieAdapter(this)
        binding.homeRv.layoutManager = LinearLayoutManager(this)
        binding.homeRv.setHasFixedSize(true)
        binding.homeRv.adapter = adapter
    }

    private fun setupObservers(){
        viewModels.movie.observe(this) { resources ->
            when (resources) {
                is Resource.Loading -> {
                    //binding.progressBar.visibility = View.VISIBLE
                }

                is Resource.Success -> {
                    //binding.progressBar.visibility = View.GONE
                    resources.data?.let { movieList ->
                        adapter.setMovieList(movieList)
                    }
                }

                is Resource.Error -> {
                    //  binding.progressBar.visibility = View.GONE
                    Log.e("MainActivity", resources.message ?: "Unknown error")
                }
            }
        }
    }


    override fun onItemClick(movies: Movie) {
        val intent = Intent(this, DetailActivity::class.java)
        intent.putExtra("EXTRA_MOVIE", movies)
        startActivity(intent)
        //Toast.makeText(this, "Clicked on: ${movies.title}", Toast.LENGTH_SHORT).show()
    }
}
