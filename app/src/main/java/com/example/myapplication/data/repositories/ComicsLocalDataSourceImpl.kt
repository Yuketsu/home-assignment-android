package com.example.myapplication.data.repositories

import com.example.myapplication.data.db.ComicDao
import com.example.myapplication.data.entities.toComicResponseModel
import com.example.myapplication.data.entities.toComicRoomEntity
import com.example.myapplication.data.interfaces.ComicsDataSource
import com.example.myapplication.domain.models.ComicResponseModel
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

class ComicsLocalDataSourceImpl @Inject constructor(private val comicDao: ComicDao): ComicsDataSource {
    override fun getAll(): Observable<List<ComicResponseModel>> {
        return Observable.fromSingle(comicDao.getAll())
            .subscribeOn(Schedulers.newThread())
            .observeOn(Schedulers.io())
            .map { data ->
                data.toList().map { it.toComicResponseModel() }
            }
    }

    override fun insert(comics: List<ComicResponseModel>) {
        comicDao.insert(comics.map { it.toComicRoomEntity() })
    }

    override fun getOne(id: Int): ComicResponseModel? {
        val entity = comicDao.getById(id)
        if (entity != null) {
            return entity.toComicResponseModel()
        }
        return null
    }
}