package com.example.myapplication.data.repositories

import com.example.myapplication.data.interfaces.ComicsDataSource
import com.example.myapplication.domain.interfaces.ComicsRepository
import com.example.myapplication.domain.models.ComicResponseModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.toList

class ComicsRepositoryImpl constructor(
    private var comicsLocalDataSource: ComicsDataSource,
    private var comicsRemoteDataSource: ComicsDataSource,
    ) : ComicsRepository {

    override suspend fun getComics(): Flow<List<ComicResponseModel>> {
        return comicsLocalDataSource.getAll()
            .map { localItems ->
                localItems.ifEmpty {
                    fetchRemoteComics()
                } as List<ComicResponseModel>
            }
    }

    private suspend fun fetchRemoteComics(): Flow<List<ComicResponseModel>> {
        return comicsRemoteDataSource.getAll()
            .map { remoteItems ->
                val items = remoteItems.filter { !it.description.isNullOrEmpty() }
                comicsLocalDataSource.insert(items)
                return@map items
            }
    }

    override suspend fun getComic(id: Int): ComicResponseModel? {
        return comicsLocalDataSource.getOne(id)
    }
}