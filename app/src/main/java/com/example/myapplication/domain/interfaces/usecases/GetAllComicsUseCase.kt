package com.example.myapplication.domain.interfaces.usecases

import com.example.myapplication.domain.models.ComicResponseModel
import kotlinx.coroutines.flow.Flow

interface GetAllComicsUseCase {
    suspend fun execute(): Flow<List<ComicResponseModel>>
}