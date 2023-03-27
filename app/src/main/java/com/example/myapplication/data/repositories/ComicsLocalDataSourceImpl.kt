package com.example.myapplication.data.repositories

import com.example.myapplication.data.interfaces.ComicsLocalDataSource
import com.example.myapplication.domain.models.ComicResponseModel

class ComicsLocalDataSourceImpl: ComicsLocalDataSource {
    override suspend fun getAll(): List<ComicResponseModel> {
        return emptyList()
    }

    override suspend fun getOne(id: String): ComicResponseModel? {
        return null
    }
}