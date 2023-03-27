package com.example.myapplication.domain.interfaces

import com.example.myapplication.domain.models.ComicResponseModel

interface ComicsRepository {
    suspend fun getComics(): List<ComicResponseModel>
    suspend fun getComic(id: Int): ComicResponseModel?
}