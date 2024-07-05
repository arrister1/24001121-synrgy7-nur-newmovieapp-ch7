package com.example.movieapp.datas.remote

import android.util.Log
import com.example.movieapp.datas.remote.network.ApiResponse
import com.example.movieapp.datas.remote.network.ApiService
import com.example.movieapp.datas.remote.response.Movies
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class RemoteDataSource(private val apiService: ApiService) {

     suspend fun getAllMovie(): Flow<ApiResponse<List<Movies>>> {
       return flow {
           try {
               val response = apiService.getMoviePopular()
               val dataArray = response.movies
               if(dataArray.isNotEmpty()){
                   emit(ApiResponse.Success(response.movies))
               } else {
                   emit(ApiResponse.Empty)
               }
           } catch (e: Exception) {
               emit(ApiResponse.Error(e.toString()))
               Log.e("RemoteDataSource", e.toString())
           }
       } .flowOn(Dispatchers.IO)
     }}