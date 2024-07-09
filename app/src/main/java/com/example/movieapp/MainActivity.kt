package com.example.movieapp

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.movieapp.R
import com.example.movieapp.common.Resource
import com.example.movieapp.databinding.ActivityMainBinding
import com.example.movieapp.datas.local.DataStore
import com.example.movieapp.domain.model.Movie
import com.example.movieapp.presentation.ui.user.UserActivity
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel




class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val intent = Intent (this,
            Class.forName("UserActivity"))
        startActivity(intent)
    }
}
//class MainActivity : AppCompatActivity(), com.example.movieapp.presentation.adapter.MovieAdapter.OnItemClickListener {
//    private lateinit var _activityMainBinding: ActivityMainBinding
//    private val binding get() = _activityMainBinding
//
//    private val dataStore: DataStore by inject()
//    private val viewModels: com.example.movieapp.presentation.viewmodel.MovieViewModel by viewModel()
//
//    private lateinit var adapter: com.example.movieapp.presentation.adapter.MovieAdapter
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        _activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
//        setContentView(binding.root)
//
//
//        binding.btnProfile.setOnClickListener {
//            startActivity(Intent(this@MainActivity, com.example.movieapp.presentation.ui.user.ProfileActivity::class.java ))
//            finish()
//        }
//
//        //dataStore = DataStore(this)
//
//        setupUsername()
//        setupRecyclerView()
//        setupObservers()
//
//        // Panggil ViewModel untuk mengambil data film
//        //viewModels.getMoviePopular()
//    }
//
//    private fun setupUI() {
//        setContentView(R.layout.activity_main)
//
//        val binding = ActivityMainBinding.inflate(layoutInflater)
//        setContentView(binding.root)
//    }
//
//    private fun setupUsername() {
//        lifecycleScope.launch {
//            dataStore.username.collect{username ->
//                binding.tvUname.text = username ?: "Guest"
//            }
//        }
//    }
//
//    private fun setupRecyclerView() {
//        adapter = com.example.movieapp.presentation.adapter.MovieAdapter(this)
//        binding.homeRv.layoutManager = LinearLayoutManager(this)
//        binding.homeRv.setHasFixedSize(true)
//        binding.homeRv.adapter = adapter
//    }
//
//    private fun setupObservers(){
//        viewModels.movie.observe(this, {resources ->
//            when(resources){
//                is Resource.Loading -> {
//                   //binding.progressBar.visibility = View.VISIBLE
//                }
//                is Resource.Success -> {
//                    //binding.progressBar.visibility = View.GONE
//                    resources.data?.let { movieList ->
//                        adapter.setMovieList(movieList)
//                    }
//                }
//                is Resource.Error -> {
//                  //  binding.progressBar.visibility = View.GONE
//                    Log.e("MainActivity", resources.message ?: "Unknown error")
//                }
//            }
//        })
//    }
//
//
//    override fun onItemClick(movies: Movie) {
//        val intent = Intent(this, com.example.movieapp.presentation.ui.main.DetailActivity::class.java)
//        intent.putExtra("EXTRA_MOVIE", movies)
//        startActivity(intent)
//        //Toast.makeText(this, "Clicked on: ${movies.title}", Toast.LENGTH_SHORT).show()
//    }
//}
