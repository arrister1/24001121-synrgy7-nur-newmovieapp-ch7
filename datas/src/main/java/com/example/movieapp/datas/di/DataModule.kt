package com.example.movieapp.datas.di

import android.content.Context
import com.chuckerteam.chucker.api.ChuckerInterceptor
import com.example.movieapp.datas.local.DataStore
import com.example.movieapp.datas.remote.RemoteDataSource
import com.example.movieapp.datas.remote.network.ApiService
import com.example.movieapp.datas.remote.repository.MovieRepository
import com.example.movieapp.domain.repository.IMovieRepository
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object DataModule {
    val networkModule = module {
        single {
            val context: Context = androidContext()

            OkHttpClient.Builder()
                .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
                .addInterceptor(ChuckerInterceptor.Builder(context).build())
                .connectTimeout(120, TimeUnit.SECONDS)
                .readTimeout(120, TimeUnit.SECONDS)
                .build()
        }
        single {
            val retrofit = Retrofit.Builder()
                .baseUrl("https://api.themoviedb.org/3/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(get())
                .build()
            retrofit.create(ApiService::class.java)
        }
    }

    val repositoryModule = module {
        single { RemoteDataSource(get()) }
        single<IMovieRepository> { MovieRepository(get()) }
    }

    val dataModule = module {
        single { DataStore(androidContext()) }}}


