package com.example.movieapp.presentation.viewmodel

import com.example.movieapp.domain.model.Movie

object DataDummy {
    fun generateDummyMovies(): List<Movie> {
        return listOf(
            Movie("1", "Movie 1", "2023-07-07", "/path1.jpg", "Overview 1"),
            Movie("2", "Movie 2", "2023-07-07", "/path2.jpg", "Overview 2")
        )
    }
}