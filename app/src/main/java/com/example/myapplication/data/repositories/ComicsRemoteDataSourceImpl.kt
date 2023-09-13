package com.example.myapplication.data.repositories

import com.example.myapplication.data.api.ComicsAPI
import com.example.myapplication.data.interfaces.ComicsDataSource
import com.example.myapplication.data.mappers.ComicApiResponseMapper
import com.example.myapplication.domain.models.ComicResponseModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class ComicsRemoteDataSourceImpl @Inject constructor(
    private val service: ComicsAPI,
    private val mapper: ComicApiResponseMapper
    ): ComicsDataSource {
    override suspend fun getAll(): Flow<List<ComicResponseModel>> {
        val response = service.getComics()
        val list = mapper.toComicsList(response.body()!!)
        return flow {
            emit(list)
        }
    }

    override suspend fun insert(comics: List<ComicResponseModel>) {
        TODO("Not yet implemented")
    }

    override suspend fun getOne(id: Int): ComicResponseModel? {
        return null
    }
}