package com.example.myapplication.data.repositories

import com.example.myapplication.data.interfaces.ComicsDataSource
import com.example.myapplication.domain.interfaces.ComicsRepository
import com.example.myapplication.domain.models.ComicResponseModel

class ComicsRepositoryImpl constructor(
    private var comicsLocalDataSource: ComicsDataSource,
    private var comicsRemoteDataSource: ComicsDataSource,
    ) : ComicsRepository {

    override suspend fun getComics(): List<ComicResponseModel> {
        val localItems = comicsLocalDataSource.getAll()
        return localItems.ifEmpty {
            val remoteItems = comicsRemoteDataSource.getAll().filter { !it.description.isNullOrEmpty() }
            comicsLocalDataSource.insert(remoteItems)
            return remoteItems
        }
    }

    override suspend fun getComic(id: Int): ComicResponseModel? {
        return comicsLocalDataSource.getOne(id)
    }
}