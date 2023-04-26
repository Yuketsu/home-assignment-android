package com.example.myapplication.data.repositories

import com.example.myapplication.data.api.ComicsAPI
import com.example.myapplication.data.interfaces.ComicsDataSource
import com.example.myapplication.data.mappers.ComicApiResponseMapper
import com.example.myapplication.domain.models.ComicResponseModel
import javax.inject.Inject

class ComicsRemoteDataSourceImpl @Inject constructor(
    private val service: ComicsAPI,
    private val mapper: ComicApiResponseMapper
    ): ComicsDataSource {
    override suspend fun getAll(): List<ComicResponseModel> {
        val response = service.getComics()
        return mapper.toComicsList(response.body()!!)
    }

    override suspend fun getOne(id: Int): ComicResponseModel? {
        return null
    }
}