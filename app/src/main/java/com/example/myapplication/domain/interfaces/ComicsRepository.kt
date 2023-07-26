package com.example.myapplication.domain.interfaces

import com.example.myapplication.domain.models.ComicResponseModel
import io.reactivex.rxjava3.core.Observable

interface ComicsRepository {
    fun getComics(): Observable<List<ComicResponseModel>>
    fun getComic(id: Int): ComicResponseModel?
}