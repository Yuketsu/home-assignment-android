package com.example.myapplication.domain.interfaces

import com.example.myapplication.domain.models.ComicResponseModel
import kotlinx.coroutines.flow.Flow

interface ComicsRepository {
    suspend fun getComics(): Flow<List<ComicResponseModel>>
    suspend fun getComic(id: Int): ComicResponseModel?
}