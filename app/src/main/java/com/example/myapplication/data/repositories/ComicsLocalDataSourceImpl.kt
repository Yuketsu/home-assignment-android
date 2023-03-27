package com.example.myapplication.data.repositories

import com.example.myapplication.data.db.ComicDao
import com.example.myapplication.data.entities.toComicResponseModel
import com.example.myapplication.data.interfaces.ComicsLocalDataSource
import com.example.myapplication.domain.models.ComicResponseModel
import kotlinx.coroutines.flow.toList

class ComicsLocalDataSourceImpl(private val comicDao: ComicDao): ComicsLocalDataSource {
    override suspend fun getAll(): List<ComicResponseModel> {
        return comicDao.getAll().toList().map { it.toComicResponseModel() }
    }

    override suspend fun getOne(id: Int): ComicResponseModel? {
        val entity = comicDao.getById(id)
        if (entity != null) {
            return entity.toComicResponseModel()
        }
        return null
    }
}