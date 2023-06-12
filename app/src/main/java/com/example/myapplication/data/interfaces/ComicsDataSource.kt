package com.example.myapplication.data.interfaces

import com.example.myapplication.domain.models.ComicResponseModel

interface ComicsDataSource {
    suspend fun getAll(): List<ComicResponseModel>
    suspend fun insert(comics: List<ComicResponseModel>)
    suspend fun getOne(id: Int): ComicResponseModel?
}