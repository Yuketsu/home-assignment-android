package com.example.myapplication.domain.usecases.comics

import com.example.myapplication.domain.interfaces.ComicsRepository
import com.example.myapplication.domain.interfaces.usecases.GetComicUseCase
import com.example.myapplication.domain.models.ComicResponseModel

class GetOneComic constructor(private val comicsRepository: ComicsRepository) : GetComicUseCase {
    override suspend fun execute(id: String): ComicResponseModel? {
        return comicsRepository.getComic(id)
    }
}