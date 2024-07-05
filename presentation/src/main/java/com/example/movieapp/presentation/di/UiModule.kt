package com.example.movieapp.presentation.di

import com.example.movieapp.presentation.viewmodel.MovieViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

object UiModule {
    val viewModelModule = module {
        viewModel { MovieViewModel(get()) }

    }
}