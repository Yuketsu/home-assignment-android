package com.example.myapplication.domain.usecases.comics

import com.example.myapplication.domain.interfaces.ComicsRepository
import com.example.myapplication.domain.interfaces.usecases.GetAllComicsUseCase
import com.example.myapplication.domain.models.ComicResponseModel
import io.reactivex.rxjava3.core.Observable

class GetComics constructor(private val comicsRepository: ComicsRepository) : GetAllComicsUseCase {
    override fun execute(): Observable<List<ComicResponseModel>> {
        return comicsRepository.getComics()
    }
}