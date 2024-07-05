package com.example.movieapp

import android.app.Application
import com.example.movieapp.datas.di.DataModule.dataModule
import com.example.movieapp.domain.di.DomModule.useCaseModule
import com.example.movieapp.datas.di.DataModule.networkModule
import com.example.movieapp.datas.di.DataModule.repositoryModule
import com.example.movieapp.presentation.di.UiModule.viewModelModule

import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App: Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin{
            androidContext(this@App)
            modules(
                listOf(
                    networkModule,
                    repositoryModule,
                    dataModule,
                    useCaseModule,
                    viewModelModule)
            )
        }
    }
}