package com.example.myapplication.data.repositories

import com.example.myapplication.data.interfaces.ComicsRemoteDataSource
import com.example.myapplication.data.interfaces.ComicsLocalDataSource
import com.example.myapplication.domain.interfaces.ComicsRepository
import com.example.myapplication.domain.models.ComicResponseModel

class ComicsRepositoryImpl constructor(
    private var comicsLocalDataSource: ComicsLocalDataSource,
    private var comicsRemoteDataSource: ComicsRemoteDataSource,
    ) : ComicsRepository {

    override suspend fun getComics(): List<ComicResponseModel> {
        return comicsLocalDataSource.getAll()
    }

    override suspend fun getComic(id: String): ComicResponseModel? {
        return comicsLocalDataSource.getOne(id)
    }
}