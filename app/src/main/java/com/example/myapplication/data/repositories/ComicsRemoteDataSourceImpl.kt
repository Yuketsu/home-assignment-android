package com.example.myapplication.data.repositories

import com.example.myapplication.data.api.ComicsAPI
import com.example.myapplication.data.interfaces.ComicsDataSource
import com.example.myapplication.data.mappers.ComicApiResponseMapper
import com.example.myapplication.domain.models.ComicResponseModel
import io.reactivex.rxjava3.schedulers.Schedulers
import io.reactivex.rxjava3.core.Observable
import javax.inject.Inject

class ComicsRemoteDataSourceImpl @Inject constructor(
    private val service: ComicsAPI,
    private val mapper: ComicApiResponseMapper
    ): ComicsDataSource {
    override fun getAll(): Observable<List<ComicResponseModel>> {
        return service.getComics()
            .subscribeOn(Schedulers.newThread())
            .observeOn(Schedulers.io())
            .map { response ->
                mapper.toComicsList(response.body()!!)
            }
    }
    override fun insert(comics: List<ComicResponseModel>) {
        TODO("Not yet implemented")
    }

    override fun getOne(id: Int): ComicResponseModel? {
        return null
    }
}