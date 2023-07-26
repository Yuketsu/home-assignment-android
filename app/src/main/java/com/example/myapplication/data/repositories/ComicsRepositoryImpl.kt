package com.example.myapplication.data.repositories

import com.example.myapplication.data.interfaces.ComicsDataSource
import com.example.myapplication.domain.interfaces.ComicsRepository
import com.example.myapplication.domain.models.ComicResponseModel
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.Schedulers
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ComicsRepositoryImpl constructor(
    private var comicsLocalDataSource: ComicsDataSource,
    private var comicsRemoteDataSource: ComicsDataSource,
    ) : ComicsRepository {

    override fun getComics(): Observable<List<ComicResponseModel>> {
        return comicsLocalDataSource.getAll()
            .subscribeOn(Schedulers.newThread())
            .observeOn(Schedulers.io())
            .flatMap { localItems ->
                if (localItems.isEmpty()) {
                    return@flatMap fetchRemoteComics()
                } else {
                    Observable.just(localItems)
                }
            }
    }

    private fun fetchRemoteComics(): Observable<List<ComicResponseModel>> {
        return comicsRemoteDataSource.getAll()
            .subscribeOn(Schedulers.io())
            .map { remoteItems ->
                val items = remoteItems.filter { !it.description.isNullOrEmpty() }
                comicsLocalDataSource.insert(items)
                return@map items
            }
    }

    override fun getComic(id: Int): ComicResponseModel? {
        return comicsLocalDataSource.getOne(id)
    }
}