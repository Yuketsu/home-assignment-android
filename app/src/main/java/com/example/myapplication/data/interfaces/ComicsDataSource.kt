package com.example.myapplication.data.interfaces

import com.example.myapplication.domain.models.ComicResponseModel
import kotlinx.coroutines.flow.Flow

interface ComicsDataSource {
    suspend fun getAll(): Flow<List<ComicResponseModel>>
    suspend fun insert(comics: List<ComicResponseModel>)
    suspend fun getOne(id: Int): ComicResponseModel?
}