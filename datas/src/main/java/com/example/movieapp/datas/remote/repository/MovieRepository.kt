package com.example.movieapp.datas.remote.repository

import com.example.movieapp.common.Resource
import com.example.movieapp.datas.remote.RemoteDataSource
import com.example.movieapp.datas.remote.network.ApiResponse
import com.example.movieapp.datas.remote.response.toMovie
import com.example.movieapp.domain.model.Movie
import com.example.movieapp.domain.repository.IMovieRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map


class MovieRepository( private val remoteDataSource: RemoteDataSource): IMovieRepository {


    override fun getAllMovie(): Flow<Resource<List<Movie>>> {
        return flow {
            emit(Resource.Loading())
            remoteDataSource.getAllMovie().map { apiResponse ->
                when(apiResponse){
                    is ApiResponse.Success -> {
                        val movieList = apiResponse.data.map { it.toMovie() }
                        Resource.Success(movieList)
                    }
                    is ApiResponse.Empty ->{
                        Resource.Success(emptyList())
                    }
                    is ApiResponse.Error -> {
                        Resource.Error(apiResponse.errorMessage)
                    }
                }
            }.collect{resource ->
                emit(resource)
            }
            }.catch { e ->
                emit(Resource.Error(e.message ?: "An unknown error"))
        }.flowOn(Dispatchers.IO)
        }
    }