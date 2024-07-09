package com.example.movieapp.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movieapp.common.Resource
import com.example.movieapp.domain.model.Movie
import com.example.movieapp.domain.usecase.MovieUseCase
import kotlinx.coroutines.launch


class MovieViewModel(private val movieUseCase: MovieUseCase) : ViewModel() {

    private val _movie = MutableLiveData<Resource<List<Movie>>>()
    val movie: LiveData<Resource<List<Movie>>> = _movie

    fun getAllMovie() {
        viewModelScope.launch {
            movieUseCase.getAllMovie().collect {
                _movie.postValue(it)
            }
        }
    }
}
