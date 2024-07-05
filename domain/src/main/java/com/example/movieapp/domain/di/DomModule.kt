package com.example.movieapp.domain.di

import com.example.movieapp.domain.usecase.MovieInteractor
import com.example.movieapp.domain.usecase.MovieUseCase
import org.koin.dsl.module

object DomModule {
    val useCaseModule = module {
        factory<MovieUseCase> { MovieInteractor(get()) }
    }}





