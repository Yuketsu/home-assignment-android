package com.example.myapplication.data.interfaces

import com.example.myapplication.domain.models.ComicResponseModel
import io.reactivex.rxjava3.core.Observable

interface ComicsDataSource {
    fun getAll(): Observable<List<ComicResponseModel>>
    fun insert(comics: List<ComicResponseModel>)
    fun getOne(id: Int): ComicResponseModel?
}