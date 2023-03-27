package com.example.myapplication.data.interfaces

import com.example.myapplication.domain.models.ComicResponseModel

interface ComicsRemoteDataSource {
    suspend fun getAll(): List<ComicResponseModel>
    suspend fun getOne(id: Int): ComicResponseModel?
}