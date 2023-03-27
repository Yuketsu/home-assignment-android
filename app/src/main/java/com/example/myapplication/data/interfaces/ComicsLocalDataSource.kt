package com.example.myapplication.data.interfaces

import com.example.myapplication.domain.models.ComicResponseModel

interface ComicsLocalDataSource {
    suspend fun getAll(): List<ComicResponseModel>
    suspend fun getOne(id: String): ComicResponseModel?
}