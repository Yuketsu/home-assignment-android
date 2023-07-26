package com.example.myapplication.domain.interfaces.usecases

import com.example.myapplication.domain.models.ComicResponseModel
import io.reactivex.rxjava3.core.Observable

interface GetAllComicsUseCase {
    fun execute(): Observable<List<ComicResponseModel>>
}