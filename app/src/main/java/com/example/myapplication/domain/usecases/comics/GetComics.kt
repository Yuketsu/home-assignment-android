package com.example.myapplication.domain.usecases.comics

import com.example.myapplication.domain.interfaces.ComicsRepository
import com.example.myapplication.domain.interfaces.usecases.GetAllComicsUseCase
import com.example.myapplication.domain.models.ComicResponseModel

class GetComics constructor(private val comicsRepository: ComicsRepository) : GetAllComicsUseCase {
    override suspend fun execute(): List<ComicResponseModel> {
        return comicsRepository.getComics()
    }
}