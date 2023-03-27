package com.example.myapplication.domain.interfaces.usecases

import com.example.myapplication.domain.models.ComicResponseModel

interface GetComicUseCase {
    suspend fun execute(id: String): ComicResponseModel?
}
