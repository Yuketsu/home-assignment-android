package com.example.myapplication.domain.interfaces.usecases

import com.example.myapplication.domain.models.ComicResponseModel

interface GetAllComicsUseCase {
    suspend fun execute(): List<ComicResponseModel>
}